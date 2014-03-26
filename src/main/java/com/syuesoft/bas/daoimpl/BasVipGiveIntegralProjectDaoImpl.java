package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasVipGiveIntegralProjectDao;
import com.syuesoft.base.vo.BasVipGiveIntegralProjectVO;
import com.syuesoft.model.BasVipGiveIntegralProject;

@Repository("basVipGiveIntegralProjectDao")
/**
 * @author mulangtao
 * */
public class BasVipGiveIntegralProjectDaoImpl extends
        BaseDaoImpl<BasVipGiveIntegralProject> implements
        BasVipGiveIntegralProjectDao
{

    
    /**
     * 	添加赠送积分项目
     * */
    public void add(BasVipGiveIntegralProject basVipGiveIntegralProject)
            throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(basVipGiveIntegralProject);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    /**
     *  删除赠送积分项目
     * */
    public void delte(BasVipGiveIntegralProject basVipGiveIntegralProject)
            throws Exception
    {
        try
        {
            this.getHibernateTemplate().delete(basVipGiveIntegralProject);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    /**
     * 	获取所有赠送积分项目
     * */
    public List<BasVipGiveIntegralProjectVO> findAll(final int page,
            final int rows, final String order, final String sort,final int enterprise_id)
            throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipGiveIntegralProjectVO>>()
        {
            
            public List<BasVipGiveIntegralProjectVO> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                //String hql = "select new com.syuesoft.base.vo.BasVipGiveIntegralProjectVO(ss.giveInteProId as givInteProId,ss.giveInteProName as givInteProName,ss.giveInteNum as givInteNum,ss.enterpriseId as enterpriseId) from BasVipGiveIntegralProject ss where ss.enterpriseId="+enterprise_id;
                String hql = "select new com.syuesoft.base.vo.BasVipGiveIntegralProjectVO(ss.giveInteProId as givInteProId,ss.giveInteProName as givInteProName,ss.giveInteNum as givInteNum,ss.enterpriseId as enterpriseId) from BasVipGiveIntegralProject ss ";
                if (order != null && sort != null)
                    hql += " order by " + sort + " " + order;
                Query query = session.createQuery(hql);
                int beginRow = (page - 1) * rows;
                query.setFirstResult(beginRow);
                query.setMaxResults(rows);
                return query.list();
            }
        });
    }

    
    /**
     * 	根据项目编号获取总送积分项目信息
     * */
    public BasVipGiveIntegralProject getById(int id) throws Exception
    {
        String hql = "from BasVipGiveIntegralProject where givInteProId='" + id
                + "'";
        try
        {
            List list = this.getHibernateTemplate().find(hql);
            if (list != null)
            {
                return (BasVipGiveIntegralProject) list.get(0);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * 	获取总记录数
     * */
    public int getTotle(int enterprise_id) throws Exception
    {
        //String hql = "from BasVipGiveIntegralProject where enterpriseId="+enterprise_id;
        String hql = "from BasVipGiveIntegralProject ";
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
     * 根据赠送积分项目名称获取赠送积分项目信息
     * */
    
    public boolean getByName(
            BasVipGiveIntegralProjectVO basVipGiveIntegralProjectVO)
            throws Exception
    {
        String hql = "from BasVipGiveIntegralProject bvgip where bvgip.givInteProName='"
                + basVipGiveIntegralProjectVO.getGivInteProName() + "' and bvgip.enterpriseId="+basVipGiveIntegralProjectVO.getEnterpriseId();
        if (basVipGiveIntegralProjectVO.getGivInteProId() != null){
            hql += "  and bvgip.givInteProId not in('"
                    + basVipGiveIntegralProjectVO.getGivInteProId() + "')";
        }
        try{
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
