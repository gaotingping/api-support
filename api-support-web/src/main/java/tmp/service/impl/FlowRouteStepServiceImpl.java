package tmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import tmp.po.FlowRouteStepPO;
import tmp.dao.FlowRouteStepDAO;
import tmp.service.FlowRouteStepService;


@Service
public class FlowRouteStepServiceImpl implements FlowRouteStepService{

	@Autowired
	private FlowRouteStepDAO flowRouteStepDAO;

	@Override
	@Transactional
	public int insertFlowRouteStep(FlowRouteStepPO po) {
		return flowRouteStepDAO.insert(po);
	}

	@Override
	@Transactional
	public int batchInsertFlowRouteStep(List<FlowRouteStepPO> list) {
		return flowRouteStepDAO.batchInsert(list);
	}

	@Override
	public FlowRouteStepPO getFlowRouteStepById(Long id) {
		return flowRouteStepDAO.selectById(id);
	}

	@Override
	public List<FlowRouteStepPO> getFlowRouteStepByPO(FlowRouteStepPO po) {
		return flowRouteStepDAO.selectByPO(po);
	}

	@Override
	@Transactional
	public int updateFlowRouteStepById(FlowRouteStepPO po) {
		return flowRouteStepDAO.updateById(po);
	}

	@Override
	@Transactional
	public int delFlowRouteStepById(Long id) {
		return flowRouteStepDAO.delById(id);
	}

	@Override
	@Transactional
	public int delFlowRouteStepByIds(List<Long> ids) {
		return flowRouteStepDAO.delByIds(ids);
	}

	@Override
	public boolean isExistFlowRouteStepByPO(FlowRouteStepPO po) {
		Long id = flowRouteStepDAO.isExistByPO(po);
		if(id == null || id<1){
			return false;
		}
		return true;
	}
}
