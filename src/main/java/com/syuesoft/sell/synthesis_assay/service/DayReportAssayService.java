package com.syuesoft.sell.synthesis_assay.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.synthesis_assay.vo.DayReportAssayVo;

public interface DayReportAssayService{
	public List getAnimatedColum(DayReportAssayVo dayReportAssayVo) throws Exception;
	public String doDayreportAssay(DayReportAssayVo dayReportAssayVo) throws Exception;
	public String doMonthreportAssay(DayReportAssayVo dayReportAssayVo) throws Exception;
	public String doSellwayAssay(DayReportAssayVo dayReportAssayVo) throws Exception;
	public String doWorkerSellAssay(DayReportAssayVo dayReportAssayVo)throws Exception;
	public String doDeptSellAssay(DayReportAssayVo dayReportAssayVo)throws Exception;
	public String doSellTeamsAssay(DayReportAssayVo dayReportAssayVo)throws Exception;
	//车辆客户档案
	public Datagrid queryCarAndCustom(DayReportAssayVo  dayReportAssayVo ) throws Exception;
	//销售日报表
	public Datagrid getSellDayReport(DayReportAssayVo  dayReportAssayVo ) throws Exception;

	/**车辆日销售分析动态列*/
	public List doDayreportAssayColumns(DayReportAssayVo dayReportAssayVo)throws Exception;
}
