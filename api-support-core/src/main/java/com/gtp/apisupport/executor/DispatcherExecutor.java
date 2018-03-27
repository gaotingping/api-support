package com.gtp.apisupport.executor;

import com.gtp.apisupport.model.ApiContext;

/**
 * 转发执行器
 * 
 * @author gaotingping@cyberzone.cn
 */
public interface DispatcherExecutor {
	
	/**
	 * 转发执行
	 */
	public void execute(ApiContext rc) throws Exception;
	
}
