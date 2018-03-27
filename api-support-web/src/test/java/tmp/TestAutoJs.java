package tmp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gtp.apisupport.doc.AutoBuildJsDocData;
import com.gtp.apisupport.doc.AutoBuildJsSDK;
import com.gtp.apisupport.doc.AutoBuildJsTree;
import com.gtp.apisupport.doc.AutoBuildWordDoc;

/**
 * 获得字段的时候，还得用
 	getFields()获得某个类的所有的公共（public）的字段，包括父类。 
	getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。 
 */
public class TestAutoJs {

	@Test //生成word文档
	public void test2() throws Exception{
		
		List<String> p=new ArrayList<>();
		//p.add("springbustest.service");
		p.add("tmp.api");
		
		AutoBuildWordDoc.build("E:/api.doc", p);
	}
	
	//在线文档
	@Test 
	public void test4() throws Exception{
		List<String> p=new ArrayList<>();
		p.add("tmp.api");
		AutoBuildJsSDK.build("E:/git/api-support/api-support-web/src/main/webapp/doc/js/data/sdk.js", p);
		AutoBuildJsTree.build("E:/git/api-support/api-support-web/src/main/webapp/doc/js/data/tree.js", p);
		AutoBuildJsDocData.build("E:/git/api-support/api-support-web/src/main/webapp/doc/js/data/data.js", p);
	}
}
