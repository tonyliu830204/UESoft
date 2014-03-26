package com.syuesoft.sell.allocateManage.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.vo.InstorehouseVo;

public interface InstorehouseDao extends BaseDaoI<BaseBean> {
	//库存查询
	public Datagrid queryInstorehouse(InstorehouseVo instorehouseVo) throws Exception;
	//库存查询
	public List queryInstorehouseSum(InstorehouseVo instorehouseVo) throws Exception;

}
