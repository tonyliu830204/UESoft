package com.syuesoft.sell.sellwork.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.model.XsSellLoan;
import com.syuesoft.sell.sellwork.dao.XsSellLoanDao;
/**
 *  购车贷款Dao
 * */
@Repository("xsSellLoanDao")
public class XsSellLoanDaoImpl extends BaseDaoImpl<XsSellLoan> implements
		XsSellLoanDao {

}
