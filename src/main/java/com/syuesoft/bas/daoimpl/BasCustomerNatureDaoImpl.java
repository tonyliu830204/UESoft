package com.syuesoft.bas.daoimpl;

import java.util.List;
import com.syuesoft.bas.dao.BasCustomerNatureDao;
import com.syuesoft.model.BasCustomNature;

/**
 * 客户性质
 * 
 * @author ChangMing
 * 
 */
public class BasCustomerNatureDaoImpl extends BaseDaoImpl<BasCustomNature>
        implements BasCustomerNatureDao
{

    
    public boolean Add(BasCustomNature BasCustomNature)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomNature where natureName='"
                                + BasCustomNature.getNatureName() + "' and enterpriseId="+BasCustomNature.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .save(BasCustomNature);
            return false;
        }
    }

    
    public void Delete(BasCustomNature BasCustomNature)
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .delete(BasCustomNature);
    }

    
    public boolean Update(BasCustomNature BasCustomNature)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomNature where natureName='"
                                + BasCustomNature.getNatureName()
                                + "' and natureId not in ("
                                + BasCustomNature.getNatureId() + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .update(BasCustomNature);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    
    public List<BasCustomNature> findAll(int page, int rows, String sort,
            String order,final int enterprse_id)
    {
        String hql = "from BasCustomNature bcn where bcn.enterpriseId="+enterprse_id;
        if (sort != null && !sort.equals("") && order != null
                && !order.equals(""))
        {
            hql += " order by " + sort + " " + order + "";
        }
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql).setFirstResult(
                        (page - 1) * rows).setMaxResults(rows).list();
    }

    @SuppressWarnings("unchecked")
    
    public List<BasCustomNature> getTotle(BasCustomNature basCustomNature)
    {
        String hql = "from BasCustomNature bcn where bcn.enterpriseId="+basCustomNature.getEnterpriseId();
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(hql);
    }

}
