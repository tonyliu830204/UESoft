package com.syuesoft.st.dao;

import com.syuesoft.util.Json;
/**
 * 前台接车配件表dao
 * @author Suming
 */
public interface FrtRcptPartsDAO {

	/**单退料模块  根据相对应工单获取工单对应计划用料信息*/
	public Json searchRecpPartsByReceptionId(final String receptionId)throws Exception;
	
	/**根据工单号加载计划用料信息*/
	public Json searchByReceptionId(final String receptionId,final int page, final int rows,final int enterpriceId)throws Exception;

}