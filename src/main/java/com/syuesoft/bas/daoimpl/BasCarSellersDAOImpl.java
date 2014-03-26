package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.syuesoft.bas.dao.BasCarSellersDAO;
import com.syuesoft.base.vo.BasCarSellersVo;
import com.syuesoft.model.BasCarSellers;

/**
 * 基础资料-->车辆性质：其他销售店Dao实现类
 * 
 * @author SuMing
 */

public class BasCarSellersDAOImpl extends BaseDaoImpl<BasCarSellers> implements
        BasCarSellersDAO
{

    private static final Logger log = Logger
            .getLogger(BasCarSellersDAO.class);

    // property constants
    public static final String SLS_NAME = "slsName";

    public static final String REMARK = "remark";

    /**
     * 基础资料-->车辆性质：其他销售店 按ID查询
     */
    public BasCarSellers findById(Short id)
    {
        log.debug("getting BasCarSellers instance with id: " + id);
        try
        {
            BasCarSellers instance = (BasCarSellers) getHibernateTemplate()
                    .get("com.syuesoft.model.BasCarSellers", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：其他销售店 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasCarSellersVo basCarSellersVo)
    {
        log.debug("finding all BasCarSellers instances");
        try
        {
            String queryString = "from BasCarSellers bcb where bcb.enterpriseId="+basCarSellersVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：其他销售店 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasCarSellers> getAllByPage(final BasCarSellersVo basCarSellersVo)
    {
        List<BasCarSellers> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasCarSellers bcb where bcb.enterpriseId="+basCarSellersVo.getEnterpriseId();
                        if (basCarSellersVo.getSort() != null || basCarSellersVo.getOrder() != null)
                        {
                            hql += " order by " + basCarSellersVo.getSort() + " " + basCarSellersVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basCarSellersVo.getPage() - 1) * basCarSellersVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basCarSellersVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }
}