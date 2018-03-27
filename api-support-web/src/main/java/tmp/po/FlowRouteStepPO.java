package tmp.po;

import java.util.Date;
import com.gtp.apisupport.annotation.ApiDescribe;
import com.gtp.apisupport.model.BasePge;

public class FlowRouteStepPO extends BasePge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiDescribe("id")
	private Long id;

	@ApiDescribe("所属路由ID")
	private Long flowRouteId;

	@ApiDescribe("步骤编号")
	private Integer stepNum;

	@ApiDescribe("步骤参与者")
	private String stepPerformer;


	public void setId(Long id) {
		this.id= id;
	}

	public void setFlowRouteId(Long flowRouteId) {
		this.flowRouteId= flowRouteId;
	}

	public void setStepNum(Integer stepNum) {
		this.stepNum= stepNum;
	}

	public void setStepPerformer(String stepPerformer) {
		this.stepPerformer= stepPerformer;
	}


	public Long getId() {
		return id;
	}

	public Long getFlowRouteId() {
		return flowRouteId;
	}

	public Integer getStepNum() {
		return stepNum;
	}

	public String getStepPerformer() {
		return stepPerformer;
	}


}
