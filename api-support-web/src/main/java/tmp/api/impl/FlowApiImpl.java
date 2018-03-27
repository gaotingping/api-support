package tmp.api.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gtp.apisupport.common.CommonBiz;
import tmp.po.FlowPO;
import tmp.api.FlowApi;
import tmp.service.FlowService;


@Component
public class FlowApiImpl implements FlowApi{

	@Autowired
	private FlowService flowService;
	
	@Override
	public FlowPO editFlow(FlowPO po) {
		
		Long id = po.getId();
		
		if(CommonBiz.isId(id)){
			flowService.updateFlowById(po);
		}else{
			flowService.insertFlow(po);
		}
		
		return po;
	}

	@Override
	public FlowPO findFlowById(Long id) {
		if(!CommonBiz.isId(id)){
			return null;
		}
		return flowService.getFlowById(id);
	}

	@Override
	public List<FlowPO> queryFlow(FlowPO po) {
		return flowService.getFlowByPO(po);
	}

	@Override
	public int delFlowById(Long id) {
		return flowService.delFlowById(id);
	}

	@Override
	public int delFlowByIds(List<Long> ids) {
		return flowService.delFlowByIds(ids);
	}
	
}
