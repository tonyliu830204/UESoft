package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasVistContentDao;
import com.syuesoft.model.BasVistContent;

public class BasVistContentDaoImpl extends BaseDaoImpl<BasVistContent>
        implements BasVistContentDao
{

    
    public void Add(BasVistContent basVistContent)
    {
        this.getHibernateTemplate().save(basVistContent);
    }

    
    public void Delete(BasVistContent basVistContent)
    {
        this.getHibernateTemplate().delete(basVistContent);
    }

    
    public void Update(BasVistContent basVistContent)
    {
        this.getHibernateTemplate().update(basVistContent);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasVistContent> findAll(final int page, final int rows)
    {
        List<BasVistContent> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVistContent";
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
     * @see com.syuesoft.DataBase.Dao.BasVistContentDao#findByCondition(int,
     * int, com.syuesoft.model.BasVistContent)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasVistContent> findByCondition(final int page, final int rows,
            final BasVistContent basVistContent)
    {

        List<BasVistContent> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVistContent ";//
                        if (basVistContent.getVistName() != null
                                && !basVistContent.getVistName().trim().equals(
                                        ""))
                        {
                            queryString += "where wordName like '%"
                                    + StringEscapeUtils.escapeSql(basVistContent.getVistName().trim()) + "%'";
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

    
    public List<BasVistContent> getTotle()
    {
        String hql = "from BasVistContent";
        return this.getHibernateTemplate().find(hql);
    }

}
