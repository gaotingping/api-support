package tmp.api;

import tmp.po.FlowPO;

import java.util.List;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiParam;
import com.gtp.apisupport.annotation.ApiService;

@ApiService("流程")
public interface FlowApi {

	@ApiMethod(value="001004001",desc="编辑")
	public FlowPO editFlow(FlowPO po);

	@ApiMethod(value="001004002",desc="按ID查询")
	public FlowPO findFlowById(@ApiParam("id") Long id);

	@ApiMethod(value="001004003",desc="查询列表")
	public List<FlowPO> queryFlow(FlowPO po);

	@ApiMethod(value="001004004",desc="按id删除")
	public int delFlowById(@ApiParam("id")Long id);
	
	@ApiMethod(value="001004005",desc="按ids删除")
	public int delFlowByIds(@ApiParam("ids")List<Long> ids);
	
}
