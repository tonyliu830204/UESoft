package com.syuesoft.vipmanagement.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasVipGiveIntegral;
import com.syuesoft.vipmanagement.dao.VipScorePresentManagementDao;
import com.syuesoft.vipmanagement.vo.VipScorePresentManagementVo;

@Repository("vipScorePresentManagementDao")
public class VipScorePresentManagementDaoImpl extends BaseDaoImpl<BasVipGiveIntegral> implements
		VipScorePresentManagementDao {
	
	/**
	 * 
	 * 通过赠送单号giveInteId获取赠送项目及赠送积分,
	 * 此赠送项目和积分是与车辆一一对应的，双击某一条datagrid数据时通过车辆id或会员id获得
	 */
	public List<Object[]> getGiveIntegralByVipId(VipScorePresentManagementVo vipScorePresentManagementVo)throws Exception {
	    String sql ="SELECT * FROM GET_PRISENT_PROJECT A WHERE A.GIVE_INTE_ID="+vipScorePresentManagementVo.getGive_Inte_Id()+"";
	    List<Object[]> list = this.createSQLQuery(sql);
		return list;
	}
}
