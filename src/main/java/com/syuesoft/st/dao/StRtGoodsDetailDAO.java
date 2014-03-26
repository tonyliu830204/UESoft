package com.syuesoft.st.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StRtGoodsDetail;
import com.syuesoft.st.vo.StRtGoodsVo;
import com.syuesoft.util.Json;

/**
 * 退货单明细dao接口
 * @author SuMing
 */
public interface StRtGoodsDetailDAO extends BaseDaoI<StRtGoodsDetail> {

	/** 退货单模块     根据入库单号获取入库单明细信息 */
	public List<StRtGoodsVo> srg_searchStRtGoodsDetailByStorageId(final String partsId,final String partsName,final String storageId,final String relcampId,final int  enterprise_id)throws Exception;
	
	/**退货单模块     根据入库单号获取入库单明细信息*/
	public List<StRtGoodsVo> srg_searchStRtGoodsDetailByStrtgmId(final String strtgmId,final String storageId)throws Exception;
	
	/**入退单查询模块   入退单信息预加载*/
	public Json loadStGoodsStorageRtGoodsSearchInfo(final String startTime,String endTime,final String pbrdName,final String fitPtype,final String partsId,final String partsName,final String storeId,final String enterprise_id)throws Exception;

	/** 入退单明细   通过入退时间获取*/
	public Json loadStGoodsStorageRtGoodsDetailSearchInfo(final String startTime,final String endTime,final String partsId,final String storeId)throws Exception;
}