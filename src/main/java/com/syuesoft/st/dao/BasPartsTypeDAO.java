package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasPartsType;
import com.syuesoft.util.Json;

/**
 * 配件型号DAO接口
 * @author SuMing
 */
public interface  BasPartsTypeDAO extends BaseDaoI<BasPartsType> {
	
	/**配件型号信息    预加载*/
	public Json loadPartsType(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception;

	/**配件型号信息    综合查询*/
	public Json searchPartsTypeByCondition(final String ptypeId,final String ptypeName,final int enterprise_id)throws Exception;
	
	/** 根据配件品牌获取配件型号*/
	
}