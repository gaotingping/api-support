package tmp.po;

import java.util.Date;
import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.model.BasePge;

public class FlowRoutePO extends BasePge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("id")
	private Long id;

	@ApiDescribe("流程ID")
	private Long flowId;

	@ApiDescribe("路由条件")
	private String flowRoute;

	@ApiDescribe("0未删除 1已删除")
	private Integer isDel;


	public void setId(Long id) {
		this.id= id;
	}

	public void setFlowId(Long flowId) {
		this.flowId= flowId;
	}

	public void setFlowRoute(String flowRoute) {
		this.flowRoute= flowRoute;
	}

	public void setIsDel(Integer isDel) {
		this.isDel= isDel;
	}


	public Long getId() {
		return id;
	}

	public Long getFlowId() {
		return flowId;
	}

	public String getFlowRoute() {
		return flowRoute;
	}

	public Integer getIsDel() {
		return isDel;
	}


}
