package tmp.service;

import java.util.List;
import tmp.po.FlowPO;


public interface FlowService {

	public int insertFlow(FlowPO po);

	public int batchInsertFlow(List<FlowPO> list);

	public FlowPO getFlowById(Long id);

	public List<FlowPO> getFlowByPO(FlowPO po);

	public int updateFlowById(FlowPO po);

	public int delFlowById(Long id);

	public int delFlowByIds(List<Long> list);

	public boolean isExistFlowByPO(FlowPO po);
}
