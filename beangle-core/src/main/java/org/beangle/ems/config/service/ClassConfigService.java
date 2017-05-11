package org.beangle.ems.config.service;

import java.util.List;

import org.beangle.ems.config.model.ClassConfig;
import org.beangle.ems.config.model.ClassPropertyConfig;

public interface ClassConfigService{

	/**
	 * 获得配置
	 * @param clazz
	 * @return
	 */
	public List<ClassPropertyConfig> findConfig(Class<?> clazz);

	/**
	 * 获得配置
	 * @param clazz
	 * @return
	 */
	public List<ClassPropertyConfig> findConfig(Class<?> clazz, ClassConfig.Mode mode);
	/**
	 * 获得配置
	 * @param clazz
	 * @param code
	 * @return
	 */
	public List<ClassPropertyConfig> findConfig(Class<?> clazz, ClassConfig.Mode mode, String code);
	
	/**
	 * 保存配置
	 * @param clazz
	 * @param config
	 */
	public void saveConfig(Class<?> clazz, List<ClassPropertyConfig> list);
	/**
	 * 保存配置
	 * @param clazz
	 * @param config
	 * @param code
	 */
	public void saveConfig(Class<?> clazz, List<ClassPropertyConfig> list, String code);
	
}
