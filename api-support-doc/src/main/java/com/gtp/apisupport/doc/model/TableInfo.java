package com.gtp.apisupport.doc.model;

import java.util.ArrayList;
import java.util.List;

import com.gtp.apisupport.doc.common.NameMapper;

/**
 * 表抽取
 */
public class TableInfo extends FileConfig {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 实体名称，默认由tableName转换而来
	 */
	private String entityName;
	
	/**
	 * 实体描述,生成时由用户指定(见文知意，精简)
	 * 其实可以可以取表的描述字段comment
	 */
	private String entityNote;
	
	/**
	 * 实体编码前缀,生成的API的方法编码
	 * 由实体的编码前缀+方法编码
	 */
	private String entityCodePrefix;

	/**
	 * 字段
	 */
	private List<FieldInfo> fields;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
		this.entityName= NameMapper.getJavaFieldName(tableName);
		this.entityName= NameMapper.firstLetterUpperCase(entityName);
	}

	public List<FieldInfo> getFields() {
		return fields;
	}

	public void setFields(List<FieldInfo> fields) {
		this.fields = fields;
	}

	public void addField(FieldInfo field) {
		if (this.fields == null) {
			this.fields = new ArrayList<>();
		}
		fields.add(field);
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getDaoFileName() {
		return this.entityName+"DAO";
	}

	public String getPoFileName() {
		return this.entityName+"PO";
	}

	public String getXmlFileName() {
		return this.entityName+"DAO";
	}

	public String getApiFileName() {
		return this.entityName+"Api";
	}

	public String getEntityNote() {
		return entityNote;
	}

	public void setEntityNote(String entityNote) {
		this.entityNote = entityNote;
	}

	public String getEntityCodePrefix() {
		return entityCodePrefix;
	}

	public void setEntityCodePrefix(String entityCodePrefix) {
		this.entityCodePrefix = entityCodePrefix;
	}
}
