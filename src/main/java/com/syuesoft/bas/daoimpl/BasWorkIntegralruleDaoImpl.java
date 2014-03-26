package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasWorkIntegralruleDao;
import com.syuesoft.model.BasWorkIntegralrule;

public class BasWorkIntegralruleDaoImpl extends
        BaseDaoImpl<BasWorkIntegralrule> implements BasWorkIntegralruleDao
{

    
    public void Add(BasWorkIntegralrule basWorkIntegralrule)
    {
        this.getHibernateTemplate().save(basWorkIntegralrule);
    }

    
    public void Delete(BasWorkIntegralrule basWorkIntegralrule)
    {
        this.getHibernateTemplate().delete(basWorkIntegralrule);
    }

    
    public void Update(BasWorkIntegralrule basWorkIntegralrule)
    {
        this.getHibernateTemplate().update(basWorkIntegralrule);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasWorkIntegralrule> findAll(final int page, final int rows)
    {
        List<BasWorkIntegralrule> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasWorkIntegralrule";
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

    @SuppressWarnings("unchecked")
    
    public List<BasWorkIntegralrule> findByCondition(final int page,
            final int rows, final BasWorkIntegralrule basWorkIntegralrule)
    {
        List<BasWorkIntegralrule> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasWorkIntegralrule ";//
                        if (basWorkIntegralrule.getWorkIntegralruleManner() != null
                                && !basWorkIntegralrule
                                        .getWorkIntegralruleManner().trim()
                                        .equals(""))
                        {
                            queryString += "where workIntegralruleManner like '%"
                                    + StringEscapeUtils.escapeSql(basWorkIntegralrule
                                            .getWorkIntegralruleManner().trim()) + "%'";
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

    
    public List<BasWorkIntegralrule> getTotle()
    {
        String hql = "from BasWorkIntegralrule";
        return this.getHibernateTemplate().find(hql);
    }

}
