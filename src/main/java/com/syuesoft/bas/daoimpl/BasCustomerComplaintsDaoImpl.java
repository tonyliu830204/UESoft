package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.List;
import com.syuesoft.bas.dao.BasCustomerComplaintsDao;
import com.syuesoft.model.BasCustomerComplaints;
import com.syuesoft.util.Json;

public class BasCustomerComplaintsDaoImpl extends
        BaseDaoImpl<BasCustomerComplaints> implements BasCustomerComplaintsDao
{

    
    public boolean Add(BasCustomerComplaints basCustomerComplaints)
    {
        List list = this.getHibernateTemplate().find(
                "from BasCustomerComplaints where complaintsType='"
                        + basCustomerComplaints.getComplaintsType() + "' and enterpriseId="+basCustomerComplaints.getEnterpriseId());
        if (list.size() > 0)
            return true;
        else
        {
            this.getHibernateTemplate().save(basCustomerComplaints);
            return false;
        }
    }

    
    public void Delete(BasCustomerComplaints basCustomerComplaints)
            throws Exception{
        this.getHibernateTemplate().delete(basCustomerComplaints);
    }

    
    public boolean Update(BasCustomerComplaints basCustomerComplaints)
            throws Exception
    {
        List list = this.getHibernateTemplate().find(
                "from BasCustomerComplaints where complaintsType='"
                        + basCustomerComplaints.getComplaintsType()
                        + "' and complaintsId not in ("
                        + basCustomerComplaints.getComplaintsId() + ")");
        if (list.size() > 0)
        {
            return true;
        }
        else
        {
            this.getHibernateTemplate().update(basCustomerComplaints);
            return false;
        }
    }

    
    public Json findAll(final int page, final int rows,
            final String sort, final String order,final int enterprise_id) throws Exception
    {
    	List<BasCustomerComplaints> list=new ArrayList<BasCustomerComplaints>();
    	 String queryString = "SELECT * FROM (SELECT complaints_id AS complaintsId,complaints_type AS complaintsType,"+
		 "complaints_content AS complaintsContent FROM bas_customer_complaints WHERE "+
		 "bas_customer_complaints.enterprise_id="+enterprise_id+") AS A where 1=1";
         if (sort != null && !sort.equals("") && order != null&& !order.equals(""))
             queryString += " order by " + sort + " " + order+ "";
    	 List<Object[]> resultList=createSQLQuery(queryString);
    	 int count =getSQLCount(queryString, null);
    	 if(resultList!=null&&resultList.size()>0){
    		Object[] obj=null;
    		for (int i = 0; i < resultList.size(); i++) {
    			BasCustomerComplaints bcc=new BasCustomerComplaints();
				obj=resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					bcc.setComplaintsId(Integer.parseInt(obj[0].toString()));
				if(obj[1]!=null&&!obj[1].equals(""))
					bcc.setComplaintsType(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					bcc.setComplaintsContent(obj[2].toString());
				list.add(bcc);
			}
    	}
    	Json json=new Json();
    	json.setRows(list);
    	json.setTotal(count);
    	return json;
    }

    
    public List<BasCustomerComplaints> getTotle() throws Exception
    {
        String hql = "from BasCustomerComplaints";
        return this.getHibernateTemplate().find(hql);
    }

    
    public List<BasCustomerComplaints> findByCondition(int page, int rows,BasCustomerComplaints basCustomerComplaints) throws Exception
    {
        return null;
    }

}
