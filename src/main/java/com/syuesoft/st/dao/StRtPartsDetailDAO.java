package com.syuesoft.st.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StRtPartsDetail;
import com.syuesoft.st.vo.StRtPartsVo;

public interface StRtPartsDetailDAO extends BaseDaoI<StRtPartsDetail> {
	
	/**通过工单退料单汇总编号获取退料单明细数据*/
	public List<StRtPartsVo> searchRecptStRtPartsDetailByStrtpmId(final String strtpmId) throws Exception;
	
	/**通过销售退料单汇总编号获取退料单明细数据*/
	public List<StRtPartsVo> searchSellStRtPartsDetailByStrtpmId(final String strtpmId) throws Exception;
}