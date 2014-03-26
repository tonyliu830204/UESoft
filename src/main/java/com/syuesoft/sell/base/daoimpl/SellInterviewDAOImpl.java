package com.syuesoft.sell.base.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.SellInterviewDAO;
import com.syuesoft.sell.model.XsSellInterview;

@Repository("sellInterviewDAO")
public class SellInterviewDAOImpl extends BaseDaoImpl<XsSellInterview> implements SellInterviewDAO{
	
}
