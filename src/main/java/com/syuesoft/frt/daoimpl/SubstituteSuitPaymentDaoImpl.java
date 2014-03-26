package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.SubstituteSuitPaymentDao;
import com.syuesoft.model.SubstituteSuitPayment;

/**
 * 代付应收款dao
 */
@Repository("substituteSuitPaymentDao")
public class SubstituteSuitPaymentDaoImpl extends
        BaseDaoImpl<SubstituteSuitPayment> implements SubstituteSuitPaymentDao
{

}
