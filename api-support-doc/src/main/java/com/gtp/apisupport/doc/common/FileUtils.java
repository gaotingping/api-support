package com.gtp.apisupport.doc.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//写出文件
public class FileUtils {

	public static void write(String path,String content) throws Exception{
		File file=getFileByPath(path);
		PrintWriter out = new PrintWriter(file);
		out.write(content);
		out.close();
	}

	private static File getFileByPath(String path) throws IOException {
		File file=new File(path);
		if(!file.exists()){
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		return file;
	}
}
