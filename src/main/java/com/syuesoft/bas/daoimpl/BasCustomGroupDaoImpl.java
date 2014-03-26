package com.syuesoft.bas.daoimpl;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasCustomGroupDao;
import com.syuesoft.model.BasCustomGroup;

/**
 * 客户类型dao
 * 
 * @author ChangMing
 * 
 */
@Repository("basCustomGroupDao")
public class BasCustomGroupDaoImpl extends BaseDaoImpl<BasCustomGroup>
        implements BasCustomGroupDao
{

    
    public boolean Add(BasCustomGroup basCustomGroup)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomGroup where cstgName='"
                                + basCustomGroup.getCstgName() + "'");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .save(basCustomGroup);
            return false;
        }
    }

    
    public void Delete(BasCustomGroup basCustomGroup)
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .delete(basCustomGroup);
    }

    
    public boolean Update(BasCustomGroup basCustomGroup)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasCustomGroup where cstgName='"
                                + basCustomGroup.getCstgName()
                                + "' and cstgId not in ("
                                + basCustomGroup.getCstgId() + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .update(basCustomGroup);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    
    public List<BasCustomGroup> findAll(int page, int rows, String sort,
            String order,int enterprise_id)
    {
        String hql = "from BasCustomGroup bcg where bcg.enterpriseId="+enterprise_id;
        if (sort != null && !sort.equals("") && order != null
                && !order.equals("")){
            hql += " order by " + sort + " " + order + "";
        }
        return this.getHibernateTemplate().getSessionFactory()
               .getCurrentSession().createQuery(hql).setFirstResult(
                (page - 1) * rows).setMaxResults(rows).list();
    }

    /*
     * 条件查询
     */
    @SuppressWarnings("unchecked")
    
    public List<BasCustomGroup> findByCondition(final int page, final int rows,
            final BasCustomGroup basCustomGroup)
    {
        String hql = "from BasCustomGroup ";
        if (basCustomGroup.getCstgName() != null
                && !basCustomGroup.getCstgName().trim().equals(""))
        {
            hql += "where cstName like '%" + StringEscapeUtils.escapeSql(basCustomGroup.getCstgName().trim())
                    + "%'";
        }
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql).setFirstResult(
                        (page - 1) * rows).setMaxResults(rows).list();
    }

    
    public List<BasCustomGroup> getTotle(int enterprise_id)
    {
        String hql = "from BasCustomGroup bcg where bcg.enterpriseId="+enterprise_id;
        return this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().createQuery(hql).list();
    }
}