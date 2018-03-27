package com.gtp.apisupport.doc;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gtp.apisupport.doc.builder.ApiBuilder;
import com.gtp.apisupport.doc.builder.DaoBuilder;
import com.gtp.apisupport.doc.builder.PoBuilder;
import com.gtp.apisupport.doc.builder.ServiceBuilder;
import com.gtp.apisupport.doc.builder.XmlBuilder;
import com.gtp.apisupport.doc.common.FileUtils;
import com.gtp.apisupport.doc.metadata.ParseMetadata;
import com.gtp.apisupport.doc.model.TableConfig;
import com.gtp.apisupport.doc.model.TableInfo;

/**
 * java crud全栈代码生成
 */
public class JavaAutoBuilder {
	
	/**
	 * 不设置查询查询所有表
	 * @param projectPath
	 * @param basePackage
	 * @param tables
	 * @throws Exception
	 */
	public static void builder(String projectPath, String basePackage, Map<String,TableConfig> tables) throws Exception{

		//查询表字段注释等等
		List<TableInfo> p = null;
		if(tables == null || tables.isEmpty()){
			p = ParseMetadata.parse();
		}else{
			Set<String> set = tables.keySet();
			String [] t=new String[set.size()];
			Iterator<String> it = set.iterator();
			int index=0;
			while(it.hasNext()){
				t[index++]=it.next();
			}
			p = ParseMetadata.parse(t);
		}

		for (TableInfo t : p) {
			//包名
			t.setDaoPackageName(basePackage+".dao");
			t.setPoPackageName(basePackage+".po");
			t.setServicePackageName(basePackage+".service");
			t.setApiPackageName(basePackage+".api");
			
			if(tables == null || tables.isEmpty()){
				t.setEntityCodePrefix("");
				t.setEntityNote("");
			}else{
				TableConfig tcf = tables.get(t.getTableName());
				t.setEntityCodePrefix(tcf.getEntityCodePrefix());
				t.setEntityNote(tcf.getEntityNote());
			}
			toBuild(t,projectPath);
		}
	}

	private static void toBuild(TableInfo tab,String projectPath) throws Exception {
		
		//po
		PoBuilder po = new PoBuilder(tab);
		FileUtils.write(projectPath+getPoFile(tab), po.getPoInfo());

		//dao
		DaoBuilder dao = new DaoBuilder(tab);
		FileUtils.write(projectPath+getDaoFile(tab), dao.getDaoInfo());

		//xml
		XmlBuilder xb = new XmlBuilder(tab);
		FileUtils.write(projectPath+getXmlFile(tab), xb.getAll());
		
		//service
		ServiceBuilder sb=new ServiceBuilder(tab);
		FileUtils.write(projectPath+getServiceFile(tab), sb.getService());
		FileUtils.write(projectPath+getServiceImplFile(tab), sb.getServiceImpl());
		
		//api
		ApiBuilder api=new ApiBuilder(tab);
		FileUtils.write(projectPath+getApiFile(tab), api.getApi());
		FileUtils.write(projectPath+getApiImplFile(tab), api.getApiImpl());
	}

	private static String getPoFile(TableInfo tab) {
		String p = tab.getPoPackageName();
		p=p.replace(".", "/");
		return p+"/"+tab.getPoFileName() + ".java";
	}
	
	private static String getDaoFile(TableInfo tab) {
		String p = tab.getDaoPackageName();
		p=p.replace(".", "/");
		return p+"/"+tab.getDaoFileName() + ".java";
	}
	
	private static String getXmlFile(TableInfo tab) {
		String p = tab.getDaoPackageName();
		p=p.replace(".", "/");
		return p+"/"+tab.getXmlFileName() + ".xml";
	}
	
	private static String getServiceFile(TableInfo tab) {
		String p = tab.getServicePackageName();
		p=p.replace(".", "/");
		return p+"/"+tab.getEntityName() + "Service.java";
	}
	
	private static String getServiceImplFile(TableInfo tab) {
		String p = tab.getServicePackageName()+".impl";
		p=p.replace(".", "/");
		return p+"/"+tab.getEntityName() + "ServiceImpl.java";
	}
	
	private static String getApiFile(TableInfo tab) {
		String p = tab.getApiPackageName();
		p=p.replace(".", "/");
		return p+"/"+tab.getEntityName() + "Api.java";
	}
	
	private static String getApiImplFile(TableInfo tab) {
		String p = tab.getApiPackageName()+".impl";
		p=p.replace(".", "/");
		return p+"/"+tab.getEntityName() + "ApiImpl.java";
	}
}
