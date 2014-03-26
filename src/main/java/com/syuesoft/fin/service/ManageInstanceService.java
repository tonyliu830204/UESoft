package com.syuesoft.fin.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.fin.vo.ManageInstanceVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface ManageInstanceService {
	/**车辆结算排行*/
	public Datagrid findCarBalanceUntangle(ManageInstanceVo miVo)throws Exception;
	/**成本分析*/
	public DatagridAnalyze findCostAanalyse(ManageInstanceVo miVo)throws Exception;
}
