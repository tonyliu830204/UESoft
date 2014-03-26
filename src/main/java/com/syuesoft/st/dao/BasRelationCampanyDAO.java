package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasRelationCampany;
import com.syuesoft.util.Json;

/**
 * 供应商Dao
 * @author SuMing
 */
public interface BasRelationCampanyDAO extends BaseDaoI<BasRelationCampany> {
	
	
	/**供应商信息    预加载*/
	public Json loadBasRelationCampany(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception;
	
	/**供应商信息    综合查询*/
	public Json searchBasRelationCampanyByCondition(final String relcampId,final String relcampName,final int enterprise_id)throws Exception;  
	
	/**财务模块    供应商对账   入退单汇总信息加载 */
	public Json loadStRtGoods(String relcampName)throws Exception;
}