package com.syuesoft.sell.util.dao;

import com.syuesoft.bas.dao.BaseDaoI;


public interface SellAccountDao extends BaseDaoI<Object>{
	
	public void saveSellAccount(Integer xsCarSelId, String accountTypeId,Integer accountType, Double accountMoney,String remark,Integer enterpriseId) throws Exception; 

}
