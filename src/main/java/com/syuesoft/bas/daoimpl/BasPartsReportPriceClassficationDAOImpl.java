package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasPartsReportPriceClassficationDAO;
import com.syuesoft.base.vo.BasPartsReportpriceclassficationVo;
import com.syuesoft.model.BasPartsReportpriceclassfication;

/**
 * 基础资料-->配件性质：配件报价分类Dao实现类
 * 
 * @author SuMing
 */
public class BasPartsReportPriceClassficationDAOImpl extends
        BaseDaoImpl<BasPartsReportpriceclassfication> implements
        BasPartsReportPriceClassficationDAO
{

    private static final Logger log = Logger
            .getLogger(BasPartsReportpriceclassfication.class);

    public static final String PARTS_NAME = "partsName";

    public static final String PARTS_BRAND = "partsBrand";

    public static final String PARTS_TYPE = "partsType";

    public static final String PARTS_INVITE_BID = "partsInviteBid";

    public static final String PARTS_SUCCESSFUL_BID = "partsSuccessfulBid";

    public static final String PARTS_UNIT_MEASUREMENT = "partsUnitMeasurement";

    /**
     * 基础资料-->配件性质：配件报价分类 删除
     */
    public void delete(BasPartsReportpriceclassfication persistentInstance)
    {
        log.debug("deleting BasPartsReportpriceclassfication instance");
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
     * 基础资料-->配件性质：配件报价分类 修改
     */
    public BasPartsReportpriceclassfication merge(
            BasPartsReportpriceclassfication detachedInstance)
    {
        log.debug("merging BasPartsReportpriceclassfication instance");
        try
        {
            BasPartsReportpriceclassfication result = (BasPartsReportpriceclassfication) getHibernateTemplate()
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
     * 基础资料-->配件性质：配件报价分类 按ID查询
     */
    public BasPartsReportpriceclassfication findById(java.lang.Short id)
    {
        log.debug("getting BasPartsReportpriceclassfication instance with id: "
                + id);
        try
        {
            BasPartsReportpriceclassfication instance = (BasPartsReportpriceclassfication) getHibernateTemplate()
                    .get("com.syuesoft.model.BasPartsReportpriceclassfication",
                            id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件报价分类 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
    {
        log.debug("finding all BasPartsReportpriceclassfication instances");
        try
        {
            String queryString = "from BasPartsReportpriceclassfication bcb where bcb.enterpriseId="+basPartsReportpriceclassficationVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件报价分类 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsReportpriceclassfication> getAllByPage(final BasPartsReportpriceclassficationVo basPartsReportpriceclassficationVo)
    {
        List<BasPartsReportpriceclassfication> list = (List) this
                .getHibernateTemplate().execute(new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasPartsReportpriceclassfication bcb where bcb.enterpriseId="+basPartsReportpriceclassficationVo.getEnterpriseId();
                        if (basPartsReportpriceclassficationVo.getSort() != null || basPartsReportpriceclassficationVo.getOrder() != null){
                            hql += " order by " + basPartsReportpriceclassficationVo.getSort() + " " + basPartsReportpriceclassficationVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basPartsReportpriceclassficationVo.getPage() - 1) * basPartsReportpriceclassficationVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basPartsReportpriceclassficationVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

}
