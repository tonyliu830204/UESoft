package com.syuesoft.st.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StMoveStorehouseDetail;
import com.syuesoft.st.vo.StMoveStorehouseVo;

public interface StMoveStorehouseDetailDAO extends BaseDaoI<StMoveStorehouseDetail>{

	/**通过移仓单单号    加载移仓单明细信息*/
	public List<StMoveStorehouseVo> searchStMoveStorehouseDetailByMsdmId(String msdmId)throws Exception;
}