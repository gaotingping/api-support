package tmp.po;

import java.util.Date;
import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.model.BasePge;

public class ApplyAuditStepPO extends BasePge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("申请表")
	private Long id;

	@ApiDescribe("步骤序号")
	private Integer stepNum;

	@ApiDescribe("步骤参数者(角色或其他条件)")
	private String stepPerformer;

	@ApiDescribe("审核人")
	private Long auditMemberId;

	@ApiDescribe("审核人角色")
	private String auditMemberRole;

	@ApiDescribe("审核人姓名")
	private String auditMemberName;

	@ApiDescribe("审核意见")
	private String auditNote;

	@ApiDescribe("审核状态0未审核 1是审核通过 2是审核失败")
	private Integer auditFlag;

	@ApiDescribe("审核时间")
	private Date auditTime;

	@ApiDescribe("当前步骤 1是 0否")
	private Integer currentStep;

	@ApiDescribe("资源ID")
	private Long resourceId;

	@ApiDescribe("资源type")
	private Integer resourceType;

	@ApiDescribe("添加时间")
	private Date stime;


	public void setId(Long id) {
		this.id= id;
	}

	public void setStepNum(Integer stepNum) {
		this.stepNum= stepNum;
	}

	public void setStepPerformer(String stepPerformer) {
		this.stepPerformer= stepPerformer;
	}

	public void setAuditMemberId(Long auditMemberId) {
		this.auditMemberId= auditMemberId;
	}

	public void setAuditMemberRole(String auditMemberRole) {
		this.auditMemberRole= auditMemberRole;
	}

	public void setAuditMemberName(String auditMemberName) {
		this.auditMemberName= auditMemberName;
	}

	public void setAuditNote(String auditNote) {
		this.auditNote= auditNote;
	}

	public void setAuditFlag(Integer auditFlag) {
		this.auditFlag= auditFlag;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime= auditTime;
	}

	public void setCurrentStep(Integer currentStep) {
		this.currentStep= currentStep;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId= resourceId;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType= resourceType;
	}

	public void setStime(Date stime) {
		this.stime= stime;
	}


	public Long getId() {
		return id;
	}

	public Integer getStepNum() {
		return stepNum;
	}

	public String getStepPerformer() {
		return stepPerformer;
	}

	public Long getAuditMemberId() {
		return auditMemberId;
	}

	public String getAuditMemberRole() {
		return auditMemberRole;
	}

	public String getAuditMemberName() {
		return auditMemberName;
	}

	public String getAuditNote() {
		return auditNote;
	}

	public Integer getAuditFlag() {
		return auditFlag;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public Integer getCurrentStep() {
		return currentStep;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public Date getStime() {
		return stime;
	}


}
