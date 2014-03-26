package com.syuesoft.frt.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtPartsDao;
import com.syuesoft.model.FrtParts;

/**
 * 前台配件查询dao
 * 
 * @author Liujian
 * 
 */
@Repository("frtPartsDao")
public class FrtPartsDaoImpl extends BaseDaoImpl<FrtParts> implements
        FrtPartsDao
{

    /**
     * 配件档案变更存储过程
     * 
     * @throws Exception
     */
    
    public boolean changePartsId(String partsId, String changedPartsId,int enterprise_Id)
            throws Exception
    {
        Session session = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        Connection conn = session.connection();
        CallableStatement call = conn
                .prepareCall("{CALL parts_archives_change(?,?,?)}");
        call.setString(1, partsId);
        call.setString(2, changedPartsId);
        call.setString(3, enterprise_Id+"");
        int i = call.executeUpdate();
        if (i == 0)
            return true;
        else
            return false;
    }

    
    public void batchChangePrice(String sql) throws Exception
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();
    }

}
