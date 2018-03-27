package tmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import tmp.po.ApplyAuditStepPO;
import tmp.dao.ApplyAuditStepDAO;
import tmp.service.ApplyAuditStepService;


@Service
public class ApplyAuditStepServiceImpl implements ApplyAuditStepService{

	@Autowired
	private ApplyAuditStepDAO applyAuditStepDAO;

	@Override
	@Transactional
	public int insertApplyAuditStep(ApplyAuditStepPO po) {
		return applyAuditStepDAO.insert(po);
	}

	@Override
	@Transactional
	public int batchInsertApplyAuditStep(List<ApplyAuditStepPO> list) {
		return applyAuditStepDAO.batchInsert(list);
	}

	@Override
	public ApplyAuditStepPO getApplyAuditStepById(Long id) {
		return applyAuditStepDAO.selectById(id);
	}

	@Override
	public List<ApplyAuditStepPO> getApplyAuditStepByPO(ApplyAuditStepPO po) {
		return applyAuditStepDAO.selectByPO(po);
	}

	@Override
	@Transactional
	public int updateApplyAuditStepById(ApplyAuditStepPO po) {
		return applyAuditStepDAO.updateById(po);
	}

	@Override
	@Transactional
	public int delApplyAuditStepById(Long id) {
		return applyAuditStepDAO.delById(id);
	}

	@Override
	@Transactional
	public int delApplyAuditStepByIds(List<Long> ids) {
		return applyAuditStepDAO.delByIds(ids);
	}

	@Override
	public boolean isExistApplyAuditStepByPO(ApplyAuditStepPO po) {
		Long id = applyAuditStepDAO.isExistByPO(po);
		if(id == null || id<1){
			return false;
		}
		return true;
	}
}
