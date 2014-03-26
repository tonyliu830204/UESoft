package com.syuesoft.frt.daoimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FinClaimantMainDao;
import com.syuesoft.model.FinClaimantMain;

/**
 * 索理赔应收款dao
 * 
 * @author Liujian
 * 
 */
@Repository("finClaimantMainDao")
public class FinClaimantMainDaoImpl extends BaseDaoImpl<FinClaimantMain>
        implements FinClaimantMainDao
{

    
    public List isExist(String receptionId)
    {
        return null;
    }

    
    public void flushCache() throws Exception
    {
        this.getSession().flush();
    }

	
	public Integer executeSQL(String sql) throws Exception {
		SQLQuery query=this.getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}
    
}
