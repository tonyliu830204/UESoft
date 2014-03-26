package com.syuesoft.sell.sellwork.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.model.XsCarInfo;
import com.syuesoft.sell.sellwork.dao.CarFixDao;

@Repository("carFixDao")
public class CarFixDaoImpl extends BaseDaoImpl<Object> implements CarFixDao {

	/**
	 * 申请加装
	 */
	
	public void doApplyFix(XsCarInfo xsCarInfo) throws Exception {
		merge(xsCarInfo);
	}
}