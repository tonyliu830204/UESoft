package com.syuesoft.systemmanagement.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.systemmanagement.vo.CustomerPerformanceStatisticsVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface CustomerPerformanceStatisticsService {
	
	
	//维修人员业绩统计(按维修员)
	public DatagridAnalyze findServicePersonScore(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//维修人员业绩统计-子项信息(按维修员)
	public List findServicePersonScoreChild(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//维修人员业绩统计(按工单号)
	public DatagridAnalyze findServicePersonScoreForReceptionId(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//维修人员业绩统计-子项信息(按工单号)
	public List findServicePersonScoreChildForReceptionId(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//结算工时查询 (按维修员)
	public DatagridAnalyze findBalanceHoursQuery(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//结算工时查询-子项信息(按维修员)
	public List findBalanceHoursQueryChild(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//结算工时查询 (按接待员)
	public DatagridAnalyze findBalanceHoursQueryForRecivePerson(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//结算工时查询-子项信息(按接待员)
	public List findBalanceHoursQueryChildForRecivePerson(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//维修人员业绩统计汇总
	public DatagridAnalyze findServicePersonMain(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//索赔结算工时统计main
	public Datagrid findClaimsHoursMain(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
	//索赔结算工时统计detail
	public Datagrid findClaimsHoursDetail(CustomerPerformanceStatisticsVo cpsVo)throws Exception;
}
