package com.syuesoft.sell.allocateManage.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.vo.SellBackVo;
import com.syuesoft.sell.model.XsSellInstorehouseDel;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellBackDao extends BaseDaoI<Object>  {
	/**
	 * 调退汇总信息查询
	 * @param sellBackVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getSellBackInfo(SellBackVo sellBackVo) throws Exception;
	/**
	 * 根据编号查明细信息
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	
	public XsSellInstorehouseDel findDelById(Integer delId) throws Exception;
	/**
	 * 查询
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List getInfor(String sql) throws Exception;
	
	/**
	 * 调退查询模块：调退汇总信息查询
	 * @param sellBackVo
	 * @return
	 * @throws Exception
	 */
	public DatagridAnalyze getSellBack(SellBackVo sellBackVo) throws Exception;
	/**
	 * 添加调退车辆：根据分销商查调拨明细
	 * @param sellBackVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getSellBackByDis(SellBackVo sellBackVo) throws Exception;
	/**
	 *  调退查询模块：调退汇总信息查询子菜单
	 */
	public List<SellBackVo> findBack(SellBackVo sellBackVo)	throws Exception;

}
