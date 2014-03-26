package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.DistributorInfoService;
import com.syuesoft.sell.base.vo.DistributorInfoVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")@Action("distributorAction")
public class DistributorInfoAction extends BaseAction implements ModelDriven<DistributorInfoVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private DistributorInfoVo distributorInfoVo=new DistributorInfoVo();
	private DistributorInfoService distributorInfoService;
	public void getPageModel(){
		try {
				distributorInfoVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(distributorInfoService.getPager(distributorInfoVo));
		} catch (Exception e) {
			logger.error("查询分销商档案失败", e);
		}
	}
	public void saveDistributorInfo(){     
		Msg msg = new Msg();
		try {
				distributorInfoVo.setEnterprise_id(getUserEnterpriseId());
			if(distributorInfoService.findDistributorTwo(distributorInfoVo.getDistributorName(),distributorInfoVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
		
			distributorInfoService.addDistributorInfo(distributorInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存分销商档案失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteDistributorInfo(){
		Msg msg = new Msg();
		try {
			distributorInfoService.deleteDistributorInfo(distributorInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("删除分销商档案失败", e);
		}
		super.writeJson(msg);
	}
	public void updateDistributorInfo(){
		Msg msg = new Msg();
		try {
			distributorInfoVo.setEnterprise_id(getUserEnterpriseId());
			if(distributorInfoService.findDistributor(distributorInfoVo.getDistributorName(),distributorInfoVo.getDistributorId(),distributorInfoVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			distributorInfoService.updateDistributorInfo(distributorInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新分销商档案失败", e);
		}
		super.writeJson(msg);
	}
	public void findAllInfo(){
		try {
			super.writeJson(distributorInfoService.findAllInfo(getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("查询分销商信息失败!", e);
		}
	}
	public DistributorInfoService getDistributorInfoService() {
		return distributorInfoService;
	}
	@Autowired
	public void setDistributorInfoService(
			DistributorInfoService distributorInfoService) {
		this.distributorInfoService = distributorInfoService;
	}
	
	public DistributorInfoVo getModel() {
		return distributorInfoVo;
	}

}
