package tmp.api;

import tmp.po.FlowRoutePO;

import java.util.List;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.annotation.ApiService;

@ApiService("流程路由")
public interface FlowRouteApi {

	@ApiMethod(value="001005001",desc="编辑")
	public FlowRoutePO editFlowRoute(FlowRoutePO po);

	@ApiMethod(value="001005002",desc="按ID查询")
	public FlowRoutePO findFlowRouteById(@ApiParam("id") Long id);

	@ApiMethod(value="001005003",desc="查询列表")
	public List<FlowRoutePO> queryFlowRoute(FlowRoutePO po);

	@ApiMethod(value="001005004",desc="按id删除")
	public int delFlowRouteById(@ApiParam("id")Long id);
	
	@ApiMethod(value="001005005",desc="按ids删除")
	public int delFlowRouteByIds(@ApiParam("ids")List<Long> ids);
	
}
