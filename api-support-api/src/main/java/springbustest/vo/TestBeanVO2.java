package springbustest.vo;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.validation.annotation.ApiValidation;

/**
 * 测试注入bean
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
@ApiDescribe("测试注入bean")
public class TestBeanVO2 extends BaseVO{

	private static final long serialVersionUID = 7880616919526000622L;

	@ApiDescribe("主鍵")
	public Integer id;
	
	@ApiDescribe("描述")
	@ApiValidation(regex="\\d+{6,12}",error="账号必须是6到12位的数字")/*演示如何标注验证,忽略循环依赖,特别不建议循环依赖*/
	public String name;
	
	@ApiDescribe("循环引用")
	public TestBeanVO3 t3;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestBeanVO3 getT3() {
		return t3;
	}

	public void setT3(TestBeanVO3 t3) {
		this.t3 = t3;
	}

	@Override
	public String toString() {
		return "TestBean2 [id=" + id + ", name=" + name + ", t3=" + t3 + "]";
	}
}
