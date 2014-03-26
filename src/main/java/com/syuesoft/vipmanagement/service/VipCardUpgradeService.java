package com.syuesoft.vipmanagement.service;

import com.syuesoft.util.Msg;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.vo.VipCardUpgradeVo;

public interface VipCardUpgradeService {
    //会员卡升级汇总查询
    public Json doVipUpCollectFind(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)throws Exception;
    
    //会员卡升级明细 查询方法
    public Json doFindVipUpInfo(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)throws Exception;
    
    //通过升级单号 查询 该升级单号下的所有 升级后会员记录
    public Json findInfoByVipUpId(VipCardUpgradeVo vipCardUpgradeVo)throws Exception;
    
    //会员卡升级 新增升级
    public Msg doAddCardUpgrade(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)throws Exception;
    
    //删除会员卡升级汇总信息
    public Msg doRemoveVipUp(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)throws Exception;
    
    //会员卡升级 修改未审核的升级信息
    public Msg doEditCardUpgrade(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)throws Exception;
    
	//会员升级 审核方法
	public Msg doAudit(VipCardUpgradeVo vipCardUpgradeVo, BasUsers user)throws Exception;
}