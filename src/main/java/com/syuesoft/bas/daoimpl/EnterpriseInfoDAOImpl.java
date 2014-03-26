package com.syuesoft.bas.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.dao.EnterpriseInfoDAO;
import com.syuesoft.base.vo.DistrubtPurviewVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.EnterpriseInfo;
/**
 * 企业信息DAO实现
 * */
@Repository("enterpriseInfoDAO")
public class EnterpriseInfoDAOImpl  extends BaseDaoImpl<Object> implements EnterpriseInfoDAO{
	/**
	 * 获取指定的企业信息
	 * */
	
	public EnterpriseInfo getEnterpriseInfo(Integer enterpriseId)
			throws Exception {
		return (EnterpriseInfo)get("from EnterpriseInfo ei where ei.enterpriseId="+enterpriseId);
	}

}
