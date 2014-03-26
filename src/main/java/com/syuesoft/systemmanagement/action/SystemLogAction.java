package com.syuesoft.systemmanagement.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.systemmanagement.service.SystemLogService;
import com.syuesoft.systemmanagement.vo.SystemLogVo;
import com.syuesoft.util.Message;
/**
 * 系统日志Action
 * */
@ParentPackage(value="basePackage")
@Action("systemLogAction")
public class SystemLogAction extends BaseAction implements	ModelDriven<SystemLogVo> {
	private static final long serialVersionUID = 1L;
	Logger logger=Logger.getLogger(this.getClass());
	private SystemLogVo systemLogVo = new SystemLogVo();
	@Autowired
	private SystemLogService systemLogService;
	
	/**
	 * 系统日志查询
	 */
	public void doFindLog(){
		try {
			systemLogVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
			super.writeJson(systemLogService.findSystemLogByUser(systemLogVo));
		}catch (Exception e) {
			logger.error("系统日志查询失败！", e);
		}
	}
	
	/**
	 * 删除系统日志
	 */
	public void doDeleteLog(){
		Message msg = new Message();
		try {
			systemLogService.doDeleteLog(systemLogVo);
			msg.setSuccess(true);
			msg.setMsg("删除系统日志成功！");
		} catch (Exception e) {
			logger.error("删除系统日志失败！", e);
			msg.setSuccess(false);
			msg.setMsg("删除系统日志失败！");
		}finally{
			super.writeJson(msg);
		}
	}
	
	
	public SystemLogVo getModel() {
		return systemLogVo;
	}
}