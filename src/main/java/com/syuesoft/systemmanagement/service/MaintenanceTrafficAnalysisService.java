package com.syuesoft.systemmanagement.service;

import java.util.List;

import org.jfree.chart.JFreeChart;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.systemmanagement.vo.MaintenanceTrafficAnalysisVo;

public interface MaintenanceTrafficAnalysisService{
	
	public Datagrid findMaintenanceTrafficAnalysisServiceLog(MaintenanceTrafficAnalysisVo mtaVo) throws Exception;//查找维修记录信息
	
	public List findMaintenanceTrafficAnalysisServiceLogByInterDate(MaintenanceTrafficAnalysisVo mtaVo) throws Exception;//查找维修记录信息-子项
	
	public DatagridAnalyze findMaintenanceTrafficAnalysisServiceTimeAnalyze(MaintenanceTrafficAnalysisVo mtaVo)throws Exception;//查找维修时间段分析信息
	
	public String findMaintenanceTrafficAnalysisServiceReceiveAnalyze(MaintenanceTrafficAnalysisVo mtaVo) throws Exception;//查找维修接待员分析信息
	
	public DatagridAnalyze findMaintenanceTrafficAnalysisServiceBrandAnalyze(MaintenanceTrafficAnalysisVo mtaVo) throws Exception;//查找维修品牌分析信息
	
	public Datagrid findMaintenanceTrafficAnalysisServicingCarAnalyze(MaintenanceTrafficAnalysisVo mtaVo) throws Exception;//查找车辆在修情况分析信息
	
	public List findMaintenanceTrafficAnalysisServicingCarAnalyzeByCbrdId(MaintenanceTrafficAnalysisVo mtaVo) throws Exception;//查找车辆在修情况分析信息
	
	public JFreeChart findServiceTimeAnalyzeSnapMap(MaintenanceTrafficAnalysisVo mtaVo)throws Exception;//获取维修时间段分析折线图信息 
	
	public JFreeChart findServiceTimeAnalyzeCakeMap(MaintenanceTrafficAnalysisVo mtaVo)throws Exception;//获取维修时间段分析饼图信息
	
	public JFreeChart findServiceReceiveAnalyzePoleMap(MaintenanceTrafficAnalysisVo mtaVo)throws Exception;//获取维修接待员分析柱状图信息  
	
	public JFreeChart findServiceReceiveAnalyzeCakeMap(MaintenanceTrafficAnalysisVo mtaVo)throws Exception;//获取维修接待员分析饼图信息
}
