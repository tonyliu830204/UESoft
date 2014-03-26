package com.syuesoft.sell.instore.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.instore.service.SellPurchaseService;
import com.syuesoft.sell.instore.vo.SellPurchaseVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("sellPurchaseAction")
public class SellPurchaseAction extends BaseAction implements ModelDriven<SellPurchaseVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellPurchaseVo purchaseVo=new SellPurchaseVo();;
	private SellPurchaseService sellPurchaseService;
	public void getPager(){
		try {
				purchaseVo.setEnterprise_id(getNowEnterpriseId());	
			super.writeJson(sellPurchaseService.getPager(purchaseVo));
		} catch (Exception e) {
			logger.error("查询月度购进计划信息失败", e);
		}
	}
	public void saveSellPurchase(){
		Msg msg = new Msg();
		try {
				purchaseVo.setEnterprise_id(getUserEnterpriseId());	
			sellPurchaseService.addSellPurchase(purchaseVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存月度购进计划信息失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteSellPurchase(){
		Msg msg = new Msg();
		try {
			purchaseVo.setEnterprise_id(getNowEnterpriseId());	
			sellPurchaseService.deleteSellPurchase(purchaseVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除月度购进计划信息失败", e);
		}
		super.writeJson(msg);
	}
	public void updateSellPurchase(){
		Msg msg = new Msg();
		try {
			purchaseVo.setEnterprise_id(getNowEnterpriseId());	
			sellPurchaseService.updateSellPurchase(purchaseVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新月度购进计划信息失败", e);
		}
		super.writeJson(msg);
	}
	
	public SellPurchaseService getSellPurchaseService() {
		return sellPurchaseService;
	}
	@Autowired
	public void setSellPurchaseService(SellPurchaseService sellPurchaseService) {
		this.sellPurchaseService = sellPurchaseService;
	}
	
	public SellPurchaseVo getModel() {
		return purchaseVo;
	}

}
