package com.syuesoft.st.daoimpl;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.MonthlyStatement;
import com.syuesoft.st.dao.MonthlyStatementDao;

@Repository("monthlyStatementDao")
public class MonthlyStatementDaoImpl extends BaseDaoImpl<MonthlyStatement> implements MonthlyStatementDao {

}
