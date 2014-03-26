package com.syuesoft.sell.instore.service;

import com.syuesoft.sell.instore.vo.SellMonthlyStatementVo;
import com.syuesoft.util.Json;

public interface SellMonthlyStatementService{
	//获取库存信息
	public Json getStock(SellMonthlyStatementVo vonthlyStatementVo)throws Exception; 
	
	//月结
	public boolean doStock(SellMonthlyStatementVo vonthlyStatementVo)throws Exception;  
	
	//反月结
	public boolean doReturnStock(SellMonthlyStatementVo sellMonthlyStatementVo)throws Exception;  
}
