package com.gtp.apisupport.model;

import java.io.Serializable;
import java.util.Date;

import com.gtp.apisupport.annotation.ApiDescribe;

@ApiDescribe("业务响应数据协议")
public class BaseResult implements Serializable{

	private static final long serialVersionUID = -4189959969699587766L;

	@ApiDescribe("状态:true/false")
	private String flag = "true";

	@ApiDescribe("发生错误时异常说明")
	private String error = "";
	
	@ApiDescribe("业务数据")
	private Object result;
	
	private Date stime=new Date();

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
