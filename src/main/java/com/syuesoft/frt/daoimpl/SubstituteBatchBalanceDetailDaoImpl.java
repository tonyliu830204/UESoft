package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.SubstituteBatchBalanceDetailDao;
import com.syuesoft.model.SubstituteBatchBalanceDetail;

/**
 * 代付批量收款明细dao
 */
@Repository("substituteBatchBalanceDetailDao")
public class SubstituteBatchBalanceDetailDaoImpl extends
        BaseDaoImpl<SubstituteBatchBalanceDetail> implements
        SubstituteBatchBalanceDetailDao
{

}
