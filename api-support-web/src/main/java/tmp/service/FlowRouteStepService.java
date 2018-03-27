package tmp.service;

import java.util.List;
import tmp.po.FlowRouteStepPO;


public interface FlowRouteStepService {

	public int insertFlowRouteStep(FlowRouteStepPO po);

	public int batchInsertFlowRouteStep(List<FlowRouteStepPO> list);

	public FlowRouteStepPO getFlowRouteStepById(Long id);

	public List<FlowRouteStepPO> getFlowRouteStepByPO(FlowRouteStepPO po);

	public int updateFlowRouteStepById(FlowRouteStepPO po);

	public int delFlowRouteStepById(Long id);

	public int delFlowRouteStepByIds(List<Long> list);

	public boolean isExistFlowRouteStepByPO(FlowRouteStepPO po);
}
