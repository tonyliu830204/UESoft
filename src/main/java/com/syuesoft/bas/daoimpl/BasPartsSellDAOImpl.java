package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasPartsSellDAO;
import com.syuesoft.base.vo.BasPartsSellVo;
import com.syuesoft.model.BasPartsSell;

/**
 * 基础资料-->配件性质：配件销售分类Dao实现类
 * 
 * @author SuMing
 */
public class BasPartsSellDAOImpl extends BaseDaoImpl<BasPartsSell> implements
        BasPartsSellDAO
{

    private static final Logger log = Logger
            .getLogger(BasPartsSellDAO.class);

    public static final String PSELL_NAME = "psellName";

    public static final String REMARK = "remark";

    /**
     * 基础资料-->配件性质：配件销售分类 删除
     */
    public void delete(BasPartsSell persistentInstance)
    {
        log.debug("deleting BasPartsSell instance");
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
     * 基础资料-->配件性质：配件销售分类 修改
     */
    public BasPartsSell findById(java.lang.Short id)
    {
        log.debug("getting BasPartsSell instance with id: " + id);
        try
        {
            BasPartsSell instance = (BasPartsSell) getHibernateTemplate().get(
                    "com.syuesoft.model.BasPartsSell", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件销售分类 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasPartsSellVo basPartsSellVo)
    {
        log.debug("finding all BasPartsSell instances");
        try
        {
            String queryString = "from BasPartsSell bcb where bcb.enterpriseId="+basPartsSellVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件销售分类 修改
     */
    public BasPartsSell merge(BasPartsSell detachedInstance)
    {
        log.debug("merging BasPartsSell instance");
        try
        {
            BasPartsSell result = (BasPartsSell) getHibernateTemplate().merge(
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
     * 基础资料-->配件性质：配件销售分类 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsSell> getAllByPage(final BasPartsSellVo basPartsSellVo)
    {
        List<BasPartsSell> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasPartsSell bcb where bcb.enterpriseId="+basPartsSellVo.getEnterpriseId();
                        if (basPartsSellVo.getSort()!= null || basPartsSellVo.getOrder() != null)
                        {
                            hql += " order by " + basPartsSellVo.getSort() + " " + basPartsSellVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basPartsSellVo.getPage() - 1) * basPartsSellVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basPartsSellVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

}
