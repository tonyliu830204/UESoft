package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasCarColorDAO;
import com.syuesoft.base.vo.BasCarColorVo;
import com.syuesoft.model.BasCarColor;

/**
 * 基础资料-->车辆性质：车辆颜色dao实现类
 * 
 * @author SuMing
 */
public class BasCarColorDAOImpl extends BaseDaoImpl<BasCarColor> implements
        BasCarColorDAO
{

    private Logger log = Logger.getLogger(this.getClass());

    public static final String COLOR_NAME = "colorName";

    public static final String REMARK = "remark";

    /**
     * 基础资料-->车辆性质：车辆颜色 按ID查询
     */
    public BasCarColor findById(Short id)
    {
        log.debug("getting BasCarColor instance with id: " + id);
        try
        {
            BasCarColor instance = (BasCarColor) getHibernateTemplate().get(
                    "com.syuesoft.model.BasCarColor", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：车辆颜色 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasCarColorVo basCarColorVo)
    {
        log.debug("finding all BasCarColor instances");
        try
        {
            String queryString = "from BasCarColor  bcb where bcb.enterpriseId="+basCarColorVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：车辆颜色 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasCarColor> getAllByPage(final BasCarColorVo basCarColorVo)
    {
        List<BasCarColor> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasCarColor  bcb where bcb.enterpriseId="+basCarColorVo.getEnterpriseId();
                        if (basCarColorVo.getSort() != null || basCarColorVo.getOrder() != null)
                        {
                            hql += " order by " + basCarColorVo.getSort() + " " + basCarColorVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basCarColorVo.getPage() - 1) * basCarColorVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basCarColorVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }
}