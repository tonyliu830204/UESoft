package com.syuesoft.systemmanagement.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.BasRepairGroup;
import com.syuesoft.systemmanagement.vo.CustomerPerformanceStatisticsVo;

public interface CustomerPerformanceStatisticsDao extends BaseDaoI<CustomerPerformanceStatisticsVo>{
	
	//获取维修班组
	public List<BasRepairGroup> getBasRepairGroup()throws Exception; 

}
