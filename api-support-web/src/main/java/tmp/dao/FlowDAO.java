package tmp.dao;

import java.util.List;
import tmp.po.FlowPO;

public interface FlowDAO {

	public int insert(FlowPO po);

	public int batchInsert(List<FlowPO> list);

	public FlowPO selectById(Long id);

	public List<FlowPO> selectByPO(FlowPO po);

	public int updateById(FlowPO po);

	public int delById(Long id);

	public int delByIds(List<Long> list);

	public Long isExistByPO(FlowPO po);

}

