package org.beangle.ems.config.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.beangle.ems.config.model.CProperty;
import org.beangle.ems.config.model.CPropertys;
import org.beangle.ems.config.model.ClassConfig;
import org.beangle.ems.config.model.ClassConfig.Mode;
import org.beangle.ems.config.model.ClassPropertyConfig;
import org.beangle.model.persist.impl.BaseServiceImpl;
import org.beangle.model.pojo.LongIdObject;
import org.beangle.model.query.builder.OqlBuilder;

public class ClassConfigServiceImpl extends BaseServiceImpl implements ClassConfigService {

	public List<ClassPropertyConfig> findConfig(Class<?> clazz) {
		return findConfig(clazz, null);
	}

	public List<ClassPropertyConfig> findConfig(Class<?> clazz, Mode mode) {
		return findConfig(clazz, mode, null);
	}

	public List<ClassPropertyConfig> findConfig(Class<?> clazz, Mode mode, String code) {
		ClassConfig cc = searchCC(clazz, code);
		List<ClassPropertyConfig> list = getClassPropertyConfigList(clazz, cc.getConfig());
		if(mode != null){
			for(Iterator<ClassPropertyConfig> itor = list.iterator(); itor.hasNext();){
				ClassPropertyConfig cpc = itor.next();
				if(mode == Mode.EDIT){
					if(!cpc.getEditable()){
						itor.remove();
					}
				}else if(mode == Mode.LIST){
					if(!cpc.getListable()){
						itor.remove();
					}
				}
			}
		}
		Collections.sort(list);
		return list;
	}
	
	public ClassConfig searchCC(Class<?> clazz, String code){
		OqlBuilder<ClassConfig> query = OqlBuilder.from(ClassConfig.class, "o");
		query.where("o.className = :className", clazz.getName());
		if (StringUtils.isEmpty(code)) {
			query.where("o.code is null");
		} else {
			query.where("o.code = :code", code);
		}
		// List<ClassConfig> cclist = new ArrayList<ClassConfig>();
		List<ClassConfig> cclist = entityDao.search(query);
		ClassConfig cc;
		if (cclist.isEmpty()) {
			cc = new ClassConfig();
			cc.setClassName(clazz.getName());
			cc.setCode(code);
		} else {
			cc = cclist.get(0);
			if (cclist.size() > 1) {// 删除重复记录
				cclist.remove(0);
				entityDao.remove(cclist);
			}
		}
		return cc;
	}

	private List<ClassPropertyConfig> getClassPropertyConfigList(Class<?> clazz, String config) {
		List<ClassPropertyConfig> cpclist = new ArrayList<ClassPropertyConfig>();
		if (StringUtils.isNotEmpty(config)) {
			try {
				JSONArray jsons = JSONArray.fromObject(config);
				for(Object o : jsons){
					JSONObject json = (JSONObject) o;
					ClassPropertyConfig cpc = (ClassPropertyConfig) JSONObject.toBean(json, ClassPropertyConfig.class);
					cpclist.add(cpc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<ClassPropertyConfig> clist = getClassPropertyConfigList(clazz);
		//更新notnull属性
		for(ClassPropertyConfig cpc : clist){
			for(ClassPropertyConfig cpc0 : cpclist){
				if(cpc.getPropertyName().equals(cpc0.getPropertyName())){
					cpc0.setNotnull(cpc.getNotnull());
				}
			}
		}
		Set<ClassPropertyConfig> set = new HashSet<ClassPropertyConfig>();
		set.addAll(cpclist);
		set.addAll(clist);
		return new ArrayList<ClassPropertyConfig>(set);
	}

	private List<ClassPropertyConfig> getClassPropertyConfigList(Class<?> clazz) {
		return getClassPropertyConfigList(clazz, new ArrayList<ClassPropertyConfig>(), new HashMap<Class<?>, String>(), "");
	}

	private List<ClassPropertyConfig> getClassPropertyConfigList(Class<?> clazz, List<ClassPropertyConfig> list, Map<Class<?>, String> map, String propertyNamePerfix) {
		if (map.get(clazz) != null) {
			return list;
		}
		map.put(clazz, "1");
		CPropertys cps = clazz.getAnnotation(CPropertys.class);
		if (cps != null) {
			String className = cps.name();
			String propertiestr = cps.properties();
			int px = 1;
			if (StringUtils.isNotEmpty(propertiestr)) {
				String[] properties = propertiestr.split(";");
				for (String property : properties) {
					if (StringUtils.isNotEmpty(property)) {
						String[] ss = property.split(",");
						if (ss.length >= 2) {
							ClassPropertyConfig cpc = new ClassPropertyConfig();
							cpc.setClassName(className);
							cpc.setPropertyName(propertyNamePerfix + ss[0]);
							cpc.setLabel(ss[1]);
							if(cpc.getPx() == 0){
								cpc.setPx(px++);
							}
							if (ss.length > 2) {
								if ("1".equals(ss[2])) {
									cpc.setEditable(true);
								}
								if (ss.length > 3) {
									cpc.setListable(true);
									if (ss.length > 4) {
										cpc.setPx(Integer.parseInt(ss[4]));
									}
								}
							}
							list.add(cpc);
						}
					}
				}
			}
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				CProperty cp = field.getAnnotation(CProperty.class);
				Class<?> propertyClass = field.getType();
				ClassPropertyConfig cpc = new ClassPropertyConfig();
				cpc.setPropertyName(propertyNamePerfix + field.getName());
				if (cp != null) {
					if(!cp.ingore()){
						cpc.setClassName(className);
						cpc.setLabel(cp.label());
						cpc.setEditable(cp.editable());
						cpc.setListable(cp.listable());
						cpc.setRequired(cp.required() || cp.notnull());
						cpc.setNotnull(cp.notnull());
						if(cpc.getPx() == 0){
							cpc.setPx(px++);
						}
						list.add(cpc);
					}
				}
				if (LongIdObject.class.isAssignableFrom(propertyClass)) {
					getClassPropertyConfigList(propertyClass, list, map, cpc.getPropertyName() + ".");
				}
			}
		}
		if (clazz.getSuperclass() != null) {
			getClassPropertyConfigList(clazz.getSuperclass(), list, map, propertyNamePerfix);
		}
		return list;
	}

	public void saveConfig(Class<?> clazz, List<ClassPropertyConfig> list) {
		saveConfig(clazz, list, null);
	}

	public void saveConfig(Class<?> clazz, List<ClassPropertyConfig> list, String code) {
		try {
//			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//			objectOutputStream.writeObject(list);
//	        String configStr = byteArrayOutputStream.toString("ISO-8859-1");  
//	        configStr = URLEncoder.encode(configStr, "UTF-8");  
			ClassConfig cc = searchCC(clazz, code);
			String configStr = JSONArray.fromObject(list).toString();
			cc.setConfig(configStr);
			entityDao.saveOrUpdate(cc);
		} catch (Exception e) {
		}
	}

}
