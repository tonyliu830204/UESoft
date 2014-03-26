package com.syuesoft.sell.base.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.DistributorInfoDAO;
import com.syuesoft.sell.base.dao.SupplierInfoDAO;
import com.syuesoft.sell.model.XsDistributorInfo;
import com.syuesoft.sell.model.XsSupplierInfo;

@Repository("supplierInfoDAO")
public class SupplierInfoDAOImpl  extends BaseDaoImpl<XsSupplierInfo> implements SupplierInfoDAO{

}
