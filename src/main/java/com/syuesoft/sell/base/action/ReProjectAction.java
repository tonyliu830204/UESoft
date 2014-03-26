package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.ReProjectService;
import com.syuesoft.sell.base.vo.RepayProjectVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("reProjectAction")
public class ReProjectAction extends BaseAction implements ModelDriven<RepayProjectVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private RepayProjectVo xsRepayProject=new RepayProjectVo();
	private ReProjectService reProjectService;
	public void getPageRePro(){
		try {
			xsRepayProject.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(reProjectService.getPager(xsRepayProject));
		} catch (Exception e) {
			logger.error("查询客户回访项目失败", e);
		}
	}
	public void saveRePro(){
		Msg msg = new Msg();
		try {
				xsRepayProject.setEnterpriseId(getUserEnterpriseId());
			if(reProjectService.findReProTwo(xsRepayProject.getProjectName(),xsRepayProject.getEnterpriseId())){
				msg.setMsg("对不起，您输入的信息已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			

			reProjectService.addReProject(xsRepayProject);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存客户回访项目失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteZsPro(){
		Msg msg = new Msg();
		try {
			xsRepayProject.setEnterpriseId(getNowEnterpriseId());
			msg=reProjectService.deleteReProject(xsRepayProject);
			
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除客户回访项目失败");
			logger.error("删除客户回访项目失败", e);
		}
		super.writeJson(msg);
	}
	public void updateRePro(){
		Msg msg = new Msg();
		try {
			xsRepayProject.setEnterpriseId(getUserEnterpriseId());
			if(reProjectService.findRePro(xsRepayProject.getProjectName(),xsRepayProject.getProjectId(),xsRepayProject.getEnterpriseId())){
				msg.setMsg("对不起，您输入的编码已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			reProjectService.updateReProject(xsRepayProject);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新客户回访项目失败", e);
		}
		super.writeJson(msg);
	}
	public void findAllRepay(){
		try {
				xsRepayProject.setEnterpriseId(getUserEnterpriseId());
			super.writeJson(reProjectService.findAllRepay(xsRepayProject.getEnterpriseId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ReProjectService getReProjectService() {
		return reProjectService;
	}
	@Autowired
	public void setReProjectService(ReProjectService reProjectService) {
		this.reProjectService = reProjectService;
	}
	
	public RepayProjectVo getModel() {
		return xsRepayProject;
	}

}
