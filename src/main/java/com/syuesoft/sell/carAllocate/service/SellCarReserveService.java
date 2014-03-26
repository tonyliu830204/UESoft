package com.syuesoft.sell.carAllocate.service;

import java.util.List;


import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.carAllocate.vo.SellCarReserveVo;

public interface SellCarReserveService {
	public Datagrid findSellCarReserve(SellCarReserveVo sellCarReserveVo)
			throws Exception;

	// 预定单交期提醒查询
	public Datagrid QueryCarReserveExpire(
			SellCarReserveVo sellCarReserveVo) throws Exception;
	// 车辆分配
	public void modifyReverd(SellCarReserveVo sellCarReserveVo) throws Exception;
	//取消分配时判断是否存在于销售单
	public Boolean isUse(SellCarReserveVo sellCarReserveVo) throws Exception;
	
}
