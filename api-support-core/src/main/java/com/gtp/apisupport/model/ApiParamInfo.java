package com.gtp.apisupport.model;

import java.io.Serializable;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.annotation.ApiParam;

@ApiDescribe("参数")
public class ApiParamInfo implements Serializable{

	private static final long serialVersionUID = -5324244498094129649L;

	@ApiDescribe("是否集合")
	private boolean isList;
	
	@ApiDescribe("类型")
	private Class<?> type;
	
	@ApiDescribe("注解")
	private ApiParam apiParam;

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
