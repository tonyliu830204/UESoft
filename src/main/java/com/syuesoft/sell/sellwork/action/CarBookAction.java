package com.syuesoft.sell.sellwork.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.sellwork.service.CarBookService;
import com.syuesoft.sell.sellwork.vo.CarBookVo;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;


@ParentPackage(value="basePackage")
@Action("carBookAction")
public class CarBookAction extends BaseAction implements ModelDriven<CarBookVo> {
    private Logger logger = Logger.getLogger(this.getClass());
	private CarBookVo carBookVo = new CarBookVo();
	
	public CarBookVo getModel() {
		return carBookVo;
	}

	@Resource
	private CarBookService carBookService;
	
	/**
	 * 新增预定单信息
	 */
	public void addCarBookInfor(){
		try {
				carBookVo.setEnterpriseId(getUserEnterpriseId());
			carBookVo.setStf_Id_Person(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId().toString());
			super.writeJson(carBookService.addCarBookInfor(carBookVo));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	/**
	 *删除 
	 */
	public void deleteCarBookInfor(){
		Message msg = new Message();
		try {
			carBookVo.setEnterpriseId(getUserEnterpriseId());
			int resault = carBookService.deleteCarBookInfor(carBookVo);
			if(resault==1){
				msg.setSuccess(false);
				msg.setMsg("此预订单已有销售记录，不可删除！");
			}else if(resault==2){
				msg.setSuccess(false);
				msg.setMsg("此预订单已有预收款记录，不可删除！");
			}else{
				msg.setSuccess(true);
				msg.setObj("删除成功！");
			}
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 修改
	 */
	public void updateCarBookInfor(){
		Message msg = new Message();
		try {
			carBookVo.setEnterpriseId(getUserEnterpriseId());
			carBookService.updateCarBook(carBookVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error(e);
			e.printStackTrace();
		}
		super.writeJson(msg);
		
	}
	
	
	/**
	 * 查询预订单汇总信息
	 */
	public void findCarBookInfor(){
		try {
				carBookVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carBookService.findCarBookInfor(carBookVo));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 审核
	 */
	public void doAudit(){
		Msg msg = new Msg();
		try {
			carBookVo.setEnterpriseId(getUserEnterpriseId());
			msg=carBookService.doAudit(carBookVo);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("订单审核失败！");
			logger.error("订单审核失败");
		}
		super.writeJson(msg);
	}
	/**
	 * 取消客户预定单
	 */
	public void doAduitCancelBook(){
		try {
			carBookVo.setEnterpriseId(getUserEnterpriseId());
			super.writeJson(carBookService.doAduitCancelBook(carBookVo));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		
	}
	/**
	 * 取消客户预定单
	 */
	public void doCancelBook(){
		Message msg = new Message();
		try {
			carBookVo.setEnterpriseId(getUserEnterpriseId());
			carBookService.doCancelBook(carBookVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			logger.error("取消客户预订单失败！",e);
			msg.setMsg("取消客户预订单失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 根据车辆型号查找标准销售价或限价
	 * */
	public void findCarTypeSellPriceOrControlPrice(){
		Message msg = new Message();
		try {
			carBookVo.setEnterpriseId(getNowEnterpriseId());
			Double value=carBookService.findCarTypeSellPriceOrControlPrice(carBookVo,carBookVo.getFlag());
			msg.setSuccess(true);
			msg.setObj(value);
		} catch (Exception e) {
			logger.error("根据车辆型号查找标准销售价或限价失败！",e);
			msg.setMsg("根据车辆型号查找标准销售价或限价失败！");
		}
		super.writeJson(msg);
	}


/**判断是否已审核*/
public void isRefundment(){
	Msg msg = new Msg();
	try {
		carBookVo.setEnterpriseId(getUserEnterpriseId());
		Boolean flag=carBookService.isRefundment(carBookVo);
		msg.setSuccess(true);
		msg.setObj(flag);
	} catch (Exception e) {
		logger.error("判断预订单是否已审核失败！", e);
		msg.setSuccess(false);
		msg.setMsg("判断预订单是否已审核失败！");
	}
	super.writeJson(msg);
}
}
