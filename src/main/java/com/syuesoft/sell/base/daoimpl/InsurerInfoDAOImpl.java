package com.syuesoft.sell.base.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.InsurerInfoDAO;
import com.syuesoft.sell.model.XsInsurerInfo;

@Repository("insurerInfoDAO")
public class InsurerInfoDAOImpl extends BaseDaoImpl<XsInsurerInfo> implements InsurerInfoDAO{
	
}
