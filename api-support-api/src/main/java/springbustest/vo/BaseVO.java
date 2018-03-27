package springbustest.vo;

import java.io.Serializable;

import com.gtp.apisupport.annotation.ApiDescribe;

public class BaseVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("公共字段")
	public Integer pid;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
