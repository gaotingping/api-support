package doc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.common.JsonUtils;

/**
 * 抽取所有的VO字段注释
 * 
 * @author gaotingping@cyberzone.cn
 */
public class VOFieldSamplingTools {

	@Test
	public void test1() {

		File dir = new File("E:/workspace/cttms-parent/cttms-main/src/main/java/com/mvwchina/cttms");
		toShowFile(dir);

	}

	private void toShowFile(File f) {
		if (f.isDirectory()) {
			File[] fs = f.listFiles();
			for (File t : fs) {
				toShowFile(t);
			}
		} else {
			// 只处理VO的东西
			if (f.isFile() && f.getName().endsWith("VO.java")) {
				toWriteVONote(f);
			}
		}
	}

	private void toWriteVONote(File file) {

		if (!file.exists()) {
			System.out.println("不存在" + file);
		}

		try {

			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String str = null;

			StringBuilder note = new StringBuilder();

			JSONObject result = new JSONObject();

			while ((str = br.readLine()) != null) {
				if (isNoteRow(str)) {
					// 过滤掉
					note.append(str);
				} else {
					if (str.trim().startsWith("private")) {
						showRowNote(str, note.toString(), result);
						note.setLength(0);
					}
				}
			}

			//
			toWriteFile(file,result);

			br.close();
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("处理错误" + file);
		}
	}

	private void toWriteFile(File f, JSONObject result) throws IOException {
		File file = new File("E:/z2018/"+f.getName()+".txt");
		if (file.exists()) {
			file.createNewFile();
		}
		PrintWriter out = new PrintWriter(file);
		out.write(JsonUtils.formatJson(result.toJSONString()));
		out.close();
	}

	private void showRowNote(String f, String note, JSONObject result) {

		// 字段名称
		String[] fs = f.split(" +");
		String fName = fs[fs.length - 1];
		fName = fName.replace(";", "");

		// 注释
		String noteName = note.replace("*", "").replace("/", "").trim();

		result.put(fName, noteName);
	}

	private boolean isNoteRow(String str) {
		String t = str.trim();
		if (t.startsWith("/*") || t.startsWith("*")) {
			return true;
		}
		return false;
	}
}
