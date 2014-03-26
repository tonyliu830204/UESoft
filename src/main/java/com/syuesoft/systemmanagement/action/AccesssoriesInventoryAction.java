package com.syuesoft.systemmanagement.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.systemmanagement.service.AccesssoriesInventoryService;
import com.syuesoft.systemmanagement.vo.AccesssoriesInventoryVo;
import com.syuesoft.util.Message;

@ParentPackage(value="basePackage")
@Action("accesssoriesInventoryAction")
public class AccesssoriesInventoryAction extends BaseAction implements
		ModelDriven<AccesssoriesInventoryVo> {

	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
	private AccesssoriesInventoryService accesssoriesInventoryService;
	private AccesssoriesInventoryVo accesssoriesInventoryVo  = new AccesssoriesInventoryVo();
	
    public AccesssoriesInventoryVo getModel() {
        return accesssoriesInventoryVo;
    }
	
	/**
	 * 查询盘点汇总信息
	 */
	public void doFind(){
		try {
			accesssoriesInventoryVo.setEnterpriseId(getNowEnterpriseId().toString());
		    super.writeJson(accesssoriesInventoryService.doFind(accesssoriesInventoryVo));
		} catch (Exception e) {
		    logger.error("查询盘点汇总信息失败", e);
		}
	}
	
	/**
	 * 查询配件信息
	 */
	public void doFindAccessInfor(){
		try {
			accesssoriesInventoryVo.setEnterpriseId(getNowEnterpriseId().toString());
			super.writeJson(accesssoriesInventoryService.doFindAccessInfor(accesssoriesInventoryVo));
		} catch (Exception e) {
		    logger.error("查询配件信息失败", e);
		}
	}
	
	/**
	 * 新增盘点信息 主表
	 */
	public void doAddFather(){
		Message msg = new Message();
		try {
			accesssoriesInventoryVo.setEnterpriseId(getNowEnterpriseId().toString());
			accesssoriesInventoryService.doAddFather(accesssoriesInventoryVo, getUsers());
			msg.setMsg("保存成功!");
			msg.setSuccess(true);
		} catch (Exception e) {
		    msg.setMsg("保存失败!");
			msg.setSuccess(false);
			logger.error("查询盘点信息失败", e);
		}
		super.writeJson(msg);
	}
	
	/**
	 * 通过盘点单好查询查询盘点明细
	 */
	public void getStInventoryDetailById(){
		try {
		    super.writeJson(accesssoriesInventoryService.getStInventoryDetailById(accesssoriesInventoryVo));
		} catch (Exception e) {
		    logger.error("根据盘点单查询失败", e);
		}
	}
	
	/**
	 * 更新主表信息
	 */
	public void doUpdateFather(){
		Message msg = new Message();
		try {
			accesssoriesInventoryService.doUpdateFather(accesssoriesInventoryVo, getUsers());
			msg.setMsg("更新成功!");
			msg.setSuccess(true);
		} catch (Exception e) {
		    msg.setMsg("更新失败!");
			msg.setSuccess(false);
			logger.error("更新盘点单失败", e);
		}
		super.writeJson(msg);
	}
	
	/**
	 * 删除配件盘点汇总信息和明细信息
	 */
	public void doDelete(){
		Message msg = new Message();
		try {
			accesssoriesInventoryService.doDelete(accesssoriesInventoryVo);
			msg.setMsg("删除成功!");
            msg.setSuccess(true);
		} catch (Exception e) {
		    msg.setMsg("删除失败!");
			msg.setSuccess(false);
			logger.error("删除盘点单失败", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 审核
	 */
	public void doShenhe(){
		try {
			accesssoriesInventoryVo.setEnterpriseId(getNowEnterpriseId().toString());
			super.writeJson(accesssoriesInventoryService.doUpdateState(accesssoriesInventoryVo));
		} catch (Exception e) {
		    logger.error("审核盘点单失败", e);
		}
	}
	
	
}