package tmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import tmp.po.FlowRoutePO;
import tmp.dao.FlowRouteDAO;
import tmp.service.FlowRouteService;


@Service
public class FlowRouteServiceImpl implements FlowRouteService{

	@Autowired
	private FlowRouteDAO flowRouteDAO;

	@Override
	@Transactional
	public int insertFlowRoute(FlowRoutePO po) {
		return flowRouteDAO.insert(po);
	}

	@Override
	@Transactional
	public int batchInsertFlowRoute(List<FlowRoutePO> list) {
		return flowRouteDAO.batchInsert(list);
	}

	@Override
	public FlowRoutePO getFlowRouteById(Long id) {
		return flowRouteDAO.selectById(id);
	}

	@Override
	public List<FlowRoutePO> getFlowRouteByPO(FlowRoutePO po) {
		return flowRouteDAO.selectByPO(po);
	}

	@Override
	@Transactional
	public int updateFlowRouteById(FlowRoutePO po) {
		return flowRouteDAO.updateById(po);
	}

	@Override
	@Transactional
	public int delFlowRouteById(Long id) {
		return flowRouteDAO.delById(id);
	}

	@Override
	@Transactional
	public int delFlowRouteByIds(List<Long> ids) {
		return flowRouteDAO.delByIds(ids);
	}

	@Override
	public boolean isExistFlowRouteByPO(FlowRoutePO po) {
		Long id = flowRouteDAO.isExistByPO(po);
		if(id == null || id<1){
			return false;
		}
		return true;
	}
}
