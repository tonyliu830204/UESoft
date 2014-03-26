package com.syuesoft.sell.base.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.ZsProjectDAO;
import com.syuesoft.sell.model.XsZsProject;

@Repository("zsProjectDAO")
public class ZsProjectDAOImpl extends BaseDaoImpl<XsZsProject> implements ZsProjectDAO{
}
