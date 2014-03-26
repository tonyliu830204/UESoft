package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.DbProjectService;
import com.syuesoft.sell.base.vo.DbProjectVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("dbProjectAction")
public class DbProjectAction extends BaseAction implements ModelDriven<DbProjectVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private DbProjectVo xsDbProject=new DbProjectVo();
	private DbProjectService dbProjectService;
	public void getPagePro(){
		try {
				xsDbProject.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(dbProjectService.getPager(xsDbProject));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询代办项目失败", e);
		}
	}
	public void savePro(){
		Msg msg = new Msg();
		try {
				xsDbProject.setEnterpriseId(getUserEnterpriseId());
			if(dbProjectService.findDbProTwo(xsDbProject.getProjectCode(),xsDbProject.getProjectName(),xsDbProject.getEnterpriseId())){
				msg.setMsg("对不起，您输入的编码或名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			
			dbProjectService.addDbProject(xsDbProject);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存代办项目失败", e);
		}
		super.writeJson(msg);
	}
	public void deletePro(){
		Msg msg = new Msg();
		try {
			xsDbProject.setEnterpriseId(getUserEnterpriseId());
			msg=dbProjectService.deleteDbProject(xsDbProject);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除代办项目失败");
			logger.error("删除代办项目失败", e);
		}
		super.writeJson(msg);
	}
	public void updatePro(){
		Msg msg = new Msg();
		try {
			xsDbProject.setEnterpriseId(getUserEnterpriseId());
			if(dbProjectService.findDbPro(xsDbProject.getProjectCode(),xsDbProject.getProjectName(),xsDbProject.getProjectId(),xsDbProject.getEnterpriseId())){
				msg.setMsg("对不起，您输入的编码或名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			dbProjectService.updateDbProject(xsDbProject);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新代办项目失败", e);
		}
		super.writeJson(msg);
	}
	public DbProjectService getDbProjectService() {
		return dbProjectService;
	}
	@Autowired
	public void setDbProjectService(DbProjectService dbProjectService) {
		this.dbProjectService = dbProjectService;
	}
	
	public DbProjectVo getModel() {
		return xsDbProject;
	}

}
