package com.syuesoft.sell.instore.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.instore.service.InstoreOutService;

import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")@Action("instoreOutAction")
public class InstoreOutAction extends BaseAction implements ModelDriven<SellRetreatVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellRetreatVo retreatVo=new SellRetreatVo();
	private  InstoreOutService  instoreOutService;
	public void getPager(){
		try {
				retreatVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instoreOutService.getPager(retreatVo));
		} catch (Exception e) {
			logger.error("查询出库单信息失败", e);
		}
	}	
	public void getPagerDel(){
		try {
			super.writeJson(instoreOutService.getPagerDel(retreatVo));
		} catch (Exception e) {
			logger.error("查询出库单明细信息失败", e);
		}
	}	
	public void deleteInstore(){
		Msg msg = new Msg();
		try {
			instoreOutService.deleteInstore(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除出库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void saveInstore(){
		Msg msg = new Msg();
		try {
				retreatVo.setEnterprise_id(getUserEnterpriseId());
			instoreOutService.saveInstore(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存出库单信息失败", e);
		}
		super.writeJson(msg);
	}

	public void updateInstore(){
		Msg msg = new Msg();
		try {
			instoreOutService.updateInstore(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新出库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void examine(){
		Msg msg = new Msg();
		try {
			retreatVo.setEnterprise_id(getUserEnterpriseId());
			instoreOutService.updateExamine(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("审核出库单信息失败", e);
		}
		super.writeJson(msg);
	}

	public InstoreOutService getInstoreOutService() {
		return instoreOutService;
	}
	@Autowired
	public void setInstoreOutService(InstoreOutService instoreOutService) {
		this.instoreOutService = instoreOutService;
	}
	public SellRetreatVo  getModel() {
		return retreatVo;
	}
	public void getCarInfo(){
		try {
			super.writeJson(instoreOutService.findByCarId(retreatVo.getCarId()));
		} catch (Exception e) {
			logger.error("查询车辆信息失败", e);
		}
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			retreatVo.setEnterprise_id(getUserEnterpriseId());
			Boolean flag=instoreOutService.isRefundment(retreatVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断出库单是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断出库单是否已审核失败！");
		}
		super.writeJson(msg);
	}

}
