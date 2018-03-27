package com.gtp.apisupport.model;

import java.io.Serializable;
import java.util.Date;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.enums.ApiErrorCode;

@ApiDescribe("总线响应数据协议")
public class MsbResult implements Serializable {

	private static final long serialVersionUID = 3525295025312897105L;

	@ApiDescribe("状态:true/false")
	private String opFlag = "false";

	@ApiDescribe("错误说明")
	private String errorMessage = "";

	@ApiDescribe("业务数据")
	private Object serviceResult = "";

	@ApiDescribe("当前时间")
	private String timestamp = new Date().getTime()+"";
	
	public MsbResult() {
		super();
	}

	public MsbResult(String opFlag, String errorMessage) {
		super();
		this.opFlag = opFlag;
		this.errorMessage = errorMessage;
	}

	public String getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getServiceResult() {
		return serviceResult;
	}

	public void setServiceResult(Object serviceResult) {
		this.serviceResult = serviceResult;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public static MsbResult failure(ApiErrorCode error) {
		
		MsbResult result = new MsbResult();
		
		result.setOpFlag("false");
		result.setErrorMessage(error.getError());
		
		return result;
	}
	
	public static MsbResult result(Object obj) {
		MsbResult result = new MsbResult();
		result.setOpFlag("true");
		result.setServiceResult(obj);
		return result;
	}
}