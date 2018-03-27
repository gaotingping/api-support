package springbustest.vo;

import java.util.List;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.validation.annotation.ApiValidation;

/**
 * 测试注入bean
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
@ApiDescribe("测试注入bean")
public class TestBeanVO extends BaseVO{

	private static final long serialVersionUID = 7880616919526000622L;

	@ApiDescribe("主鍵")
	public Integer id;
	
	@ApiDescribe("描述")
	@ApiValidation(regex="\\d+{6,12}",error="账号必须是6到12位的数字")
	public String name;
	
	@ApiDescribe("引用bean")
	public TestBeanVO2 t2;
	
	@ApiDescribe("引用bean-list")
	public List<TestBeanVO2> list;

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

	public TestBeanVO2 getT2() {
		return t2;
	}

	public void setT2(TestBeanVO2 t2) {
		this.t2 = t2;
	}

	public List<TestBeanVO2> getList() {
		return list;
	}

	public void setList(List<TestBeanVO2> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "TestBean [id=" + id + ", name=" + name + ", t2=" + t2 + ", list=" + list + "]";
	}
}
