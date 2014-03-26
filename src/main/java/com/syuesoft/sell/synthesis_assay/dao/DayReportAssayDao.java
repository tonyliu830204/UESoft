package com.syuesoft.sell.synthesis_assay.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.synthesis_assay.vo.DayReportAssayVo;

public interface DayReportAssayDao extends BaseDaoI{
	public Datagrid queryCarAndCustom(DayReportAssayVo  dayReportAssayVo ) throws Exception;
	//销售日报表
	public Datagrid getSellDayReport(DayReportAssayVo  dayReportAssayVo ) throws Exception;

}
