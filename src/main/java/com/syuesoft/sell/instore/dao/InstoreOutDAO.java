package com.syuesoft.sell.instore.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.sell.instore.vo.SellServicingVo;
import com.syuesoft.sell.model.XsSellInstorehouse;
import com.syuesoft.sell.model.XsSellInstorehouseDel;

public interface InstoreOutDAO extends BaseDaoI<BaseBean>{
	public List<XsSellInstorehouseDel> findByInstoreId(Integer instoreId)throws Exception;
	public XsSellInstorehouse findById(Integer instoreId) throws Exception;
	public XsSellInstorehouseDel findDelById(Integer delId) throws Exception;
	public List<SellRetreatVo> findByCarId(Integer sellId)throws Exception;

}