package com.syuesoft.sell.financemanage.service;

import java.io.Serializable;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.financemanage.vo.SellReceiptVo;


public interface SellReceiptService {

	public Datagrid getPager(SellReceiptVo sellReceiptVo) throws Exception;

	public Serializable addReceipt(SellReceiptVo sellReceiptVo)throws Exception;

	public void deleteReceipt(SellReceiptVo sellReceiptVo)throws Exception;

	public void updateReceipt(SellReceiptVo sellReceiptVo)throws Exception;
	
	public  Boolean  findReceiptById(SellReceiptVo sellReceiptVo)throws Exception;

}

