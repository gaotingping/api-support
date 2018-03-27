package tmp.api;

import tmp.po.ApplyPO;

import java.util.List;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.annotation.ApiService;

@ApiService("请假申请")
public interface ApplyApi {

	@ApiMethod(value="001001001",desc="编辑")
	public ApplyPO editApply(ApplyPO po);

	@ApiMethod(value="001001002",desc="按ID查询")
	public ApplyPO findApplyById(@ApiParam("id") Long id);

	@ApiMethod(value="001001003",desc="查询列表")
	public List<ApplyPO> queryApply(ApplyPO po);

	@ApiMethod(value="001001004",desc="按id删除")
	public int delApplyById(@ApiParam("id")Long id);
	
	@ApiMethod(value="001001005",desc="按ids删除")
	public int delApplyByIds(@ApiParam("ids")List<Long> ids);
	
}
