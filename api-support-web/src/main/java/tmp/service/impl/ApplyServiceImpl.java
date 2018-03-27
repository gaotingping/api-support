package tmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import tmp.po.ApplyPO;
import tmp.dao.ApplyDAO;
import tmp.service.ApplyService;


@Service
public class ApplyServiceImpl implements ApplyService{

	@Autowired
	private ApplyDAO applyDAO;

	@Override
	@Transactional
	public int insertApply(ApplyPO po) {
		return applyDAO.insert(po);
	}

	@Override
	@Transactional
	public int batchInsertApply(List<ApplyPO> list) {
		return applyDAO.batchInsert(list);
	}

	@Override
	public ApplyPO getApplyById(Long id) {
		return applyDAO.selectById(id);
	}

	@Override
	public List<ApplyPO> getApplyByPO(ApplyPO po) {
		return applyDAO.selectByPO(po);
	}

	@Override
	@Transactional
	public int updateApplyById(ApplyPO po) {
		return applyDAO.updateById(po);
	}

	@Override
	@Transactional
	public int delApplyById(Long id) {
		return applyDAO.delById(id);
	}

	@Override
	@Transactional
	public int delApplyByIds(List<Long> ids) {
		return applyDAO.delByIds(ids);
	}

	@Override
	public boolean isExistApplyByPO(ApplyPO po) {
		Long id = applyDAO.isExistByPO(po);
		if(id == null || id<1){
			return false;
		}
		return true;
	}
}
