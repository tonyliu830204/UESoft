package com.syuesoft.sell.instore.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.sell.instore.vo.SellServicingVo;

public interface InstoreOutService {
	public Datagrid getPagerDel(SellRetreatVo retreatVo)throws Exception;
	public void deleteInstore(SellRetreatVo retreatVo)throws Exception;
	public void  updateExamine(SellRetreatVo retreatVo) throws Exception;
	public void updateInstore(SellRetreatVo retreatVo) throws Exception;
	public Datagrid getPager(SellRetreatVo retreatVo)throws Exception;
	public void saveInstore(SellRetreatVo retreatVo)throws Exception;
	public List<SellRetreatVo> findByCarId(Integer sellId)throws Exception;
	public Boolean isRefundment(SellRetreatVo sellRetreatVo)throws Exception;
}
