package com.syuesoft.systemmanagement.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtParts;

public interface InventoryFindDao extends BaseDaoI<Object>{
	
	//查询配件档案 获取配件名称
	public List<FrtParts> getPartsName()throws Exception;
}
