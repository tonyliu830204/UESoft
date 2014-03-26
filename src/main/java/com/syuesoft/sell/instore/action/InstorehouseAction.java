package com.syuesoft.sell.instore.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.instore.service.InstorehouseService;
import com.syuesoft.sell.instore.vo.SellInstorehouseVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("instorehouseAction")
public class InstorehouseAction extends BaseAction implements ModelDriven<SellInstorehouseVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellInstorehouseVo instoreVo=new SellInstorehouseVo();
	private  InstorehouseService  instorehouseService;
	public void getPager(){
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instorehouseService.getPager(instoreVo),true);
		} catch (Exception e) {
			logger.error("查询入库单信息失败", e);
		}
	}	
	//退厂单新增选车
	public void getPagerDel(){
		try {
				instoreVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instorehouseService.getPagerDel(instoreVo));
		} catch (Exception e) {
			logger.error("查询入库单明细信息失败", e);
		}
	}
	public void findAllInstore(){
		try {
				instoreVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instorehouseService.findAllInstore(instoreVo));
		} catch (Exception e) {
			logger.error("查询入库单信息失败", e);
		}
	}
	//销售作业：车辆档案查询
	public void getQueryData(){
		try {
				instoreVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instorehouseService.getQueryData(instoreVo));
		} catch (Exception e) {
			logger.error("查询车辆信息失败", e);
		}
	}
	public void saveInstore(){
		Msg msg = new Msg();
		try {
				instoreVo.setEnterprise_id(getUserEnterpriseId());
			instoreVo.setStfId(this.getUsers().getBasStuff().getStfId());
			instorehouseService.addInstore(instoreVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存入库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void importInstore(){
		Msg msg = new Msg();
		try {
				instoreVo.setEnterprise_id(getUserEnterpriseId());
			instoreVo.setStfId(this.getUsers().getBasStuff().getStfId());
			instorehouseService.saveImportInstore(instoreVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("导入库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteInstore(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			if(instorehouseService.findDels(instoreVo)){
				msg.setMsg("对不起,该条记录已出库或月结，不能删除！");
				super.writeJson(msg);
				return;
			}
			instorehouseService.deleteInstore(instoreVo);
			msg.setSuccess(true);
			msg.setMsg("success");
			
		} catch (Exception e) {
			logger.error("删除入库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void findInstore(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			Boolean b=instorehouseService.findDels(instoreVo);
			msg.setObj(b);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("查询入库单信息失败", e);
		}
		super.writeJson(msg);
	}
	public void updateInstore(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			if(instorehouseService.findDels(instoreVo)){
				msg.setMsg("对不起,该条记录已出库或月结，不能删除！");
				super.writeJson(msg);
				return;
			}
			instoreVo.setStfId(this.getUsers().getBasStuff().getStfId());
			instorehouseService.updateInstore(instoreVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新入库单信息失败", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 判断是否已使用
	 * */
	public void isUseDocument(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=instorehouseService.findDels(instoreVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断入库单是否已使用失败！", e);
			msg.setMsg("判断入库单是否已使用失败！");
		}
		super.writeJson(msg);
	}
	public void examine(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			instorehouseService.updateExamine(instoreVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("审核入库单信息失败", e);
		}
		super.writeJson(msg);
	}
	//入库单查询
	public void queryInstore(){
		try {
			
				instoreVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(instorehouseService.findInstore(instoreVo));
		} catch (Exception e) {
			logger.error("查询入库单信息失败", e);
		}
	}
	//入库单查询
	public void getTreegridChild(){
		try {
				instoreVo.setEnterprise_id(getNowEnterpriseId());
				super.writeJson(instorehouseService.getTreegridChild(instoreVo));
			} catch (Exception e) {
				logger.error("查询入库单信息失败", e);
			}
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=instorehouseService.isRefundment(instoreVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断入库单是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断入库单是否已审核失败！");
		}
		super.writeJson(msg);
	}
	/**判断是否是增值发票*/
	public void isfpType(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=instorehouseService.isfpType(instoreVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断是否增值发票失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断是否增值发票失败！");
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	public void isfpTypeId(){
		Msg msg = new Msg();
		try {
			instoreVo.setEnterprise_id(getNowEnterpriseId());
			Integer id=instorehouseService.isfpTypeId(instoreVo);
			msg.setSuccess(true);
			msg.setObj(id);
		} catch (Exception e) {
			logger.error("判断是否增值发票失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断是否增值发票失败！");
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	public InstorehouseService getInstorehouseService() {
		return instorehouseService;
	}
	@Autowired
	public void setInstorehouseService(InstorehouseService instorehouseService) {
		this.instorehouseService = instorehouseService;
	}
	public SellInstorehouseVo getModel() {
		return instoreVo;
	}

}
