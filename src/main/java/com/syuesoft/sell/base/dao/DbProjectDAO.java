package com.syuesoft.sell.base.dao;


import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsDbProject;

public interface DbProjectDAO extends BaseDaoI<XsDbProject> {
	public List<Object[]> findById(Integer deptId);
}
