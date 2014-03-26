package com.syuesoft.systemmanagement.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jfree.chart.ChartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.systemmanagement.service.MaintenanceTrafficAnalysisService;
import com.syuesoft.systemmanagement.vo.MaintenanceTrafficAnalysisVo;

@ParentPackage(value="basePackage")
@Action("maintenanceTrafficAnalysisAction")
public class MaintenanceTrafficAnalysisAction extends BaseAction implements
		ModelDriven<MaintenanceTrafficAnalysisVo> {
	private final Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private MaintenanceTrafficAnalysisService maintenanceTrafficAnalysisService;
	private MaintenanceTrafficAnalysisVo mtaVo  = new MaintenanceTrafficAnalysisVo();
	
	/**
	 * 查找维修记录信息
	 * */
	public void findMaintenanceTrafficAnalysisServiceLog(){
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServiceLog(mtaVo));
		} catch (Exception e) {
			logger.error("查找维修记录信息失败！", e);
		}
	}
	/**
	 * 查找维修记录信息-子项
	 * */
	public void findMaintenanceTrafficAnalysisServiceLogByInterDate(){
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServiceLogByInterDate(mtaVo));
		} catch (Exception e) {
			logger.error("查找维修记录信息-子项失败！", e);
		}
	}
	/**
	 * 查找维修时间段分析信息
	 * */
	public void findMaintenanceTrafficAnalysisServiceTimeAnalyze(){
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServiceTimeAnalyze(mtaVo));
		} catch (Exception e) {
			logger.error("查找维修时间段分析信息失败！", e);
		}
	}
	
	/**
	 * 查找维修接待员分析信息
	 * */
	public void findMaintenanceTrafficAnalysisServiceReceiveAnalyze(){
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			if(mtaVo.getFlag()!=null&&mtaVo.getFlag()==true){
				String ss=maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServiceReceiveAnalyze(mtaVo);
				super.writeJson(JSON.parseObject(ss));
			}else{
				super.writeJson(maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServiceReceiveAnalyze(mtaVo));				
			}
		} catch (Exception e) {
			logger.error("查找维修接待员分析信息失败！", e);
		}
	}
	/**
	 * 查找维修品牌分析信息
	 * */
	public void findMaintenanceTrafficAnalysisServiceBrandAnalyze(){
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServiceBrandAnalyze(mtaVo));
		} catch (Exception e) {
			logger.error("查找维修品牌分析信息失败！", e);
		}
	}
	/**
	 * 查找车辆在修情况分析信息
	 * */
	public void findMaintenanceTrafficAnalysisServicingCarAnalyze(){
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServicingCarAnalyze(mtaVo));
		} catch (Exception e) {
			logger.error("查找车辆在修情况分析信息失败！", e);
		}
	}
	/**
	 * 查找车辆在修情况分析-子项信息
	 * */
	public void findMaintenanceTrafficAnalysisServicingCarAnalyzeByCbrdId(){
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(maintenanceTrafficAnalysisService.findMaintenanceTrafficAnalysisServicingCarAnalyzeByCbrdId(mtaVo));
		} catch (Exception e) {
			logger.error("查找车辆在修情况分析-子项信息失败！", e);
		}
	}
	/**
	 *获取维修接待员分析柱状图信息  
	 * @throws Exception 
	 * */
	public void findServiceReceiveAnalyzePoleMap() throws Exception{
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),maintenanceTrafficAnalysisService.findServiceReceiveAnalyzePoleMap(mtaVo), 1100, 360);			
		} catch (Exception e) {
			logger.error("获取维修接待员分析柱状图信息失败！", e);
		}
	}
	/**
	 *获取维修接待员分析饼图信息  
	 * @throws Exception 
	 * */
	public void findServiceReceiveAnalyzeCakeMap() throws Exception{
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),maintenanceTrafficAnalysisService.findServiceReceiveAnalyzeCakeMap(mtaVo), 380, 360);			
		} catch (Exception e) {
			logger.error("获取维修接待员分析饼图信息失败！", e);
		}
	}
	/**
	 *获取维修时间段分析折线图信息
	 * @throws Exception 
	 * */
	public void findServiceTimeAnalyzeSnapMap() throws Exception{
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),maintenanceTrafficAnalysisService.findServiceTimeAnalyzeSnapMap(mtaVo), 1100, 360);			
		} catch (Exception e) {
			logger.error("获取维修时间段分析折线图信息失败！", e);
		}
	}
	/**
	 *获取维修时间段分析饼图信息
	 * @throws Exception 
	 * */
	public void findServiceTimeAnalyzeCakeMap() throws Exception{
		try {
			mtaVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),maintenanceTrafficAnalysisService.findServiceTimeAnalyzeCakeMap(mtaVo), 380, 360);			
		} catch (Exception e) {
			logger.error("获取维修时间段分析饼图信息失败！", e);
		}
	}
	
	
	public MaintenanceTrafficAnalysisVo getModel() {
		return mtaVo;
	}
}

