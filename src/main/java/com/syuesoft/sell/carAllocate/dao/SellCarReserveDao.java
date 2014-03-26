package com.syuesoft.sell.carAllocate.dao;


import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.carAllocate.vo.SellCarReserveVo;
import com.syuesoft.sell.model.XsSellCarReserve;

public interface SellCarReserveDao extends BaseDaoI<Object>{
	//删除预定单信息
	public void deleteSellCarReserve(SellCarReserveVo sellCarReserveVo)throws Exception;
	//预定单交期提醒查询
	public Datagrid queryCarReserveExpire(SellCarReserveVo sellCarReserveVo)throws Exception;
		

}
