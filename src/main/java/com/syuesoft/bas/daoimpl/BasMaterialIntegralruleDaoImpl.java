package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.BasMaterialIntegralruleDao;
import com.syuesoft.model.BasMaterialIntegralrule;

@Repository("basMaterialIntegralruleDao")
public class BasMaterialIntegralruleDaoImpl extends
        BaseDaoImpl<BasMaterialIntegralrule> implements
        BasMaterialIntegralruleDao
{

    
    public boolean Add(BasMaterialIntegralrule basMaterialIntegralrule)
            throws Exception
    {
        String sql = "from BasMaterialIntegralrule where materialIntegralruleId="
                + basMaterialIntegralrule.getMaterialIntegralruleId();
        List<BasMaterialIntegralrule> list = this.getHibernateTemplate().find(
                sql);
        if (list.size() == 0)
        {
            this.getHibernateTemplate().save(basMaterialIntegralrule);
            return true;
        }
        else
        {
            return false;
        }
    }

    
    public void Delete(BasMaterialIntegralrule basMaterialIntegralrule)
    {
        this.getHibernateTemplate().delete(basMaterialIntegralrule);
    }

    
    public void Update(BasMaterialIntegralrule basMaterialIntegralrule)
    {
        this.getHibernateTemplate().update(basMaterialIntegralrule);
    }

    @SuppressWarnings("unchecked")
    
    public List<BasMaterialIntegralrule> findAll(final int page, final int rows)
    {
        List<BasMaterialIntegralrule> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasMaterialIntegralrule ";
                        org.hibernate.Query query = session
                                .createQuery(queryString);
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
     * @see
     * com.syuesoft.DataBase.Dao.BasMaterialIntegralruleDao#findByCondition(int,
     * int, com.syuesoft.model.BasMaterialIntegralrule)
     */
    @SuppressWarnings("unchecked")
    
    public List<BasMaterialIntegralrule> findByCondition(final int page,
            final int rows,
            final BasMaterialIntegralrule basMaterialIntegralrule)
    {

        List<BasMaterialIntegralrule> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasMaterialIntegralrule ";//
                        if (basMaterialIntegralrule
                                .getMaterialIntegralruleManner() != null
                                && !basMaterialIntegralrule
                                        .getMaterialIntegralruleManner().trim()
                                        .equals(""))
                        {
                            queryString += "where claimsName like '%"
                                    + StringEscapeUtils.escapeSql(basMaterialIntegralrule.getMaterialIntegralruleManner().trim())
                                    + "%'";
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

    
    public List<BasMaterialIntegralrule> getTotle()
    {
        String hql = "from BasMaterialIntegralrule";
        return this.getHibernateTemplate().find(hql);
    }
}