package tmp.service;

import java.util.List;
import tmp.po.FlowRoutePO;


public interface FlowRouteService {

	public int insertFlowRoute(FlowRoutePO po);

	public int batchInsertFlowRoute(List<FlowRoutePO> list);

	public FlowRoutePO getFlowRouteById(Long id);

	public List<FlowRoutePO> getFlowRouteByPO(FlowRoutePO po);

	public int updateFlowRouteById(FlowRoutePO po);

	public int delFlowRouteById(Long id);

	public int delFlowRouteByIds(List<Long> list);

	public boolean isExistFlowRouteByPO(FlowRoutePO po);
}
