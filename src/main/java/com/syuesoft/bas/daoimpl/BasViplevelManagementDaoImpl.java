package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasViplevelManagementDao;
import com.syuesoft.model.BasViplevelManagement;

public class BasViplevelManagementDaoImpl extends
        BaseDaoImpl<BasViplevelManagement> implements BasViplevelManagementDao
{

    
    public void Add(BasViplevelManagement basViplevelManagement)
    {
        this.getHibernateTemplate().save(basViplevelManagement);
    }

    
    public void Delete(BasViplevelManagement basViplevelManagement)
    {
        this.getHibernateTemplate().delete(basViplevelManagement);
    }

    
    public void Update(BasViplevelManagement basViplevelManagement)
    {
        this.getHibernateTemplate().update(basViplevelManagement);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasViplevelManagement> findAll(final int page, final int rows)
    {
        List<BasViplevelManagement> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasViplevelManagement";
                        org.hibernate.Query query = session
                                .createQuery(queryString);
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
     * @see
     * com.syuesoft.DataBase.Dao.BasViplevelManagementDao#findByCondition(int,
     * int, com.syuesoft.model.BasViplevelManagement)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasViplevelManagement> findByCondition(final int page,
            final int rows, final BasViplevelManagement basViplevelManagement)
    {

        List<BasViplevelManagement> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasViplevelManagement ";//
                        if (basViplevelManagement.getViptypeName() != null
                                && !basViplevelManagement.getViptypeName()
                                        .trim().equals(""))
                        {
                            queryString += "where workIntegralruleManner like '%"
                                    + StringEscapeUtils.escapeSql(basViplevelManagement.getViptypeName().trim())
                                    + "%'";
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

    
    public List<BasViplevelManagement> getTotle()
    {
        String hql = "from BasViplevelManagement";
        return this.getHibernateTemplate().find(hql);
    }
}