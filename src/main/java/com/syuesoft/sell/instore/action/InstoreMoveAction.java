package com.syuesoft.sell.instore.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.instore.service.InstoreMoveService;
import com.syuesoft.sell.instore.vo.SellInstorehouseVo;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("instoreMoveAction")
public class InstoreMoveAction extends BaseAction implements ModelDriven<SellRetreatVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellRetreatVo instoreMoveVo=new SellRetreatVo();
	private  InstoreMoveService  instoreMoveService;
	public void getPager(){
		try {
				instoreMoveVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instoreMoveService.getPager(instoreMoveVo));
		} catch (Exception e) {
			logger.error("查询移库单信息失败", e);
		}
	}	
	//移库选车
	public void getPagerCar(){
		try {
				instoreMoveVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instoreMoveService.getPagerCar(instoreMoveVo));
		} catch (Exception e) {
			logger.error("查询移库单信息失败", e);
		}
	}	
	public void getPagerDel(){
		try {
			super.writeJson(instoreMoveService.getPagerDel(instoreMoveVo));
		} catch (Exception e) {
			logger.error("查询移库单明细信息失败", e);
		}
	}
	public void saveInstoreMove(){
		Msg msg = new Msg();
		try {
				instoreMoveVo.setEnterprise_id(getUserEnterpriseId());
			instoreMoveService.addInstore(instoreMoveVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存移库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteInstoreMove(){
		Msg msg = new Msg();
		try {
			instoreMoveVo.setEnterprise_id(getNowEnterpriseId());
			instoreMoveService.deleteInstore(instoreMoveVo);
			msg.setSuccess(true);
			msg.setMsg("success");
			
		} catch (Exception e) {
			logger.error("删除移库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void updateInstoreMove(){
		Msg msg = new Msg();
		try {
			instoreMoveVo.setEnterprise_id(getNowEnterpriseId());
			instoreMoveService.updateInstore(instoreMoveVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新移库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void examine(){
		Msg msg = new Msg();
		try {
			instoreMoveVo.setEnterprise_id(getNowEnterpriseId());
			instoreMoveService.updateExamine(instoreMoveVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("审核入库单信息失败", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 判断是否已审核
	 * */
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			instoreMoveVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=instoreMoveService.isRefundment(instoreMoveVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("审核入库单信息失败", e);
			msg.setMsg("判断移库单是否已审核失败！");
		}
		super.writeJson(msg);
	}
	public InstoreMoveService getInstoreMoveService() {
		return instoreMoveService;
	}
	@Autowired
	public void setInstoreMoveService(InstoreMoveService instoreMoveService) {
		this.instoreMoveService = instoreMoveService;
	}

	
	public SellRetreatVo getModel() {
		return instoreMoveVo;
	}	
	


}
