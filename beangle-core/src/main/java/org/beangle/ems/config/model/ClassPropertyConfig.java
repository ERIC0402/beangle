package org.beangle.ems.config.model;

import org.apache.commons.lang.StringUtils;
import org.beangle.model.pojo.LongIdObject;

public class ClassPropertyConfig extends LongIdObject implements Comparable<ClassPropertyConfig>{
	
	public String className;

	/**
	 * 属性名
	 */
	public String propertyName;
	/**
	 * 标题
	 */
	public String label;
	/**
	 * 标题
	 */
	public String newLabel;
	/**
	 * 排序
	 */
	private Integer px = 0;
	/**
	 * 是否必填
	 */
	private Boolean required = false;
	/**
	 * 是否可编辑
	 */
	private Boolean editable = false;
	/**
	 * 是否在列表显示
	 */
	private Boolean listable = false;
	/**
	 * 是否非空
	 */
	private Boolean notnull = false;
	/**
	 * 宽度
	 */
	private String width;
	/**
	 * 默认值
	 */
	private String defaultValue;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getListable() {
		return listable;
	}

	public void setListable(Boolean listable) {
		this.listable = listable;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

	public boolean equals(Object o) {
		if (o instanceof ClassPropertyConfig) {
			ClassPropertyConfig config = (ClassPropertyConfig) o;
			return StringUtils.equals(this.propertyName, config.propertyName);
		}
		return false;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getNewLabel() {
		if(newLabel == null){
			newLabel = label;
		}
		return newLabel;
	}

	public void setNewLabel(String newLabel) {
		this.newLabel = newLabel;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public int compareTo(ClassPropertyConfig o) {
		
		return px.compareTo(o.px);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String getClassNameAndPx(){
		return className + px;
	}

	public Boolean getNotnull() {
		return notnull;
	}

	public void setNotnull(Boolean notnull) {
		this.notnull = notnull;
		if(notnull){
			setRequired(true);
		}
	}

}
