package com.gtp.apisupport.model;

import java.io.Serializable;

/**
 * 分页公共封装
 * 
 * @author gaotingping@cyberzone.cn
 */
public class BasePge implements Serializable{

	private static final long serialVersionUID = -6068036644803551046L;

	private int pages;/*总页数*/
	
	private int total;/*总记录数*/
	
	private int currentPage;/*当前页*/
	
	private int pageSize;/*每页大小*/

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
