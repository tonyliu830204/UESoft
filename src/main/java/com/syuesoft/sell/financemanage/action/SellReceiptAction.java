package com.syuesoft.sell.financemanage.action;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.financemanage.service.SellReceiptService;
import com.syuesoft.sell.financemanage.vo.SellReceiptVo;
import com.syuesoft.util.Msg;
@ParentPackage(value="basePackage")
@Action("sellReceiptAction") 
public class SellReceiptAction extends BaseAction implements ModelDriven<SellReceiptVo> {
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellReceiptVo sellReceiptVo = new SellReceiptVo();
	
	public SellReceiptVo getModel() {
		return sellReceiptVo;
	}
	@Resource
	private SellReceiptService  receiptService;
	
	
	/**
	 * 承兑汇票管理
	 */
	public void getPage(){
		try {
				sellReceiptVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(receiptService.getPager(sellReceiptVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存票据信息
	 */
	public void saveReceipt(){     
		Msg msg = new Msg();
		try {
				sellReceiptVo.setEnterpriseId(getNowEnterpriseId());
			Serializable id = receiptService.addReceipt(sellReceiptVo);
			msg.setSuccess(true);
			msg.setMsg("success");
			msg.setObj(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	public void deleteReceipt(){
		Msg msg = new Msg();
		try {
			if(receiptService.findReceiptById(sellReceiptVo)){
				msg.setMsg("该数据已被使用，不能删除!");
				super.writeJson(msg);
				return;
			};
			receiptService.deleteReceipt(sellReceiptVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除票据信息失败", e);
		}
		super.writeJson(msg);
	}
	public void updateReceipt(){
		Msg msg = new Msg();
		try {
			receiptService.updateReceipt(sellReceiptVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("修改票据信息失败", e);
		}
		super.writeJson(msg);
	}

}
