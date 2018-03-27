package tmp.api.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gtp.apisupport.common.CommonBiz;
import tmp.po.ApplyAuditStepPO;
import tmp.api.ApplyAuditStepApi;
import tmp.service.ApplyAuditStepService;


@Component
public class ApplyAuditStepApiImpl implements ApplyAuditStepApi{

	@Autowired
	private ApplyAuditStepService applyAuditStepService;
	
	@Override
	public ApplyAuditStepPO editApplyAuditStep(ApplyAuditStepPO po) {
		
		Long id = po.getId();
		
		if(CommonBiz.isId(id)){
			applyAuditStepService.updateApplyAuditStepById(po);
		}else{
			applyAuditStepService.insertApplyAuditStep(po);
		}
		
		return po;
	}

	@Override
	public ApplyAuditStepPO findApplyAuditStepById(Long id) {
		if(!CommonBiz.isId(id)){
			return null;
		}
		return applyAuditStepService.getApplyAuditStepById(id);
	}

	@Override
	public List<ApplyAuditStepPO> queryApplyAuditStep(ApplyAuditStepPO po) {
		return applyAuditStepService.getApplyAuditStepByPO(po);
	}

	@Override
	public int delApplyAuditStepById(Long id) {
		return applyAuditStepService.delApplyAuditStepById(id);
	}

	@Override
	public int delApplyAuditStepByIds(List<Long> ids) {
		return applyAuditStepService.delApplyAuditStepByIds(ids);
	}
	
}
