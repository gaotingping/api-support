package com.gtp.apisupport.doc.model;

/**
 * 表配置
 */
public class TableConfig{

	/**
	 * 实体描述,生成时由用户指定(见文知意，精简)
	 * 其实可以可以取表的描述字段comment
	 */
	private String entityNote;
	
	/**
	 * 实体编码前缀,生成的API的方法编码
	 * 由实体的编码前缀+方法编码组成
	 */
	private String entityCodePrefix;
	
	public TableConfig(String entityNote, String entityCodePrefix) {
		super();
		this.entityNote = entityNote;
		this.entityCodePrefix = entityCodePrefix;
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
