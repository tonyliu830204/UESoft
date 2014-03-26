package com.syuesoft.st.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StPreOutDetail;
import com.syuesoft.st.vo.StPreOutVo;

public interface StPreOutDetailDAO extends BaseDaoI<StPreOutDetail> {
	
	/**根据预出库单号加载相关预出库明细信息*/
	public List<StPreOutVo> searchStPreOutDetailByStpremId(final String stpremId)throws Exception;

}