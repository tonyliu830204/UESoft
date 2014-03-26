package com.syuesoft.systemmanagement.service;

import java.util.List;

import com.syuesoft.util.Json;
import com.syuesoft.vipmanagement.vo.CarLostAnalysisVo;

public interface CarLostAnalysisService {
	//获取车辆流失汇总信息
	public List<CarLostAnalysisVo> getCollectinfor(CarLostAnalysisVo carLostAnalysisVo)throws Exception;
	
	//获取车辆流失  明细信息
	public List<CarLostAnalysisVo> getDetailsInforById(CarLostAnalysisVo carLostAnalysisVo)throws Exception;
	
	//判断父节点下是否有 维修项目
	public List getchild(CarLostAnalysisVo carLostAnalysisVo)throws Exception;

	//
	public Json getLiushi()throws Exception;
	
	//车辆流失情况分析 统计 已回访 和未回访
	public List finishvisite(String sql)throws Exception;
	
	
}
