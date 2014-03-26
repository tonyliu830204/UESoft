package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.ZsProjectService;
import com.syuesoft.sell.base.vo.ZsProjectVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("zsProjectAction")
public class ZsProjectAction extends BaseAction implements ModelDriven<ZsProjectVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private ZsProjectVo xsZsProject=new ZsProjectVo();
	private ZsProjectService zsProjectService;
	public void getPageZsPro(){
		try {
			xsZsProject.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(zsProjectService.getPager(xsZsProject));
		} catch (Exception e) {
			logger.error("查询赠送项目失败", e);
		}
	}
	public void saveZsPro(){
		Msg msg = new Msg();
		try {
				xsZsProject.setEnterpriseId(getUserEnterpriseId());
			if(zsProjectService.findZsProTwo(xsZsProject.getProjectName(),xsZsProject.getEnterpriseId())){
				msg.setMsg("对不起，您输入的名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			
			zsProjectService.addZsProject(xsZsProject);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存赠送项目失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteZsPro(){
		Msg msg = new Msg();
		try {
			xsZsProject.setEnterpriseId(getUserEnterpriseId());
			msg=zsProjectService.deleteZsProject(xsZsProject);
			
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除赠送项目失败");
			logger.error("删除赠送项目失败", e);
		}
		super.writeJson(msg);
	}
	public void updateZsPro(){
		Msg msg = new Msg();
		try {
			xsZsProject.setEnterpriseId(getUserEnterpriseId());
			if(zsProjectService.findZsPro(xsZsProject.getProjectName(),xsZsProject.getZsprojectId(),xsZsProject.getEnterpriseId())){
				msg.setMsg("对不起，您输入的名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			zsProjectService.updateZsProject(xsZsProject);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新赠送项目失败", e);
		}
		super.writeJson(msg);
	}
	public ZsProjectService getZsProjectService() {
		return zsProjectService;
	}
	@Autowired
	public void setZsProjectService(ZsProjectService zsProjectService) {
		this.zsProjectService = zsProjectService;
	}
	
	public ZsProjectVo getModel() {
		return xsZsProject;
	}

}
