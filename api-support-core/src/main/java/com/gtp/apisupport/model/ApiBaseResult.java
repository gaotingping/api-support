package com.gtp.apisupport.model;

import java.io.Serializable;
import java.util.Date;

import com.gtp.apisupport.annotation.ApiDescribe;

@ApiDescribe("业务响应数据协议")
public class ApiBaseResult implements Serializable{

	private static final long serialVersionUID = -4189959969699587766L;

	@ApiDescribe("状态:true/false")
	protected String flag = "true";

	@ApiDescribe("发生错误时异常说明")
	protected String error = "";
	
	@ApiDescribe("业务数据")
	protected Object result;
	
	@ApiDescribe("当前时间")
	protected Date stime=new Date();

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
