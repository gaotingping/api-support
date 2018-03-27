package tmp.dao;

import java.util.List;
import tmp.po.ApplyPO;

public interface ApplyDAO {

	public int insert(ApplyPO po);

	public int batchInsert(List<ApplyPO> list);

	public ApplyPO selectById(Long id);

	public List<ApplyPO> selectByPO(ApplyPO po);

	public int updateById(ApplyPO po);

	public int delById(Long id);

	public int delByIds(List<Long> list);

	public Long isExistByPO(ApplyPO po);

}

