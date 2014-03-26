package com.syuesoft.sell.allocateManage.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.allocateManage.vo.SellCustomAnalyseVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellCustomAnalyseDao extends BaseDaoI<BaseBean>{
	public Datagrid querySellCustomInfor(
			SellCustomAnalyseVo sellCustomAnalyseVo) throws Exception;
	
	//销售毛利分析
	public DatagridAnalyze querySellMlAnaly(SellCustomAnalyseVo sellCustomAnalyseVo) throws Exception;
}
