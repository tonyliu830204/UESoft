package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.InsuranceTypeDao;
import com.syuesoft.fbk.vo.InsuranceTypeVo;
import com.syuesoft.model.InsuranceType;

@Repository("insuranceTypeDao")
public class InsuranceTypeDaoImpl extends BaseDaoImpl<InsuranceType> implements
        InsuranceTypeDao
{

    
    public boolean Add(InsuranceTypeVo insuranceTypeVo) throws Exception
    {
        InsuranceType in = new InsuranceType();
        in.setIncount(insuranceTypeVo.getIncount());
        in.setInfee(insuranceTypeVo.getInfee());
        in.setInfeelv(insuranceTypeVo.getInfeelv());
        in.setIntype(insuranceTypeVo.getIntype());
        in.setMemo(insuranceTypeVo.getMemo());
        in.setEnterpriseId(insuranceTypeVo.getEnterpriseId());
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from InsuranceType where intype='"
                                + insuranceTypeVo.getIntype() + "'  and  enterpriseId="+insuranceTypeVo.getNowEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().save(in);
            return false;
        }

    }

    
    public void Delete(InsuranceTypeVo insuranceTypeVo) throws Exception
    {
        InsuranceType in = new InsuranceType();
        in.setId(Integer.parseInt(insuranceTypeVo.getId().toString()));
        in.setIncount(insuranceTypeVo.getIncount());
        in.setInfee(insuranceTypeVo.getInfee());
        in.setInfeelv(insuranceTypeVo.getInfeelv());
        in.setIntype(insuranceTypeVo.getIntype());
        in.setMemo(insuranceTypeVo.getMemo());
        this.getHibernateTemplate().delete(in);
    }

    
    public boolean Update(InsuranceTypeVo insuranceTypeVo) throws Exception
    {
        InsuranceType in = new InsuranceType();
        in.setId(Integer.parseInt(insuranceTypeVo.getId().toString()));
        in.setIncount(insuranceTypeVo.getIncount());
        in.setInfee(insuranceTypeVo.getInfee());
        in.setInfeelv(insuranceTypeVo.getInfeelv());
        in.setIntype(insuranceTypeVo.getIntype());
        in.setMemo(insuranceTypeVo.getMemo());
        in.setEnterpriseId(insuranceTypeVo.getEnterpriseId());
        List list = this.getHibernateTemplate().getSessionFactory()
                .getCurrentSession().find(
                        "from InsuranceType where intype='"
                                + insuranceTypeVo.getIntype()
                                + "' and id not in (" + insuranceTypeVo.getId()
                                + ")  and  enterpriseId="+insuranceTypeVo.getNowEnterpriseId());
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(in);
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    
    public List findAll(final int page, final int rows, final String sort,
            final String order,final int enterpriseId) throws Exception
    {
        List list = this.getHibernateTemplate().execute(new HibernateCallback<List>()
        {
            
            public List doInHibernate(Session hsession)
                    throws HibernateException, SQLException
            {
                String hql = "from InsuranceType bcb where bcb.enterpriseId="+enterpriseId;
                if (sort != null && !sort.equals("") && order != null&& !order.equals("")){
                    hql += " order by " + sort + " " + order + "";
                }
                Query query = hsession.createQuery(hql);
                HttpSession session = ServletActionContext.getRequest().getSession();
                session.setAttribute("size", query.list().size());
                int begin = (page - 1) * rows;
                query.setFirstResult(begin);
                query.setMaxResults(rows);
                return query.list();
            }
        });
        return list;
    }
}