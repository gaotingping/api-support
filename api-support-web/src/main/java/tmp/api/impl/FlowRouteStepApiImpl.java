package tmp.api.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gtp.apisupport.common.CommonBiz;
import tmp.po.FlowRouteStepPO;
import tmp.api.FlowRouteStepApi;
import tmp.service.FlowRouteStepService;


@Component
public class FlowRouteStepApiImpl implements FlowRouteStepApi{

	@Autowired
	private FlowRouteStepService flowRouteStepService;
	
	@Override
	public FlowRouteStepPO editFlowRouteStep(FlowRouteStepPO po) {
		
		Long id = po.getId();
		
		if(CommonBiz.isId(id)){
			flowRouteStepService.updateFlowRouteStepById(po);
		}else{
			flowRouteStepService.insertFlowRouteStep(po);
		}
		
		return po;
	}

	@Override
	public FlowRouteStepPO findFlowRouteStepById(Long id) {
		if(!CommonBiz.isId(id)){
			return null;
		}
		return flowRouteStepService.getFlowRouteStepById(id);
	}

	@Override
	public List<FlowRouteStepPO> queryFlowRouteStep(FlowRouteStepPO po) {
		return flowRouteStepService.getFlowRouteStepByPO(po);
	}

	@Override
	public int delFlowRouteStepById(Long id) {
		return flowRouteStepService.delFlowRouteStepById(id);
	}

	@Override
	public int delFlowRouteStepByIds(List<Long> ids) {
		return flowRouteStepService.delFlowRouteStepByIds(ids);
	}
	
}
