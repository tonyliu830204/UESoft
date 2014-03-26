package com.syuesoft.sell.base.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.CustomInfoDAO;
import com.syuesoft.sell.model.XsCustomInfo;

@Repository("customInfoDAO")
public class CustomInfoDAOImpl extends BaseDaoImpl<XsCustomInfo> implements CustomInfoDAO{

	
	public List<XsCustomInfo> findByNumber(String customNumber, Integer enid) throws Exception {
		return this.find("from XsCustomInfo custom where 1=1 and  custom.xsCustomNumber='"+customNumber+"' " +
				"and enterpriseId="+enid);
		  
	}
	
	public List<XsCustomInfo> findByNumber(String customNumber,Integer customId,Integer enid) throws Exception {
		return this.find("from XsCustomInfo custom where 1=1 and  custom.xsCustomNumber='"+customNumber+"' " +
				"and custom.customId!='"+customId+"' and enterpriseId="+enid);
		  
	}
	
}
