package com.syuesoft.sell.sellwork.service;

import com.syuesoft.fin.vo.Datagrid;

import com.syuesoft.sell.sellwork.vo.SellInvoiceVo;
import com.syuesoft.util.Msg;

public interface SellInvoiceService {
	public Datagrid findSellInfor(SellInvoiceVo sellInvoiceVo)throws Exception;

	public void saveSellInvoice(SellInvoiceVo sellInvoiceVo)throws Exception;

	public Msg deleteSellInvoice(SellInvoiceVo sellInvoiceVo)throws Exception;

	public Msg updateSellInvoice(SellInvoiceVo sellInvoiceVo)throws Exception;

	public void updateExamin(SellInvoiceVo sellInvoiceVo)throws Exception;
	public Boolean isRefundment(SellInvoiceVo sellInvoiceVo) throws Exception;
	
}
