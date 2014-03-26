package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.RepayService;
import com.syuesoft.sell.base.vo.RepayVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("repayAction")
public class RepayAction extends BaseAction implements ModelDriven<RepayVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private RepayVo xsRepay=new RepayVo();
	private RepayService repayService;
	public void getPageRepay(){
		try {
				xsRepay.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(repayService.getPager(xsRepay));
		} catch (Exception e) {
			logger.error("查询回访进度失败", e);
		}
	}
	public void saveRepay(){
		Msg msg = new Msg();
		try {
				xsRepay.setEnterpriseId(getUserEnterpriseId());
			if(repayService.findRepayTwo(xsRepay.getRepayName(),xsRepay.getEnterpriseId())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			
			repayService.addRepay(xsRepay);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存回访进度失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteRepay(){
		Msg msg = new Msg();
		try {
			xsRepay.setEnterpriseId(getUserEnterpriseId());
			msg=repayService.deleteRepay(xsRepay);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除回访进度失败");
			logger.error("删除回访进度失败", e);
		}
		super.writeJson(msg);
	}
	public void updateRepay(){
		Msg msg = new Msg();
		try {
			xsRepay.setEnterpriseId(getUserEnterpriseId());
			if(repayService.findRepay(xsRepay.getRepayName(),xsRepay.getRepayId(),xsRepay.getEnterpriseId())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			repayService.updateRepay(xsRepay);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新回访进度失败", e);
		}
		super.writeJson(msg);
	}
	public RepayService getRepayService() {
		return repayService;
	}
	@Autowired
	public void setRepayService(RepayService repayService) {
		this.repayService = repayService;
	}
	
	public RepayVo getModel() {
		return xsRepay;
	}

}
