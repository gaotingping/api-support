package doc2.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 扫描文件注释
 */
public class ScanFileNote {

	//所有类的注释
	private static Map<String, JSONObject> allNote = new HashMap<>();
	
	private static String SUFFIX_NAME=null;
	
	/**
	 * 获得指定目录下，指定结尾子串文件的所有字段注释
	 * @param dirPath
	 * @param suffixName
	 * @return
	 */
	public static Map<String, JSONObject> getAllNote(String dirPath,String suffixName) {
		SUFFIX_NAME=suffixName;
		File dir = new File(dirPath);
		toShowFile(dir);
		return allNote;
	}
	
	private static void toShowFile(File f) {
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			for (File t : fs) {
				toShowFile(t);
			}
		} else {
			if (isFilterFile(f)) {
				toWriteVONote(f);
			}
		}
	}

	/**
	 * 是否是自己需要的文件
	 * @param f
	 * @return
	 */
	private static boolean isFilterFile(File f) {
		if(f.isFile() && f.getName().endsWith(SUFFIX_NAME)){
			return true;
		}
		return false;
	}

	/**
	 * 获得一个类的所有字段的说明
	 * 不含父类，最后再合并父类的字段
	 * 
	 * @param file
	 */
	private static void toWriteVONote(File file) {

		if (!file.exists()) {
			System.out.println("不存在" + file);
		}

		try {

			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String str = null;

			StringBuilder note = new StringBuilder();

			JSONObject result = new JSONObject();
			
			boolean f=false;
			
			while ((str = br.readLine()) != null) {
				
				str=str.trim();
				
				 /*从第一个public开始读取，防止获得类的注释*/
				if(!f && str.startsWith("public")){
					f=true;
					continue;
				}
				
				/*注释行获取*/
				if (isNoteRow(str)) {
					note.append(str);
				} else {
					/*仅挨注释行的以private开始的行被认为是字段定义行*/
					if (str.startsWith("private")) {
						handleFieldNote(str, note.toString(), result);
						note.setLength(0);//清空
					}
				}
			}

			//一个类的结果
			fullClassNote(file, result);

			br.close();
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("处理错误" + file);
		}
	}

	/**
	 * 填充一个类的注释
	 * @param file
	 * @param result
	 * @throws IOException
	 */
	private static void fullClassNote(File file, JSONObject result) throws IOException {

		String fName = file.getName();
		fName = fName.replace(".java", "");

		allNote.put(fName, result);
	}

	/**
	 * 处理一个字段的注释
	 * @param fieldRow
	 * @param note
	 * @param result
	 */
	private static void handleFieldNote(String fieldRow, String note, JSONObject result) {

		// 字段名称
		String fName = getFiledName(fieldRow);

		// 注释
		String noteName = note.replace("*", "").replace("/", "").trim();

		result.put(fName, noteName);
	}

	/**
	 * 根据行获取字段的名称
	 * @param fieldRow
	 * @return
	 */
	private static String getFiledName(String fieldRow) {

		String f=null;
		if (fieldRow.indexOf("=") > 0) {/*有默认值的取“=”之前最后一个*/
			f = fieldRow.split("=")[0];
		}else{
			f=fieldRow;
		}

		String[] fs = f.split(" +");
		String fName = fs[fs.length - 1];
		fName = fName.replace(";", "");
		
		return fName;
	}

	/**
	 * 判断当前行是否是注释行
	 * 注释行的规则:以"/*,*"开始的行号
	 * @param str
	 * @return
	 */
	private static boolean isNoteRow(String str) {
		if (str.startsWith("/*") || str.startsWith("*")) {
			return true;
		}
		return false;
	}
}
