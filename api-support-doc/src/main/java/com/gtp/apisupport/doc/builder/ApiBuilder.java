package com.gtp.apisupport.doc.builder;

import com.gtp.apisupport.doc.model.TableInfo;
import com.gtp.apisupport.doc.template.TemplateTool;

//负责创建api
public class ApiBuilder extends BaseBuilder{

	public ApiBuilder(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}
	
	public String getApi() throws Exception {
		
		String packageName= tableInfo.getApiPackageName();
		
		StringBuilder imp=new StringBuilder();
		imp.append("import "+tableInfo.getPoPackageName()+"."+tableInfo.getPoFileName()+";\r\n");
		String imports=imp.toString();
				
		String entityName=tableInfo.getEntityName();
		String entityNote=tableInfo.getEntityNote();
		String entityCodePrefix=tableInfo.getEntityCodePrefix();
		
		return TemplateTool.getApi(packageName, imports, entityName,entityNote,entityCodePrefix);
	}
	
	public String getApiImpl() throws Exception {
		
		String packageName= tableInfo.getApiPackageName()+".impl";
		
		//api po service
		StringBuilder imp=new StringBuilder();
		imp.append("import "+tableInfo.getPoPackageName()+"."+tableInfo.getPoFileName()+";\r\n");
		imp.append("import "+tableInfo.getApiPackageName()+"."+tableInfo.getApiFileName()+";\r\n");
		imp.append("import "+tableInfo.getServicePackageName()+"."+tableInfo.getEntityName()+"Service;\r\n");
		
		String imports=imp.toString();
		String entityName=tableInfo.getEntityName();
		
		return TemplateTool.getApiImpl(packageName, imports, entityName);
	}
}
