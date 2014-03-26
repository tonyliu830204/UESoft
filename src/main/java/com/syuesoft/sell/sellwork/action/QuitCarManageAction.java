package com.syuesoft.sell.sellwork.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.sellwork.service.QuitCarManageService;
import com.syuesoft.sell.sellwork.vo.QuitCarManageVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value="basePackage")
@Action("quitCarManageAction")
public class QuitCarManageAction extends BaseAction implements ModelDriven<QuitCarManageVo>{

	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private QuitCarManageService quitCarManageService;
	
	private QuitCarManageVo quitCarManageVo = new QuitCarManageVo();
	
	public QuitCarManageVo getModel() {
		return quitCarManageVo;
	}
	
	/**
	 * 添加退车信息 
	 */
	public void addQuitInfor(){
		Message msg = new Message();
		try {
				quitCarManageVo.setEnterpriseId(getUserEnterpriseId());
			quitCarManageService.addQuitInfor(quitCarManageVo);
			msg.setSuccess(true);
			msg.setMsg("退车单添加成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("退车单添加失败！", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 获取退车信息 
	 */
	public void getQuitInfor(){
		try {
				quitCarManageVo.setEnterpriseId(getNowEnterpriseId());
			Json json = quitCarManageService.getQuitInfor(quitCarManageVo);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error("获取退车信息失败！", e);
		}
	}

	/**
	 * 审核退车信息
	 */
	public void doAuditQuitInfor(){
		try {
			quitCarManageVo.setEnterpriseId(getNowEnterpriseId());
			quitCarManageVo.setExitCar_Check_Person(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId().toString()); 
			super.writeJson(quitCarManageService.doAuditQuitInfor(quitCarManageVo));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("审核失败！", e);
		}
		
	}
	
	/**
	 * 删除退车信息
	 */
	public void deleteQuitInfor(){
		Message msg = new Message();
		try {
			quitCarManageService.deleteQuitInfor(quitCarManageVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("删除失败！", e);
		}
		super.writeJson(msg);
	}
	/**
	 *修改退车信息
	 */
	public void updateQuitInfor(){
		Message msg = new Message();
		try {
			quitCarManageService.updateQuitInfor(quitCarManageVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("修改失败！", e);
		}
		super.writeJson(msg);
	}
	
	/**
	 * 通知退款保存
	 */
	public void doNoticeRefundment(){
		Message msg = new Message();
		try {
			quitCarManageVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=quitCarManageService.doNoticeRefundment(quitCarManageVo);
			if(flag!=null&&flag==true){
				msg.setSuccess(true);
				msg.setMsg("通知退款成功！");
			}else{
				msg.setMsg("数据不完整，操作失败！");
			}
		} catch (Exception e) {
			logger.error("通知退款保存失败！", e);
			msg.setSuccess(false);
			msg.setMsg("通知退款保存失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 退车时 获取应退金额
	 */
	public void getAmountByExit(){
		try {
			super.writeJson(quitCarManageService.getAmountByExit(quitCarManageVo));
		} catch (Exception e) {
			logger.error("获取应退金额失败！", e);
		} 
	}
	
}
