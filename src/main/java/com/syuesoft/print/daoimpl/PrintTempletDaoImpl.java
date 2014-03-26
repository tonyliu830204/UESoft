package com.syuesoft.print.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.PrintTemplet;
import com.syuesoft.print.dao.PrintTempletDao;

@Repository("printTempletDaoImpl")
public class PrintTempletDaoImpl extends BaseDaoImpl<PrintTemplet> implements
        PrintTempletDao
{

}