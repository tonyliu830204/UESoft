package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasStorehouseDAO;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.model.BasStorehouse;

/**
 * 基础资料-->配件性质：仓别分类Dao实现类
 * 
 * @author SuMing
 */
@Repository("basStorehouseDAO")
public class BasStorehouseDAOImpl extends BaseDaoImpl<BasStorehouse> implements
        BasStorehouseDAO
{

    private static final Logger log = Logger
            .getLogger(BasStorehouseDAO.class);

    public static final String STORE_NAME = "storeName";

    public static final String REMARK = "remark";

    @SuppressWarnings("unchecked")
    public List isUsed(final BasStorehouseVo basStorehouseVo) throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {
            public List doInHibernate(Session session)
            {
                String queryString = "SELECT parts_change_price.STORE_ID,parts_change_price.PARTS_NOW_COUNT "
                        + " FROM parts_change_price WHERE parts_change_price.enterprise_id="+basStorehouseVo.getEnterpriseId()+" and parts_change_price.PARTS_NOW_COUNT>0 ";
                if (basStorehouseVo.getStoreId() != null
                        && !"".equals(basStorehouseVo.getStoreId()))
                {
                    queryString += " AND  parts_change_price.STORE_ID="
                            + basStorehouseVo.getStoreId();
                }
                queryString += "  GROUP BY parts_change_price.STORE_ID";
                Query query = session.createSQLQuery(queryString);
                return query.list();
            }
        });
        return list;
    }

    /**
     * 配件库存量查询模块 仓库名称信息全查
     */
    @SuppressWarnings("unchecked")
    public List pnc_searchBasStorehouseByCondition() throws Exception
    {
        List list = this.getHibernateTemplate().executeFind(
                new HibernateCallback()
                {
                    public Object doInHibernate(Session sesison)
                            throws HibernateException, SQLException
                    {
                        String queryString = "SELECT bas_storehouse.STORE_ID,"
                                + "bas_storehouse.STORE_NAME"
                                + " FROM bas_storehouse"
                                + " ORDER BY bas_storehouse.STORE_ID";
                        Query query = sesison.createSQLQuery(queryString);
                        return query.list();
                    }
                });
        return list;
    }

    /**
     * 基础资料-->配件性质：仓别分类 删除
     */
    public void delete(BasStorehouse persistentInstance)
    {
        log.debug("deleting BasStorehouse instance");
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
     * 基础资料-->配件性质：仓别分类 修改
     */
    public BasStorehouse merge(BasStorehouse detachedInstance)
    {
        log.debug("merging BasStorehouse instance");
        try
        {
            BasStorehouse result = (BasStorehouse) getHibernateTemplate()
                    .merge(detachedInstance);
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
     * 基础资料-->配件性质：仓别分类 按ID查询
     */
    public BasStorehouse findById(java.lang.Short id)
    {
        log.debug("getting BasStorehouse instance with id: " + id);
        try
        {
            BasStorehouse instance = (BasStorehouse) getHibernateTemplate()
                    .get("com.syuesoft.model.BasStorehouse", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：仓别分类 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasStorehouseVo basStorehouseVo)
    {
        log.debug("finding all BasStorehouse instances");
        try
        {
            String queryString = "from BasStorehouse bcb where bcb.enterpriseId="+basStorehouseVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：仓别分类 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasStorehouse> getAllByPage(final BasStorehouseVo basStorehouseVo)
    {
        List<BasStorehouse> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasStorehouse bcb where bcb.enterpriseId="+basStorehouseVo.getEnterpriseId();
                        if (basStorehouseVo.getSort() != null || basStorehouseVo.getOrder() != null){
                            hql += " order by " + basStorehouseVo.getSort() + " " + basStorehouseVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basStorehouseVo.getPage() - 1) * basStorehouseVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basStorehouseVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }
}
