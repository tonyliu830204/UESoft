package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.ProvidebankService;
import com.syuesoft.sell.base.vo.ProvidebankVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("providebankAction")
public class ProvidebankAction extends BaseAction implements ModelDriven<ProvidebankVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private ProvidebankVo providebankVo=new ProvidebankVo();
	private ProvidebankService providebankService;
	public void getPageModel(){
		try {
				providebankVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(providebankService.getPager(providebankVo));
		} catch (Exception e) {
			logger.error("查询贷款银行档案失败", e);
		}
	}
	public void saveProvidebank(){     
		Msg msg = new Msg();
		try {
				providebankVo.setEnterprise_id(getUserEnterpriseId());
			if(providebankService.existBankTwo(providebankVo.getProvidebankName(),providebankVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			
			providebankService.addProvidebank(providebankVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存贷款银行档案失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteProvidebank(){
		Msg msg = new Msg();
		try {
			providebankService.deleteProvidebank(providebankVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除贷款银行档案失败", e);
		}
		super.writeJson(msg);
	}
	public void updateProvidebank(){
		Msg msg = new Msg();
		try {
			providebankVo.setEnterprise_id(getUserEnterpriseId());
			if(providebankService.existBank(providebankVo.getProvidebankName(),providebankVo.getProvidebankId(),providebankVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			providebankService.updateProvidebank(providebankVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新贷款银行档案失败", e);
		}
		super.writeJson(msg);
	}
	public ProvidebankService getProvidebankService() {
		return providebankService;
	}
	@Autowired
	public void setProvidebankService(ProvidebankService providebankService) {
		this.providebankService = providebankService;
	}
	
	public ProvidebankVo getModel() {
		return providebankVo;
	}

}
