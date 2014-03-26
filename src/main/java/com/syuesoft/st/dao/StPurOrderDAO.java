package com.syuesoft.st.dao;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StPurOrder;
import com.syuesoft.util.Json;

public interface StPurOrderDAO extends BaseDaoI<StPurOrder> {
	
	
	/**采购单模块    预加载*/
	public Json loadStPurOrder(final String sort,final String order,final int rows,final int page,final String examine,final String state,final int enterpriseId)throws Exception;
	
	/**采购单模块  采购单汇总综合查询*/
	public Json searchByCondition(final String orderDateStart,final String orderDateEnd,final String deliveryDateStart,final String deliveryDateEnd, final String orderId,final String relcampName,final String transitToState
			,final String storageId,final String st_classification,final String st_examine,final String st_paid,final String state,final int enterpriseId)throws Exception;

}