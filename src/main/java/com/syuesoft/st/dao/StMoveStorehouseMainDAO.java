package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StMoveStorehouseMain;
import com.syuesoft.util.Json;

public interface StMoveStorehouseMainDAO extends BaseDaoI<StMoveStorehouseMain>{

	/** 移仓调拨单汇总      综合查询 */
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String msdmDateStart,final String msdmDateEnd,final String msdmManager,final String msdmId,final String msdmRemark,final int enterpriseId)throws Exception;
	
	/**  移仓调拨单未审核汇总      预加载 */
	public Json loadUnExamineStMoveStorehouse(final int page, final int rows, final String sort,final String order,final int enterpriseId)throws Exception;
	
	/** 移仓调拨单汇总      综合查询 */
	public Json searchUnExamineByCondition(final String msdmDateStart,final String msdmDateEnd,final String msdmManager,final String msdmId,final String msdmRemark,final int enterpriseId)throws Exception;
}