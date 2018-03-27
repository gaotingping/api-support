package com.gtp.apisupport.enums;

/**
 * 框架错误码
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
public enum ApiErrorCode {
	
	SYSTEM_ERROR(10001,"系统错误"),
	PARAM_ERROR(10002,"参数错误"),
	SERVICE_MODULE_ERROR(10003,"服务模块错误"),
	SERVICE_CODE_EMPTY(10004,"方法编码错误"),
	SERVICE_CODE_NOT_FIND(10005,"方法不存在"),
	PARAM_NOT_JSON(10006,"参数格式错误"),
	REQUIRED_NOT_SET(10007,"必要参数未填");
	
	private int code;
	
	private String error;
	
	private ApiErrorCode(int code, String error) {
		this.code = code;
		this.error = error;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getError() {
		return error;
	}
}
