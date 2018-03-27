package tmp.api.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gtp.apisupport.common.CommonBiz;
import tmp.po.ApplyAuditStepMemberPO;
import tmp.api.ApplyAuditStepMemberApi;
import tmp.service.ApplyAuditStepMemberService;


@Component
public class ApplyAuditStepMemberApiImpl implements ApplyAuditStepMemberApi{

	@Autowired
	private ApplyAuditStepMemberService applyAuditStepMemberService;
	
	@Override
	public ApplyAuditStepMemberPO editApplyAuditStepMember(ApplyAuditStepMemberPO po) {
		
		Long id = po.getId();
		
		if(CommonBiz.isId(id)){
			applyAuditStepMemberService.updateApplyAuditStepMemberById(po);
		}else{
			applyAuditStepMemberService.insertApplyAuditStepMember(po);
		}
		
		return po;
	}

	@Override
	public ApplyAuditStepMemberPO findApplyAuditStepMemberById(Long id) {
		if(!CommonBiz.isId(id)){
			return null;
		}
		return applyAuditStepMemberService.getApplyAuditStepMemberById(id);
	}

	@Override
	public List<ApplyAuditStepMemberPO> queryApplyAuditStepMember(ApplyAuditStepMemberPO po) {
		return applyAuditStepMemberService.getApplyAuditStepMemberByPO(po);
	}

	@Override
	public int delApplyAuditStepMemberById(Long id) {
		return applyAuditStepMemberService.delApplyAuditStepMemberById(id);
	}

	@Override
	public int delApplyAuditStepMemberByIds(List<Long> ids) {
		return applyAuditStepMemberService.delApplyAuditStepMemberByIds(ids);
	}
	
}
