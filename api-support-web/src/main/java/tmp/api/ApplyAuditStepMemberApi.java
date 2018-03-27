package tmp.api;

import tmp.po.ApplyAuditStepMemberPO;

import java.util.List;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.annotation.ApiService;

@ApiService("申请审核各步骤审核人")
public interface ApplyAuditStepMemberApi {

	@ApiMethod(value="001003001",desc="编辑")
	public ApplyAuditStepMemberPO editApplyAuditStepMember(ApplyAuditStepMemberPO po);

	@ApiMethod(value="001003002",desc="按ID查询")
	public ApplyAuditStepMemberPO findApplyAuditStepMemberById(@ApiParam("id") Long id);

	@ApiMethod(value="001003003",desc="查询列表")
	public List<ApplyAuditStepMemberPO> queryApplyAuditStepMember(ApplyAuditStepMemberPO po);

	@ApiMethod(value="001003004",desc="按id删除")
	public int delApplyAuditStepMemberById(@ApiParam("id")Long id);
	
	@ApiMethod(value="001003005",desc="按ids删除")
	public int delApplyAuditStepMemberByIds(@ApiParam("ids")List<Long> ids);
	
}
