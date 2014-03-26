package com.syuesoft.sell.allocateManage.service;

import org.jfree.chart.JFreeChart;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.SellCustomAnalyseVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellCustomAnalyseService {
	public Datagrid querySellCustomInfor(
			SellCustomAnalyseVo sellCustomAnalyseVo) throws Exception;
	public JFreeChart findAnalyzeCakeMap(
			SellCustomAnalyseVo sellCustomAnalyseVo) throws Exception;
	
	//销售毛利分析
	public DatagridAnalyze querySellMlAnaly(SellCustomAnalyseVo sellCustomAnalyseVo) throws Exception;
}
