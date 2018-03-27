package com.gtp.apisupport.model;

import java.io.Serializable;

import com.gtp.apisupport.annotation.ApiDescribe;

/**
 * 分页公共封装
 * 
 * @author gaotingping@cyberzone.cn
 */
@ApiDescribe("分页封装")
public class ApiBasePage implements Serializable{

	private static final long serialVersionUID = -6068036644803551046L;

	@ApiDescribe("总页数")
	protected int pages;
	
	@ApiDescribe("总记录数")
	protected int total;
	
	@ApiDescribe("当前页")
	protected int currentPage;
	
	@ApiDescribe("每页大小")
	protected int pageSize;

	/**
	 * 总页数
	 */
	public int getPages() {
		
		if(total==0){
			pages=0;
		}else if(total%pageSize!=0){
			pages=total/pageSize+1;
		}else {
			pages=total/pageSize;
		}
		
		return pages;
	}
	
	/**
	 * 开始页
	 */
	public int getStartRow(){
		return (currentPage-1)*pageSize;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
