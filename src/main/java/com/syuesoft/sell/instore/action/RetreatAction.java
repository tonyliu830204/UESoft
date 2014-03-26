package com.syuesoft.sell.instore.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.instore.service.RetreatService;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("retreatAction")
public class RetreatAction extends BaseAction implements ModelDriven<SellRetreatVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellRetreatVo retreatVo=new SellRetreatVo();;
	private RetreatService retreatService;
	public void getPager(){
		try {
				retreatVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(retreatService.getPager(retreatVo));
		} catch (Exception e) {
			logger.error("查询退厂单信息失败", e);
		}
	}
	public void getQueryBack(){
		try {
				retreatVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(retreatService.findQueryBack(retreatVo));
		} catch (Exception e) {
			logger.error("查询退厂单信息失败", e);
		}
	}
	public void getBack(){
		try {
				retreatVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(retreatService.getTreegridChild(retreatVo));
		} catch (Exception e) {
			logger.error("查询退厂单信息失败", e);
		}
	}
	public void getPagerDel(){
		try {
			super.writeJson(retreatService.getPagerDel(retreatVo));
		} catch (Exception e) {
			logger.error("查询退厂单明细信息失败", e);
		}
	}
	public void saveRetreat(){
		Msg msg = new Msg();
		try {
				retreatVo.setEnterprise_id(getUserEnterpriseId());
			retreatService.addRetreat(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存退厂单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteRetreat(){
		Msg msg = new Msg();
		try {
			retreatVo.setEnterprise_id(getNowEnterpriseId());
			retreatService.deleteRetreat(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除退厂单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void updateRetreat(){
		Msg msg = new Msg();
		try {
			retreatVo.setEnterprise_id(getNowEnterpriseId());
			retreatService.updateRetreat(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("修改退厂单失败", e);
			msg.setMsg("修改退厂单失败！");
		}
		super.writeJson(msg);
	}
	public void updateExamine(){
		Msg msg = new Msg();
		try {
			retreatVo.setEnterprise_id(getNowEnterpriseId());
			retreatService.updateExamine(retreatVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新审核状态失败", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 判断是否已审核
	 * */
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			retreatVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=retreatService.isRefundment(retreatVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断退厂单是否已审核失败！", e);
			msg.setMsg("判断是否已审核失败！");
		}
		super.writeJson(msg);
	}
	public RetreatService getRetreatService() {
		return retreatService;
	}
	@Autowired
	public void setRetreatService(RetreatService retreatService) {
		this.retreatService = retreatService;
	}
	
	public SellRetreatVo getModel() {
		return retreatVo;
	}

}
