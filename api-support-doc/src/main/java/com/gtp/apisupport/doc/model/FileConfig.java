package com.gtp.apisupport.doc.model;

/**
 * 生成的文件-配置
 */
public class FileConfig {

	// 包
	private String daoPackageName;
	private String poPackageName;
	private String servicePackageName;
	private String apiPackageName;

	// 父类
	private String parentClassName;

	public String getDaoPackageName() {
		return daoPackageName;
	}

	public void setDaoPackageName(String daoPackageName) {
		this.daoPackageName = daoPackageName;
	}

	public String getServicePackageName() {
		return servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}

	public String getPoPackageName() {
		return poPackageName;
	}

	public void setPoPackageName(String poPackageName) {
		this.poPackageName = poPackageName;
	}

	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}

	public String getApiPackageName() {
		return apiPackageName;
	}

	public void setApiPackageName(String apiPackageName) {
		this.apiPackageName = apiPackageName;
	}
}
