package com.syuesoft.sell.allocateManage.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.allocateManage.service.SellBackService;
import com.syuesoft.sell.allocateManage.vo.SellBackVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")@Action("sellBackAction")
public class SellBackAction extends BaseAction implements
		ModelDriven<SellBackVo> {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SellBackService sellBackService;
	private SellBackVo back = new SellBackVo();

	
	public SellBackVo getModel() {
		return back;
	}

	/**
	 * 查询调退清单
	 */
	public void getSellBackInfo() {
		try {
			back.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellBackService.getSellBackInfo(back));
		} catch (Exception e) {
			logger.debug("查询调退单信息失败！");
		}
	}

	/**
	 * 新增调拨单信息
	 */
	public void addInstoreCar() {
		Msg msg = new Msg();
		try {
			back.setEnterprise_id(getUserEnterpriseId());
			sellBackService.addInstoreCar(back);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("保存新增调退单信息失败！");
		}
		super.writeJson(msg);

	}

	/**
	 * 删除调退单
	 */
	public void deleteAllocatel(){
		Msg msg = new Msg();
		try {
			back.setEnterprise_id(getNowEnterpriseId());
			sellBackService.deleteRecord(back);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("删除调退单操作失败！");
		}
		super.writeJson(msg);
	}
	public void modifyBack(){
		Msg msg = new Msg();
		try {
			back.setEnterprise_id(getNowEnterpriseId());
			sellBackService.modifySellAllocatel(back);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("修改调退单操作失败！");
		}
		super.writeJson(msg);
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			back.setEnterprise_id(getNowEnterpriseId());
			Boolean flag=sellBackService.isRefundment(back);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断调退单是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断调退单是否已审核失败！");
		}
		super.writeJson(msg);
	}
	
	//审核调退单
	public void examine(){
		Msg msg = new Msg();
		try {
			back.setEnterprise_id(getNowEnterpriseId());
			sellBackService.updateExamine(back);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("审核调退单信息失败", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 添加调退车辆：根据分销商查调拨明细
	 */
	public void getSellBackByDis() {
		try {
				back.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellBackService.getSellBackByDis(back));
		} catch (Exception e) {
			logger.debug("查询调退明细失败！");
		}
	}
	/**
	 * 调退查询模块：调退汇总信息查询
	 */
	public void getSellBack() {
		try {
				back.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellBackService.getSellBack(back));
		} catch (Exception e) {
			logger.debug("查询调退单失败！");
		}
	}
	/**
	 * 调退查询模块：调退汇总信息查询子菜单
	 */
	public void getBackDetails() {
		try {
			back.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellBackService.findBack(back));
		} catch (Exception e) {
			logger.debug("查询调退明细失败！");
		}
	}

}
