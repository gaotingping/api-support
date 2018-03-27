package tmp.po;

import java.util.Date;
import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.model.BasePge;

public class ApplyAuditStepMemberPO extends BasePge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("各步骤审核者")
	private Long id;

	@ApiDescribe("审核步骤ID")
	private Long auditStepId;

	@ApiDescribe("用户标识")
	private String member;

	@ApiDescribe("用户角色")
	private String memberRole;


	public void setId(Long id) {
		this.id= id;
	}

	public void setAuditStepId(Long auditStepId) {
		this.auditStepId= auditStepId;
	}

	public void setMember(String member) {
		this.member= member;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole= memberRole;
	}


	public Long getId() {
		return id;
	}

	public Long getAuditStepId() {
		return auditStepId;
	}

	public String getMember() {
		return member;
	}

	public String getMemberRole() {
		return memberRole;
	}


}
