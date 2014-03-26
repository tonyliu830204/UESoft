package com.syuesoft.bas.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.dao.FbkDcserveyNameDao;
import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.util.Json;


@Repository("fbkDcserveyNameDao")
public class FbkDcserveyNameDaoImpl extends BaseDaoImpl<FbkDcserveyName> implements FbkDcserveyNameDao {

	
	public boolean Add(FbkDcserveyName fbkDcserveyName)throws Exception {
		List list = this.getHibernateTemplate().find("from FbkDcserveyName where serveyName='"+fbkDcserveyName.getServeyName()+"' and enterpriseId="+fbkDcserveyName.getEnterpriseId() );
		if(list.size()>0){
			return true;
		}else{
			this.getHibernateTemplate().save(fbkDcserveyName);
			return false;
		}
	}

	
	public boolean Update(FbkDcserveyName fbkDcserveyName)throws Exception {
		List list = this.getHibernateTemplate().find("from FbkDcserveyName where serveyName='"+fbkDcserveyName.getServeyName()+"' and dcNameId not in ("+fbkDcserveyName.getDcNameId()+")" );
		if(list.size()>0){
			return true;
		}else{
			this.getHibernateTemplate().update(fbkDcserveyName);
			return false;
		}
	}

	
	public Json findAll(final int page, final int rows,final String sort,final String order,final int enterprise_id)throws Exception {
		List<FbkDcserveyName> list=new ArrayList<FbkDcserveyName>();
		StringBuffer sb=new StringBuffer("SELECT * FROM (SELECT fbk_dcservey_name.DC_NAME_ID AS dcNameId,fbk_dcservey_name.SERVEY_NAME AS serveyName"+
		" ,fbk_dcservey_name.MEMO AS memo,fbk_dcservey_name.enterprise_id AS enterpriseId"+
		"  FROM fbk_dcservey_name) AS a where enterpriseId="+enterprise_id);
		if(sort!=null && !sort.equals("") && order!=null && !order.equals(""))
			sb.append(" order by "+sort+" "+order+"");
		List<Object[]> resultList=createSQLQuery(sb.toString(), page, rows);
		Object[] obj=null;
		int count =getSQLCount(sb.toString(),null);
		if(resultList!=null&&resultList.size()>0){
			for (int i = 0; i < resultList.size(); i++) {
				FbkDcserveyName fbkDcserveyName=new FbkDcserveyName();
				obj=resultList.get(i);
				if(obj[0]!=null&&!obj[0].equals(""))
					fbkDcserveyName.setDcNameId(Integer.parseInt(obj[0].toString()));
				if(obj[1]!=null&&!obj[1].equals(""))
					fbkDcserveyName.setServeyName(obj[1].toString());
				if(obj[2]!=null&&!obj[2].equals(""))
					fbkDcserveyName.setMemo(obj[2].toString());
				if(obj[3]!=null&&!obj[3].equals(""))
					fbkDcserveyName.setEnterpriseId(Integer.parseInt(obj[3].toString()));
				list.add(fbkDcserveyName);
					
			}
		}
		Json json=new Json();
		json.setRows(list);
		json.setTotal(count);
		return json;
	}

}
