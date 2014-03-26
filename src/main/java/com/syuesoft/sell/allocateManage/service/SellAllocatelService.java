package com.syuesoft.sell.allocateManage.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellAllocatelService {
	/**
	 * 调拨单查询
	 */

	public Datagrid querySellAllocatel(
			SellAllocatelVo sellAllocatelVo) throws Exception;


	/**
	 * 修改调拨单
	 */
	public void modifySellAllocatel(SellAllocatelVo SellAllocatelVo)
			throws Exception;

	/**
	 * 新增调拨单信息
	 * 
	 * @param sellAllocatelVo
	 * @throws Exception
	 */
	public void addInstoreCar(SellAllocatelVo sellAllocatelVo) throws Exception;

	
	/**
	 * 删除汇总信息
	 */
	public void deleteRecord(SellAllocatelVo sellAllocatelVo) throws Exception;
	/**
	 * 判断是否已审核
	 */
	public Boolean isRefundment(SellAllocatelVo sellAllocatelVo) throws Exception;
	/**
	 * 审核
	 * @param sellAllocatelVo
	 * @throws Exception
	 */
	public void  updateExamine(SellAllocatelVo sellAllocatelVo) throws Exception;
	/**
	 * 根据分销商查调拨单
	 */
	public Datagrid queryList(
			SellAllocatelVo sellAllocatelVo) throws Exception;
	/**
	 *  调拨单查询模块，查询父菜单信息功能
	 * @param sellAllocatelVo
	 * @return
	 * @throws Exception
	 */
	public DatagridAnalyze  queryFather(
			SellAllocatelVo sellAllocatelVo) throws Exception;
	/**
	 *  调拨单查询模块，查询子菜单信息功能
	 * @param sellAllocatelVo
	 * @return
	 * @throws Exception
	 */
	public List<SellAllocatelVo> findAllocatel(SellAllocatelVo sellAllocatelVo)	throws Exception;
	/**
	 * 判断是否在其他地方使用
	 */
	public Boolean isUse(SellAllocatelVo sellAllocatelVo) throws Exception;
	
}
