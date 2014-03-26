package com.syuesoft.sell.instore.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.instore.vo.SellInstorehouseVo;
import com.syuesoft.sell.instore.vo.SellRetreatVo;


public interface InstoreMoveService {
	public Datagrid getPager(SellRetreatVo instoreMoveVo)throws Exception;
	public void addInstore(SellRetreatVo  instoreMoveVo)throws Exception;
	public void deleteInstore(SellRetreatVo  instoreMoveVo)throws Exception;
	public Datagrid getPagerDel(SellRetreatVo instoreMoveVo) throws Exception;
	public void  updateExamine(SellRetreatVo instoreMoveVo) throws Exception;
	public void updateInstore(SellRetreatVo instoreMoveVo)throws Exception;
	public Datagrid getPagerCar(SellRetreatVo instoreMoveVo)throws Exception;
	public Boolean isRefundment(SellRetreatVo instoreMoveVo)throws Exception;//判断是否已审核
}
