package tmp.po;

import java.util.Date;
import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.model.BasePge;

public class ApplyPO extends BasePge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("申请表")
	private Long id;

	@ApiDescribe("考勤流程id")
	private Long flowId;

	@ApiDescribe("单位标识(基地)")
	private String hid;

	@ApiDescribe("申请人")
	private Long applyMemberId;

	@ApiDescribe("申请人角色")
	private String applyMemberRole;

	@ApiDescribe("申请说明")
	private String applyNote;

	@ApiDescribe("申请时间")
	private Date applyTime;

	@ApiDescribe("审核时间")
	private Date auditTime;

	@ApiDescribe("审批状态: 0未审 1进行中 2成功 3拒绝 4取消")
	private Integer auditFlag;

	@ApiDescribe("最终审核备注")
	private String auditNote;

	@ApiDescribe("添加时间")
	private Date stime;

	@ApiDescribe("是否删除 1是 0否")
	private Integer isDel;


	public void setId(Long id) {
		this.id= id;
	}

	public void setFlowId(Long flowId) {
		this.flowId= flowId;
	}

	public void setHid(String hid) {
		this.hid= hid;
	}

	public void setApplyMemberId(Long applyMemberId) {
		this.applyMemberId= applyMemberId;
	}

	public void setApplyMemberRole(String applyMemberRole) {
		this.applyMemberRole= applyMemberRole;
	}

	public void setApplyNote(String applyNote) {
		this.applyNote= applyNote;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime= applyTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime= auditTime;
	}

	public void setAuditFlag(Integer auditFlag) {
		this.auditFlag= auditFlag;
	}

	public void setAuditNote(String auditNote) {
		this.auditNote= auditNote;
	}

	public void setStime(Date stime) {
		this.stime= stime;
	}

	public void setIsDel(Integer isDel) {
		this.isDel= isDel;
	}


	public Long getId() {
		return id;
	}

	public Long getFlowId() {
		return flowId;
	}

	public String getHid() {
		return hid;
	}

	public Long getApplyMemberId() {
		return applyMemberId;
	}

	public String getApplyMemberRole() {
		return applyMemberRole;
	}

	public String getApplyNote() {
		return applyNote;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public Integer getAuditFlag() {
		return auditFlag;
	}

	public String getAuditNote() {
		return auditNote;
	}

	public Date getStime() {
		return stime;
	}

	public Integer getIsDel() {
		return isDel;
	}


}
