package com.syuesoft.sell.base.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.base.vo.RepayVo;
import com.syuesoft.sell.model.XsRepay;

public interface RepayDAO extends BaseDaoI<XsRepay>{
	public List<XsRepay> findAllRepay(Integer enterpriseId) throws Exception;
}
