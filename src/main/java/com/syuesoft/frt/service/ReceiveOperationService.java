package com.syuesoft.frt.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.ReceiveOperationVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface ReceiveOperationService {
	public DatagridAnalyze receiveOperationMain(ReceiveOperationVo roVo)throws Exception;//查询接待员业绩统计汇总信息
	
	public DatagridAnalyze receiveOperationDetail(ReceiveOperationVo roVo)throws Exception;//查询接待员业绩统计明细信息
}
