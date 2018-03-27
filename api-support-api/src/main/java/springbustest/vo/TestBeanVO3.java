package springbustest.vo;

import java.util.Date;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.validation.annotation.ApiValidation;

/**
 * 测试注入bean
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
@ApiDescribe("测试注入bean")
public class TestBeanVO3 extends BaseVO{

	private static final long serialVersionUID = 7880616919526000622L;

	@ApiDescribe("主鍵")
	public Integer id;
	
	@ApiDescribe("描述")
	@ApiValidation(regex="\\d+{6,12}",error="账号必须是6到12位的数字")
	public String name;
	
	@ApiDescribe("时间")
	public Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TestBean3 [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
}
