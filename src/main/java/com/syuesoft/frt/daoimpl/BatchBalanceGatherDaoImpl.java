package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.BatchBalanceGatherDao;
import com.syuesoft.model.BatchBalanceGather;

/**
 * 批量结算汇总dao
 */
@Repository("batchBalanceGatherDao")
public class BatchBalanceGatherDaoImpl extends BaseDaoImpl<BatchBalanceGather>
        implements BatchBalanceGatherDao
{

}
