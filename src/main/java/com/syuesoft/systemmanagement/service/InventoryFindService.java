package com.syuesoft.systemmanagement.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FrtParts;
import com.syuesoft.systemmanagement.vo.InventoryFindVo;

public interface InventoryFindService {
	//配件盘点查询树形结构查询 父项
	public Datagrid getFatherInfor(InventoryFindVo inventoryFindVo)throws Exception;
	
	//配件盘点查询树形结构 通过配件编号查询 子项
	public List<InventoryFindVo> getChildInfor(InventoryFindVo inventoryFindVo)throws Exception;
	
	//查询配件档案 获取配件名称
	public List<FrtParts> getPartsName()throws Exception;
}
