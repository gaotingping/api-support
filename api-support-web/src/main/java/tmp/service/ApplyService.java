package tmp.service;

import java.util.List;
import tmp.po.ApplyPO;


public interface ApplyService {

	public int insertApply(ApplyPO po);

	public int batchInsertApply(List<ApplyPO> list);

	public ApplyPO getApplyById(Long id);

	public List<ApplyPO> getApplyByPO(ApplyPO po);

	public int updateApplyById(ApplyPO po);

	public int delApplyById(Long id);

	public int delApplyByIds(List<Long> list);

	public boolean isExistApplyByPO(ApplyPO po);
}
