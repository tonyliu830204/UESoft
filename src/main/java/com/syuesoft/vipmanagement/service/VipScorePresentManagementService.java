package com.syuesoft.vipmanagement.service;

import com.syuesoft.util.Msg;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.vo.VipScorePresentManagementVo;

public interface VipScorePresentManagementService {
	public Json getHzGiveIntegral(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)throws Exception;//获取赠送积分汇总信息
    public Json getBasVipGiveIntegralProject() throws Exception;//获取赠送项目信息
    public Msg addRelationTable(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)throws Exception;//将实际赠分 与 赠送积分表的id 和 赠送项目表的id 存入中间表中 bas_vip_relationship16
	public Json getGiveIntegralByVipId(VipScorePresentManagementVo vipScorePresentManagementVo)throws Exception;//通过vipid获取赠送项目及赠送积分
	public Msg updateRelationTable(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)throws Exception;//修改积分赠送项目积分
	public Msg delete(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)throws Exception; //删除中间表中的赠送积分记录编号
	public Msg updateShenhe(VipScorePresentManagementVo vipScorePresentManagementVo, BasUsers user)throws Exception;//将未审核的记录改为已审核
}