package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StSellOrderitem;
import com.syuesoft.util.Json;
/**
 * 销售单明细dao接口
 * @author SuMing
 */
public interface StSellOrderitemDAO extends BaseDaoI<StSellOrderitem> {

	/**仓库模块：双击销售汇总单号获取相关销售明细信息，财务模块：销售结算单明细模块    通过销售汇总单单号获取销售单明细信息*/
	public Json loadStSellOrderItemBySellmmId(final String sellmmId)throws Exception;
	
	/**销售退料单模块，根据退料单号加载销售退料单明细信息*/
	public Json searchByCondition(final String sellmmId)throws Exception;
	
	/**销售退料模块   销售单信息预加载 */
	public Json loadSellOrderInfo(final int page, final int rows,final String sort, final String order,final int enterprise_id)throws Exception;
	
	/** 销售退料模块   销售单信息  综合查询*/
	public Json searchStSellOrderByCondition(final String sellmmId,final int enterprise_id)throws Exception;
	
	/**消退单查询模块   入退单信息预加载*/
	public Json loadStSellOrderStRtSellOrderSearchInfo(final String startTime,String endTime,final String pbrdId,final String ptypeId,final String partsId,final String partsName,final String storeId,final int enterprise_id)throws Exception;
	
}