package com.syuesoft.vipmanagement.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasVipInfor;
import com.syuesoft.model.BasVipRecharge;
import com.syuesoft.model.BasVipRechargeRegulation;

public interface VipRechargeDao extends BaseDaoI<BasVipRecharge>{
	public void add(BasVipRecharge bvr) throws Exception;                                //添加充值记录
	public BasVipInfor getByVipNumber(String vipNumber) throws Exception;                //根据会员卡号获取会员信息
	public BasVipRechargeRegulation getByRecAmount(Integer recAmount) throws Exception;  //根据充值金额获取会员充值规则
	public void deleteVipRecharge(BasVipRecharge bvr) throws Exception;  				 //删除充值记录
	public BasVipRecharge getById(String id) throws Exception;                           //根据充值编号获取充值信息
}
