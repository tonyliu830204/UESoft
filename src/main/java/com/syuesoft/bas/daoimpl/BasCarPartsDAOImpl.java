package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasCarPartsDAO;
import com.syuesoft.base.vo.BasCarPartsVo;
import com.syuesoft.model.BasCarParts;

/**
 * 基础资料-->车辆性质：车辆部位Dao实现类
 * 
 * @author SuMing
 */
public class BasCarPartsDAOImpl extends BaseDaoImpl<BasCarParts> implements
        BasCarPartsDAO
{

    private static final Logger log = Logger.getLogger(BasCarPartsDAOImpl.class);

    public static final String CAR_PART_NAME = "carPartName";

    public static final String CAR_PART_REMARK = "carPartRemark";

    /**
     * 基础资料-->车辆性质：车辆部位 按ID查询
     */
    public BasCarParts findById(java.lang.Short id)
    {
        log.debug("getting BasCarParts instance with id: " + id);
        try
        {
            BasCarParts instance = (BasCarParts) getHibernateTemplate().get(
                    "com.syuesoft.model.BasCarParts", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：车辆部位 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasCarPartsVo basCarPartsVo)
    {
        log.debug("finding all BasCarParts instances");
        try
        {
            String queryString = "from BasCarParts bcb where bcb.enterpriseId="+basCarPartsVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：车辆部位 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasCarParts> getAllByPage(final BasCarPartsVo basCarPartsVo)
    {
        List<BasCarParts> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasCarParts bcb where bcb.enterpriseId="+basCarPartsVo.getEnterpriseId();
                        if (basCarPartsVo.getSort() != null || basCarPartsVo.getOrder() != null){
                            hql += " order by " + basCarPartsVo.getSort() + " " + basCarPartsVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basCarPartsVo.getPage() - 1) * basCarPartsVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basCarPartsVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }
}