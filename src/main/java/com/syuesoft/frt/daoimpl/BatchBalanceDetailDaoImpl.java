package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.BatchBalanceDetailDao;
import com.syuesoft.model.BatchBalanceDetail;

/**
 * 批量结算明细dao
 */
@Repository("batchBalanceDetailDao")
public class BatchBalanceDetailDaoImpl extends BaseDaoImpl<BatchBalanceDetail>
        implements BatchBalanceDetailDao
{

}
