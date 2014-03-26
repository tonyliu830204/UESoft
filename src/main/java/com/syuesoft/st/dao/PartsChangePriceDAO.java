package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.PartsChangePrice;
import com.syuesoft.util.Json;

/**
 * 配件调价DAO接口
 * @author SuMing
 */
public interface PartsChangePriceDAO  extends BaseDaoI<PartsChangePrice>{
	
	/** 出库单管理          配件信息 按配件编号  ，名称查询   加载 */
	public Json searchByCondition(final String partsId,final String partsName,final String storeId,final int enterprise_id)throws Exception;
	

}