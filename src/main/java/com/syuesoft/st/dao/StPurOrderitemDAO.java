package com.syuesoft.st.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StPurOrderitem;
import com.syuesoft.st.vo.StPurOrderVo;
public interface StPurOrderitemDAO extends BaseDaoI<StPurOrderitem> {
	
	/**(采购单模块, 入库单模块)  根据采购单单号获取采购明细信息*/
	public List<StPurOrderVo> searchStOrderItemByOrderId(final String orderId)  throws Exception;

}