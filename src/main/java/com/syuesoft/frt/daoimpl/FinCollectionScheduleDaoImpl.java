package com.syuesoft.frt.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FinCollectionScheduleDao;
import com.syuesoft.model.FinCollectionSchedule;

@Repository("finCollectionScheduleDao")
public class FinCollectionScheduleDaoImpl extends
        BaseDaoImpl<FinCollectionSchedule> implements FinCollectionScheduleDao
{
}
