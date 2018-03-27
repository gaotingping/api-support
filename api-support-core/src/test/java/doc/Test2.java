package doc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 每个VO抽取注释
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
public class Test2 {

	private static final String PATH="E:/workspace/cttms-parent/cttms-main/src/main/java/com/mvwchina/cttms";
	
	@Test
	public void test1() throws Exception{
		toShowAllNote("/notice/vo/TeachingVO.java");
	}
	
	public void toShowAllNote(String fname) throws Exception{
		String path=PATH+fname;
		File file = new File(path);
		if (file.exists()) {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			
			StringBuilder note=new StringBuilder();
			
			
			JSONObject result=new JSONObject();
			
			while ((str = br.readLine()) != null) {
				if(isNoteRow(str)){
					//过滤掉
					note.append(str);
				}else{
					if(str.trim().startsWith("private")){
						showRowNote(str,note.toString(),result);
						note.setLength(0);
					}
				}
			}
			
			System.out.println(result);
			
			br.close();
			reader.close();
		}
	}
	
	private void showRowNote(String f, String note,JSONObject result) {
		
		//字段名称
		String[] fs = f.split(" +");
		String fName=fs[fs.length-1];
		fName=fName.replace(";", "");
		
		//注释
		String noteName=note.replace("*", "").replace("/", "").trim();
		
		result.put(fName, noteName);
	}

	private boolean isNoteRow(String str) {
		String t = str.trim();
		if(t.startsWith("/*") || t.startsWith("*")){
			return true;
		}
		return false;
	}
}
