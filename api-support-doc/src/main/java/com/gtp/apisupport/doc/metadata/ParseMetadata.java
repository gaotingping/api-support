package com.gtp.apisupport.doc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gtp.apisupport.doc.common.DbManager;
import com.gtp.apisupport.doc.model.FieldInfo;
import com.gtp.apisupport.doc.model.TableInfo;

//负责解析元数据
public class ParseMetadata {
	
	public static List<TableInfo> parse() throws Exception{
		List<String> list = getAllTable();
		return toParse(list);
	}
	
	public static List<TableInfo> parse(String ... tables) throws Exception{
		if(tables==null || tables.length <1){
			return parse();
		}
		List<String> list=new ArrayList<>();
		for(String tab:tables){
			list.add(tab);
		}
		return toParse(list);
	}
	
	//获得所有表
	private static List<String> getAllTable() throws Exception{
		Connection con = DbManager.getCon();
		DatabaseMetaData db = con.getMetaData();
		ResultSet rs = db.getTables(null,null,null,new String[]{"TABLE"});
		List<String> list=new ArrayList<>();
		while(rs.next()){
			String tableName = rs.getString("TABLE_NAME");
			list.add(tableName);
		}
		con.close();
		return list;
	}

	private static List<TableInfo> toParse(List<String> list) throws Exception{
		
		List<TableInfo> result=new ArrayList<>();
		Connection con = DbManager.getCon();
		
		for(String table:list){
			PreparedStatement ps = con.prepareStatement("SHOW FULL COLUMNS FROM "+table);
			ResultSet rs = ps.executeQuery();
			
			
			TableInfo info=new TableInfo();
			info.setTableName(table);
			
			while(rs.next()){
				FieldInfo fieldInfo=new FieldInfo();
				fieldInfo.setComment(rs.getString("Comment"));
				fieldInfo.setJdbcType(rs.getString("Type"));
				fieldInfo.setName(rs.getString("Field"));
				info.addField(fieldInfo);
			}
	        rs.close();
	        
	        result.add(info);
		}
		
		return result;
	}
}
