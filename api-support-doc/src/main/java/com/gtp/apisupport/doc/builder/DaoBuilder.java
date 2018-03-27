package com.gtp.apisupport.doc.builder;

import com.gtp.apisupport.doc.model.TableInfo;
import com.gtp.apisupport.doc.template.TemplateTool;

//负责构建DAO
public class DaoBuilder extends BaseBuilder{
	
	public DaoBuilder(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}
	
	public String getDaoInfo() throws Exception {
		
		String packageName=tableInfo.getDaoPackageName();
		String importList="import "+tableInfo.getPoPackageName()+"."+tableInfo.getPoFileName()+";";
		String entityName=tableInfo.getEntityName();
		
		return TemplateTool.getDao(packageName,importList,entityName);
	}
}
