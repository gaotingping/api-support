package springbustest.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.annotation.ApiService;
import com.gtp.apisupport.monitor.annotation.ApiMonitor;

import springbustest.dto.TestBeanDTO;
import springbustest.vo.TestBeanVO;

/**
 * 演示如何定义接口
 * 方法最终的编码是:接口编码+方法编码
 */
@ApiService("0014")
public interface TestApi {

	@ApiMethod(value="001",desc="空参数测试方法")
	public TestBeanVO test1();
	
	/**
	 * 注入原生args,适用用参数结构超复杂的时候
	 * 因为你不可能兼容所有，预留这种是非常必须的
	 */
	@ApiMethod(value="002",desc="原生参数测试方法",skipBP=true)
	public TestBeanVO test2(JSONObject args);
	
	/**
	 * 注入自己需要的参数
	 */
	@ApiMethod(value="003",desc="自定义参数123")
	public TestBeanVO test3(TestBeanDTO b);
	
	/**
	 * 返回list(输入输出只支持基本类型，pojo等，不支持map,set等)
	 */
	@ApiMonitor(playId="xxx",key="004",field1="${id}")
	@ApiMethod(value="004",desc="返回list") //按照这个规范，每个方法只接收bean,不要基本类型
	public List<TestBeanVO> test4(TestBeanDTO b);
	
	/**
	 * 注入非pojo都需要加注解标注名称
	 * 不允许一个方法中有的指定，有的不知道注解
	 * 
	 * @param list
	 * @return
	 */
	@ApiMethod(value="005",desc="注入list") //按照这个规范，每个方法只接收bean,不要基本类型
	public List<TestBeanVO> test5(@ApiParam("list") List<TestBeanDTO> list);
}
