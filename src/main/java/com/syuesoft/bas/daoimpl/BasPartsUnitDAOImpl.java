package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasPartsUnitDAO;
import com.syuesoft.base.vo.BasPartsUnitVo;
import com.syuesoft.model.BasPartsUnit;

/**
 * 基础资料-->配件性质：配件单位Dao实现类
 * 
 * @author SuMing
 */
@Repository("basPartsUnitDAO")
public class BasPartsUnitDAOImpl extends BaseDaoImpl<BasPartsUnit> implements
        BasPartsUnitDAO
{

    private static final Logger log = Logger
            .getLogger(BasPartsUnitDAO.class);

    public static final String PUNIT_NAME = "punitName";

    public static final String REMARK = "remark";

    /**
     * 基础资料-->配件性质：配件单位 删除
     */
    public void delete(BasPartsUnit persistentInstance)
    {
        log.debug("deleting BasPartsUnit instance");
        try
        {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch(RuntimeException re){
            log.error("delete failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件单位 修改
     */
    public BasPartsUnit merge(BasPartsUnit detachedInstance)
    {
        log.debug("merging BasPartsUnit instance");
        try
        {
            BasPartsUnit result = (BasPartsUnit) getHibernateTemplate().merge(
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
     * 基础资料-->配件性质：配件单位 按ID查询
     */
    public BasPartsUnit findById(java.lang.Short id)
    {
        log.debug("getting BasPartsUnit instance with id: " + id);
        try
        {
            BasPartsUnit instance = (BasPartsUnit) getHibernateTemplate().get(
                    "com.syuesoft.model.BasPartsUnit", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件单位 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasPartsUnitVo basPartsUnitVo)
    {
        log.debug("finding all BasPartsUnit instances");
        try{
        	String queryString = "from BasPartsUnit bcb where bcb.enterpriseId="+basPartsUnitVo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        } catch(RuntimeException re){
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：配件单位 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsUnit> getAllByPage(final BasPartsUnitVo basPartsUnitVo)
    {
        List<BasPartsUnit> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasPartsUnit bcb where bcb.enterpriseId="+basPartsUnitVo.getEnterpriseId();
                        if (basPartsUnitVo.getSort() != null || basPartsUnitVo.getOrder() != null){
                            hql += " order by " +basPartsUnitVo.getSort() + " " + basPartsUnitVo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (basPartsUnitVo.getPage() - 1) * basPartsUnitVo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(basPartsUnitVo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

	
	public BasPartsUnit findByName(String unitName) throws Exception {
		String hql="from  BasPartsUnit unit where unit.punitName='"+unitName+"'";
		List<BasPartsUnit> units=this.find(hql);
		if(units!=null && units.size()>0){
			return units.get(0);
		}
		return null;
	}
}