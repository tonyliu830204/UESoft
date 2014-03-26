package com.syuesoft.st.service;

import com.syuesoft.st.vo.StSellOrderStRtSellOrderSearchVo;
import com.syuesoft.util.Json;

public interface StSellOrderStRtSellOrderSearchService {

	/**消退单查询模块  消退单信息预加载*/
	public Json loadStSellOrderStRtSellOrderSearchInfo(StSellOrderStRtSellOrderSearchVo ssosVo)throws Exception;
}
