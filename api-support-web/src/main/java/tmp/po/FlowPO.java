package tmp.po;

import java.util.Date;
import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.model.BasePge;

public class FlowPO extends BasePge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("流程类型表")
	private Long id;

	@ApiDescribe("单位标识(基地标识)")
	private String hid;

	@ApiDescribe("流程名称")
	private String name;

	@ApiDescribe("流程类型 1考勤 2日常签到  3教学活动")
	private Integer type;

	@ApiDescribe("1启用 0停用")
	private Integer flag;

	@ApiDescribe("是否删除 1是 0否")
	private Integer isDel;


	public void setId(Long id) {
		this.id= id;
	}

	public void setHid(String hid) {
		this.hid= hid;
	}

	public void setName(String name) {
		this.name= name;
	}

	public void setType(Integer type) {
		this.type= type;
	}

	public void setFlag(Integer flag) {
		this.flag= flag;
	}

	public void setIsDel(Integer isDel) {
		this.isDel= isDel;
	}


	public Long getId() {
		return id;
	}

	public String getHid() {
		return hid;
	}

	public String getName() {
		return name;
	}

	public Integer getType() {
		return type;
	}

	public Integer getFlag() {
		return flag;
	}

	public Integer getIsDel() {
		return isDel;
	}


}
