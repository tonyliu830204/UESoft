package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StRtPartsMain;
import com.syuesoft.util.Json;

public interface StRtPartsMainDAO extends BaseDaoI<StRtPartsMain> {
	
	 /**工单退料汇总单信息     综合查询*/
	public Json searchByCondition(final int page, final int rows, final String sort,final String order,final String strtpmDateStart,final String strtpmDateqiEnd,final String receptionId,final String carLicense,final String strtpmId,final int enterpriseId)throws Exception;
	
	 /**销售退料汇总单信息     预加载*/
	public Json loadSellStRtPartsMain(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception;
	
	/**销售退料汇总单信息      综合查询*/
	public Json searchSellStRtPartsMainByCondition(final String strtpmDateStart,final String strtpmDateqiEnd,final String receptionId,final String carLicense,final String strtpmId ,final int enterprise_id)throws Exception;
	
}