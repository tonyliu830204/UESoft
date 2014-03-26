package com.syuesoft.sell.base.daoimpl;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.CustomLevaDAO;
import com.syuesoft.sell.base.vo.CustomLevaVo;
import com.syuesoft.sell.model.XsCustomLeva;

@Repository("customLevaDAO")
public class CustomLevaDAOImpl extends BaseDaoImpl<XsCustomLeva> implements CustomLevaDAO{
	public List<XsCustomLeva> findAllLeva(CustomLevaVo leva1) throws Exception{
		String hql="from  XsCustomLeva leva where 1=1 and leva.enterpriseId="+leva1.getEnterpriseId();
			if(leva1.getQ()!=null && !("".equals(leva1.getQ()))){
				hql+="	and leva.levaName like '%"+StringEscapeUtils.escapeSql(leva1.getQ().trim())+"%'";
			}
		return this.find(hql);
	}
}
