package com.syuesoft.sell.carAllocate.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.carAllocate.service.SellCarReserveService;
import com.syuesoft.sell.carAllocate.vo.SellCarReserveVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("sellCarReserveAction")
public class SellCarReserveAction extends BaseAction implements ModelDriven<SellCarReserveVo> {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private SellCarReserveService sellService;
	
	private SellCarReserveVo sellVo = new SellCarReserveVo();
	
	
	public SellCarReserveVo getModel() {
		return sellVo;
	}
	/**
	 * 预定单查询
	 */
	public void getSellList(){
		try {
				sellVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellService.findSellCarReserve(sellVo));
		} catch (Exception e) {
			logger.error("查询订单失败！", e);
		}
	}
	/**
	 * 预定单交期提醒查询
	 */
	public void getCarReserveExpireList(){
		try {
				sellVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellService.QueryCarReserveExpire(sellVo));
		} catch (Exception e) {
			logger.error("查询订单失败！", e);
		}
	}
	
	/**
	 * 车辆分配
	 */
	public void modifyReverd(){
		Msg msg = new Msg();
		try {
			sellVo.setEnterpriseId(getNowEnterpriseId());
			sellService.modifyReverd(sellVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("操作失败！");
		}
		super.writeJson(msg);
	}
	
	
	public void isUseDocument(){
		Msg msg = new Msg();
		try {
			sellVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=sellService.isUse(sellVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断预订单是否已使用失败！", e);
			msg.setMsg("判断预订单是否已使用失败！");
		}
		super.writeJson(msg);
	}
	
	

}