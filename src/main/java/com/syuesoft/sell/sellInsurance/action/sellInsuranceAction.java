package com.syuesoft.sell.sellInsurance.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.sellInsurance.service.SellInsuranceListService;
import com.syuesoft.sell.sellInsurance.vo.SellInsuranceListVo;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.ParentPackage;
@ParentPackage(value="basePackage")
@Action("sellInsuranceAction")
public class sellInsuranceAction extends BaseAction implements ModelDriven<SellInsuranceListVo> {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SellInsuranceListService  sellInsuranceListService;
	private SellInsuranceListVo sell=new SellInsuranceListVo();
	
	public SellInsuranceListVo getModel() {
		return sell;
	}
	
	/**
	 * 车辆保单管理，保单汇总查询
	 */
	public void getInsuranceList(){
		try {
				sell.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellInsuranceListService.getSellInsurance(sell));
		} catch (Exception e) {
			logger.error("查询保单信息失败！", e);
		}
	}
	public void getInfo(){
		try {
				sell.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellInsuranceListService.getInfo(sell));
		} catch (Exception e) {
			logger.error("查询保险险种信息失败！", e);
		}
	}

	public void getInsurance(){
		try {
				sell.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellInsuranceListService.getInsurance(sell));
		} catch (Exception e) {
			logger.error("新增保险险种信息表失败！", e);
		}
	}
	public void addInsurance(){
		Msg msg = new Msg();
		try {
			sell.setEnterpriseId(getNowEnterpriseId());
			sellInsuranceListService.addSellInsurance(sell);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("保存保单信息失败！");
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
		
		public void modifyInsurance(){
			Msg msg = new Msg();
			try {
				sell.setEnterpriseId(getNowEnterpriseId());
				sellInsuranceListService.modifyInsurance(sell);
				msg.setSuccess(true);
				msg.setMsg("success");
			} catch (Exception e) {
				logger.debug("修改保单信息失败！");
				e.printStackTrace();
			}
			super.writeJson(msg);
		}
		public void deleteInsurance(){
			Msg msg = new Msg();
			try {
				sellInsuranceListService.deleteInsurance(sell);
				msg.setSuccess(true);
				msg.setMsg("success");
			} catch (Exception e) {
				logger.debug("删除保单信息失败！");
				e.printStackTrace();
			}
			super.writeJson(msg);
		}
		/**判断是否已审核*/
		public void isRefundment(){
			Msg msg = new Msg();
			try {
				sell.setEnterpriseId(getNowEnterpriseId());
				Boolean flag=sellInsuranceListService.isRefundment(sell);
				msg.setSuccess(true);
				msg.setObj(flag);
			} catch (Exception e) {
				logger.error("判断保单是否已审核失败！", e);
				msg.setSuccess(false);
				msg.setMsg("判断保单是否已审核失败！");
			}
			super.writeJson(msg);
		}
		/**判断是否未审核*/
		public void isNotRefundment(){
			Msg msg = new Msg();
			try {
				sell.setEnterpriseId(getNowEnterpriseId());
				Boolean flag=sellInsuranceListService.isNotRefundment(sell);
				msg.setSuccess(true);
				msg.setObj(flag);
			} catch (Exception e) {
				logger.error("判断保险单是否未审核失败！", e);
				msg.setSuccess(false);
				msg.setMsg("判断保险单是否未审核失败！");
			}
			super.writeJson(msg);
		}
		
		
		public void updateExamine(){
			Msg msg = new Msg();
			try {
				sell.setEnterpriseId(getNowEnterpriseId());
				sellInsuranceListService.updateExamine(sell);
				msg.setSuccess(true);
				msg.setMsg("success");
			} catch (Exception e) {
				logger.debug("审核保单信息失败！");
				e.printStackTrace();
			}
			super.writeJson(msg);
			
		}
		
		public void updateSum(){
			Msg msg = new Msg();
			try {
					sell.setEnterpriseId(getNowEnterpriseId());
				sellInsuranceListService.updateSum(sell);
				msg.setSuccess(true);
				msg.setMsg("success");
			} catch (Exception e) {
				logger.debug("转结算失败！");
				e.printStackTrace();
			}
			super.writeJson(msg);
			
		}
		//车辆保险模块：车辆保险父查询 
		public void getSellInsuranceF(){
			try {
					sell.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(sellInsuranceListService.getSellInsuranceF(sell));
			} catch (Exception e) {
				logger.error("查询保单信息失败！", e);
			}
		}
		//车辆保险模块：车辆保险子查询 
		public void getInsuranceDetails(){
			try {
					sell.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(sellInsuranceListService.getInsuranceDetails(sell));
			} catch (Exception e) {
				logger.error("查询保单明细信息失败！", e);
			}
		}
	/*	public void getCarBrand(){
			try {
				super.writeJson(sellInsuranceListService.getCarBrand(sell));
			} catch (Exception e) {
				logger.error("查询车辆品牌信息失败！", e);
			}
		}
		public void getCarModel(){
			try {
				super.writeJson(sellInsuranceListService.getCarModel(sell));
			} catch (Exception e) {
				logger.error("查询车辆型号信息失败！", e);
			}
		}*/
		//车辆保险模块:车辆保险查询
		public void getCarInsurance(){
			try {
					sell.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(sellInsuranceListService.getCarInsurance(sell));
			} catch (Exception e) {
				logger.error("查询车辆保险信息失败！", e);
			}
		}
		public void isUseDocument(){
			Msg msg = new Msg();
			try {
				sell.setEnterpriseId(getNowEnterpriseId());
				Boolean flag=sellInsuranceListService.isUse(sell);
				msg.setSuccess(true);
				msg.setObj(flag);
			} catch (Exception e) {
				logger.error("判断保险单是否已使用失败！", e);
				msg.setMsg("判断保险单是否已使用失败！");
			}
			super.writeJson(msg);
		}
		
		//车辆代保查询:保单查询父级
		public void getInsuranceInfor(){
			try {
					sell.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(sellInsuranceListService.getInsuranceInfor(sell));
			} catch (Exception e) {
				logger.error("查询保险信息失败！", e);
			}
		}
		//车辆代保查询:保单查询子级级
		public void getInsuranceDel(){
			try {
					sell.setEnterpriseId(getNowEnterpriseId());
				super.writeJson(sellInsuranceListService.getInsuranceDel(sell));
			} catch (Exception e) {
				logger.error("查询保险信息失败！", e);
			}
		
		}

}
