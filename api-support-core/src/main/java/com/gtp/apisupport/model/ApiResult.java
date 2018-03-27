package com.gtp.apisupport.model;

import java.io.Serializable;
import java.util.Date;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.enums.ApiErrorCode;

//FIXME:应该节俭和优化下
@ApiDescribe("总线响应数据协议")
public class ApiResult implements Serializable {

	private static final long serialVersionUID = 3525295025312897105L;

	@ApiDescribe("状态:true/false")
	private String opFlag = "false";

	/**
	 * #FIXME:
	 * 返回错误码而不是描述信息，有如下好处
	 * 1.方便判断处理
	 * 2.同样的错误，方便不同场景，不同终端给用户友好的提示
	 */
	@ApiDescribe("错误说明")
	private String errorMessage = "";

	@ApiDescribe("业务数据")
	private Object serviceResult = "";

	@ApiDescribe("当前时间")
	private String timestamp = new Date().getTime()+"";
	
	public ApiResult() {
		super();
	}

	public ApiResult(String opFlag, String errorMessage) {
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

	public static ApiResult failure(ApiErrorCode error) {
		
		ApiResult result = new ApiResult();
		
		result.setOpFlag("false");
		result.setErrorMessage(error.getError());
		
		return result;
	}
	
	public static ApiResult result(Object obj) {
		ApiResult result = new ApiResult();
		result.setOpFlag("true");
		result.setServiceResult(obj);
		return result;
	}
}