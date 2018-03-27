package doc2.common;

import java.io.File;

/**
 * 自动生成文档配置
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
public class DocConfig {

	//文档全路径
	public final static String doc_path="F:/桌面/Desktop/项目/轮转合并版本/doc/住培4.0文档-20180126.doc";
	
	// 项目logic目录
	public final static String logic_dir = getPjectPath()+"/services/cttms-parent/cttms-main/src/main/java/com/mvwchina/cttms/entry/logic/";
	
	//dto写出目录和文件后缀
	public final static String dto_path="F:/桌面/Desktop/项目/轮转合并版本/doc/zdto/";
	public final static String dto_suffix_name=".js";
	
	//vo写出目录和文件后缀
	public final static String vo_path="F:/桌面/Desktop/项目/轮转合并版本/doc/zvo/";
	public final static String vo_suffix_name=".js";
	
	//项目vo_dto所在目录
	public final static String dto_vo_dir=getPjectPath()+"/services/cttms-parent/cttms-main/src/main/java/com/mvwchina/cttms";
	
	private static String getPjectPath() {
		File f=new File("");
		return f.getAbsolutePath();
	}
	
	public final static String basePackage="com.mvwchina.cttms";
}
