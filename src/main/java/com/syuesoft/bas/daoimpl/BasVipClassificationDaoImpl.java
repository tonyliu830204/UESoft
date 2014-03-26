package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasVipClassificationDao;
import com.syuesoft.model.BasVipClassification;

public class BasVipClassificationDaoImpl extends
        BaseDaoImpl<BasVipClassification> implements BasVipClassificationDao
{

    
    public void Add(BasVipClassification basVipClassification) throws Exception
    {
        this.getHibernateTemplate().save(basVipClassification);

    }

    
    public void Delete(BasVipClassification basVipClassification)
    {
        this.getHibernateTemplate().delete(basVipClassification);

    }

    
    public void Update(BasVipClassification basVipClassification)
    {
        this.getHibernateTemplate().update(basVipClassification);

    }

    /*
     * (non-Javadoc) 查询所有的方法
     * 
     * @see com.syuesoft.DataBase.Dao.BasVipClassificationDao#findAll(int, int)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasVipClassification> findAll(final int page, final int rows)
    {
        List<BasVipClassification> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVipClassification";
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
     * com.syuesoft.DataBase.Dao.BasVipClassificationDao#findByCondition(int,
     * int, com.syuesoft.model.BasVipClassification)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasVipClassification> findByCondition(final int page,
            final int rows, final BasVipClassification basVipClassification)
    {
        List<BasVipClassification> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVipClassification ";//
                        if (basVipClassification.getVipClassificationName() != null
                                && !basVipClassification
                                        .getVipClassificationName().trim()
                                        .equals(""))
                        {
                            queryString += "where workIntegralruleManner like '%"
                                    + StringEscapeUtils.escapeSql(basVipClassification
                                            .getVipClassificationName().trim()) + "%'";
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

    
    public List<BasVipClassification> getTotle()
    {
        String hql = "from BasVipClassification";
        return this.getHibernateTemplate().find(hql);
    }

}
