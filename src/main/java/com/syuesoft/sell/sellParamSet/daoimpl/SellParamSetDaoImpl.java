package com.syuesoft.sell.sellParamSet.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.sell.model.XsSellParameter;
import com.syuesoft.sell.sellParamSet.dao.SellParamSetDao;

@Repository("SellParamSetDao")
public class SellParamSetDaoImpl extends BaseDaoImpl<XsSellParameter> implements SellParamSetDao{
	
	@SuppressWarnings("unchecked")
	
	public List<XsSellParameter> findAll(String param) {
		
		return this.getHibernateTemplate().find("from XsSellParameter cis where 1 = 1 " + param);
	}
	@SuppressWarnings("unchecked")
	 public Object saveOrUpdate(final List<XsSellParameter> cisVos)
	    {
	        return this.getHibernateTemplate().execute(new HibernateCallback()
	        {
	            public Object doInHibernate(Session session)
	                    throws HibernateException, SQLException
	            {
	                if (cisVos != null && cisVos.size() > 0)
	                {
	                    Connection conn = null;
	                    PreparedStatement ps = null;
	                    String SQL = "UPDATE xs_sell_parameter SET CI_VALUE = ? WHERE CI_ID = ? AND CI_NAME = ?";
	                    try
	                    {
	                        conn = extracted(session);
	                        conn.setAutoCommit(false);
	                        ps = conn.prepareStatement(SQL);
	                        for (int count = 0; count < cisVos.size(); count++)
	                        {
	                        	XsSellParameter enteyC = cisVos.get(count);
	                            ps.setString(1, enteyC.getCiValue());
	                            ps.setString(2, enteyC.getCiId());
	                            ps.setString(3, enteyC.getCiName());
	                            ps.addBatch();
	                            if (count != 0
	                                    && (count % 20 == 0 || count == cisVos
	                                            .size() - 1))
	                            {
	                                ps.executeBatch();
	                                try
	                                {
	                                    conn.commit();
	                                }
	                                catch(SQLException exc)
	                                {
	                                    conn.rollback();
	                                }
	                            }
	                        }
	                    }
	                    catch(SQLException e)
	                    {
	                        if (conn != null)
	                            try
	                            {
	                                conn.close();
	                            }
	                            catch(SQLException esql)
	                            {
	                                esql.printStackTrace();
	                            }
	                        ;
	                        e.printStackTrace();
	                    } finally
	                    {
	                        try
	                        {
	                            if (ps != null)
	                                ps.close();
	                            if (conn != null)
	                                conn.close();
	                            session.close();
	                        }
	                        catch(SQLException e)
	                        {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	                return null;
	            }

	            private Connection extracted(Session session)
	            {
	                return session.connection();
	            }
	        });

	    }

}
