package com.syuesoft.sell.instore.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.instore.vo.SellPurchaseVo;

public interface SellPurchaseService {
	public Datagrid getPager(SellPurchaseVo  purchaseVo)throws Exception;
	public void addSellPurchase(SellPurchaseVo  purchaseVo)throws Exception;
	public void deleteSellPurchase(SellPurchaseVo  purchaseVo)throws Exception;
	public void updateSellPurchase(SellPurchaseVo  purchaseVo)throws Exception;
}
