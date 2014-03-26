package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasRepairWorkDao;
import com.syuesoft.model.BasRepairWork;

public class BasRepairWorkDaoImpl extends BaseDaoImpl<BasRepairWork> implements
        BasRepairWorkDao
{

    
    public boolean Add(BasRepairWork basRepairWork) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasRepairWork where repwkName='"
                                + basRepairWork.getRepwkName() + "' AND enterpriseId="+basRepairWork.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().save(basRepairWork);
            return false;
        }
    }

    
    public void Delete(BasRepairWork basRepairWork)
    {
        this.getHibernateTemplate().delete(basRepairWork);
    }

    
    public boolean Update(BasRepairWork basRepairWork)
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from BasRepairWork where repwkName='"
                                + basRepairWork.getRepwkName()
                                + "' and repwkId not in ("
                                + basRepairWork.getRepwkId() + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(basRepairWork);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    
    public List<BasRepairWork> findAll(final int page, final int rows,
            final String sort, final String order,final int enterpriseId)
    {
        List<BasRepairWork> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasRepairWork bcb where bcb.enterpriseId="+enterpriseId;
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
     * @see com.syuesoft.DataBase.Dao.BasRepairWorkDao#findByCondition(int, int,
     * com.syuesoft.model.BasRepairWork)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasRepairWork> findByCondition(final int page, final int rows,
            final BasRepairWork basRepairWork)
    {

        List<BasRepairWork> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasRepairWork bcb where bcb.enterpriseId="+basRepairWork.getEnterpriseId();
                        if (basRepairWork.getRepwkName() != null
                                && !basRepairWork.getRepwkName().trim().equals(
                                        ""))
                        {
                            queryString += "and repwkName like '%"
                                    + StringEscapeUtils.escapeSql(basRepairWork.getRepwkName().trim()) + "%'";
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