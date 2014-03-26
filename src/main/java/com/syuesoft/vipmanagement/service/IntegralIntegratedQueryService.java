package com.syuesoft.vipmanagement.service;

import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.vo.IntegralIntegratedQueryVo;

public interface IntegralIntegratedQueryService {
	//积分汇总查询
	public Json getIntegralIntegratedQuery(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception;
	
	//维修积分查询
	public Json getMaintenancePointsInquiry(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception;
	
	//销售积分查询
	public Json getSellPointsInquiry(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers users) throws Exception;
	
	//储值赠分查询
	public Json getStoredValuePoints(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception;
	
	//赠送赠分查询
	public Json getPresentationPoints(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user) throws Exception;
	
	//积分兑换查询
    public Object getConvertPoints(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers users)throws Exception;
    
	//会员卡升级查询
	public Json getVipCardUpgrade(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception;
	
	//积分综合查询的续会赠分
	public Json getAdjournmentFind(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception;
	
	//退会扣分 查询
	public Json getExitMemberFind(IntegralIntegratedQueryVo integralIntegratedQueryVo, BasUsers user)throws Exception;
}