package springbustest.dto;

import java.util.List;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.validation.annotation.ApiValidation;
import com.gtp.apisupport.validation.enums.ApiVP;

/**
 * 测试注入bean
 * 
 * @author gaotingping@cyberzone.cn
 *
 */
@ApiDescribe("测试注入bean")
public class TestBeanDTO extends BaseDTO{

	private static final long serialVersionUID = 7880616919526000622L;

	@ApiDescribe("主鍵")
	private Integer id;
	
	@ApiDescribe("描述")
	@ApiValidation(regex="\\d+{6,12}",error="账号必须是6到12位的数字")
	private String name;
	
	@ApiDescribe("集合")
	@ApiValidation(pattern=ApiVP.NOTNULL,error="list必填")
	private List<String> list;

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

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}
