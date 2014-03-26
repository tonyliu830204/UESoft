package com.syuesoft.sell.sellwork.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsCarInfo;

public interface CarFixDao extends BaseDaoI<Object>{
	
	//申请加装
	public void doApplyFix(XsCarInfo xsCarInfo)throws Exception;
}
