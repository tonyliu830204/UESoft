package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.syuesoft.bas.dao.BasCarPhoneTrackerResultDAO;
import com.syuesoft.base.vo.BasCarPhonetrackerresultVo;
import com.syuesoft.model.BasCarPhonetrackerresult;

/**
 * 基础资料-->车辆性质：电话跟踪结果Dao接口
 * 
 * @author SuMing
 */
public class BasCarPhoneTrackerResultDAOImpl extends
        BaseDaoImpl<BasCarPhonetrackerresult> implements
        BasCarPhoneTrackerResultDAO
{

    private static final Logger log = Logger
            .getLogger(BasCarPhoneTrackerResultDAOImpl.class);

    public static final String CAR_PHONETRACKERRESULT_NAME = "carPhonetrackerresultName";

    public static final String CAR_PHONETRACKERRESULT_REMARK = "carPhonetrackerresultRemark";

    /**
     * 基础资料-->车辆性质：电话跟踪结果 按ID查询
     */
    public BasCarPhonetrackerresult findById(Short id)
    {
        log.debug("getting BasCarPhonetrackerresult instance with id: " + id);
        try
        {
            BasCarPhonetrackerresult instance = (BasCarPhonetrackerresult) getHibernateTemplate()
                    .get("com.syuesoft.model.BasCarPhonetrackerresult", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：电话跟踪结果 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasCarPhonetrackerresultVo bcpvo)
    {
        log.debug("finding all BasCarPhonetrackerresult instances");
        try
        {
            String queryString = "from BasCarPhonetrackerresult  bcb where bcb.enterpriseId="+bcpvo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->车辆性质：电话跟踪结果 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasCarPhonetrackerresult> getAllByPage(final BasCarPhonetrackerresultVo bcpvo)
    {
        List<BasCarPhonetrackerresult> list = (List) this
                .getHibernateTemplate().execute(new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasCarPhonetrackerresult  bcb where bcb.enterpriseId="+bcpvo.getEnterpriseId();
                        if (bcpvo.getSort() != null || bcpvo.getOrder() != null)
                        {
                            hql += " order by " + bcpvo.getSort() + " " + bcpvo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (bcpvo.getPage() - 1) * bcpvo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(bcpvo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

}