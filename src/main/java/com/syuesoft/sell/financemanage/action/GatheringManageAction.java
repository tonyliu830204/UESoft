package com.syuesoft.sell.financemanage.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.financemanage.service.GatheringManageService;
import com.syuesoft.sell.financemanage.vo.GatheringManageVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;


@ParentPackage(value="basePackage")
@Action("gatheringManageAction") 
public class GatheringManageAction extends BaseAction implements ModelDriven<GatheringManageVo> {

	Logger logger=Logger.getLogger(this.getClass());
	private GatheringManageVo gatheringManageVo = new GatheringManageVo();
	
	public GatheringManageVo getModel() {
		return gatheringManageVo;
	}
	@Resource
	private GatheringManageService gatheringManageService;

	
	/**
	 * 预订单选择 查询
	 */
	public void findBillInfor(){
		try {
				gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			Json json = gatheringManageService.findBillInfor(gatheringManageVo);
			super.writeJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取应收款信息
	 */
	public void findShouldAccountInfor(){
		try {
				gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			Json json = gatheringManageService.findShouldAccountInfor(gatheringManageVo);
			super.writeJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *收款   保存收款记录
	 */
	public void saveAmonunt(){
		Message msg = new Message();
		try {
			gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			gatheringManageService.saveAmonunt(gatheringManageVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
	/**
	 * 获取收款 记录
	 */
	public void getAmonunt(){
		try {
			gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			Json json = gatheringManageService.getAmonunt(gatheringManageVo);
			super.writeJson(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 新增预收款记录
	 */
	public void addYamount(){
		Message msg = new Message();
		try {
			gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			gatheringManageService.addYamount(gatheringManageVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	} 
	
	/** 
	 * 删除预收款记录
	 */
	public void deleteYamount(){
		Message msg = new Message();
		try {
			gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			gatheringManageService.deleteYamount(gatheringManageVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
		
	}
	/** 
	 * 修改预收款记录
	 */
	public void updateYamount(){
		Message msg = new Message();
		try {
			gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			gatheringManageService.updateYamount(gatheringManageVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
	/**
	 * 获取预收款使用记录
	 */
	 public void getUseRecord(){
		try {
			gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			List rlist = gatheringManageService.getUseRecord(gatheringManageVo);
			super.writeJson(rlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 预订单是否已结算转收银
	 * */
	 public void isToBalance(){
		 Message msg = new Message();
		try {
			gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=gatheringManageService.isToBalance(gatheringManageVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("查找预订单是否己结算转收银失败！", e);
			msg.setMsg("查找预订单是否己结算转收银失败！");
		}
		super.writeJson(msg);
	 }
	 /**
	  * 查找预收款最大可预交金额
	  * */
	 public void findMaxCouldToBalance(){
		 Message msg = new Message();
		try {
				gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			Double temp=gatheringManageService.findMaxCouldToBalance(gatheringManageVo);
			msg.setSuccess(true);
			msg.setObj(temp);
		} catch (Exception e) {
			logger.error("查找预收款最大可预交金额失败！", e);
			msg.setMsg("查找预收款最大可预交金额失败！");
		}
		super.writeJson(msg); 
	 }
	 /**
	  * 获取预收款使用记录
	  */
	 public void dowhetheradvice(){
		 Message msg = new Message();
		 try {
			 gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
			 boolean bool = gatheringManageService.dowhetheradvice(gatheringManageVo);
			 if(bool){
				 msg.setSuccess(true);
			 }else{
				 msg.setSuccess(false);
				 msg.setMsg("该订单已取消，不可继续收款！");
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 super.writeJson(msg);
	 }
		/**
		 * 预收款 查询
		 */
		public void getReadyBillInfor(){
			try {
					gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(gatheringManageService.getReadyBillInfor(gatheringManageVo));
			} catch (Exception e) {
				logger.error("预收款查询失败！", e);
				e.printStackTrace();
			}
		}
		/**
		 * 预收款余额 查询
		 */
		public void getYuEBillInfor(){
			try {
					gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(gatheringManageService.getYuEBillInfor(gatheringManageVo));
			} catch (Exception e) {
				logger.error("预收款余额查询失败！", e);
				e.printStackTrace();
			}
		}
		
	 
		/**
		 *应收款 查询
		 */
		public void getShouldBillInfor(){
			try {
					gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(gatheringManageService.getShouldBillInfor(gatheringManageVo));
			} catch (Exception e) {
				logger.error("应收款查询失败！", e);
				e.printStackTrace();
			}
		}
		/**
		 * 应收款欠款查询
		 */
		public void getQkBillInfor(){
			try {
					gatheringManageVo.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(gatheringManageService.getQkBillInfor(gatheringManageVo));
			} catch (Exception e) {
				logger.error("应收款欠款查询失败！", e);
				e.printStackTrace();
			}
		}
		
	 
}
