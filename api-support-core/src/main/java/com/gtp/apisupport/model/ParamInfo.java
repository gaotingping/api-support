package com.gtp.apisupport.model;

import java.io.Serializable;

import com.gtp.apisupport.annotation.ApiParam;

public class ParamInfo implements Serializable{

	private static final long serialVersionUID = -5324244498094129649L;

	private boolean isList;/*是否集合*/
	
	private Class<?> type;/*类型*/
	
	private ApiParam apiParam;/*注解*/

	public boolean getIsList() {
		return isList;
	}

	public void setIsList(boolean isList) {
		this.isList = isList;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public ApiParam getApiParam() {
		return apiParam;
	}

	public void setApiParam(ApiParam apiParam) {
		this.apiParam = apiParam;
	}
}
