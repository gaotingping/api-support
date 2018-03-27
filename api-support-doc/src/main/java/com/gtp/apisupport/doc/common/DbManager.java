package com.gtp.apisupport.doc.common;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 生成代码时-数据库管理者
 */
public class DbManager {

	private static String url = "jdbc:mysql://127.0.0.1:3306/test_flow?useUnicode=true&characterEncoding=utf-8";
	private static String username = "root";
	private static String password = "123456";

	public static Connection getCon() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		// 获得连接
		return DriverManager.getConnection(url, username, password);
	}

	//是有点别扭啊
	public static void init(String url1, String username1, String password1) {
		url = url1;
		username = username1;
		password = password1;
	}
}
