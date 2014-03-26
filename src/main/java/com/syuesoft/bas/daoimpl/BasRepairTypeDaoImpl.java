package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasRepairTypeDao;
import com.syuesoft.model.BasRepairType;

public class BasRepairTypeDaoImpl extends BaseDaoImpl<BasRepairType> implements
        BasRepairTypeDao
{

    @SuppressWarnings("deprecation")
	
    public boolean Add(BasRepairType basRepairType) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasRepairType where reptypName='"
                                + basRepairType.getReptypName() + "' and enterpriseId="+basRepairType.getEnterpriseId());
        if (list.size() > 0)
            return true;
        else
        {
            this.getHibernateTemplate().save(basRepairType);
            return false;
        }
    }

    
    public void Delete(BasRepairType basRepairType) throws Exception
    {
        this.getHibernateTemplate().delete(basRepairType);
    }

    @SuppressWarnings("deprecation")
	
    public boolean Update(BasRepairType basRepairType) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasRepairType where reptypName='"
                                + basRepairType.getReptypName()
                                + "' and reptypId not in("
                                + basRepairType.getReptypId() + ")  and  enterpriseId="+basRepairType.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(basRepairType);
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    
    public List<BasRepairType> findAll(final int page, final int rows,
            final String sort, final String order,final int enterpriseId) throws Exception
    {
        List<BasRepairType> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasRepairType bcb where bcb.enterpriseId="+enterpriseId;
                        if (sort != null && !sort.equals("") && order != null
                                && !order.equals("")){
                            queryString += " order by " + sort + " " + order+ "";
                        }
                        org.hibernate.Query query = session.createQuery(queryString);
                        HttpSession sesion = ServletActionContext.getRequest().getSession();
                        sesion.setAttribute("totlesize", query.list().size());
                        int beginRow = (page - 1) * rows;
                        query.setFirstResult(beginRow);
                        query.setMaxResults(rows);
                        return query.list();
                    }

                });
        return list;
    }

    @SuppressWarnings("unchecked")
    
    public List<BasRepairType> findByCondition(final int page, final int rows,
            final BasRepairType basRepairType) throws Exception
    {

        List<BasRepairType> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasRepairType bcb where bcb.enterpriseId="+basRepairType.getEnterpriseId();
                        if (basRepairType.getReptypName() != null
                                && !basRepairType.getReptypName().trim()
                                        .equals(""))
                        {
                            queryString += "and reptypName like '%"
                                    + StringEscapeUtils.escapeSql(basRepairType.getReptypName().trim()) + "%'";
                        }
                        org.hibernate.Query query = session
                                .createQuery(queryString);
                        int totlesize = query.list().size();
                        HttpSession sesion = ServletActionContext.getRequest()
                                .getSession();
                        sesion.setAttribute("totlesize", totlesize);
                        int beginRow = (page - 1) * rows;
                        query.setFirstResult(beginRow);
                        query.setMaxResults(rows);
                        return query.list();
                    }

                });
        return list;
    }
}