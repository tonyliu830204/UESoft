package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasVipServiceDao;
import com.syuesoft.model.BasVipService;

public class BasVipServiceDaoImpl extends BaseDaoImpl<BasVipService> implements
        BasVipServiceDao
{

    
    public void Add(BasVipService basVipService)
    {
        this.getHibernateTemplate().save(basVipService);
    }

    
    public void Delete(BasVipService basVipService)
    {
        this.getHibernateTemplate().delete(basVipService);
    }

    
    public void Update(BasVipService basVipService)
    {
        this.getHibernateTemplate().update(basVipService);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasVipService> findAll(final int page, final int rows)
    {
        List<BasVipService> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasVipService";
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

    
    public List<BasVipService> getTotle()
    {
        String hql = "from BasVipService";
        return this.getHibernateTemplate().find(hql);
    }

}
