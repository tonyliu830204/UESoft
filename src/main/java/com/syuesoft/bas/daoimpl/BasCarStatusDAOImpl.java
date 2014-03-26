package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasCarStatusDAO;
import com.syuesoft.base.vo.BasCarStatusVo;
import com.syuesoft.model.BasCarStatus;

/**
 * 基础资料-->车辆性质：流失去向Dao接口
 * 
 * @author SuMing
 */
public class BasCarStatusDAOImpl extends BaseDaoImpl<BasCarStatus> implements
        BasCarStatusDAO
{

    private static final Logger log = Logger
            .getLogger(BasCarStatusDAO.class);

    public static final String STATUS_NAME = "statusName";

    public static final String REMARK = "remark";

    /**
     * 基础资料-->车辆性质：流失去向 按ID查询
     */
    public BasCarStatus findById(Short id)
    {
        log.debug("getting BasCarStatus instance with id: " + id);
        try
        {
            BasCarStatus instance = (BasCarStatus) getHibernateTemplate().get(
                    "com.syuesoft.model.BasCarStatus", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：流失去向 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasCarStatusVo basCarStatusVo)
    {
        log.debug("finding all BasCarStatus instances");
        try
        {
            String queryString = "from BasCarStatus  bcb where bcb.enterpriseId="+basCarStatusVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：流失去向 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasCarStatus> getAllByPage(final BasCarStatusVo basCarStatusVo)
    {
        List<BasCarStatus> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasCarStatus  bcb where bcb.enterpriseId="+basCarStatusVo.getEnterpriseId();
                        if (basCarStatusVo.getSort() != null || basCarStatusVo.getOrder() != null)
                        {
                            hql += " order by " + basCarStatusVo.getSort() + " " + basCarStatusVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basCarStatusVo.getPage() - 1) * basCarStatusVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basCarStatusVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

}