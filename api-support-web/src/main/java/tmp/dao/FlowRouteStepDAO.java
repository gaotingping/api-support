package tmp.dao;

import java.util.List;
import tmp.po.FlowRouteStepPO;

public interface FlowRouteStepDAO {

	public int insert(FlowRouteStepPO po);

	public int batchInsert(List<FlowRouteStepPO> list);

	public FlowRouteStepPO selectById(Long id);

	public List<FlowRouteStepPO> selectByPO(FlowRouteStepPO po);

	public int updateById(FlowRouteStepPO po);

	public int delById(Long id);

	public int delByIds(List<Long> list);

	public Long isExistByPO(FlowRouteStepPO po);

}

