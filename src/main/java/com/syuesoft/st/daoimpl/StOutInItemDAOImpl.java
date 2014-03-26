package com.syuesoft.st.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.StOutInItem;
import com.syuesoft.st.dao.StOutInItemDAO;

@Repository("stOutInItemDAO")
public class StOutInItemDAOImpl extends BaseDaoImpl<StOutInItem> implements
		StOutInItemDAO {

}
