package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasPartsBrandDAO;
import com.syuesoft.model.BasPartsBrand;
import com.syuesoft.st.vo.BasPartsBrandVo;
import com.syuesoft.st.vo.PartsNowCountVo;
import com.syuesoft.util.Json;

/**
 * 基础资料-->配件性质：配件品牌Dao实现类
 * 
 * @author SuMing
 */
@Repository("basPartsBrandDAO")
public class BasPartsBrandDAOImpl extends BaseDaoImpl<BasPartsBrand> implements
        BasPartsBrandDAO
{

    private static final Logger log = Logger
            .getLogger(BasPartsBrandDAO.class);

    // property constants
    public static final String PBRD_NAME = "pbrdName";

    public static final String REPAIR_RATE = "repairRate";

    public static final String SELL_RATE = "sellRate";

    public static final String POINT_RATE = "pointRate";

    public static final String SPECIAL_RATE = "specialRate";

    /**
     * 配件库存量信息 预加载
     */
    public Json loadPartsBrand(final int page, final int rows,
            final String sort, final String order,final int enterprise_id) throws Exception
    {
        List<PartsNowCountVo> list = new ArrayList<PartsNowCountVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT bas_parts_brand.PBRD_ID, bas_parts_brand.PBRD_NAME FROM bas_parts_brand  WHERE bas_parts_brand.enterprise_id="+enterprise_id);
        int count = getSQLCount(sb.toString(), null);
        List<Object[]> resultList = createSQLQuery(sb.toString(), page, rows);
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++)
            {
                PartsNowCountVo pncvo = new PartsNowCountVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    pncvo.setCbrdId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    pncvo.setCbrdName(obj[1].toString());
                list.add(pncvo);
            }
        }
        Json json = new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 配件库存量信息 综合查询
     */
    public Json serachPartsBrandByCondition(final String pbrdId,
            final String pbrdName,final int enterprise_id) throws Exception
    {
        List<PartsNowCountVo> list = new ArrayList<PartsNowCountVo>();
        StringBuffer sb = new StringBuffer(
                "SELECT bas_parts_brand.PBRD_ID,bas_parts_brand.PBRD_NAME FROM bas_parts_brand  WHERE bas_parts_brand.enterprise_id="+enterprise_id);
        if (pbrdId != null && !pbrdId.equals(""))
            sb.append(" AND bas_parts_brand.PBRD_ID LIKE '%" + StringEscapeUtils.escapeSql(pbrdId.trim()) + "%'");
        if (pbrdName != null && !pbrdName.equals(""))
            sb.append(" AND bas_parts_brand.PBRD_NAME LIKE '%" + StringEscapeUtils.escapeSql(pbrdName.trim())+ "%'");
        int count = getSQLCount(sb.toString(), null);
        List<Object[]> resultList = createSQLQuery(sb.toString());
        if (resultList != null && resultList.size() > 0)
        {
            Object[] obj = null;
            for (int i = 0; i < resultList.size(); i++){
                PartsNowCountVo pncvo = new PartsNowCountVo();
                obj = (Object[]) resultList.get(i);
                if (obj[0] != null && !obj[0].equals(""))
                    pncvo.setCbrdId(obj[0].toString());
                if (obj[1] != null && !obj[1].equals(""))
                    pncvo.setCbrdName(obj[1].toString());
                list.add(pncvo);
            }
        }
        Json json = new Json();
        json.setRows(list);
        json.setTotal(count);
        return json;
    }

    /**
     * 基础资料-->配件性质：配件品牌 删除
     */
    public void delete(BasPartsBrand persistentInstance)
    {
        log.debug("deleting BasPartsBrand instance");
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
     * 基础资料-->配件性质：配件品牌 修改
     */
    public BasPartsBrand merge(BasPartsBrand detachedInstance)
    {
        log.debug("merging BasPartsBrand instance");
        try
        {
            BasPartsBrand result = (BasPartsBrand) getHibernateTemplate()
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
     * 基础资料-->配件性质：配件品牌 按ID查询
     */
    public BasPartsBrand findById(Short id)
    {
        log.debug("getting BasPartsBrand instance with id: " + id);
        try
        {
            BasPartsBrand instance = (BasPartsBrand) getHibernateTemplate()
                    .get("com.syuesoft.model.BasPartsBrand", id);
            return instance;
        }
        catch(RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质：根据配件品牌编号，名称查询配件信息
     */
    @SuppressWarnings("unchecked")
    public List searchByPdIdAndPdName(final String pbrdId, final String pbrdName)
            throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {
            
            public List doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                String queryString = "SELECT basPartsBrand.pbrdId,basPartsBrand.pbrdName FROM BasPartsBrand basPartsBrand WHERE 1=1";
                if (pbrdId != null && !pbrdId.equals(""))
                {
                    queryString += " and basPartsBrand.pbrdId="
                            + Short.parseShort(pbrdId);
                }
                if (pbrdName != null && !pbrdName.equals(""))
                {
                    queryString += " and basPartsBrand.pbrdName=" + "'"
                            + pbrdName + "'";
                }
                Query query = session.createQuery(queryString);
                return query.list();
            }
        });
        return list;
    }

    /**
     * 基础资料-->配件性质：配件品牌 全部显示
     */
    @SuppressWarnings("unchecked")
    public List findAll(final BasPartsBrandVo bpbvo)
    {
        log.debug("finding all BasPartsBrand instances");
        try
        {
            String queryString = "from BasPartsBrand bcb where bcb.enterpriseId="+bpbvo.getEnterpriseId();
            return getHibernateTemplate().find(queryString);
        }
        catch(RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 基础资料-->配件性质： 配件品牌 分页
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsBrand> getAllByPage(final BasPartsBrandVo baspartsbrandvo)
    {
        List<BasPartsBrand> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException
                    {
                        String hql = "from BasPartsBrand bcb where bcb.enterpriseId="+baspartsbrandvo.getEnterpriseId();
                        if (baspartsbrandvo.getSort() != null || baspartsbrandvo.getOrder() != null){
                            hql += " order by " + baspartsbrandvo.getSort() + " " + baspartsbrandvo.getOrder();
                        }
                        Query query = session.createQuery(hql);
                        int beginRow = (baspartsbrandvo.getPage() - 1) * baspartsbrandvo.getRows();
                        query.setFirstResult(beginRow);
                        query.setMaxResults(baspartsbrandvo.getRows());
                        return query.list();
                    }
                });
        return list;
    }

    /**
     * 基础资料-->配件性质： 配件品牌 全部显示
     */
    @SuppressWarnings("unchecked")
    public List<BasPartsBrand> findAll(final String param, final int page,
            final int rows) throws Exception
    {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<BasPartsBrand>>()
        {
            
            public List<BasPartsBrand> doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                Query query = session.createQuery(
                        "from BasPartsBrand bpb where 1=1 " + param)
                        .setFirstResult((page - 1) * rows).setMaxResults(rows);
                return query.list();
            }
        });
    }

}