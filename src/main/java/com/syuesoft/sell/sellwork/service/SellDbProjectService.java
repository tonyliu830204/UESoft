package com.syuesoft.sell.sellwork.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;

import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellDbProjectService {
	public Datagrid findSellInfor(SellDbProjectVo sellDbProjectVo)throws Exception;
	public Datagrid findSellDb(SellDbProjectVo sellDbProjectVo)throws Exception;

	public void saveSellPro(SellDbProjectVo sellDbProjectVo)throws Exception;

	public void deleteSellPro(SellDbProjectVo sellDbProjectVo)throws Exception;

	public void updateSellPro(SellDbProjectVo sellDbProjectVo)throws Exception;
	public void updateExamin(SellDbProjectVo sellDbProjectVo)throws Exception;
	//代办项目转结算
	public void updateSellAcount(SellDbProjectVo sellDbProjectVo)throws Exception;

	/**
	 * 判断是否已审核
	 */
	public Boolean isRefundment(SellDbProjectVo sellDbProjectVo) throws Exception;
	
	/**
	 * 判断是否未审核
	 */
	public Boolean isNotRefundment(SellDbProjectVo sellDbProjectVo) throws Exception;
	/**
	 * 判断是否在其他地方使用
	 */
	public Boolean isUse(SellDbProjectVo sellDbProjectVo) throws Exception;
	//代办项目查询:父级信息
	public DatagridAnalyze queryDbInfor(SellDbProjectVo sellDbProjectVo)throws Exception;
	public List queryDbChildInfor (SellDbProjectVo sellDbProjectVo)throws Exception;
	//代办，开票选销售单
	public Datagrid getInvoSellInfor(SellDbProjectVo sellDbProjectVo)throws Exception;
	
	
}
