package com.syuesoft.print.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.PrintGroup;
import com.syuesoft.print.dao.PrintGroupDao;

@Repository("printGroupDaoImpl")
public class PrintGroupDaoImpl extends BaseDaoImpl<PrintGroup> implements
        PrintGroupDao
{

}