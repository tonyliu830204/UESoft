package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasSumscoreRegulationDao;
import com.syuesoft.model.BasSumscoreRegulation;

public class BasSumscoreRegulationDaoImpl extends
        BaseDaoImpl<BasSumscoreRegulation> implements BasSumscoreRegulationDao
{

    
    public void Add(BasSumscoreRegulation basSumscoreRegulation)
    {
        this.getHibernateTemplate().save(basSumscoreRegulation);
    }

    
    public void Delete(BasSumscoreRegulation basSumscoreRegulation)
    {
        this.getHibernateTemplate().delete(basSumscoreRegulation);
    }

    
    public void Update(BasSumscoreRegulation basSumscoreRegulation)
    {
        this.getHibernateTemplate().update(basSumscoreRegulation);
    }

    /*
     * (non-Javadoc) 查询方法
     * 
     * @see com.syuesoft.DataBase.Dao.BasSumscoreRegulationDao#findAll(int, int)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasSumscoreRegulation> findAll(final int page, final int rows)
    {
        List<BasSumscoreRegulation> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasSumscoreRegulation";
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
     * (non-Javadoc) 通过条件查询
     * 
     * @see
     * com.syuesoft.DataBase.Dao.BasSumscoreRegulationDao#findByCondition(int,
     * int, com.syuesoft.model.BasSumscoreRegulation)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasSumscoreRegulation> findByCondition(final int page,
            final int rows, final BasSumscoreRegulation basSumscoreRegulation)
    {
        List<BasSumscoreRegulation> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasSumscoreRegulation ";//
                        if (basSumscoreRegulation.getSumscoreRegulationManner() != null
                                && !basSumscoreRegulation
                                        .getSumscoreRegulationManner().trim()
                                        .equals(""))
                        {
                            queryString += "where sumscoreRegulationManner like '%"
                                    + StringEscapeUtils.escapeSql(basSumscoreRegulation
                                            .getSumscoreRegulationManner().trim())
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

    /*
     * (non-Javadoc) 查询所有 (获取查询的总数)
     * 
     * @see com.syuesoft.DataBase.Dao.BasSumscoreRegulationDao#getTotle()
     */
    
    public List<BasSumscoreRegulation> getTotle()
    {
        String hql = "from BasSumscoreRegulation";
        return this.getHibernateTemplate().find(hql);
    }

}
