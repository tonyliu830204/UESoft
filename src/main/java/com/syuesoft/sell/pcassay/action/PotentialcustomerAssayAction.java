package com.syuesoft.sell.pcassay.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.jfree.chart.ChartUtilities;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.pcassay.service.PotentialcustomerAssayService;
import com.syuesoft.sell.pcassay.vo.PotentialcustomerAssayVo;

@Action("potentialcustomerAssayAction")
public class PotentialcustomerAssayAction extends BaseAction implements
		ModelDriven<PotentialcustomerAssayVo> {
	private Logger log = Logger.getLogger(this.getClass());
	private PotentialcustomerAssayVo potentialcustomerAssayVo = new PotentialcustomerAssayVo();

	
	public PotentialcustomerAssayVo getModel() {
		return potentialcustomerAssayVo;
	}

	@Resource
	private PotentialcustomerAssayService potentialcustomerAssayService;

	// 获取成交障碍类型 数量及百分比
	public void getPotentialCustomerAssay() {

		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getPotentialCustomerAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取成交障碍信息失败", e);
			e.printStackTrace();
		}
	}

	// 获取成交障碍报表图形
	public void getJFreeChartJpg() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getPotentialCustomerAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfPotentialCustomerAssay(list),
							"成交障碍分析图"), 800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取成交几率 数量及百分比
	public void getBargainonAssay() {

		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getBargainonAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取成交几率信息失败", e);
			e.printStackTrace();
		}
	}

	// 获取成交几率 报表图形
	public void getJFreeChartJpgOfBargainonAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getBargainonAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(),
					potentialcustomerAssayService.getJFreeChart(list,
							potentialcustomerAssayService
									.createPieDatasetOfBargainonAssay(list),
							"成交几率分析图"), 800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取潜在客户来源数量及百分比
	public void getCustomSourceAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getCustomSourceAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取潜在客户来源信息失败", e);
			e.printStackTrace();
		}
	}

	// 获取潜在客户来源 报表图形
	public void getJFreeChartJpgOfCustomSource() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getCustomSourceAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(),
					potentialcustomerAssayService.getJFreeChart(list,
							potentialcustomerAssayService
									.createPieDatasetOfCustomSource(list),
							"潜在客户来源分析图"), 800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取跟踪部门来源数量及百分比
	public void getDeptAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getDeptAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取跟踪部门信息失败", e);
			e.printStackTrace();
		}
	}

	// 获取跟踪部门来源数量及百分比报表图
	public void getJFreeChartJpgOfDept() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getDeptAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfDeptAssay(list), "跟踪部门分析图"),
					800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取跟踪班组数量及百分比
	public void getSellTeamAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getSellTeamAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取跟踪班组信息失败", e);
			e.printStackTrace();
		}
	}

	// 获取跟踪班组数量及百分比报表图
	public void getJFreeChartJpgOfSellTeam() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getSellTeamAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfSellTeamAssay(list), "跟踪班组分析图"),
					800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取跟踪业务员数量及百分比
	public void getStfNameAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getStfNameAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取跟踪班组信息失败", e);
			e.printStackTrace();
		}
	}

	// 获取跟踪业务员数量及百分比报表图
	public void getJFreeChartJpgOfStfName() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getStfNameAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfStfNameAssay(list), "跟踪业务分析图"),
					800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取潜在客户等级数量及百分比
	public void getCustomerLevelAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getCustomerLevelAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取跟踪班组信息失败", e);
			e.printStackTrace();
		}
	}

	// 获取潜在客户等级数量及百分比报表图
	public void getJFreeChartJpgOfCustomerLevel() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getCustomerLevelAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfCustomerLevelAssay(list),
							"跟踪业务分析图"), 800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 购买车型号数量及百分比
	public void getBuyCarModelAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getBuyCarModelAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取跟踪班组信息失败", e);
			e.printStackTrace();
		}
	}

	// 购买车型号数量及百分比报表图
	public void getJFreeChartJpgOfBuyCarModel() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getBuyCarModelAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfBuyCarModelAssay(list),
							"购买车型号分析图"), 800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 战败原因数量及百分比
	public void getLoseBellAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取战败原因信息失败", e);
			e.printStackTrace();
		}
	}

	// 战败原因数量及百分比报表图
	public void getJFreeChartJpgOfLoseBell() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfLoseBell(list), "战败原因分析图"), 800,
					330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 战败车型数量及百分比
	public void getLoseBellCarModelAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellCarModelAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取战败车型信息失败", e);
			e.printStackTrace();
		}
	}

	// 战败车型数量及百分比报表图
	public void getJFreeChartJpgOfLoseBellCarModel() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellCarModelAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfLoseBellCarModel(list),
							"战败车型分析图"), 800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 战败部门数量及百分比
	public void getLoseBellDepartAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellDepartAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取战败部门信息失败", e);
			e.printStackTrace();
		}
	}

	// 战败部门数量及百分比报表图
	public void getJFreeChartJpgOfLoseBellDepart() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellDepartAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(),
					potentialcustomerAssayService.getJFreeChart(list,
							potentialcustomerAssayService
									.createPieDatasetOfLoseBellDepart(list),
							"战败部门分析图"), 800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 战败班组数量及百分比
	public void getLoseBellGroupAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellGroupAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取战败班组信息失败", e);
			e.printStackTrace();
		}
	}

	// 战败班组数量及百分比报表图
	public void getJFreeChartJpgOfLoseBellGroup() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellGroupAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfLoseBellGroup(list), "战败班组分析图"),
					800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 战败业务数量及百分比
	public void getLoseBellWorkAssay() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellWorkAssay(potentialcustomerAssayVo);
			if (list != null && list.size() > 0) {
				super.writeJson(list);
			}
		} catch (Exception e) {
			log.error("获取战败班组信息失败", e);
			e.printStackTrace();
		}
	}

	// 战败业务数量及百分比报表图
	public void getJFreeChart() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			List list = potentialcustomerAssayService
					.getLoseBellWorkAssay(potentialcustomerAssayVo);
			ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse()
					.getOutputStream(), potentialcustomerAssayService
					.getJFreeChart(list, potentialcustomerAssayService
							.createPieDatasetOfLoseBellWork(list), "战败业务分析图"),
					800, 330);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//22 获取战败客户记录
	public void getLosedCustomerRecord() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(potentialcustomerAssayService
					.getLosedCustomerRecord(potentialcustomerAssayVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//11 获取战败客户记录战败原因动态列
	public void getLosedCustomerColumn() {
		try {
				potentialcustomerAssayVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(potentialcustomerAssayService
					.getLosedCustomerColumn(potentialcustomerAssayVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
