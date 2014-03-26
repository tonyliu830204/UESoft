package com.syuesoft.sell.allocateManage.action;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.allocateManage.service.SellAllocatelService;
import com.syuesoft.sell.allocateManage.vo.SellAllocatelVo;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")
@Action("sellAllocatelAction")
public class SellAllocatelAction extends BaseAction implements ModelDriven<SellAllocatelVo> {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private SellAllocatelService sellAllocatelService;
	private SellAllocatelVo sellAllocatelVo=new SellAllocatelVo();

	
	public SellAllocatelVo getModel() {
		return sellAllocatelVo;
	}
	/**
	 * 查询调拨清单
	 */
	public void getSellAllocatelList(){
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());	
			super.writeJson(sellAllocatelService.querySellAllocatel(sellAllocatelVo));
		} catch (Exception e) {
			logger.debug("查询调拨清单失败！");
		}	
	}
	/**
	 * 修改调拨单
	 */
	public void modifySellAllocatel(){
		Msg msg = new Msg();
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());
			sellAllocatelService.modifySellAllocatel(sellAllocatelVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("操作失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 新增调拨单信息
	 */
	public void addInstoreCar(){
		Msg msg = new Msg();
		try {
			
			sellAllocatelVo.setEnterprise_id(getUserEnterpriseId());	
			sellAllocatelService.addInstoreCar(sellAllocatelVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("保存入库单信息失败！");
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
	/**
	 * 删除汇总
	 */
	public void deleteInfo(){
		Msg msg = new Msg();
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());
			sellAllocatelService.deleteRecord(sellAllocatelVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			 msg.setMsg("对不起，该调拨单已存在于调退单中，不允许删除！");
			logger.error("删除调拨单信息失败！", e);
		}
		super.writeJson(msg);
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=sellAllocatelService.isRefundment(sellAllocatelVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断调拨单是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断调拨单是否已审核失败！");
		}
		super.writeJson(msg);
	}
	//审核调拨单
	public void examine(){
		Msg msg = new Msg();
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());
			sellAllocatelService.updateExamine(sellAllocatelVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("审核调拨单信息失败", e);
		}
		super.writeJson(msg);
	}
	
	/**
	 * 根据分销商查调拨单
	 */
	public void getSellList(){
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());	
			super.writeJson(sellAllocatelService.queryList(sellAllocatelVo));
		} catch (Exception e) {
			logger.debug("查询调拨清单失败！");
		}	
	}
	/**
	 *调拨单查询模块，查询父菜单信息功能
	 */
	public void getFFatherList(){
		try {
				sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());	
			super.writeJson(sellAllocatelService.queryFather(sellAllocatelVo));
		} catch (Exception e) {
			logger.debug("查询调拨单失败！");
		}	
	}
	public void getAllocatel(){
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellAllocatelService.findAllocatel(sellAllocatelVo));
		} catch (Exception e) {
			logger.debug("查询调拨清单失败！");
		}	
	}
	
	public void isUseDocument(){
		Msg msg = new Msg();
		try {
			sellAllocatelVo.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=sellAllocatelService.isUse(sellAllocatelVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断调拨单是否已使用失败！", e);
			msg.setMsg("判断调拨单是否已使用失败！");
		}
		super.writeJson(msg);
	}
	
	public SellAllocatelService getSellAllocatelService() {
		return sellAllocatelService;
	}
	@Autowired
	public void setSellAllocatelService(SellAllocatelService sellAllocatelService) {
		this.sellAllocatelService = sellAllocatelService;
	}
	
	

}
