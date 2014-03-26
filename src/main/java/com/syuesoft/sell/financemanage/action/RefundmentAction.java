package com.syuesoft.sell.financemanage.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.financemanage.service.RefundmentService;
import com.syuesoft.sell.financemanage.vo.RefundmentVo;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;


@ParentPackage(value="basePackage")
@Action("refundmentAction") 
public class RefundmentAction extends BaseAction implements ModelDriven<RefundmentVo> {

	Logger logger =Logger.getLogger(this.getClass());
	private RefundmentVo refundmentVo = new RefundmentVo();
	
	public RefundmentVo getModel() {
		return refundmentVo;
	}
	@Resource
	private RefundmentService refundmentService;
	
	/**
	 * 获取需退车记录
	 */
	public void getRefundmentInfo(){
		try {
				refundmentVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(refundmentService.getRefundmentInfo(refundmentVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增退款单
	 */
	public void saveRefundmentAmount(){
		Message msg = new Message();
		try {
			refundmentVo.setEnterpriseId(getNowEnterpriseId());
			refundmentService.saveRefundmentAmount(refundmentVo);
			msg.setSuccess(true);
			msg.setMsg("新增退款单成功！");
		} catch (Exception e) {
			logger.error("新增退款单失败!", e);
			msg.setSuccess(false);
			msg.setMsg("新增退款单失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 修改退款单
	 */
	public void updateRefundmentAmount(){
		Message msg = new Message();
		try {
			refundmentVo.setEnterpriseId(getNowEnterpriseId());
			refundmentService.updateRefundmentAmount(refundmentVo);
			msg.setSuccess(true);
			msg.setMsg("修改退款单成功！");
		} catch (Exception e) {
			logger.error("修改退款单失败!", e);
			msg.setSuccess(false);
			msg.setMsg("修改退款单失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 查找未退金额
	 * */
	public void findNoBackMoney(){
		try {
			super.writeJson(refundmentService.findNoBackMoney(refundmentVo));
		} catch (Exception e) {
			logger.error("查找未退金额失败！", e);
		}
	}
	/**审核*/
	public void examine(){
		Msg msg = new Msg();
		try {
			refundmentVo.setEnterpriseId(getNowEnterpriseId());
			refundmentService.updateExamine(refundmentVo);
			msg.setSuccess(true);
			msg.setMsg("审核退款单成功！");
		} catch (Exception e) {
			logger.error("审核退款单失败", e);
			msg.setSuccess(false);
			msg.setMsg("审核退款单失败！");
		}
		super.writeJson(msg);
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			refundmentVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=refundmentService.isRefundment(refundmentVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断退款单是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断退款单是否已审核失败！");
		}
		super.writeJson(msg);
	}
}
