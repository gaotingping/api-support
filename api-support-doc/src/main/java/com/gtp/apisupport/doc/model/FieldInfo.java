package com.gtp.apisupport.doc.model;

import com.gtp.apisupport.doc.common.NameMapper;

/**
 * 表-字段抽取
 */
public class FieldInfo {

	/**
	 * 字段名称
	 */
	private String name;

	/**
	 * 字段类型
	 */
	private String jdbcType;

	/**
	 * 对应的java类型
	 */
	private String javaType;

	/**
	 * 字段注释
	 */
	private String comment;

	/**
	 * java字段名称
	 */
	private String javaName = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if(this.name !=null){
			this.javaName= NameMapper.getJavaFieldName(this.name);
		}
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		if (jdbcType != null) {
			//int(11) -> 这里去掉"(xxx)"
			int index = jdbcType.indexOf("(");
			if (index > 0) {
				this.jdbcType = jdbcType.substring(0, index);
			} else {
				this.jdbcType = jdbcType;
			}
		} else {
			this.jdbcType = jdbcType;
		}
		if (this.jdbcType != null) {
			this.javaType=NameMapper.getJavaType(this.jdbcType);
		}
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJavaField() {
		return javaName;
	}
}
