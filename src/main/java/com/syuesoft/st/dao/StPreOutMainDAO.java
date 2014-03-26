package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StPreOutMain;
import com.syuesoft.util.Json;


public interface StPreOutMainDAO extends BaseDaoI<StPreOutMain>{
	
	 /**预出库汇总信息      综合查询*/
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String stpremTimeStart,final String stpremTimeEnd,final String receptionId,final String stpremId,final String stpremFlg,final int enterpriseId)throws Exception;

}