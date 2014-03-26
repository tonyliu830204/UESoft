package com.syuesoft.frt.service;

import org.jfree.chart.JFreeChart;

import com.syuesoft.frt.vo.FrtQueryVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface BespeakAnalyseService {
	public DatagridAnalyze findBespeakAnalyse(FrtQueryVo fqVo)throws Exception;//查找预约分析数据
	
	public JFreeChart findSnapMap(FrtQueryVo fqVo)throws Exception;//查找预约分析折线图
	
	public JFreeChart findCakeMap(FrtQueryVo fqVo)throws Exception;//查找预约分析饼图
}
