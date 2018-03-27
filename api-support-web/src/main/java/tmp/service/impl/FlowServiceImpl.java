package tmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import tmp.po.FlowPO;
import tmp.dao.FlowDAO;
import tmp.service.FlowService;


@Service
public class FlowServiceImpl implements FlowService{

	@Autowired
	private FlowDAO flowDAO;

	@Override
	@Transactional
	public int insertFlow(FlowPO po) {
		return flowDAO.insert(po);
	}

	@Override
	@Transactional
	public int batchInsertFlow(List<FlowPO> list) {
		return flowDAO.batchInsert(list);
	}

	@Override
	public FlowPO getFlowById(Long id) {
		return flowDAO.selectById(id);
	}

	@Override
	public List<FlowPO> getFlowByPO(FlowPO po) {
		return flowDAO.selectByPO(po);
	}

	@Override
	@Transactional
	public int updateFlowById(FlowPO po) {
		return flowDAO.updateById(po);
	}

	@Override
	@Transactional
	public int delFlowById(Long id) {
		return flowDAO.delById(id);
	}

	@Override
	@Transactional
	public int delFlowByIds(List<Long> ids) {
		return flowDAO.delByIds(ids);
	}

	@Override
	public boolean isExistFlowByPO(FlowPO po) {
		Long id = flowDAO.isExistByPO(po);
		if(id == null || id<1){
			return false;
		}
		return true;
	}
}
