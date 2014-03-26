package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasClaimsTypeDao;
import com.syuesoft.model.BasClaimsType;

public class BasClaimsTypeDaoImpl extends BaseDaoImpl<BasClaimsType> implements
        BasClaimsTypeDao
{

    
    public boolean Add(BasClaimsType basClaimsType) throws Exception
    {

        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasClaimsType where claimsName='"
                                + basClaimsType.getClaimsName() + "' and enterpriseId="+basClaimsType.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().save(basClaimsType);
            return false;
        }
    }

    
    public void Delete(BasClaimsType basClaimsType) throws Exception
    {
        this.getHibernateTemplate().delete(basClaimsType);
    }

    
    public boolean Update(BasClaimsType basClaimsType) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasClaimsType where claimsName='"
                                + basClaimsType.getClaimsName()
                                + "' and claimsId not in ("
                                + basClaimsType.getClaimsId() + ")  and  enterpriseId="+basClaimsType.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(basClaimsType);
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    
    public List<BasClaimsType> findAll(final int page, final int rows,
            final String sort, final String order,final int enterpriseId) throws Exception
    {
        List<BasClaimsType> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasClaimsType bcb where bcb.enterpriseId="+enterpriseId;
                        if (sort != null && !sort.equals("") && order != null
                                && !order.equals("")){
                            queryString += " order by " + sort + " " + order+ "";
                        }
                        org.hibernate.Query query = session
                                .createQuery(queryString);
                        HttpSession sesion = ServletActionContext.getRequest()
                                .getSession();
                        sesion.setAttribute("size", query.list().size());
                        int beginRow = (page - 1) * rows;
                        query.setFirstResult(beginRow);
                        query.setMaxResults(rows);
                        return query.list();
                    }

                });
        return list;
    }

    /*
     * (non-Javadoc) 条件查询
     * 
     * @see com.syuesoft.DataBase.Dao.BasClaimsTypeDao#findByCondition(int, int,
     * com.syuesoft.model.BasClaimsType)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasClaimsType> findByCondition(final int page, final int rows,
            final BasClaimsType basClaimsType) throws Exception
    {

        List<BasClaimsType> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasClaimsType bcb where bcb.enterpriseId="+basClaimsType.getEnterpriseId();
                        if (basClaimsType.getClaimsName() != null
                                && !basClaimsType.getClaimsName().trim()
                                        .equals(""))
                        {
                            queryString += "and claimsName like '%"
                                    + StringEscapeUtils.escapeSql(basClaimsType.getClaimsName().trim()) + "%'";
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
