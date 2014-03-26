package com.syuesoft.sell.instore.dao;

import java.util.Date;
import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.model.XsSellInstorehouse;
import com.syuesoft.sell.model.XsSellInstorehouseDel;

public interface InstorehouseDAO extends BaseDaoI<BaseBean>{
	public List<XsSellInstorehouseDel> findByInstoreId(Integer instoreId)throws Exception;
	public XsSellInstorehouse findById(Integer instoreId) throws Exception;
	public XsSellInstorehouseDel findDelById(Integer delId) throws Exception;
	public List<Object[]> queryInstore(int page, int rows) throws Exception;
	public Float GetInstoreAge(Date instoreAge)throws Exception;

}