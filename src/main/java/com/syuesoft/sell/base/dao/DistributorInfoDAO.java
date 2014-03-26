package com.syuesoft.sell.base.dao;
import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsDistributorInfo;

public interface DistributorInfoDAO extends BaseDaoI<XsDistributorInfo> {
	public List<XsDistributorInfo> findAllInfo( Integer enterprise_id) throws Exception;
}
