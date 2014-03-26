package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.syuesoft.bas.dao.BasVipmaterialDiscountDao;
import com.syuesoft.model.BasVipmaterialDiscount;

public class BasVipmaterialDiscountDaoImpl extends
        BaseDaoImpl<BasVipmaterialDiscount> implements
        BasVipmaterialDiscountDao
{

    
    public void Add(BasVipmaterialDiscount basVipmaterialDiscount)
    {
        this.getHibernateTemplate().save(basVipmaterialDiscount);
    }

    
    public void Delete(BasVipmaterialDiscount basVipmaterialDiscount)
    {
        this.getHibernateTemplate().delete(basVipmaterialDiscount);
    }

    
    public void Update(BasVipmaterialDiscount basVipmaterialDiscount)
    {
        this.getHibernateTemplate().update(basVipmaterialDiscount);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasVipmaterialDiscount> findAll(final int page, final int rows)
    {
        List<BasVipmaterialDiscount> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVipmaterialDiscount";
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
     * com.syuesoft.DataBase.Dao.BasVipmaterialDiscountDao#findByCondition(int,
     * int, com.syuesoft.model.BasVipmaterialDiscount)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasVipmaterialDiscount> findByCondition(final int page,
            final int rows, final BasVipmaterialDiscount basVipmaterialDiscount)
    {

        List<BasVipmaterialDiscount> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVipmaterialDiscount ";//
                        if (basVipmaterialDiscount.getDiscountId() != null
                                && !basVipmaterialDiscount.getDiscountId()
                                        .equals(""))
                        {
                            queryString += "where discountId = "
                                    + basVipmaterialDiscount.getDiscountId();
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

    
    public List<BasVipmaterialDiscount> getTotle()
    {
        String hql = "from BasVipmaterialDiscount";
        return this.getHibernateTemplate().find(hql);
    }
}