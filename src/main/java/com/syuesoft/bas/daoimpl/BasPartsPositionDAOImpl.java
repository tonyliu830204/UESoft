package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasPartsPositionDAO;
import com.syuesoft.base.vo.BasPartsPositionVo;
import com.syuesoft.model.BasPartsPosition;

/**
 * 基础资料-->配件性质：配件部位Dao实现类
 * 
 * @author SuMing
 */
@Repository("basPartsPositionDAO")
public class BasPartsPositionDAOImpl extends BaseDaoImpl<BasPartsPosition>
        implements BasPartsPositionDAO
{

    private static final Logger log = Logger
            .getLogger(BasPartsPositionDAO.class);

    public static final String POS_NAME = "posName";

    public static final String REMARK = "remark";

    /**
     * 配件库存量查询模块 配件部位信息加载
     */
    @SuppressWarnings("unchecked")
    public List pnc_searchPartsPosition() throws Exception
    {
        List list = this.getHibernateTemplate().executeFind(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "SELECT bas_parts_position.POS_ID,"
                                + "bas_parts_position.POS_NAME "
                                + "FROM bas_parts_position"
                                + " GROUP BY bas_parts_position.POS_ID"
                                + " ORDER BY bas_parts_position.POS_ID";
                        Query query = session.createSQLQuery(queryString);
                        return query.list();
                    }
                });
        return list;
    }

    /**
     * 基础资料-->配件性质：配件部位 删除
     */
    public void delete(BasPartsPosition persistentInstance)
    {
        log.debug("deleting BasPartsPosition instance");
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
     * 基础资料-->配件性质：配件部位 修改
     */
    public BasPartsPosition merge(BasPartsPosition detachedInstance)
    {
        log.debug("merging BasPartsPosition instance");
        try
        {
            BasPartsPosition result = (BasPartsPosition) getHibernateTemplate()
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
     * 基础资料-->配件性质：配件部位 按ID查询
     */
    public BasPartsPosition findById(Short id)
    {
        log.debug("getting BasPartsPosition instance with id: " + id);
        try
        {
            BasPartsPosition instance = (BasPartsPosition) getHibernateTemplate()
                    .get("com.syuesoft.model.BasPartsPosition", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件部位 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasPartsPositionVo basPartsPositionVo)
    {
        log.debug("finding all BasPartsPosition instances");
        try
        {
            String queryString = "from BasPartsPosition bcb where bcb.enterpriseId="+basPartsPositionVo.getEnterpriseId(); 
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件部位 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsPosition> getAllByPage(final BasPartsPositionVo basPartsPositionVo)
    {
        List<BasPartsPosition> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasPartsPosition bcb where bcb.enterpriseId="+basPartsPositionVo.getEnterpriseId();
                        if (basPartsPositionVo.getSort() != null || basPartsPositionVo.getOrder() != null){
                            hql += " order by " + basPartsPositionVo.getSort() + " " + basPartsPositionVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basPartsPositionVo.getPage() - 1) * basPartsPositionVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basPartsPositionVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

}