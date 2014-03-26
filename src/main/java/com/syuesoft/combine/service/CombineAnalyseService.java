package com.syuesoft.combine.service;

import org.jfree.chart.JFreeChart;

import com.syuesoft.combine.vo.CombineAnalyseVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

/**
 * 集团分析Service
 * @author LWJ
 * */
public interface CombineAnalyseService {
	/**
	 * 查询接待台次分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return String  返回数据表格对象源代码或数据字符串
	 * */
	public String receptionCountAnalyse(CombineAnalyseVo combineAnalyseVo)throws Exception;
	/**
	 * 获取接待台次分析折线图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	public JFreeChart findReceptionCountAnalyseSnapMap(CombineAnalyseVo combineAnalyseVo)throws Exception;
	/**
	 * 查询结算台次分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return String  返回数据表格对象源代码或数据字符串
	 * */
	public String balanceCountAnalyse(CombineAnalyseVo combineAnalyseVo)throws Exception;
	/**
	 * 获取结算台次分析折线图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	public JFreeChart findBalanceCountAnalyseSnapMap(CombineAnalyseVo combineAnalyseVo)throws Exception;
	/**
	 * 查询结算费用分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return DatagridAnalyze  返回数据表格统计对象
	 * */
	public DatagridAnalyze findBalanceRateAnalyse(CombineAnalyseVo combineAnalyseVo)throws Exception;
	/**
	 * 查询结算费用分析柱状图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	public JFreeChart findBalanceRateAnalysePoleMap(CombineAnalyseVo combineAnalyseVo)throws Exception;
	/**
	 * 查询应收账款分析信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return String  返回数据表格对象源代码或数据字符串
	 * */
	public String findAccountReceivableAnalyse(CombineAnalyseVo combineAnalyseVo)throws Exception;
	/**
	 * 获取应收账款分析折线图信息
	 * @param combineAnalyseVo 集团分析视图对象
	 * @exception Exception 总异常
	 * @return JFreeChart  返回JFreeChart图表组件对象 
	 * */
	public JFreeChart findAccountReceivableAnalyseSnapMap(CombineAnalyseVo combineAnalyseVo)throws Exception;
}
