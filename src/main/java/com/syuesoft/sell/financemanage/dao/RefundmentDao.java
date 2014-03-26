package com.syuesoft.sell.financemanage.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.financemanage.vo.RefundmentVo;
import com.syuesoft.sell.model.XsBackCarLog;
import com.syuesoft.util.Json;

public interface RefundmentDao extends BaseDaoI<XsBackCarLog>{
	//保存退款记录
	public void saveRefundmentAmount(RefundmentVo refundmentVo)throws Exception;
	//保存退款记录
	public String findNoBackMoney(RefundmentVo refundmentVo)throws Exception;
	//刷新缓存
	public void flush()throws Exception;
}
