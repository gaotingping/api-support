package com.gtp.apisupport.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置抽取
 * 
 * @author gaotingping@cyberzone.cn
 */
public class BusSupportConfig {

	/**
	 * 模块
	 */
	private String serviceModule;
	
	/**
	 * 需要扫描的包:即API接口所在的目录
	 */
	private List<String> packages = new ArrayList<String>();
	
	/**
	 * 监控状态
	 */
	private boolean monitor=false;
	
	/**
	 * 监控消息异步处理线程数
	 */
	private int monitorTaskThread=1;
	
	/**
	 * 监控消息缓存队列
	 */
	private int monitorMaxQueue=1024;

	public String getServiceModule() {
		return serviceModule;
	}

	public void setServiceModule(String serviceModule) {
		this.serviceModule = serviceModule;
	}

	public List<String> getPackages() {
		return packages;
	}

	public void setPackages(List<String> packages) {
		this.packages = packages;
	}

	public boolean isMonitor() {
		return monitor;
	}

	public void setMonitor(boolean monitor) {
		this.monitor = monitor;
	}

	public int getMonitorTaskThread() {
		return monitorTaskThread;
	}

	public void setMonitorTaskThread(int monitorTaskThread) {
		this.monitorTaskThread = monitorTaskThread;
	}

	public int getMonitorMaxQueue() {
		return monitorMaxQueue;
	}

	public void setMonitorMaxQueue(int monitorMaxQueue) {
		this.monitorMaxQueue = monitorMaxQueue;
	}
}
