package com.syuesoft.st.service;

import com.syuesoft.st.vo.StOutVo;
import com.syuesoft.util.Json;

/**
 * 出退单查询Service
 * @author SuMing
 */
public interface StOutStRtPartsSearchService {

	/**出退单查询模块   出退单信息预加载 */
	public Json loadStOutAndStRtPartsSearchInfo(StOutVo stOutVo)throws Exception;
	
	/** 出退单明细  根据出退单时间获取*/
    public Json loadStOutAndStRtPartsDetailSearchInfo(StOutVo stOutVo)throws Exception;
}
