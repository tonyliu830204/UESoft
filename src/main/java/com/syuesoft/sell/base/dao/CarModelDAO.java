package com.syuesoft.sell.base.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsCarModel;

public interface CarModelDAO extends BaseDaoI<XsCarModel> {
	public Integer findModelIdByName(String name) throws Exception;
}
