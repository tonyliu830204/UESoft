package com.syuesoft.bas.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasPartsStateDao;
import com.syuesoft.model.BasPartsState;
@Repository("basPartsStateDao")
public class BasPartsStateDaoImpl extends BaseDaoImpl<BasPartsState> implements
        BasPartsStateDao
{

}