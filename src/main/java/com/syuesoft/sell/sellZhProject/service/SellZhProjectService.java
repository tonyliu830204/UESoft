package com.syuesoft.sell.sellZhProject.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.sell.sellZhProject.vo.SellZhProjectVo;
import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellZhProjectService {

	public Datagrid findSellInfor(SellZhProjectVo sellZhProjectVo)throws Exception;
	
	/**
	 * 根据销售单号查装潢项目清单
	 * @param sellZhProjectVo
	 * @return
	 * @throws Exception
	 */
	public  Datagrid getZhList(SellZhProjectVo sellZhProjectVo) throws Exception;
	/**
	 * 保存新增信息
	 * @param sellZhProjectVo
	 * @return
	 * @throws Exception
	 */
	public void saveSellZh(SellZhProjectVo sellZhProjectVo)throws Exception;
	/**
	 * 修改信息
	 * @param sellZhProjectVo
	 * @return
	 * @throws Exception
	 */
	public void updateSellZh(SellZhProjectVo sellZhProjectVo)throws Exception;
	/**
	 * 删除信息
	 * @param sellZhProjectVo
	 * @return
	 * @throws Exception
	 */
	public void deleteSellZh(SellZhProjectVo sellZhProjectVo)throws Exception;
	/**
	 * 判断是否已审核
	 */
	public Boolean isRefundment(SellZhProjectVo sellZhProjectVo) throws Exception;
	
	/**
	 * 判断是否未审核
	 */
	public Boolean isNotRefundment(SellZhProjectVo sellZhProjectVo) throws Exception;
	/**
	 * 审核
	 * @param sellZhProjectVo
	 * @throws Exception
	 */
	public void updateExamin(SellZhProjectVo sellZhProjectVo)throws Exception;
	/**
	 * 转预算
	 * @param sellZhProjectVo
	 * @throws Exception
	 */
	public void updateSum(SellZhProjectVo sellZhProjectVo)throws Exception;
	public  Datagrid getSellList(SellZhProjectVo sellZhProjectVo) throws Exception;
	/**
	 * 判断是否在其他地方使用
	 */
	public Boolean isUse(SellZhProjectVo sellZhProjectVo) throws Exception;
	//综合分析：装潢项目查询
	public DatagridAnalyze getZhInfor(SellZhProjectVo sellZhProjectVo) throws Exception;
	public List getZhDelInfor(SellZhProjectVo SellZhProjectVo)throws Exception ;

}
