//package test_log;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.junit.Test;
//
//import com.gtp.apisupport.common.DocReflectUtil;
//import com.gtp.apisupport.model.ApiPageResult;
//
////日志收集中的变量替换
//public class Test1 {
//
//	@Test
//	public void test1(){
//		//Matcher m = Pattern.compile("\\$\\{\\w+\\}").matcher(template);  
//		String str="abc";
//	
//		//step1:获得参数名称
//		Pattern p = Pattern.compile("\\$\\{(\\w+)\\}");
//		Matcher m = p.matcher(str);  
//		if(m.find()) {  
//		    str=m.group(1);
//		} 
//		System.out.println("str:"+str);
//		
//		//step2:按照xxx.xxx截取,参数名称中就别用“.”了啊
//		int i = str.indexOf("\\.");
//		if(i<0){
//			//单个，直接getString("paramName")
//		}
//		String[] ps = str.split("\\.");
//		for(int j=0;j<ps.length-1;j++){
//			//获得json对象
//		}
//		//ps[ps.length-1] 获得字符串
//	}
//	
//	@Test
//	public void test2(){
//		System.out.println(DocReflectUtil.allFields(ApiPageResult.class));
//	}
//}
