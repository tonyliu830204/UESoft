package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;
import com.syuesoft.bas.dao.BasCarBodysStatusDAO;
import com.syuesoft.base.vo.BasCarBodysStatusVo;
import com.syuesoft.model.BasCarBodysStatus;

/**
 * 基础资料-->车辆性质：车身状态Dao实现类
 * @author SuMing
 */
@Repository("basCarBodysStatusDAO")
public class BasCarBodysStatusDAOImpl extends BaseDaoImpl<BasCarBodysStatus>
        implements BasCarBodysStatusDAO
{
    private static final Logger log = Logger
    .getLogger(BasCarBodysStatusDAOImpl.class);
    public static final String BODY_NAME = "bodyName";
    public static final String BODY_REMARK = "bodyRemark";
    /**
     * 基础资料-->车辆性质：车身状态 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasCarBodysStatus> getAllByPage(final BasCarBodysStatusVo
    		basCarBodysStatusVo)
    {
        List<BasCarBodysStatus> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasCarBodysStatus bcb where  1=1  ";;
                        if (basCarBodysStatusVo.getSort() != null || basCarBodysStatusVo.getOrder() != null)
                        {
                            hql += " order by " + basCarBodysStatusVo.getSort() + " " + basCarBodysStatusVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basCarBodysStatusVo.getPage() - 1) * basCarBodysStatusVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basCarBodysStatusVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }
    public List findAll()
    {
        log.debug("finding all BasCarBodysStatus instances");
        try
        {
            String queryString = "from BasCarBodysStatus";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }
}
