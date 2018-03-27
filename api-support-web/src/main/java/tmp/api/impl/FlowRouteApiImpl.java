package tmp.api.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gtp.apisupport.common.CommonBiz;
import tmp.po.FlowRoutePO;
import tmp.api.FlowRouteApi;
import tmp.service.FlowRouteService;


@Component
public class FlowRouteApiImpl implements FlowRouteApi{

	@Autowired
	private FlowRouteService flowRouteService;
	
	@Override
	public FlowRoutePO editFlowRoute(FlowRoutePO po) {
		
		Long id = po.getId();
		
		if(CommonBiz.isId(id)){
			flowRouteService.updateFlowRouteById(po);
		}else{
			flowRouteService.insertFlowRoute(po);
		}
		
		return po;
	}

	@Override
	public FlowRoutePO findFlowRouteById(Long id) {
		if(!CommonBiz.isId(id)){
			return null;
		}
		return flowRouteService.getFlowRouteById(id);
	}

	@Override
	public List<FlowRoutePO> queryFlowRoute(FlowRoutePO po) {
		return flowRouteService.getFlowRouteByPO(po);
	}

	@Override
	public int delFlowRouteById(Long id) {
		return flowRouteService.delFlowRouteById(id);
	}

	@Override
	public int delFlowRouteByIds(List<Long> ids) {
		return flowRouteService.delFlowRouteByIds(ids);
	}
	
}
