package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.DistributpurviewService;
import com.syuesoft.base.vo.DistrubtPurviewVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;
/**
 * 分布点权限管理Action
 * */
@ParentPackage(value="basePackage")
@Action("distrubtPurviewAction")
public class DistrubtPurviewAction extends BaseAction implements ModelDriven<DistrubtPurviewVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private DistrubtPurviewVo dpVo=new DistrubtPurviewVo();
	@Autowired
	private DistributpurviewService distributpurviewService;
	/**
	 * 分布点权限管理datagrid
	 * */
	public void distrubtPurviewDataGrid(){
		try {
		    dpVo.setEnterpriseId(getUserEnterpriseId());
			super.writeJson(distributpurviewService.distributpurviewDataGrid(dpVo));
		} catch (Exception e) {
			logger.error("查询分布点权限管理数据失败！",e);
		}
	}
	/**
	 * 分布点权限管理treegrid
	 * */
	public void distrubtPurviewTreeGrid(){
		try {
			dpVo.setBasUsersLevel(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getLeavl());
			super.writeJson(distributpurviewService.distributpurviewTreeGrid(dpVo));
		} catch (Exception e) {
			logger.error("查询分布点权限管理数据失败！",e);
		}
	}
	/**
	 * 分布点权限管理treegrid子级数据
	 * */
	public void distrubtPurviewTreeGridChild(){
		try {
			super.writeJson(distributpurviewService.distributpurviewTreeGridChild(dpVo));
		} catch (Exception e) {
			logger.error("查询分布点权限管理数据子级数据失败！",e);
		}
	}
	/**
	 * 分布点权限管理用户数据
	 * */
	public void findDistrubtPurviewUsers(){
		try {
			dpVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(distributpurviewService.findDistrubtPurviewUsers(dpVo));
		} catch (Exception e) {
			logger.error("查询分布点权限管理用户数据失败！", e);
		}
	}
	
	/**
	 * 获取集团的所有角色
	 * */
	public void findEnterpriseInfoRoles(){
		try {
			super.writeJson(distributpurviewService.findEnterpriseInfoRoles(dpVo));
		} catch (Exception e) {
			logger.error("获取集团的所有角色失败！", e);
		}
	}
	/**
	 * 指定角色的系统类型与指定的系统类型是否一致
	 * */
	public void isAccordSystemType(){
		Msg msg=new Msg();
		try {
			Boolean flag=distributpurviewService.isAccordSystemType(dpVo);
			if(flag==null){
				msg.setSuccess(false);
				msg.setMsg("数据不完整，无法比对系统类型，请刷新后再试！");
			}else if(flag==true){
				msg.setSuccess(true);
				msg.setMsg("指定角色的系统类型与当前系统类型一致！");
			}else{
				msg.setSuccess(false);
				msg.setMsg("指定角色的系统类型与当前系统类型不一致，无法操作，请重新选择角色！");
			}
		} catch (Exception e) {
			logger.error("判断指定角色的系统类型与指定的系统类型是否一致失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断指定角色的系统类型与指定的系统类型是否一致失败！");
		}finally{
			super.writeJson(msg);
		}
	}
	
	/**
	 * 当前用户可访问的所有企业信息
	 * */
	public void findAllStoreOfUser(){
		try {
			dpVo.setStfId(getUsers().getBasStuff().getStfId().toString());
			dpVo.setEnterpriseId(getUserEnterpriseId());
			super.writeJson(distributpurviewService.findAllStoreOfUser(dpVo));
		} catch (Exception e) {
			logger.error("查找当前用户可访问的所有企业信息失败！", e);
			e.printStackTrace();
		}
	}
	/**
	 * 当前用户是否为超级管理员
	 * */
	public void isAdministrator(){
		Msg msg=new Msg();
		try {
			if(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
				msg.setSuccess(true);
				msg.setMsg("当前用户为超级管理员！");
			}else{
				msg.setSuccess(false);
				msg.setMsg("当前用户非超级管理员,不可操作！");
				msg.setObj("true");
			}
		} catch (Exception e) {
			logger.error("查询当前用户是否为超级管理员失败！", e);
			msg.setMsg("查询当前用户是否为超级管理员失败！");
			msg.setSuccess(false);
		}finally{
			super.writeJson(msg);
		}
	}
	/**
	 * 查询员工系统级别（除超级管理员，集团管理员）
	 * */
	public void findSystemLevel(){
		try {
			super.writeJson(distributpurviewService.findSystemLevel(dpVo));
		} catch (Exception e) {
			logger.error("查询员工系统级别失败！",e);
		}
	}
	
	public DistrubtPurviewVo getModel() {
		return dpVo;
	}

}
