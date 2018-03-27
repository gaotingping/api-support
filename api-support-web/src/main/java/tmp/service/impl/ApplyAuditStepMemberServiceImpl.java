package tmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import tmp.po.ApplyAuditStepMemberPO;
import tmp.dao.ApplyAuditStepMemberDAO;
import tmp.service.ApplyAuditStepMemberService;


@Service
public class ApplyAuditStepMemberServiceImpl implements ApplyAuditStepMemberService{

	@Autowired
	private ApplyAuditStepMemberDAO applyAuditStepMemberDAO;

	@Override
	@Transactional
	public int insertApplyAuditStepMember(ApplyAuditStepMemberPO po) {
		return applyAuditStepMemberDAO.insert(po);
	}

	@Override
	@Transactional
	public int batchInsertApplyAuditStepMember(List<ApplyAuditStepMemberPO> list) {
		return applyAuditStepMemberDAO.batchInsert(list);
	}

	@Override
	public ApplyAuditStepMemberPO getApplyAuditStepMemberById(Long id) {
		return applyAuditStepMemberDAO.selectById(id);
	}

	@Override
	public List<ApplyAuditStepMemberPO> getApplyAuditStepMemberByPO(ApplyAuditStepMemberPO po) {
		return applyAuditStepMemberDAO.selectByPO(po);
	}

	@Override
	@Transactional
	public int updateApplyAuditStepMemberById(ApplyAuditStepMemberPO po) {
		return applyAuditStepMemberDAO.updateById(po);
	}

	@Override
	@Transactional
	public int delApplyAuditStepMemberById(Long id) {
		return applyAuditStepMemberDAO.delById(id);
	}

	@Override
	@Transactional
	public int delApplyAuditStepMemberByIds(List<Long> ids) {
		return applyAuditStepMemberDAO.delByIds(ids);
	}

	@Override
	public boolean isExistApplyAuditStepMemberByPO(ApplyAuditStepMemberPO po) {
		Long id = applyAuditStepMemberDAO.isExistByPO(po);
		if(id == null || id<1){
			return false;
		}
		return true;
	}
}
