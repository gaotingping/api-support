package com.gtp.apisupport.doc.builder;

import com.gtp.apisupport.doc.model.TableInfo;
import com.gtp.apisupport.doc.template.TemplateTool;

//负责创建service
public class ServiceBuilder extends BaseBuilder{

	public ServiceBuilder(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}
	
	public String getService() throws Exception {
		
		String packageName= tableInfo.getServicePackageName();
		
		StringBuilder imp=new StringBuilder();
		imp.append("import "+tableInfo.getPoPackageName()+"."+tableInfo.getPoFileName()+";\r\n");
		String importList=imp.toString();
				
		String entityName=tableInfo.getEntityName();
		
		return TemplateTool.getService(packageName,importList,entityName);
	}
	
	public String getServiceImpl() throws Exception {
		
		String packageName= tableInfo.getServicePackageName()+".impl";
		
		StringBuilder imp=new StringBuilder();
	
		//dao service po
		imp.append("import "+tableInfo.getPoPackageName()+"."+tableInfo.getPoFileName()+";\r\n");
		imp.append("import "+tableInfo.getDaoPackageName()+"."+tableInfo.getDaoFileName()+";\r\n");
		imp.append("import "+tableInfo.getServicePackageName()+"."+tableInfo.getEntityName()+"Service;\r\n");
		
		String importList=imp.toString();
				
		String entityName=tableInfo.getEntityName();
		
		return TemplateTool.getServiceImpl(packageName,importList,entityName);
	}
}
