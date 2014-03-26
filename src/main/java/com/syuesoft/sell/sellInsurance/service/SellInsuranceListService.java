package com.syuesoft.sell.sellInsurance.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellInsuranceListService {
	/**
	 * 保单汇总信息查询
	 * 
	 * @param sellInsuranceListVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getSellInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception;

	/**
	 * 查询保险险种信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getInfo(SellInsuranceListVo sellInsuranceListVo)
			throws Exception;
	/**
	 * 新增保险险种信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getInsurance(SellInsuranceListVo sellInsuranceListVo)throws Exception;


	/**
	 * 新增保单明细信息
	 * 
	 * @param sellInsuranceListVo
	 * @throws Exception
	 */
	public void addSellInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception;

	/**
	 * 修改保单明细信息
	 * 
	 * @param sellInsuranceListVo
	 * @throws Exception
	 */
	public void modifyInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception;

	/**
	 * 删除保单明细信息
	 * 
	 * @param sellInsuranceListVo
	 * @throws Exception
	 */
	public void deleteInsurance(SellInsuranceListVo sellInsuranceListVo)
			throws Exception;
	/**
	 * 判断是否已审核
	 */
	public Boolean isRefundment(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	/**
	 * 判断是否未审核
	 */
	public Boolean isNotRefundment(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	/**
	 * 审核
	 * @param sellInsuranceListVo
	 * @throws Exception
	 */
	public void updateExamine(SellInsuranceListVo sellInsuranceListVo)
			throws Exception;
	/**
	 * 转结算
	 * @param sellInsuranceListVo
	 * @throws Exception
	 */
	public void updateSum(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	/**
	 * 保单汇总信息查询父级菜单
	 * @param sellInsuranceListVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getSellInsuranceF(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	/**
	 * 保单汇总信息查询子级菜单
	 * @param sellInsuranceListVo
	 * @return
	 * @throws Exception
	 */
	public List getInsuranceDetails(SellInsuranceListVo sellInsuranceListVo)throws Exception;
	/**
	 * 查询车辆品牌父级
	 * @param sellInsuranceListVo
	 * @return
	 * @throws Exception
	 */
	public List getCarBrand(SellInsuranceListVo sellInsuranceListVo)throws Exception;
	/**
	 * 查询车辆品牌型号子级菜单
	 * @param sellInsuranceListVo
	 * @return
	 * @throws Exception
	 */
	public List getCarModel(SellInsuranceListVo sellInsuranceListVo)throws Exception;
	/**
	 * 保险车辆查询
	 * @param sellInsuranceListVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getCarInsurance(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	/**
	 * 判断是否在其他地方使用
	 */
	public Boolean isUse(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	//综合分析：车辆代保查询
	public DatagridAnalyze getInsuranceInfor(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	public List getInsuranceDel(SellInsuranceListVo sellInsuranceListVo)throws Exception;

}
