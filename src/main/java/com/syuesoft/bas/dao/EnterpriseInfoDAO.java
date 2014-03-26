package com.syuesoft.bas.dao;

import com.syuesoft.base.vo.DistrubtPurviewVo;
import com.syuesoft.model.EnterpriseInfo;

/**企业信息管理DAO*/
public interface EnterpriseInfoDAO extends BaseDaoI<Object> {
	/**获取指定的企业信息*/
	public EnterpriseInfo getEnterpriseInfo(Integer enterpriseId) throws Exception;
}
