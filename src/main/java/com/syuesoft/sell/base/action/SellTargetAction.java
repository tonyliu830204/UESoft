package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.SellTargetService;
import com.syuesoft.sell.base.vo.SellTargetVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("sellTargetAction")
public class SellTargetAction extends BaseAction implements ModelDriven<SellTargetVo> {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellTargetVo sellTargetVo=new SellTargetVo();
	private SellTargetService sellTargetService;
	public void loadTree(){
		try {
			
				sellTargetVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellTargetService.retriveTree(sellTargetVo.getEnterprise_id()));
		} catch (Exception e) {
			logger.error("加载部门员工信息失败!", e);
		}
	}
	public void getPager(){
		try {
				sellTargetVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellTargetService.getPager(sellTargetVo));
		} catch (Exception e) {
			logger.error("查询销售指标失败!", e);
		}
	}
	public void saveSellTarget(){
		Msg msg = new Msg();
		try {
				sellTargetVo.setEnterprise_id(getUserEnterpriseId());
			
			sellTargetService.saveSellTarget(sellTargetVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("添加销售指标失败!", e);
		}
		super.writeJson(msg);
	}
	public void deleteSellTarget(){
		Msg msg = new Msg();
		try {
			sellTargetVo.setEnterprise_id(getNowEnterpriseId());
			sellTargetService.deleteSellTarget(sellTargetVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除销售指标失败!", e);
		}
		super.writeJson(msg);
	}
	public void updateSellTarget(){
		Msg msg = new Msg();
		try {
			sellTargetVo.setEnterprise_id(getNowEnterpriseId());
			sellTargetService.updateSellTarget(sellTargetVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新销售指标失败!", e);
		}
		super.writeJson(msg);
	}
	public SellTargetService getSellTargetService() {
		return sellTargetService;
	}
	@Autowired
	public void setSellTargetService(SellTargetService sellTargetService) {
		this.sellTargetService = sellTargetService;
	}
	
	public SellTargetVo getModel() {
		return sellTargetVo;
	}

}
