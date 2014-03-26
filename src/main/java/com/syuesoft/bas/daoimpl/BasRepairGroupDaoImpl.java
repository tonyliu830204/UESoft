package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasRepairGroupDao;
import com.syuesoft.model.BasRepairGroup;

public class BasRepairGroupDaoImpl extends BaseDaoImpl<BasRepairGroup>
        implements BasRepairGroupDao
{

    
    public boolean Add(BasRepairGroup basRepairGroup,Integer nowEnterpriseId) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasRepairGroup where repgrpName='"
                                + basRepairGroup.getRepgrpName() + "' and enterpriseId="+nowEnterpriseId);
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
        	basRepairGroup.setEnterpriseId(nowEnterpriseId);
            this.getHibernateTemplate().save(basRepairGroup);
            return false;
        }

    }

    
    public boolean Delete(BasRepairGroup basRepairGroup) throws Exception
    {
        String hql = "from BasStuff where repgrpId ="
                + basRepairGroup.getRepgrpId() + "";
        List list = this.getHibernateTemplate().find(hql);
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().delete(basRepairGroup);
            return false;
        }

    }

    
    public boolean Update(BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasRepairGroup where repgrpName='"
                                + basRepairGroup.getRepgrpName()
                                + "' and repgrpId not in ("
                                + basRepairGroup.getRepgrpId() + ")  and  enterpriseId="+nowEnterpriseId);
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
        	basRepairGroup.setEnterpriseId(nowEnterpriseId);
            this.getHibernateTemplate().update(basRepairGroup);
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    
    public List<BasRepairGroup> findAll(final int page, final int rows,
            final String sort, final String order,final int enterpriseId) throws Exception
    {
        List<BasRepairGroup> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasRepairGroup bcb where bcb.enterpriseId="+enterpriseId;
                        if (sort != null && !sort.equals("") && order != null
                                && !order.equals(""))
                        {
                            queryString += " order by " + sort + " " + order
                                    + "";
                        }
                        org.hibernate.Query query = session
                                .createQuery(queryString);
                        HttpSession sesion = ServletActionContext.getRequest()
                                .getSession();
                        sesion.setAttribute("size", query.list().size());
                        int beginRow = (page - 1) * rows;
                        query.setFirstResult(beginRow);
                        query.setMaxResults(rows);
                        return query.list();
                    }

                });
        return list;
    }

    /*
     * (non-Javadoc) 条件查询
     * 
     * @see com.syuesoft.DataBase.Dao.BasRepairGroupDao#findByCondition(int,
     * int, com.syuesoft.model.BasRepairGroup)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasRepairGroup> findByCondition(final int page, final int rows,
    		final BasRepairGroup basRepairGroup,   final Integer  nowEnterpriseId)
    {
        List<BasRepairGroup> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasRepairGroup bcb where bcb.enterpriseId="+nowEnterpriseId;
                        if (basRepairGroup.getRepgrpId() != null)
                        {
                            queryString += "and repgrpId like '%"
                                    + StringEscapeUtils.escapeSql(basRepairGroup.getRepgrpId().toString().trim()) + "%'";
                        }
                        if (basRepairGroup.getRepgrpName() != null
                                && !basRepairGroup.getRepgrpName().trim()
                                        .equals(""))
                        {
                            queryString += "and repgrpName like '%"
                                    + StringEscapeUtils.escapeSql(basRepairGroup.getRepgrpName().trim()) + "%'";
                        }
                        org.hibernate.Query query = session
                                .createQuery(queryString);
                        int totlesize = query.list().size();
                        HttpSession sesion = ServletActionContext.getRequest()
                                .getSession();
                        sesion.setAttribute("totlesize", totlesize);
                        int beginRow = (page - 1) * rows;
                        query.setFirstResult(beginRow);
                        query.setMaxResults(rows);
                        return query.list();
                    }

                });
        return list;

    }
}