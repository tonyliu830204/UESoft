package com.syuesoft.sell.synthesis_assay.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.synthesis_assay.service.DayReportAssayService;
import com.syuesoft.sell.synthesis_assay.vo.DayReportAssayVo;
import com.syuesoft.util.Json;

@Action("dayReportAssayAction")
public class DayReportAssayAction extends BaseAction implements ModelDriven<DayReportAssayVo> {

	private Logger log = Logger.getLogger(this.getClass());
	private DayReportAssayVo dayReportAssayVo = new DayReportAssayVo();
	
	public DayReportAssayVo getModel() {
		return dayReportAssayVo;
	}
	@Resource
	private DayReportAssayService dayReportAssayService;
	
	/**
	 * 获取该车型作为动态列
	 */
	public void getAnimatedColum(){
		try {
			List columlsit = dayReportAssayService.getAnimatedColum(dayReportAssayVo);
			super.writeJson(columlsit);
		} catch (Exception e) {
			log.error("获取该车型作为动态列失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 车辆日销售分析动态列
	 */
	public void doDayreportAssayColumns(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.doDayreportAssayColumns(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取车辆销售分析动态列失败",e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取当天该车型的统计数量
	 */
	public void doDayreportAssay(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.doDayreportAssay(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取该车型统计数量失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 获取当月该车型的统计数量
	 */
	public void doMonthreportAssay(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.doMonthreportAssay(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取该车型统计数量失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 获取該銷售類型车型的统计数量   
	 */
	public void doSellwayAssay(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.doSellwayAssay(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取该车型统计数量失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 获取該业务员车型的统计数量   
	 */
	public void doWorkerSellAssay(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.doWorkerSellAssay(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取该车型统计数量失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 获取該部门车型的统计数量   
	 */
	public void doDeptSellAssay(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.doDeptSellAssay(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取该车型统计数量失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 获取該班组车型的统计数量   
	 */
	public void doSellTeamsAssay(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.doSellTeamsAssay(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取该车型统计数量失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 车辆客户档案   
	 */
	public void getCarAndCustom(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.queryCarAndCustom(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取车辆客户档案信息失败",e);
			e.printStackTrace();
		}
	}/**
	 * 销售日报表信息
	 */
	public void getSellDayReport(){
		try {
				dayReportAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(dayReportAssayService.getSellDayReport(dayReportAssayVo));
		} catch (Exception e) {
			log.error("获取销售日报表信息失败",e);
			e.printStackTrace();
		}
	}
}
