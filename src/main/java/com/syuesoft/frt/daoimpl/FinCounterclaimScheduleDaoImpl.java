package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FinCounterclaimScheduleDao;
import com.syuesoft.model.FinCounterclaimSchedule;

/**
 * 索赔收款
 * */
@Repository("finCounterclaimScheduleDao")
public class FinCounterclaimScheduleDaoImpl extends
        BaseDaoImpl<FinCounterclaimSchedule> implements
        FinCounterclaimScheduleDao
{

}
