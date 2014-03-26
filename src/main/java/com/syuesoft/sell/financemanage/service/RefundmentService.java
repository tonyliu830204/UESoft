package com.syuesoft.sell.financemanage.service;

import com.syuesoft.sell.financemanage.vo.RefundmentVo;
import com.syuesoft.util.Json;

public interface RefundmentService{
	//获取收款记录
	public Json getRefundmentInfo(RefundmentVo refundmentVo)throws Exception;
	
	//保存退款单
	public void saveRefundmentAmount(RefundmentVo refundmentVo)throws Exception;
	
	//修改退款单
	public void updateRefundmentAmount(RefundmentVo refundmentVo)throws Exception;
	
	//查找未退金额
	public String findNoBackMoney(RefundmentVo refundmentVo)throws Exception;
	
	//审核
	public void  updateExamine(RefundmentVo refundmentVo) throws Exception;
	
	//判断是否已审核
	public Boolean isRefundment(RefundmentVo refundmentVo) throws Exception;
}
