package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StRtGoodsMain;
import com.syuesoft.util.Json;

/**
 * 退货单dao接口
 * @author SuMing
 */
public interface StRtGoodsMainDAO extends BaseDaoI<StRtGoodsMain> {
	
	/**退货单汇总信息    综合查询*/
	public Json searchByCondition(final String sort,final String order,final int page, final int rows,final String strtgmDateStart,final String strtgmDateEnd,
			final String strtgmId,final String relcampName,final String storeId,final int enterpriseId)throws Exception;
	
}