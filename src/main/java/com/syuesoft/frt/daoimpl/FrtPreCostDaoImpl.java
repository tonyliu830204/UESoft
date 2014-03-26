package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtPreCostDao;
import com.syuesoft.model.FrtPreClearingCost;

/**
 * 交车结算->费用情况清单
 * 
 * @author LiWeijun
 * 
 */
@Repository("frtPreCostDao")
public class FrtPreCostDaoImpl extends BaseDaoImpl<FrtPreClearingCost>
        implements FrtPreCostDao
{

}
