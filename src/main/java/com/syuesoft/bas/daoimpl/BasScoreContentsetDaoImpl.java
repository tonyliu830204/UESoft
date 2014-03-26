package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasScoreContentsetDao;
import com.syuesoft.model.BasScoreContentset;

public class BasScoreContentsetDaoImpl extends BaseDaoImpl<BasScoreContentset>
        implements BasScoreContentsetDao
{

    
    public void Add(BasScoreContentset basScoreContentset)
    {
        this.getHibernateTemplate().save(basScoreContentset);
    }

    
    public void Delete(BasScoreContentset basScoreContentset)
    {
        this.getHibernateTemplate().delete(basScoreContentset);
    }

    
    public void Update(BasScoreContentset basScoreContentset)
    {
        this.getHibernateTemplate().update(basScoreContentset);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasScoreContentset> findAll(final int page, final int rows)
    {
        List<BasScoreContentset> list = this.getHibernateTemplate().execute(
                new HibernateCallback<List<BasScoreContentset>>()
                {

                    
                    public List<BasScoreContentset> doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasScoreContentset";
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
     * @see com.syuesoft.DataBase.Dao.BasScoreContentsetDao#findByCondition(int,
     * int, com.syuesoft.model.BasScoreContentset)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasScoreContentset> findByCondition(final int page,
            final int rows, final BasScoreContentset basScoreContentset)
    {

        List<BasScoreContentset> list = this.getHibernateTemplate()
                .execute(new HibernateCallback<List<BasScoreContentset>>()
                {

                    
                    public List<BasScoreContentset> doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasScoreContentset ";//
                        if (basScoreContentset.getScoreContentsetName() != null
                                && !basScoreContentset.getScoreContentsetName()
                                        .trim().equals(""))
                        {
                            queryString += "where scoreContentsetName like '%"
                                    + StringEscapeUtils.escapeSql(basScoreContentset
                                            .getScoreContentsetName().trim()) + "%'";
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

    
    public List<BasScoreContentset> getTotle()
    {
        String hql = "from BasScoreContentset";
        return this.getHibernateTemplate().find(hql);
    }
}