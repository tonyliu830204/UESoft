package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasVipworkDiscountDao;
import com.syuesoft.model.BasVipworkDiscount;

public class BasVipworkDiscountDaoImpl extends BaseDaoImpl<BasVipworkDiscount>
        implements BasVipworkDiscountDao
{

    
    public void Add(BasVipworkDiscount basVipworkDiscount)
    {
        this.getHibernateTemplate().save(basVipworkDiscount);
    }

    
    public void Delete(BasVipworkDiscount basVipworkDiscount)
    {
        this.getHibernateTemplate().delete(basVipworkDiscount);
    }

    
    public void Update(BasVipworkDiscount basVipworkDiscount)
    {
        this.getHibernateTemplate().update(basVipworkDiscount);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasVipworkDiscount> findAll(final int page, final int rows)
    {
        List<BasVipworkDiscount> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVipworkDiscount";
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
     * @see com.syuesoft.DataBase.Dao.BasVipworkDiscountDao#findByCondition(int,
     * int, com.syuesoft.model.BasVipworkDiscount)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasVipworkDiscount> findByCondition(final int page,
            final int rows, final BasVipworkDiscount basVipworkDiscount)
    {

        List<BasVipworkDiscount> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVipworkDiscount ";//
                        if (basVipworkDiscount.getDiscountId() != null)
                        {
                            queryString += "where discountId ="
                                    + basVipworkDiscount.getDiscountId();
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

    
    public List<BasVipworkDiscount> getTotle()
    {
        String hql = "from BasVipworkDiscount";
        return this.getHibernateTemplate().find(hql);
    }

}
