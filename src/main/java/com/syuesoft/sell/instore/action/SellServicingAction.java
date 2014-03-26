package com.syuesoft.sell.instore.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.instore.service.SellServicingService;
import com.syuesoft.sell.instore.vo.SellServicingVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")@Action("sellServicingAction")
public class SellServicingAction extends BaseAction implements ModelDriven<SellServicingVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellServicingVo servicingVo=new SellServicingVo();
	private  SellServicingService  sellServicingService;
	public void getPager(){
		try {
				servicingVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellServicingService.getPager(servicingVo));
		} catch (Exception e) {
			logger.error("查询库存车辆维护信息失败", e);
		}
	}	
	public void getPagerPro(){
		try {
			super.writeJson(sellServicingService.getPagerPro(servicingVo));
		} catch (Exception e) {
			logger.error("查询库存车辆维护信息失败", e);
		}
	}
	public void getPagerCar(){
		try {
				servicingVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellServicingService.getPagerCar(servicingVo));
		} catch (Exception e) {
			logger.error("查询库存车辆信息失败", e);
		}
	}
	public void saveSellServicing(){
		Msg msg = new Msg();
		try {
			servicingVo.setEnterprise_id(getNowEnterpriseId());
			sellServicingService.saveSellServicing(servicingVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存维护项目信息失败", e);
		}
		super.writeJson(msg);
	}
	public SellServicingService getSellServicingService() {
		return sellServicingService;
	}
	@Autowired
	public void setSellServicingService(SellServicingService sellServicingService) {
		this.sellServicingService = sellServicingService;
	}

	public SellServicingVo getModel() {
		return servicingVo;
	}

}
