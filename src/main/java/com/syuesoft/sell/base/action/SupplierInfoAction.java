package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.SupplierInfoService;
import com.syuesoft.sell.base.vo.SupplierInfoVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("supplierInfoAction")
public class SupplierInfoAction extends BaseAction implements ModelDriven<SupplierInfoVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SupplierInfoVo supplierInfoVo = new SupplierInfoVo();
	private SupplierInfoService supplierInfoService;
	
	public void getPageModel(){
		try {
			supplierInfoVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(supplierInfoService.getPager(supplierInfoVo));
		} catch (Exception e) {
			logger.error("查询供应商档案失败!", e);
		}
	}
	
	public void findAllSupplier(){
		try {
			supplierInfoVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(supplierInfoService.findAllSupplier(supplierInfoVo));
		} catch (Exception e) {
			logger.error("查询供应商信息失败！", e);
		}
	}
	
	/**
	 * 保存供应商档案
	 */
	public void saveSupplierInfo(){     
		Msg msg = new Msg();
		try {
				supplierInfoVo.setEnterprise_id(getUserEnterpriseId());
			if(supplierInfoService.existSupplierTwo(supplierInfoVo.getSupplierName(),supplierInfoVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			supplierInfoService.addSupplierInfo(supplierInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存供应商档案失败!", e);
		}
		super.writeJson(msg);
	}
	
	public void deleteSupplierInfo(){
		Msg msg = new Msg();
		try {
			supplierInfoVo.setEnterprise_id(getNowEnterpriseId());
			msg = supplierInfoService.deleteSupplierInfo(supplierInfoVo);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除供应商档案失败");
			logger.error("删除供应商档案失败", e);
		}
		super.writeJson(msg);
	}
	
	public void updateSupplierInfo(){
		Msg msg = new Msg();
		try {
			supplierInfoVo.setEnterprise_id(getUserEnterpriseId());
			if(supplierInfoService.existSupplier(supplierInfoVo.getSupplierName(),supplierInfoVo.getSupplierId(),supplierInfoVo.getEnterprise_id())){
				msg.setMsg("对不起，您输入的名称已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			supplierInfoService.updateSupplierInfo(supplierInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新供应商档案失败", e);
		}
		super.writeJson(msg);
	}
	
	public SupplierInfoService getSupplierInfoService() {
		return supplierInfoService;
	}
	
	@Autowired
	public void setSupplierInfoService(SupplierInfoService supplierInfoService) {
		this.supplierInfoService = supplierInfoService;
	}
	
	public SupplierInfoVo getModel() {
		return supplierInfoVo;
	}

}
