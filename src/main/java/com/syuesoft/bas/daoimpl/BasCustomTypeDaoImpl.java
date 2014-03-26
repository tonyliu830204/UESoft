package com.syuesoft.bas.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCustomTypeDao;
import com.syuesoft.base.vo.BasCustomTypeVo;
import com.syuesoft.model.BasCustomType;

/**
 * 客户分类dao
 * 
 * @author ChangMing
 * 
 */
@Repository("basCustomTypeDao")
public class BasCustomTypeDaoImpl extends BaseDaoImpl<BasCustomType> implements
        BasCustomTypeDao
{

    
    public boolean Add(BasCustomType basCustomType)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomType where cstName='"
                                + basCustomType.getCstName() + "' and enterpriseId="+basCustomType.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .save(basCustomType);
            return false;
        }
    }

    
    public void Delete(BasCustomType basCustomType)
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .delete(basCustomType);
    }

    
    public boolean Update(BasCustomType basCustomType)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomType where cstName='"
                                + basCustomType.getCstName()
                                + "' and cstId not in ("
                                + basCustomType.getCstId() + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .update(basCustomType);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    
    public List<BasCustomType> findAll(final int page, final int rows,final int enterprise_id)
    {
        String hql = "from BasCustomType bct where bct.enterpriseId="+enterprise_id;
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql).setFirstResult(
                        (page - 1) * rows).setMaxResults(rows).list();
    }

    
    public List<BasCustomType> getTotle(BasCustomTypeVo basCustomTypeVo)
    {
        String hql = "from BasCustomType bct where bct.enterpriseId="+basCustomTypeVo.getEnterpriseId();
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql).list();
    }

    
    public List<BasCustomType> findByCondition(int page, int rows,
            BasCustomType basCustomType)
    {
        String hql = "from BasCustomType bc where bc.enterpriseId="+basCustomType.getEnterpriseId();
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql).list();
    }
}