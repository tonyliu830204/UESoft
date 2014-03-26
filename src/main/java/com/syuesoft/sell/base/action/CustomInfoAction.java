package com.syuesoft.sell.base.action;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.CustomInfoService;
import com.syuesoft.sell.base.vo.CustomInfoVo;
import com.syuesoft.util.Msg;
@ParentPackage(value="basePackage")
@Action("customInfoAction")
public class CustomInfoAction extends BaseAction implements ModelDriven<CustomInfoVo> {
	/**
	 * 
	 */
	private final Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private CustomInfoVo customInfoVo=new CustomInfoVo();
	private CustomInfoService customInfoService;
	
	public void getPage(){
		try {
				customInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(customInfoService.getPager(customInfoVo));
		} catch (Exception e) {
			logger.error("查询客户档案失败", e);
		}
	}
	//销售作业：客户档案查询
	public void queryCustom(){
		try {
				customInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(customInfoService.getCustomInfo(customInfoVo));
		} catch (Exception e) {
			logger.error("查询客户档案失败", e);
		}
	}
	public void saveCustomInfo(){     
		Msg msg = new Msg();
		try {
				customInfoVo.setEnterpriseId(getUserEnterpriseId());
			boolean flage=customInfoService.findByNumber(customInfoVo);
			if(flage==true){
				msg.setMsg("对不起，您输入的自编号已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			if(customInfoService.isExtisCustomCard(customInfoVo)){
				msg.setMsg("对不起，您输入的身份证号已存在，请查看是否输入正确！");
				super.writeJson(msg);
				return;
				
			}
		
			Serializable sr=customInfoService.addCustomInfo(customInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
			msg.setObj(sr);
		} catch (Exception e) {
			logger.error("保存客户档案失败", e);
			msg.setMsg("保存客户档案失败！");
		}
		super.writeJson(msg);
	}
	public void deleteCustomInfo(){
		Msg msg = new Msg();
		try {
			msg=customInfoService.deleteCustomInfo(customInfoVo);
		} catch (Exception e) {
			logger.error("删除客户档案失败", e);
			 msg.setMsg("删除客户档案失败！");
			msg.setSuccess(false);
			
		}
		super.writeJson(msg);
	}
	public void updateCustomInfo(){
		Msg msg = new Msg();
		try {
				customInfoVo.setEnterpriseId(getUserEnterpriseId());
			boolean flage=customInfoService.findByNumberEdit(customInfoVo);
			if(flage==true){
				msg.setMsg("对不起，您输入的自编号已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			if(customInfoService.isExtisCustomCard(customInfoVo)){
				msg.setMsg("对不起，您输入的身份证号已存在，请查看是否输入正确！");
				super.writeJson(msg);
				return;
				
			}
			customInfoService.updateCustomInfo(customInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新客户档案失败", e);
		}
		super.writeJson(msg);
	}
	public void isExtisCustomCard(){
		Msg msg = new Msg();
		try {
				customInfoVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=customInfoService.isExtisCustomCard(customInfoVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断客户是否已存在失败！", e);
			msg.setMsg("判断客户是否已存在失败！");
		}
		super.writeJson(msg);
	}
	public CustomInfoService getCustomInfoService() {
		return customInfoService;
	}
	@Autowired
	public void setCustomInfoService(CustomInfoService customInfoService) {
		this.customInfoService = customInfoService;
	}
	
	
	public CustomInfoVo getModel() {
		return customInfoVo;
	}

}
