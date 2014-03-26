package com.syuesoft.sell.instore.service;


import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.instore.vo.SellServicingVo;

public interface SellServicingService {
	public Datagrid getPager(SellServicingVo servicingVo)throws Exception;

	public Datagrid getPagerPro(SellServicingVo servicingVo) throws Exception;

	public void saveSellServicing(SellServicingVo servicingVo)throws Exception;

	public Object getPagerCar(SellServicingVo servicingVo)throws Exception;
}
