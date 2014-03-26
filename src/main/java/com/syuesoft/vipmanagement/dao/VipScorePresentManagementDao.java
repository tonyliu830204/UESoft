package com.syuesoft.vipmanagement.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasVipGiveIntegral;
import com.syuesoft.vipmanagement.vo.VipScorePresentManagementVo;

public interface VipScorePresentManagementDao extends BaseDaoI<BasVipGiveIntegral>{
	public List<Object[]> getGiveIntegralByVipId(VipScorePresentManagementVo vipScorePresentManagementVo)throws Exception;//获取赠送项目及赠送积分
}