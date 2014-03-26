package com.syuesoft.sell.base.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.base.vo.CustomLevaVo;
import com.syuesoft.sell.model.XsCustomLeva;

public interface CustomLevaDAO extends BaseDaoI<XsCustomLeva> {
	//查询所有潜在客户等级
	public List<XsCustomLeva> findAllLeva(CustomLevaVo leva1)throws Exception;
}
