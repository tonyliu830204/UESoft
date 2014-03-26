package com.syuesoft.st.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StOutItem;
import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.Json;

/**
 * 出库单明细DAO 
 * @author SuMing
 */
public interface StOutItemDAO extends BaseDaoI<StOutItem> {

	/**根据入库单号根据出库单号预加载出库单明细*/
	public List<StOutVo> serachStOutItemByCondition(final String stomId)throws Exception;
	
	/** 出退单查询模块   出退单信息预加载 */
	public Json loadStOutAndStRtPartsSearchInfo(final String startTime,String endTime,final String pbrdId,final String typeId,final String partsId,final String partsName,
			final String storeId,final String claimsName,final String outItemRemark,final int enterpriseId)throws Exception;

	/** 出退单查询模块   出退单信息预加载 */
	public Json loadStOutAndStRtPartsDetailSearchInfo(final String startTime,String endTime,final String partsId,
			final String storeId,final int enterpriseId)throws Exception;
}