package com.syuesoft.sell.sellwork.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.sellwork.service.SellInvoiceService;
import com.syuesoft.sell.sellwork.vo.SellInvoiceVo;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("sellInvoiceAction")
public class SellInvoiceAction extends BaseAction implements ModelDriven<SellInvoiceVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellInvoiceVo sellInvoiceVo =new SellInvoiceVo();
	private SellInvoiceService invoiceService;
	/**
	 * 查询开票信息
	 */
	@SuppressWarnings("unchecked")
	public void findSellInfor(){
		try {
				sellInvoiceVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(invoiceService.findSellInfor(sellInvoiceVo));
		} catch (Exception e) {
			logger.error("查询销售单汇总信息异常", e);
		}
		
	}

	/**
	 * 开票保存方法
	 */
	public void saveSellInvoice(){
		Message msg = new Message();
		try {
				sellInvoiceVo.setEnterprise_id(getNowEnterpriseId());
			//sellDbProjectVo.setDbProjectPerson(Integer.parseInt(String.valueOf(this.getUsers().getBasStuff().getStfId())));
			invoiceService.saveSellInvoice(sellInvoiceVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("开票信息保存失败",e);
		}
		super.writeJson(msg);
	}
	/**
	 * 开票删除方法
	 */
	public void deleteSellInvoice(){
		Msg msg = new Msg();
		try {
			sellInvoiceVo.setEnterprise_id(getNowEnterpriseId());
			msg=invoiceService.deleteSellInvoice(sellInvoiceVo);
		} catch (Exception e) {
			logger.error("删除车辆财务信息失败!", e);
			msg.setMsg("删除车辆车辆财务信息失败!");
			msg.setSuccess(false);
		}
		super.writeJson(msg);
	}
	/**
	 * 开票更新方法
	 */
	public void updateSellInvoice(){
		Msg msg = new Msg();
		try {
			sellInvoiceVo.setEnterprise_id(getNowEnterpriseId());
			msg=invoiceService.updateSellInvoice(sellInvoiceVo);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("修改车辆财务信息失败!");
			logger.error("修改车辆财务信息失败",e);
		}
		super.writeJson(msg);
	}

	/**
	 * 开票审核
	 */
	public void updateExamin(){
		Message msg = new Message();
		try {
			sellInvoiceVo.setEnterprise_id(getNowEnterpriseId());
			invoiceService.updateExamin(sellInvoiceVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("审核车辆财务信息失败",e);
		}
		super.writeJson(msg);
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			sellInvoiceVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=invoiceService.isRefundment(sellInvoiceVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断车辆财务是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断车辆财务是否已审核失败！");
		}
		super.writeJson(msg);
	}
	
	public SellInvoiceService getInvoiceService() {
		return invoiceService;
	}
	@Autowired
	public void setInvoiceService(SellInvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	public SellInvoiceVo getModel() {
		return sellInvoiceVo;
	}

}
