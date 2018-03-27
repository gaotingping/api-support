package tmp;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.gtp.apisupport.doc.JavaAutoBuilder;
import com.gtp.apisupport.doc.model.TableConfig;

//自动生成代码
public class TestAutoJava {

	@Test
	public void test3() throws Exception{
		
		//DbManager.init(url1, username1, password1);
		
		String projectPath = getProjectPath();
		String basePackage = "tmp";
		
		//单表的CRUD操作，需要给给每个表指定方法编码
		Map<String,TableConfig> tables=new HashMap<>();
		tables.put("apply", new TableConfig("请假申请","001001"));
		tables.put("apply_audit_step", new TableConfig("申请审核步骤","001002"));
		tables.put("apply_audit_step_member", new TableConfig("申请审核各步骤审核人","001003"));
		tables.put("flow", new TableConfig("流程","001004"));
		tables.put("flow_route", new TableConfig("流程路由","001005"));
		tables.put("flow_route_step", new TableConfig("流程步骤","001006"));
		
		JavaAutoBuilder.builder(projectPath, basePackage,tables);
	}
	
	private static String getProjectPath() {
		File f = new File("");
		return f.getAbsolutePath() + "/src/main/java/";
	}
}
