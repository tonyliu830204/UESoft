package com.syuesoft.st.service;

import com.syuesoft.st.vo.StGoodsStorageRtGoodsSearchVo;
import com.syuesoft.util.Json;

public interface StGoodsStorageRtGoodsSearchService{

	/**入退单查询模块   入退单信息预加载*/
	public Json loadStGoodsStorageRtGoodsSearchInfo(StGoodsStorageRtGoodsSearchVo sgsvo)throws Exception;
	
	/**入退单明细   通过入退时间获取*/
	public Json loadStGoodsStorageRtGoodsDetailSearchInfo(StGoodsStorageRtGoodsSearchVo sgsvo)throws Exception;
}
