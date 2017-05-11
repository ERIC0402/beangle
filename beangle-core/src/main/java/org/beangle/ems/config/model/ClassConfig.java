package org.beangle.ems.config.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import org.beangle.model.pojo.LongIdObject;

/**
 * 表单自定义
 * 
 * @author CHII
 * 
 */
@Entity(name = "org.beangle.ems.config.model.ClassConfig")
public class ClassConfig extends LongIdObject {

	public static final String ENTITY_NAME = "entity";

	public enum Mode {
		EDIT, LIST
	}

	/**
	 * 类名
	 */
	private String className;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 配置 格式 {propertyName:{title:"title"}}
	 */
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(columnDefinition = "CLOB")
	private String config;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

}
