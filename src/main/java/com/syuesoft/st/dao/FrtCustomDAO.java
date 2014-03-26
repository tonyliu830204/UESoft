package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtCustom;
import com.syuesoft.util.Json;

public interface FrtCustomDAO extends BaseDaoI<FrtCustom> {

	 /**销售单汇总模块     客户信息   预加载*/
	public Json loadBasCustom(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception;
	
	 /**销售单汇总模块     客户信息   综合查询*/
	public Json searchBasCustomByCondition(final String customId,final String customName,final int enterprise_id)throws Exception;
	
}