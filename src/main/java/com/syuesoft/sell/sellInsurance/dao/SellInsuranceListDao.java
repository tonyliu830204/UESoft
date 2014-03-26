package com.syuesoft.sell.sellInsurance.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellInsuranceListDao  extends BaseDaoI<BaseBean>{
	/**
	 * 保单汇总信息查询
	 * @param sellInsuranceListVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getSellInsurance(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	/**
	 * 查询保险险种信息
	 * @return
	 * @throws Exception
	 */
	public List getinfo(SellInsuranceListVo sellInsuranceListVo)throws Exception;
	
	
	
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
	//综合分析：车辆代保查询
	public DatagridAnalyze getInsuranceInfor(SellInsuranceListVo sellInsuranceListVo) throws Exception;
	public List getInsuranceDel(SellInsuranceListVo sellInsuranceListVo)throws Exception;
	
}
