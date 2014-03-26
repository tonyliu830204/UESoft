package com.syuesoft.fin.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.fin.dao.StUsePerchargeDao;
import com.syuesoft.model.StUsePercharge;

/**
 * 维修应收款使用记录dao
 */
@Repository("stUsePerchargeDao")
public class StUsePerchargeDaoImpl extends BaseDaoImpl<StUsePercharge>
        implements StUsePerchargeDao
{

}
