package tmp.dao;

import java.util.List;
import tmp.po.ApplyAuditStepPO;

public interface ApplyAuditStepDAO {

	public int insert(ApplyAuditStepPO po);

	public int batchInsert(List<ApplyAuditStepPO> list);

	public ApplyAuditStepPO selectById(Long id);

	public List<ApplyAuditStepPO> selectByPO(ApplyAuditStepPO po);

	public int updateById(ApplyAuditStepPO po);

	public int delById(Long id);

	public int delByIds(List<Long> list);

	public Long isExistByPO(ApplyAuditStepPO po);

}

