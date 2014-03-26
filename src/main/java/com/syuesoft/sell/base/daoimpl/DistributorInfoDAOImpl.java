package com.syuesoft.sell.base.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.DistributorInfoDAO;
import com.syuesoft.sell.model.XsDistributorInfo;

@Repository("distributorInfoDAO")
public class DistributorInfoDAOImpl  extends BaseDaoImpl<XsDistributorInfo> implements DistributorInfoDAO{
	public List<XsDistributorInfo> findAllInfo(Integer enterprise_id) throws Exception{
		return this.find("from  XsDistributorInfo  where  enterprise_id="+enterprise_id);
	}
}
