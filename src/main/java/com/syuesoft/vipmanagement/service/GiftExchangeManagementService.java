package com.syuesoft.vipmanagement.service;

import java.util.List;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.vo.GiftExchangeManagementVo;

public interface GiftExchangeManagementService {
    //礼品兑换汇总信息查询
    public Json getGiftExchanJsongeInfo(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user)throws Exception;
    
    //礼品兑换管理 的 可兑换礼品查询
    public Json getExchangeable(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user) throws Exception;
    
    //礼品兑换 保存已选礼品记录
    public Message doAdd(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user)throws Exception;
    
    //通过兑换表的id 获取兑换明细信息
    public List getGiftExchangeDetail(GiftExchangeManagementVo giftExchangeManagementVo)throws Exception;
    
    //修改礼品兑换记录
    public Message doUpdate(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user)throws Exception;
    
    //删除操作：删除该兑换单 以及兑换单下的兑换明细信息
    public Message doDelete(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user)throws Exception;
    
	//礼品兑换审核
	public Message doAudit(GiftExchangeManagementVo giftExchangeManagementVo, BasUsers user)throws Exception;
}