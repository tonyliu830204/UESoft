package com.syuesoft.bas.daoimpl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasCustomAreaDao;
import com.syuesoft.model.BasCustomArea;

/**
 * 客户区域dao
 * 
 * @author ChangMing
 * 
 */
@Repository("basCustomAreaDao")
public class BasCustomAreaDaoImpl extends BaseDaoImpl<BasCustomArea> implements
        BasCustomAreaDao
{

    
    public boolean Add(BasCustomArea basCustomArea)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomArea where pareaName='"
                                + basCustomArea.getPareaName() + "' and enterpriseId="+basCustomArea.getEnterpriseId());
        if (list.size() > 0)
            return true;
        else
        {
            this.getSessionFactory().getCurrentSession().save(basCustomArea);
            return false;
        }
    }

    
    public boolean Delete(BasCustomArea basCustomArea)
    {
        boolean bool = false;
        try
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .delete(basCustomArea);
            bool = true;
        }
        catch(DataAccessException e)
        {
            e.printStackTrace();
        }
        return bool;
    }

    
    public boolean Update(BasCustomArea basCustomArea)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomArea where pareaName='"
                                + basCustomArea.getPareaName()
                                + "' and pareaId not in ("
                                + basCustomArea.getPareaId() + ")");
        if (list.size() > 0)
            return true;
        else{
            this.getSessionFactory().getCurrentSession().update(basCustomArea);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    
    public List<BasCustomArea> findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception
    {
        String hql = "from BasCustomArea bca where bca.enterpriseId="+enterprise_id;
        if (sort != null && !sort.equals("") && order != null&& !order.equals(""))
            hql += " order by " + order + " " + sort + " ";
        return this.getSessionFactory().getCurrentSession().createQuery(hql)
                .setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    
    public List<BasCustomArea> getTotle(BasCustomArea basCustomArea)
    {
        String hql = "from BasCustomArea bca where bca.enterpriseId="+basCustomArea.getEnterpriseId();
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(hql);
    }

}
