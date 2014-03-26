package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasVipRechargeRegulationDao;
import com.syuesoft.model.BasVipRechargeRegulation;

@Repository("basVipRechargeRegulationDao")
public class BasVipRechargeRegulationDaoImpl extends
        BaseDaoImpl<BasVipRechargeRegulation> implements
        BasVipRechargeRegulationDao
{

    
    /**
     * 新增会员充值规则
     * */
    public void add(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(basVipRechargeRegulation);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    /**
     * 删除会员充值规则
     * */
    public void delte(BasVipRechargeRegulation basVipRechargeRegulation)
            throws Exception
    {
        try
        {
            this.getHibernateTemplate().delete(basVipRechargeRegulation);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    /**
     * 分页查询
     * */
    public List<BasVipRechargeRegulation> findAll(final int page,
            final int rows, final String order, final String sort,final int enterprise_id)
            throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipRechargeRegulation>>()
        {

            
            public List<BasVipRechargeRegulation> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                //String hql = "from BasVipRechargeRegulation where enterpriseId="+enterprise_id;
                String hql = "from BasVipRechargeRegulation ";
                if (order != null && sort != null){
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
     * 获得总记录数
     * */
    public int getTotle(int enterprise_id) throws Exception
    {
        //String hql = "from BasVipRechargeRegulation where enterpriseId="+enterprise_id;
        String hql = "from BasVipRechargeRegulation ";
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
     * 根据开始金额和结束金额获取会员充值规则
     * 
     * @recRulName 起始金额
     * @recRulNameEnd 结束金额
     * */
    
    public boolean getByRecRu(BasVipRechargeRegulation bvr) throws Exception
    {
        String hql = "from BasVipRechargeRegulation where recRulName='"
                + bvr.getRecRulName() + "' and recRulNameEnd='"
                + bvr.getRecRulNameEnd() + "' and enterpriseId="+bvr.getEnterpriseId();
        if (bvr.getRecRulId() != null)
        {
            hql += " and recRulId not in('" + bvr.getRecRulId() + "')";
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
