package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.ReptypeDao;
import com.syuesoft.model.Reptype;

@Repository("reptypeDao")
public class ReptypeDaoImpl extends BaseDaoImpl<Reptype> implements ReptypeDao
{

    
    public boolean Add(Reptype reptype) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from Reptype where reptName='" + reptype.getReptName()
                                + "' and enterpriseId="+reptype.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            super.save(reptype);
            return false;
        }

    }

    
    public void Delete(Reptype reptype) throws Exception
    {
        this.getHibernateTemplate().delete(reptype);
    }

    
    public boolean Update(Reptype reptype) throws Exception
    {
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from Reptype where reptName='" + reptype.getReptName()
                                + "' and reptId not in (" + reptype.getReptId()
                                + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(reptype);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    
    public List<Reptype> findAll(final int page, final int rows,
            final String sort, final String order, final int enterpriseId) throws Exception
    {
        List<Reptype> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {
                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from Reptype bcb where bcb.enterpriseId="+enterpriseId;
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
     * @see com.syuesoft.DataBase.Dao.ReptypeDao#findByCondition(int, int,
     * com.syuesoft.model.Reptype)
     */
    @SuppressWarnings("unchecked")
    
    public List<Reptype> findByCondition(final int page, final int rows,
            final Reptype reptype) throws Exception
    {

        List<Reptype> list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from Reptype where bcb where bcb.enterpriseId="+reptype.getEnterpriseId();
                        if (reptype.getReptId() != null)
                        {
                            queryString += "and reptId = "
                                    + reptype.getReptId() + " ";
                        }
                        if (reptype.getReptName() != null)
                        {
                            queryString += "and reptName like %"
                                    + StringEscapeUtils.escapeSql(reptype.getReptName().trim()) + "% ";
                        }
                        if (reptype.getPartCreditsRate() != null)
                        {
                            queryString += "and partCreditsRate like %"
                                    + StringEscapeUtils.escapeSql(reptype.getPartCreditsRate().toString().trim()) + "%";
                        }
                        if (reptype.getWorkCreditsRate() != null)
                        {
                            queryString += "and workCreditsRate like %"
                                    + StringEscapeUtils.escapeSql(reptype.getWorkCreditsRate().toString().trim()) + "%";
                        }
                        if (reptype.getSumCreditsRate() != null)
                        {
                            queryString += "and sumCreditsRate like %"
                                    + StringEscapeUtils.escapeSql(reptype.getSumCreditsRate().toString().trim()) + "%";
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