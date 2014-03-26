package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.model.BasVipLevel;

@Repository("basVipLevelDao")
public class BasVipLevelDaoImpl extends BaseDaoImpl<BasVipLevel> implements
        com.syuesoft.bas.dao.BasVipLevelDao
{

    
    /**
     * 添加会员等级
     * @bvl 会员等级实体
     * */
    public void add(BasVipLevel bvl) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(bvl);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    /**
     * 删除会员等级
     * @bvl 会员等级实体
     * */
    public void delte(BasVipLevel bvl) throws Exception
    {
        try
        {
            this.getHibernateTemplate().delete(bvl);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    /**
     * 分页查询所有会员等级
     * @page 当前页
     * @rows 每页显示记录数
     * @order 排序类型
     * @sort 排序字段
     * */
    public List<BasVipLevelVO> findAll(final int page, final int rows,
            final String order, final String sort,final int enterprise_id) throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipLevelVO>>()
        {
            
            public List<BasVipLevelVO> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                //String hql = "select new com.syuesoft.base.vo.BasVipLevelVO(bvl.vipLevelId,bvl.vipLevelName,bvl.vipLevelNote) from BasVipLevel bvl where bvl.enterpriseId="+enterprise_id;
                String hql = "select new com.syuesoft.base.vo.BasVipLevelVO(bvl.vipLevelId,bvl.vipLevelName,bvl.vipLevelNote) from BasVipLevel bvl ";
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
        //String hql = "from BasVipLevel where enterpriseId="+enterprise_id;
        String hql = "from BasVipLevel ";
        List list = this.getHibernateTemplate().find(hql);
        if (list != null){
            return list.size();
        }
        return 0;
    }

    /**
     * 根据会员名称获取会员等级
     * 
     * @name 会员名称
     * */
    
    public boolean getByName(BasVipLevelVO bvlVO) throws Exception
    {
        String hql = "from BasVipLevel where vipLevelName='"
                + bvlVO.getVipLevelName() + "' and enterpriseId="+bvlVO.getEnterpriseId();
        if (bvlVO.getVipLevelId() != null)
        {
            hql += " and vipLevelId not in('" + bvlVO.getVipLevelId() + "')";
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
