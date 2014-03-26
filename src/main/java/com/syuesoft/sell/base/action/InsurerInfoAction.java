package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.InsurerInfoService;
import com.syuesoft.sell.base.vo.InsurerInfoVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("insurerInfoAction")
public class InsurerInfoAction extends BaseAction implements ModelDriven<InsurerInfoVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private InsurerInfoVo insurerInfoVo=new InsurerInfoVo();
	private InsurerInfoService insurerInfoService;
	public void getPageLeva(){
		try {
				insurerInfoVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(insurerInfoService.getPager(insurerInfoVo));
		} catch (Exception e) {
			logger.error("查询保险公司档案失败", e);
		}
	}
	public void saveInsurerInfo(){     
		Msg msg = new Msg();
		try {
				insurerInfoVo.setEnterprise_id(getUserEnterpriseId());
			if(insurerInfoService.findInsurerTwo(insurerInfoVo.getInsurerName(),insurerInfoVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			
			insurerInfoService.addInsurerInfo(insurerInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存保险公司档案失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteInsurerInfo(){
		Msg msg = new Msg();
		try {
			insurerInfoService.deleteInsurerInfo(insurerInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除保险公司档案失败", e);
		}
		super.writeJson(msg);
	}
	public void updateInsurerInfo(){
		Msg msg = new Msg();
		try {
			insurerInfoVo.setEnterprise_id(getUserEnterpriseId());
			if(insurerInfoService.findInsurer(insurerInfoVo.getInsurerName(),insurerInfoVo.getInsurerId(),insurerInfoVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			insurerInfoService.updateInsurerInfo(insurerInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新保险公司档案失败", e);
		}
		super.writeJson(msg);
	}


	public InsurerInfoService getInsurerInfoService() {
		return insurerInfoService;
	}
	@Autowired
	public void setInsurerInfoService(InsurerInfoService insurerInfoService) {
		this.insurerInfoService = insurerInfoService;
	}
	
	public InsurerInfoVo getModel() {
		return insurerInfoVo;
	}

}
