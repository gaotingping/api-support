package doc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

/**
 * 测试:把所有VO的注释都刷过来,以便于文档生成
 */
public class Test1 {
	
	@Test
	public void test2(){
		toShowFile(new File("E:/workspace/cttms-parent/cttms-main/src/main/java/com/mvwchina/cttms"));
	}

	private void toShowFile(File f) {
		if(f.isDirectory()){
			File[] fs = f.listFiles();
			for(File t:fs){
				toShowFile(t);
			}
		}else{
			if(f.isFile() && f.getName().endsWith("VO.java")){
				System.out.println(f);
			}
		}
	}

	/**
	 *
     * 操作时间
     * @return operating_time 操作时间
     */
	@Test
	public void test1() throws Exception{
		String path="E:/workspace/cttms-parent/cttms-main/src/main/java/com/mvwchina/cttms/log/vo/SystemLogsVO.java";
		File file = new File(path);
		if (file.exists()) {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			
			StringBuilder note=new StringBuilder();
			
			while ((str = br.readLine()) != null) {
				if(isNoteRow(str)){
					//过滤掉
					note.append(str);
				}else{
					if(note.length()>0){
						showNote(note.toString());
						note.setLength(0);
					}
					System.out.println(str);
				}
			}
			br.close();
			reader.close();
		}
	}

	private void showNote(String note) {
		
		//忽略
		if(note.indexOf("@param") > -1 || note.indexOf("@return") > -1){
			return;
		}
		
		//格式化
		System.out.println("    @ApiMonitor(\""+note.replace("*", "").replace("/", "").trim()+"\")");
	}

	private boolean isNoteRow(String str) {
		String t = str.trim();
		if(t.startsWith("/*") || t.startsWith("*")){
			return true;
		}
		return false;
	}
}
