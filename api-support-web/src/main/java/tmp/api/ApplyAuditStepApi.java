package tmp.api;

import tmp.po.ApplyAuditStepPO;

import java.util.List;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.annotation.ApiService;

@ApiService("申请审核步骤")
public interface ApplyAuditStepApi {

	@ApiMethod(value="001002001",desc="编辑")
	public ApplyAuditStepPO editApplyAuditStep(ApplyAuditStepPO po);

	@ApiMethod(value="001002002",desc="按ID查询")
	public ApplyAuditStepPO findApplyAuditStepById(@ApiParam("id") Long id);

	@ApiMethod(value="001002003",desc="查询列表")
	public List<ApplyAuditStepPO> queryApplyAuditStep(ApplyAuditStepPO po);

	@ApiMethod(value="001002004",desc="按id删除")
	public int delApplyAuditStepById(@ApiParam("id")Long id);
	
	@ApiMethod(value="001002005",desc="按ids删除")
	public int delApplyAuditStepByIds(@ApiParam("ids")List<Long> ids);
	
}
