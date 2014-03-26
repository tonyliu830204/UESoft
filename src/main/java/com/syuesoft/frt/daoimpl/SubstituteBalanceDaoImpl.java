package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.SubstituteBalanceDao;
import com.syuesoft.model.SubstituteBalance;

/**
 * 代付收款结算单dao
 */
@Repository("substituteBalanceDao")
public class SubstituteBalanceDaoImpl extends BaseDaoImpl<SubstituteBalance>
        implements SubstituteBalanceDao
{

}
