package com.syuesoft.systemmanagement.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.StInventoryMain;
import com.syuesoft.systemmanagement.vo.AccesssoriesInventoryVo;

public interface AccesssoriesInventoryDao extends BaseDaoI<StInventoryMain>{
	
	//更新字表盘点配件信息
	public void	doUpdateChild(AccesssoriesInventoryVo accesssoriesInventoryVo)throws Exception;
	
	//更新字表盘点配件信息
	public void	doUpdateFather(AccesssoriesInventoryVo accesssoriesInventoryVo)throws Exception;
}
