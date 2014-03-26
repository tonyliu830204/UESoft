package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasVipGruopDao;
import com.syuesoft.base.vo.BasVipGruopVO;
import com.syuesoft.model.BasVipGruop;

@Repository("basVipGruopDao")
public class BasVipGruopDaoImpl extends BaseDaoImpl<BasVipGruop> implements
        BasVipGruopDao
{

    
    /**
     * 	添加会员分组
     * */
    public void add(BasVipGruop basVipGruop) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(basVipGruop);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    /**
     * 	删除会员分组
     * */
    public void delte(BasVipGruop basVipGruop) throws Exception
    {
        try
        {
            this.getHibernateTemplate().delete(basVipGruop);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    
    /**
     * 分页获取所有会员分组
     * */
    public List<BasVipGruopVO> findAll(final int page, final int rows,
            final String order, final String sort,final int enterprise_id) throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasVipGruopVO>>()
        {

            
            public List<BasVipGruopVO> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                //String hql = "select new com.syuesoft.base.vo.BasVipGruopVO(vipGruopId,vipGruopName,vipGruopNote) from BasVipGruop where enterpriseId="+enterprise_id;
                String hql = "select new com.syuesoft.base.vo.BasVipGruopVO(vipGruopId,vipGruopName,vipGruopNote) from BasVipGruop ";
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
     * 	获取所有记录数
     * */
    public int getTotle(int enterprise_id) throws Exception
    {
        //String hql = "from BasVipGruop where enterpriseId="+enterprise_id;
        String hql = "from BasVipGruop ";
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
     * 根据会员分组名称获取会员分组信息
     * 
     * @name 会员分组名称
     * */
    
    public boolean getByName(BasVipGruopVO basVipGruopVO) throws Exception
    {
        String hql = "from BasVipGruop where vipGruopName='"
                + basVipGruopVO.getVipGruopName() + "' and enterpriseId="+basVipGruopVO.getEnterpriseId();
        if (basVipGruopVO.getVipGruopId() != null)
        {
            hql += " and vipGruopId not in('" + basVipGruopVO.getVipGruopId()
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
