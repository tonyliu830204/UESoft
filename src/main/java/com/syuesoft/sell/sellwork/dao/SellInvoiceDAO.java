package com.syuesoft.sell.sellwork.dao;


import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.sellwork.vo.SellInvoiceVo;

public interface SellInvoiceDAO extends BaseDaoI<Object> {

	public List<Object> findExaminState(SellInvoiceVo sellInvoiceVo) throws Exception;

	
}
