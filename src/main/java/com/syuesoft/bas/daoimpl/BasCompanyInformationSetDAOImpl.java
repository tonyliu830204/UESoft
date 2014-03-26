package com.syuesoft.bas.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCompanyInformationSetDAO;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.BasCompanyInformationSet;

/**
 * 基础资料->公司信息设定DAO
 * @author Liujian
 */
@Repository("basCompanyInformationSetDAO")
public class BasCompanyInformationSetDAOImpl extends
        BaseDaoImpl<BasCompanyInformationSet> implements
        BasCompanyInformationSetDAO{

    
    public void delete(String id){
        BasCompanyInformationSet cifs = this.getHibernateTemplate().get(BasCompanyInformationSet.class, id);
        this.getHibernateTemplate().delete(cifs);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasCompanyInformationSet> findAll(String param)
    {
    	String sql="from BasCompanyInformationSet cis where 1 = 1 " + param;
        return this.getHibernateTemplate().find(sql);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasCompanyInformationSet> findAll(final String param,
            final int page, final int rows){
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasCompanyInformationSet>>()
        {
            
            public List<BasCompanyInformationSet> doInHibernate(Session session)
                    throws HibernateException, SQLException{
                Query query = session.createQuery("from BasCompanyInformationSet cis where 1 = 1 "+ param).setFirstResult((page - 1) * rows)
                        .setMaxResults(rows);
                return query.list();
            }
        });
    }

    
    public void update(BasCompanyInformationSet cis)
    {
        this.getHibernateTemplate().merge(cis);
    }

    @SuppressWarnings("unchecked")
    public Object saveOrUpdate(final List<BasCompanyInformationSet> cisVos)
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
                    String SQL = "UPDATE bas_company_information_set SET CI_VALUE = ? WHERE CI_ID = ? AND CI_NAME = ? AND enterprise_id=?";
                    try
                    {
                        conn = extracted(session);
                        conn.setAutoCommit(false);
                        ps = conn.prepareStatement(SQL);
                        for (int count = 0; count < cisVos.size(); count++)
                        {
                            BasCompanyInformationSet enteyC = cisVos.get(count);
                            ps.setString(1, enteyC.getCiValue());
                            ps.setString(2, enteyC.getCiId());
                            ps.setString(3, enteyC.getCiName());
                            ps.setInt(4, enteyC.getEnterpriseId());
                            ps.addBatch();
                            if (count != 0&& (count % 20 == 0 || count == cisVos.size() - 1)){
                                ps.executeBatch();
                                try{
                                    conn.commit();
                                }catch(SQLException exc){
                                    conn.rollback();
                                }
                            }
                        }
                    }
                    catch(SQLException e){
                        if (conn != null)
                            try{
                                conn.close();
                            }catch(SQLException esql){
                                esql.printStackTrace();
                            }
                        e.printStackTrace();
                    }finally{
                        try{
                            if (ps != null)
                                ps.close();
                            if (conn != null)
                                conn.close();
                            session.close();
                        }catch(SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }
            private Connection extracted(Session session){
                return session.connection();
            }
        });

    }

    
    public BasCompanyInformationSet getBasCompanyInformationSet(String type,
            String name,int enterpriseId)
    {
        String param = "";
        if (type != null && name != null)
        {
            param += " and cis.ciType = '" + type + "' and cis.ciName = '"
                    + name + "' and enterpriseId="+enterpriseId;
        }
        List<BasCompanyInformationSet> list = this.findAll(param);
        
        return list != null&&list.size()>0 ? list.get(0) : null;
    }
    
    
    public BasCompanyInformationSet getBasCompanyInformationSet(String type,String name)
    {
        String param = "";
        if (type != null && name != null)
        {
            param += " and cis.ciType = '" + type + "' and cis.ciName = '"
                    + name + "' ";
        }
        List<BasCompanyInformationSet> list = this.findAll(param);
        return list != null &&list.size()>0? list.get(0) : null;
    }
    
    /**
	 * 维修价格保留小数
	 * basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SERVICEDECIMAL,   维修
	 * basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.COSTDECIMAL,      成本
	 * basCompanyInformationSetDAO.repairPersistFigure(PARAMETER_SET.INOUTDEPOT, INOUTDEPOT.SELLBASEPRICE,    销售
	 * 
	 */
	public String repairPersistFigure(String name,String type,String value){
		BasCompanyInformationSet bcinfo=this.getBasCompanyInformationSet(name, type);
		double b=Double.parseDouble(value);
		String format="0.00";
		if(bcinfo.getCiValue().equals("0")){
			format="0";
			double p= Math.pow(10,0); 
			b=Math.round(b*p)/p;
		}
		else if(bcinfo.getCiValue().equals("1")){
			format="0.0";
			double p= Math.pow(10,1); 
			b=Math.round(b*p)/p;
		}
		DecimalFormat df = new DecimalFormat(format); 
		return df.format(b);
	}
	
}