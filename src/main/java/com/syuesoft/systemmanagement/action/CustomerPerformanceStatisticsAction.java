package com.syuesoft.systemmanagement.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.systemmanagement.service.CustomerPerformanceStatisticsService;
import com.syuesoft.systemmanagement.vo.CustomerPerformanceStatisticsVo;

@ParentPackage(value="basePackage")
@Action("customerPerformanceStatisticsAction")
public class CustomerPerformanceStatisticsAction extends BaseAction implements ModelDriven<CustomerPerformanceStatisticsVo>{
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private CustomerPerformanceStatisticsService customerPerformanceStatisticsService; 
	
	private CustomerPerformanceStatisticsVo customerPerformanceStatisticsVo = new CustomerPerformanceStatisticsVo();
	
	/**
	 * 维修人员业绩统计汇总
	 */
	public void findServicePersonMain(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findServicePersonMain(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("查找维修人员业绩统计信息失败！", e);
		}
	}
	
	/**
	 * 维修人员业绩统计(按维修员)
	 */
	public void findServicePersonScore(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findServicePersonScore(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("查找维修人员业绩统计信息失败！", e);
		}
	}
	/**
	 * 维修人员业绩统计-子项信息(按维修员)
	 */
	public void findServicePersonScoreChild(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findServicePersonScoreChild(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("查找维修人员业绩统计信息失败！", e);
		}
	}
	/**
	 * 维修人员业绩统计(按工单号)
	 */
	public void findServicePersonScoreForReceptionId(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findServicePersonScoreForReceptionId(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("查找维修人员业绩统计信息失败！", e);
		}
	}
	/**
	 * 维修人员业绩统计-子项信息(按工单号)
	 */
	public void findServicePersonScoreChildForReceptionId(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findServicePersonScoreChildForReceptionId(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("查找维修人员业绩统计信息失败！", e);
		}
	}
	/**
	 * 结算工时查询(按维修员)
	 * */
	public void findBalanceHoursQuery(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findBalanceHoursQuery(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("结算工时查询失败！", e);
		}
	}
	/**
	 * 结算工时查询-子项信息(按维修员)
	 * */
	public void findBalanceHoursQueryChild(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findBalanceHoursQueryChild(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("结算工时查询失败！", e);
		}
	}
	/**
	 * 结算工时查询(按接待员)
	 * */
	public void findBalanceHoursQueryForRecivePerson(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findBalanceHoursQueryForRecivePerson(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("结算工时查询失败！", e);
		}
	}
	/**
	 * 结算工时查询-子项信息(接待员)
	 * */
	public void findBalanceHoursQueryChildForRecivePerson(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findBalanceHoursQueryChildForRecivePerson(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("结算工时查询失败！", e);
		}
	}
	/**
	 * 索赔结算工时统计main
	 * */
	public void findClaimsHoursMain(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findClaimsHoursMain(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("索赔结算工时统计main失败！", e);
		}
	}
	/**
	 * 索赔结算工时统计detail
	 * */
	public void findClaimsHoursDetail(){
		try {
			customerPerformanceStatisticsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(customerPerformanceStatisticsService.findClaimsHoursDetail(customerPerformanceStatisticsVo));
		} catch (Exception e) {
			logger.error("索赔结算工时统计detail失败！", e);
		}
	}
	
	
	public CustomerPerformanceStatisticsVo getModel() {
		return customerPerformanceStatisticsVo;
	}
	
}
