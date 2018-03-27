package tmp.api;

import tmp.po.FlowRouteStepPO;

import java.util.List;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.annotation.ApiService;

@ApiService("流程步骤")
public interface FlowRouteStepApi {

	@ApiMethod(value="001006001",desc="编辑")
	public FlowRouteStepPO editFlowRouteStep(FlowRouteStepPO po);

	@ApiMethod(value="001006002",desc="按ID查询")
	public FlowRouteStepPO findFlowRouteStepById(@ApiParam("id") Long id);

	@ApiMethod(value="001006003",desc="查询列表")
	public List<FlowRouteStepPO> queryFlowRouteStep(FlowRouteStepPO po);

	@ApiMethod(value="001006004",desc="按id删除")
	public int delFlowRouteStepById(@ApiParam("id")Long id);
	
	@ApiMethod(value="001006005",desc="按ids删除")
	public int delFlowRouteStepByIds(@ApiParam("ids")List<Long> ids);
	
}
