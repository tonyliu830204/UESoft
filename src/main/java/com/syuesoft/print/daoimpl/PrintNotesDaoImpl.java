package com.syuesoft.print.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.PrintNotes;
import com.syuesoft.print.dao.PrintNotesDao;

@Repository("printNotesDaoImpl")
public class PrintNotesDaoImpl extends BaseDaoImpl<PrintNotes> implements
        PrintNotesDao
{

}