package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.CustomLevaService;
import com.syuesoft.sell.base.vo.CustomLevaVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")
@Action("customLevaAction")
public class CustomLevaAction extends BaseAction implements ModelDriven<CustomLevaVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private CustomLevaVo xsCustomLeva=new CustomLevaVo();
	private CustomLevaService customLevaService;
	public void findAllLeva(){
		try {
				xsCustomLeva.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(customLevaService.findAllLeva(xsCustomLeva));
		} catch (Exception e) {
			logger.error("查询潜在客户等级失败", e);
		}
	}
	public void getPageLeva(){
		try {
				xsCustomLeva.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(customLevaService.getPager(xsCustomLeva));
		} catch (Exception e) {
			logger.error("查询潜在客户等级失败", e);
		}
	}
	public void saveCustom(){     
		Msg msg = new Msg();
		try {
			xsCustomLeva.setEnterpriseId(getUserEnterpriseId());
			if(customLevaService.findLevaTwo(xsCustomLeva.getLevaCode(),xsCustomLeva.getLevaName(),xsCustomLeva.getEnterpriseId())){
				msg.setMsg("对不起，您输入的编码或名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			
			customLevaService.addCustom(xsCustomLeva);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存潜在客户等级失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteCustom(){
		Msg msg = new Msg();
		try {
			customLevaService.deleteCustom(xsCustomLeva);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除潜在客户等级失败", e);
		}
		super.writeJson(msg);
	}
	public void updateCustom(){
		Msg msg = new Msg();
		try {
			xsCustomLeva.setEnterpriseId(getUserEnterpriseId());
			if(customLevaService.findLeva(xsCustomLeva.getLevaCode(),xsCustomLeva.getLevaName(),xsCustomLeva.getLevaId(),xsCustomLeva.getEnterpriseId())){
				msg.setMsg("对不起，您输入的编码或名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			
			customLevaService.updateCustom(xsCustomLeva);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新潜在客户等级失败", e);
		}
		super.writeJson(msg);
	}
	public CustomLevaService getCustomLevaService() {
		return customLevaService;
	}
	@Autowired
	public void setCustomLevaService(CustomLevaService customLevaService) {
		this.customLevaService = customLevaService;
	}

	
	public CustomLevaVo getModel() {
		return xsCustomLeva;
	}

}
