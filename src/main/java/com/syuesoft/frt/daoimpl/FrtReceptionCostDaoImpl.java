package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtReceptionCostDao;
import com.syuesoft.model.FrtReceptionCost;

/**
 * 前台接车->其他费用明细dao
 * 
 * @author Liujian
 * 
 */
@Repository("frtReceptionCostDao")
public class FrtReceptionCostDaoImpl extends BaseDaoImpl<FrtReceptionCost>
        implements FrtReceptionCostDao
{

}
