package com.syuesoft.sell.allocateManage.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.allocateManage.vo.SellBackVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellBackService {
	public Datagrid getSellBackInfo(SellBackVo sellBackVo) throws Exception;

	/**
	 * 新增调退单信息
	 * 
	 * @param SellBackVo
	 * @throws Exception
	 */
	public void addInstoreCar(SellBackVo SellBackVo) throws Exception;
	/**
	 * 删除汇总
	 * @param SellBackVo
	 * @throws Exception
	 */
	public void deleteRecord(SellBackVo sellBackVo) throws Exception;
	/**
	 * 修改汇总
	 * @param sellBackVo
	 * @throws Exception
	 */
	public void modifySellAllocatel(SellBackVo  sellBackVo )throws Exception;
	/**
	 * 判断是否已审核
	 */
	public Boolean isRefundment(SellBackVo sellBackVo) throws Exception;
	/**
	 * 审核
	 * @param sellAllocatelVo
	 * @throws Exception
	 */
	public void  updateExamine(SellBackVo sellBackVo) throws Exception;
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
	/**
	 * 调退查询模块：调退汇总信息查询
	 * @param sellBackVo
	 * @return
	 * @throws Exception
	 */
	public DatagridAnalyze getSellBack(SellBackVo sellBackVo) throws Exception;

}
