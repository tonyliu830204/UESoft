package com.syuesoft.sell.allocateManage.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.service.InstorehouseService;
import com.syuesoft.sell.allocateManage.vo.InstorehouseVo;
import com.syuesoft.util.Msg;

@ParentPackage(value = "basePackage")
@Action("instoreHouseQueryAction")
public class InstoreHouseQueryAction extends BaseAction implements
		ModelDriven<InstorehouseVo> {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private InstorehouseService houseService;
	private InstorehouseVo inVo = new InstorehouseVo();

	
	public InstorehouseVo getModel() {
		// TODO Auto-generated method stub
		return inVo;
	}

	/**
	 * 库存量查询
	 */
	public void getInstorehouseList() {
			try {
					inVo.setEnterprise_id(getNowEnterpriseId());
				super.writeJson(houseService.queryInstorehouse(inVo));
			} catch (Exception e) {
				logger.debug("库存量查询失败！");
			}

	}

	/**
	 * 库存量查询
	 */
	public void getInstorehouseSum() {

		Msg msg = new Msg();
		try {
				inVo.setEnterprise_id(getNowEnterpriseId());
			List d= houseService.queryInstorehouseSum(inVo);
			msg.setSuccess(true);
			msg.setMsg("success");
			msg.setObj(d);

		} catch (Exception e) {
			logger.debug("操作失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 车辆库存分析动态列
	 */
	public void doAssayColumns() {
			try {
					inVo.setEnterprise_id(getNowEnterpriseId());
				super.writeJson(houseService.doAssayColumns(inVo));
			} catch (Exception e) {
				logger.debug("查询车辆库存分析动态列失败！");
			}

	}
	/**
	 * 车辆库存分析：按颜色分类
	 */
	public void getCarColor() {
			try {
					inVo.setEnterprise_id(getNowEnterpriseId());
				super.writeJson(houseService.getCarColor(inVo));
			} catch (Exception e) {
				logger.debug("查询车辆库存分析按颜色分类信息失败！");
			}

	}
	/**
	 * 车辆库存分析：按分销商分类
	 */
	public void getInforByDis() {
			try {
					inVo.setEnterprise_id(getNowEnterpriseId());
				super.writeJson(houseService.getInforByDis(inVo));
			} catch (Exception e) {
				logger.debug("查询车辆库存分析按分销商分类信息失败！");
			}

	}
	/**
	 * 车辆库存分析：按销售状态分类
	 */
	public void getInforBySellState() {
			try {
					inVo.setEnterprise_id(getNowEnterpriseId());
				super.writeJson(houseService.getInforBySellState(inVo));
			} catch (Exception e) {
				logger.debug("查询车辆库存分析按销售状态分类信息失败！");
			}

	}
}
