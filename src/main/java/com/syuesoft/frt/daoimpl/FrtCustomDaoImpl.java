package com.syuesoft.frt.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.frt.dao.FrtCustomDao;
import com.syuesoft.model.FrtCustom;
/**
 * 客户档案dao
 * @author Liujian
 */
@Repository("frtCustomDao")
public class FrtCustomDaoImpl extends BaseDaoImpl<FrtCustom> implements
        FrtCustomDao
{

	
	public boolean changeCustomId(String customId,String customIdOld, String customName) throws Exception {
/*		 StringBuffer procedureName=new StringBuffer("{CALL custom_id_change(?,?,?)}");
	     List<Object> params = new ArrayList<Object>();
	     params.add(0,customId);
	     params.add(1,customIdOld);
	     params.add(2,customName);
	     List<Object[]> resultList=getProcedureQuery(procedureName.toString(), params);
		 if(resultList!=null&&resultList.size()>0)
			 return true;
	     else
	    	 return false;*/
	     
		Session session = this.getHibernateTemplate().getSessionFactory()
         .getCurrentSession();
		 Connection conn = session.connection();
		 CallableStatement call = conn.prepareCall("{CALL custom_id_change(?,?,?)}");
		 call.setString(1, customId);
		 call.setString(2, customIdOld);
		 call.setString(3, customName);
		 int i = call.executeUpdate();
		 if (i == 0)
			 return false;
		 else
			 return true;
	}

}
