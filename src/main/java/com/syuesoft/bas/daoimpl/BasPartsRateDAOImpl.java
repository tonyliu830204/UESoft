package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasPartsRateDAO;
import com.syuesoft.model.BasPartsRate;

/**
 * 基础资料-->配件性质：配件加价率Dao实现类
 * 
 * @author SuMing
 */
public class BasPartsRateDAOImpl extends BaseDaoImpl<BasPartsRate> implements
        BasPartsRateDAO
{
    private static final Logger log = Logger
            .getLogger(BasPartsRateDAO.class);

    public static final String PARTS_STYLE = "partsStyle";

    public static final String PARTS_STARTAMOUNT = "partsStartamount";

    public static final String PARTS_ENDAMOUNT = "partsEndamount";

    public static final String PARTS_RATE = "partsRate";

    public static final String PARTS_REMARK = "partsRemark";

    /**
     * 基础资料-->配件性质：配件加价率 删除
     */
    public void delete(BasPartsRate persistentInstance)
    {
        log.debug("deleting BasPartsRate instance");
        try
        {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch(RuntimeException re)
        {
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件加价率 修改
     */
    public BasPartsRate merge(BasPartsRate detachedInstance)
    {
        log.debug("merging BasPartsRate instance");
        try
        {
            BasPartsRate result = (BasPartsRate) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch(RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件加价率 按ID查询
     */
    public BasPartsRate findById(java.lang.Short id)
    {
        log.debug("getting BasPartsRate instance with id: " + id);
        try
        {
            BasPartsRate instance = (BasPartsRate) getHibernateTemplate().get(
                    "com.syuesoft.model.BasPartsRate", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件加价率 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll()
    {
        log.debug("finding all BasPartsRate instances");
        try
        {
            String queryString = "from BasPartsRate";
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件加价率 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsRate> getAllByPage(final int page, final int rows,
            final String sort, final String order)
    {
        List<BasPartsRate> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasPartsRate";
                        if (sort != null || order != null)
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
        return list;
    }

}
