package springbustest.dto;

import java.io.Serializable;

import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.validation.annotation.ApiValidation;
import com.gtp.apisupport.validation.enums.ApiVP;

/**
 * 公共字段抽取:
 * 为了快速开发和做功能:
 * 允许在VO,DTO中引用PO
 * 允许VO,DTO传递到映射层
 * 但是VO和DTO就别相互引用了
 * -在速度和功能是第一位的情况下
 */
public class BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("公共字段")
	@ApiValidation(pattern=ApiVP.NOTNULL,error="pid必填")
	private Integer pid;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
