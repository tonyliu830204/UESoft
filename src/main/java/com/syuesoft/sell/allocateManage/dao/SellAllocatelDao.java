package com.syuesoft.sell.allocateManage.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.model.XsSellAllocatel;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellAllocatelDao extends BaseDaoI<BaseBean> {
	// 调拨单查询
	public Datagrid querySellAllocatel(
			SellAllocatelVo sellAllocatelVo) throws Exception;

	// 根据编号查明细信息
	public XsSellInstorehouseDel findDelById(Integer delId) throws Exception;

	// 根据id查汇总信息
	public XsSellAllocatel findById(Integer instoreId) throws Exception;

	public List getInfor(String sql) throws Exception;
	// 调拨单查询模块，查询功能
	public List<SellAllocatelVo> queryAllocatelList(
			SellAllocatelVo sellAllocatelVo) throws Exception;
	// 调拨单查询模块，查询父菜单信息功能
	public DatagridAnalyze queryFather(
			SellAllocatelVo sellAllocatelVo) throws Exception;
	// 调拨单查询模块，查询子菜单信息功能
	public List<SellAllocatelVo> findAllocatel(SellAllocatelVo sellAllocatelVo)	throws Exception;
}
