package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.BatchPaymentDetailDao;
import com.syuesoft.model.BatchPaymentDetail;

/**
 * 批量收款明细dao
 */
@Repository("batchPaymentDetailDao")
public class BatchPaymentDetailDaoImpl extends BaseDaoImpl<BatchPaymentDetail>
        implements BatchPaymentDetailDao
{

}
