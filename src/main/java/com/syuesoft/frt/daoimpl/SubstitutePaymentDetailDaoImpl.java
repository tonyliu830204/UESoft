package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.SubstitutePaymentDetailDao;
import com.syuesoft.model.SubstitutePaymentDetail;

/**
 * 代付收款明细dao
 */
@Repository("substitutePaymentDetailDao")
public class SubstitutePaymentDetailDaoImpl extends
        BaseDaoImpl<SubstitutePaymentDetail> implements
        SubstitutePaymentDetailDao
{

}
