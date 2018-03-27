package com.gtp.apisupport.model;

import com.gtp.apisupport.annotation.ApiDescribe;

@ApiDescribe("分页响应数据协议")
public class ApiPageResult extends ApiBasePage{
	
	private static final long serialVersionUID = 7803733010010207363L;
	
	@ApiDescribe("业务数据")
	private Object data = "";

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ApiPageResult() {
		super();
	}

	public ApiPageResult(int total,int currentPage,int pageSize,Object data) {
		this.total=total;
		this.currentPage=currentPage;
		this.pageSize=pageSize;
		this.data = data;
	}
}
