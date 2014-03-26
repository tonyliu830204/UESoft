package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasVipGiveProjectDao;
import com.syuesoft.model.BasVipGiveProject;

@Repository("basVipGiveProjectDao")
/**
 * @author mulangtao
 * */
public class BasVipGiveProjectDaoImpl extends BaseDaoImpl<BasVipGiveProject>
        implements BasVipGiveProjectDao
{

    
    /**
     * 	添加会员赠送项目
     * @basVipGiveProject 会员赠送项目实体
     * */
    public void add(BasVipGiveProject basVipGiveProject) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(basVipGiveProject);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    /**
     *  分页查询所有会员信息
     *  @page 当前页
     *  @rows 每页显示记录数
     *  @order 排序规则
     *  @sort 排序字段
     * */
    public List<BasVipGiveProject> findAll(final int page, final int rows,
            final String order, final String sort,final int enterprise_id) throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipGiveProject>>()
        {

            
            public List<BasVipGiveProject> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                //String hql = "from BasVipGiveProject where enterpriseId="+enterprise_id;
                String hql = "from BasVipGiveProject ";
                if (order != null && sort != null)
                {
                    hql += " order by " + sort + " " + order;
                }
                Query query = session.createQuery(hql);
                int beginRow = (page - 1) * rows;
                query.setFirstResult(beginRow);
                query.setMaxResults(rows);
                return query.list();
            }
        });
    }

    
    /**
     * 获取所有记录数
     * */
    public int getTotle(int enterprise_id) throws Exception
    {
        //String hql = "from BasVipGiveProject where enterpriseId="+enterprise_id;
        String hql = "from BasVipGiveProject ";
        try
        {
            List list = this.getHibernateTemplate().find(hql);
            if (list != null)
            {
                return list.size();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据会员赠送项目名称获取会员赠送项目信息
     * 
     * @name 会员赠送项目名称
     * */
    
    public boolean getByName(BasVipGiveProject basVipGiveProject)
            throws Exception
    {
        String hql = "from BasVipGiveProject where vipGpName='"
                + basVipGiveProject.getVipGpName() + "' and enterpriseId="+basVipGiveProject.getEnterpriseId();
        if (basVipGiveProject.getVipGpId() != null)
        {
            hql += " and vipGpId not in('" + basVipGiveProject.getVipGpId()
                    + "')";
        }
        try
        {
            List list = this.getHibernateTemplate().find(hql);
            if (list != null && list.size() > 0)
            {
                return true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
