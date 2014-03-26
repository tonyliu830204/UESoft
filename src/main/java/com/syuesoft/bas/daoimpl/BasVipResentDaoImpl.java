package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasVipResentDao;
import com.syuesoft.base.vo.BasVipResentVO;
import com.syuesoft.model.BasVipResent;

@Repository("BasVipResentDao")
public class BasVipResentDaoImpl extends BaseDaoImpl<BasVipResent> implements
        BasVipResentDao
{
    /**
     * 添加礼品信息
     * 
     * @basVipResent 礼品实体
     * */
    
    public void add(BasVipResent basVipResent) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(basVipResent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 删除礼品信息
     * 
     * @basVipResent 礼品实体
     * */
    
    public void delte(BasVipResent basVipResent) throws Exception
    {
        try
        {
            this.getHibernateTemplate().delete(basVipResent);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询所有礼品信息
     * 
     * @page 当前页
     * @rows 每页显示记录数
     * @order 排序类型
     * @sort 排序字段
     * */
    @SuppressWarnings("unchecked")
    
    public List<BasVipResentVO> findAll(final int page, final int rows,
            final String order, final String sort) throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipResentVO>>()
        {

            
            public List<BasVipResentVO> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String hql = "select new com.syuesoft.base.vo.BasVipResentVO(pstId,pstName,pstCount,pstUnit,pstInte) from BasVipResent";
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
    
    public int getTotle() throws Exception
    {
        String hql = "from BasVipResent";
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

    
    public boolean getByName(String name) throws Exception
    {
        String hql = "from BasVipResent where pstName='" + name + "'";
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
