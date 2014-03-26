package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.BasWorkhourSortDao;
import com.syuesoft.model.BasWorkhourSort;

@Repository("basWorkhourSortDao")
public class BasWorkhourSortDaoImpl extends BaseDaoImpl<BasWorkhourSort>
        implements BasWorkhourSortDao
{

    
    public boolean doAdd(BasWorkhourSort basWorkhourSort) throws Exception
    {
        List list = this.getHibernateTemplate().find(
                "from BasWorkhourSort where whSortName='"
                        + basWorkhourSort.getWhSortName() + "'  and enterpriseId="+basWorkhourSort.getEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().save(basWorkhourSort);
            return false;
        }
    }

    
    public void doDelete(BasWorkhourSort basWorkhourSort) throws Exception
    {
        this.getHibernateTemplate().delete(basWorkhourSort);

    }

    @SuppressWarnings("unchecked")
    
    public List<BasWorkhourSort> doFindAll(final int page, final int rows,
            final String sort, final String order,final int enterpriseId) throws Exception
    {
        List<BasWorkhourSort> list = (List) this.getHibernateTemplate()
                .execute(new HibernateCallback()
                {

                    
                    public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException
                    {
                        String queryString = "from BasWorkhourSort bcb where bcb.enterpriseId="+enterpriseId;
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
                        query.setFirstResult((page - 1) * rows).setMaxResults(
                                rows);
                        return query.list();
                    }

                });
        return list;
    }

    
    public boolean doUpdate(BasWorkhourSort basWorkhourSort) throws Exception
    {
        List list = this.getHibernateTemplate().find(
                "from BasWorkhourSort where whSortName='"
                        + basWorkhourSort.getWhSortName()
                        + "' and whSortId not in ("
                        + basWorkhourSort.getWhSortId() + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(basWorkhourSort);
            return false;
        }
    }
}
