package com.syuesoft.sell.base.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.base.vo.SellTargetVo;

public interface SellTargetDAO extends BaseDaoI<BaseBean> {
	public List<Object[]> findAllDept( Integer enterprise_id  ) throws Exception;
	public List<Object[]> findStuffByDept(Short deptId,Integer enterprise_id) throws Exception;
}
