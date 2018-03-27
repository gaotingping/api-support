package tmp.service;

import java.util.List;
import tmp.po.ApplyAuditStepMemberPO;


public interface ApplyAuditStepMemberService {

	public int insertApplyAuditStepMember(ApplyAuditStepMemberPO po);

	public int batchInsertApplyAuditStepMember(List<ApplyAuditStepMemberPO> list);

	public ApplyAuditStepMemberPO getApplyAuditStepMemberById(Long id);

	public List<ApplyAuditStepMemberPO> getApplyAuditStepMemberByPO(ApplyAuditStepMemberPO po);

	public int updateApplyAuditStepMemberById(ApplyAuditStepMemberPO po);

	public int delApplyAuditStepMemberById(Long id);

	public int delApplyAuditStepMemberByIds(List<Long> list);

	public boolean isExistApplyAuditStepMemberByPO(ApplyAuditStepMemberPO po);
}
