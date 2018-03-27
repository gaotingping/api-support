package tmp.api.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.gtp.apisupport.common.CommonBiz;
import tmp.po.ApplyPO;
import tmp.api.ApplyApi;
import tmp.service.ApplyService;


@Component
public class ApplyApiImpl implements ApplyApi{

	@Autowired
	private ApplyService applyService;
	
	@Override
	public ApplyPO editApply(ApplyPO po) {
		
		Long id = po.getId();
		
		if(CommonBiz.isId(id)){
			applyService.updateApplyById(po);
		}else{
			applyService.insertApply(po);
		}
		
		return po;
	}

	@Override
	public ApplyPO findApplyById(Long id) {
		if(!CommonBiz.isId(id)){
			return null;
		}
		return applyService.getApplyById(id);
	}

	@Override
	public List<ApplyPO> queryApply(ApplyPO po) {
		return applyService.getApplyByPO(po);
	}

	@Override
	public int delApplyById(Long id) {
		return applyService.delApplyById(id);
	}

	@Override
	public int delApplyByIds(List<Long> ids) {
		return applyService.delApplyByIds(ids);
	}
	
}
