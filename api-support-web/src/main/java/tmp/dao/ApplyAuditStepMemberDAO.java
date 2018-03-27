package tmp.dao;

import java.util.List;
import tmp.po.ApplyAuditStepMemberPO;

public interface ApplyAuditStepMemberDAO {

	public int insert(ApplyAuditStepMemberPO po);

	public int batchInsert(List<ApplyAuditStepMemberPO> list);

	public ApplyAuditStepMemberPO selectById(Long id);

	public List<ApplyAuditStepMemberPO> selectByPO(ApplyAuditStepMemberPO po);

	public int updateById(ApplyAuditStepMemberPO po);

	public int delById(Long id);

	public int delByIds(List<Long> list);

	public Long isExistByPO(ApplyAuditStepMemberPO po);

}

