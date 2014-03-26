package com.syuesoft.sell.sellwork.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.sellwork.vo.CarSellManageVo;
import com.syuesoft.util.Json;

public interface CarSellManageDao extends BaseDaoI<XsCarSellInfo>{
	
	//获取所有的预定单号
	public List getReserveCode(CarSellManageVo carSellManageVo)throws Exception;
	
	//通过预定单号查询 客户 车辆  财务信息
	public Json getInforById(CarSellManageVo carSellManageVo)throws Exception;
	
	//删除销售单汇总信息
	public void deleteSellInfor(CarSellManageVo carSellManageVo)throws Exception;
	
	
	//获取PDI检测内容(通过销售单号)
	public List getPdiCheck(CarSellManageVo carSellManageVo)throws Exception;
	
	//获取PDI检测内容
	public List getPdiCheck2(CarSellManageVo carSellManageVo)throws Exception;

	// 销售单查询模块，查询子菜单信息功能
	public List<CarSellManageVo> findChildList(CarSellManageVo carSellManageVo)	throws Exception;

	
}
