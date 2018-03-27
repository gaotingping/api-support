package tmp.service;

import java.util.List;
import tmp.po.ApplyAuditStepPO;


public interface ApplyAuditStepService {

	public int insertApplyAuditStep(ApplyAuditStepPO po);

	public int batchInsertApplyAuditStep(List<ApplyAuditStepPO> list);

	public ApplyAuditStepPO getApplyAuditStepById(Long id);

	public List<ApplyAuditStepPO> getApplyAuditStepByPO(ApplyAuditStepPO po);

	public int updateApplyAuditStepById(ApplyAuditStepPO po);

	public int delApplyAuditStepById(Long id);

	public int delApplyAuditStepByIds(List<Long> list);

	public boolean isExistApplyAuditStepByPO(ApplyAuditStepPO po);
}
