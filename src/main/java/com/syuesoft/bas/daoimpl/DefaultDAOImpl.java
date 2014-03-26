package com.syuesoft.bas.daoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.DefaultDAO;

@Repository("defaultDAO")
public class DefaultDAOImpl extends BaseDaoImpl<Object> implements DefaultDAO
{

    @SuppressWarnings("unchecked")
    
    public List getObjList(String hql)
    {
        List list = this.getHibernateTemplate().find(hql);
        return list;
    }

    @SuppressWarnings("unchecked")
    public List getSQLObjList(String sql)
    {
        List list = this.getSessionFactory().openSession().createSQLQuery(sql)
                .list();
        return list;
    }

    
    public Object getObj(String hql)
    {
        return this.getHibernateTemplate().find(hql).get(0);
    }

}
