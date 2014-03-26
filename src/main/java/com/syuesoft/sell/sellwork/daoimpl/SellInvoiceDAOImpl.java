package com.syuesoft.sell.sellwork.daoimpl;


import java.util.List;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.model.XsSellInvoice;
import com.syuesoft.sell.sellwork.dao.SellInvoiceDAO;
import com.syuesoft.sell.sellwork.vo.SellInvoiceVo;

@Repository("sellInvoiceDAO")
public class SellInvoiceDAOImpl  extends BaseDaoImpl<Object> implements SellInvoiceDAO{

	
	public List<Object> findExaminState(SellInvoiceVo sellInvoiceVo)
			throws Exception {
		
		return this.find("from  XsSellInvoice  invoice  where invoice.invoiceCode='"+sellInvoiceVo.getInvoiceCode()+"'");
	}


}
