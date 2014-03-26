package com.syuesoft.sell.pcassay.service;

import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import com.syuesoft.sell.pcassay.vo.PotentialcustomerAssayVo;
import com.syuesoft.util.Json;

public interface PotentialcustomerAssayService {
	// 获取成交障碍类型数量级百分比
	public List getPotentialCustomerAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	//
	public JFreeChart getJFreeChart(List list, PieDataset pieDataset,
			String titles) throws Exception;

	//
	public PieDataset createPieDatasetOfPotentialCustomerAssay(List list)
			throws Exception;

	//
	public List getBargainonAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	//
	public PieDataset createPieDatasetOfBargainonAssay(List list);

	//
	public PieDataset createPieDatasetOfCustomSource(List list);

	//
	public List getCustomSourceAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 跟踪部门分析
	public PieDataset createPieDatasetOfDeptAssay(List list) throws Exception;

	// 获取跟踪部门级跟踪数量 和 百分比
	public List getDeptAssay(PotentialcustomerAssayVo potentialcustomerAssayVo)
			throws Exception;

	// 跟踪班组分析
	public PieDataset createPieDatasetOfSellTeamAssay(List list)
			throws Exception;

	// 获取跟踪班组分析信息
	public List getSellTeamAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 跟踪业务员分析
	public PieDataset createPieDatasetOfStfNameAssay(List list)
			throws Exception;

	// 获取跟踪业务员分析信息
	public List getStfNameAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 潜在客户等级 图形报表
	public PieDataset createPieDatasetOfCustomerLevelAssay(List list)
			throws Exception;

	// 潜在客户等级 分析信息
	public List getCustomerLevelAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 购买车型号分析信息
	public List getBuyCarModelAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 购买车型号分析信息 图形报表
	public PieDataset createPieDatasetOfBuyCarModelAssay(List list)
			throws Exception;

	// 战败原因分析信息
	public List getLoseBellAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 战败原因分析信息 图形报表
	public PieDataset createPieDatasetOfLoseBell(List list) throws Exception;

	// 战败车型分析信息
	public List getLoseBellCarModelAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 战败车型分析信息 图形报表
	public PieDataset createPieDatasetOfLoseBellCarModel(List list)
			throws Exception;

	// 战败部门分析信息
	public List getLoseBellDepartAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 战败部门分析信息 图形报表
	public PieDataset createPieDatasetOfLoseBellDepart(List list)
			throws Exception;

	// 战败班组分析信息
	public List getLoseBellGroupAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 战败班组分析信息 图形报表
	public PieDataset createPieDatasetOfLoseBellGroup(List list)
			throws Exception;

	// 战败业务分析信息
	public List getLoseBellWorkAssay(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

	// 战败业务分析信息 图形报表
	public PieDataset createPieDatasetOfLoseBellWork(List list)
			throws Exception;

	// 获取战败客户记录
	public String getLosedCustomerRecord(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;
	
	//获取战败客户记录动态列
	public List getLosedCustomerColumn(
			PotentialcustomerAssayVo potentialcustomerAssayVo) throws Exception;

}
