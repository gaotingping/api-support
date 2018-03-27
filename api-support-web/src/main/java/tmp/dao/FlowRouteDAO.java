package tmp.dao;

import java.util.List;
import tmp.po.FlowRoutePO;

public interface FlowRouteDAO {

	public int insert(FlowRoutePO po);

	public int batchInsert(List<FlowRoutePO> list);

	public FlowRoutePO selectById(Long id);

	public List<FlowRoutePO> selectByPO(FlowRoutePO po);

	public int updateById(FlowRoutePO po);

	public int delById(Long id);

	public int delByIds(List<Long> list);

	public Long isExistByPO(FlowRoutePO po);

}

