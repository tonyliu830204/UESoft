package com.syuesoft.frt.service;

import org.jfree.chart.JFreeChart;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtQueryVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface ServiceDestClerkAnalyseService {
	public String findServiceDestClerkAnalyse(FrtQueryVo fqVo)throws Exception;//查找维修接待员分析数据
	
	public JFreeChart findSnapMap(FrtQueryVo fqVo)throws Exception;//查找维修接待员分析折线图
	
	public JFreeChart findCakeMap(FrtQueryVo fqVo)throws Exception;//查找维修接待员分析饼图
	
	public String findServiceClassAnalyse(FrtQueryVo fqVo)throws Exception;//查找维修类别分析数据
	
	public JFreeChart findClassSnapMap(FrtQueryVo fqVo)throws Exception;//查找维修类别分析折线图
	
	public JFreeChart findClassCakeMap(FrtQueryVo fqVo)throws Exception;//查找维修类别分析饼图
	
	public DatagridAnalyze findInsurePersonAnalyseMain(FrtQueryVo fqVo)throws Exception;//查找保险送修人分析汇总数据
	
	public Datagrid findInsurePersonAnalyseDetail(FrtQueryVo fqVo)throws Exception;//查找保险送修人分析明细数据
	
	
}
