package com.syuesoft.sell.sellZhProject.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.sellZhProject.service.SellZhProjectService;
import com.syuesoft.sell.sellZhProject.vo.SellZhProjectVo;
import com.syuesoft.util.Msg;
@ParentPackage(value="basePackage")
@Action("sellZhProjectAction")
public class SellZhProjectAction  extends BaseAction implements ModelDriven<SellZhProjectVo>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SellZhProjectService sellZhProjectService;
	private SellZhProjectVo sellZhProjectVo = new SellZhProjectVo();
	
	public SellZhProjectVo getModel() {
		return sellZhProjectVo;
	}
	//查询销售
	public void getsellCar(){
		try {
				sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellZhProjectService.findSellInfor(sellZhProjectVo));
		} catch (Exception e) {
			logger.debug("查询销售汇总失败！");
		}	
		
	}
	//查询装潢清单
	public void getSellZhlist(){
		try {
				sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellZhProjectService.getZhList(sellZhProjectVo));
		} catch (Exception e) {
			logger.debug("查询装潢清单失败！");
		}	
		
	}
	//保存
	public void savaZhInfor(){
		Msg msg = new Msg();
		try {
			sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellZhProjectService.saveSellZh(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("保存装潢信息失败！");
		}
		super.writeJson(msg);
	}
	//修改
	public void updateZhInfor(){
		Msg msg = new Msg();
		try {
			sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellZhProjectService.updateSellZh(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("修改装潢信息失败！");
		}
		super.writeJson(msg);
	}
	//删除
	public void deleteZhInfor(){
		Msg msg = new Msg();
		try {
			sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellZhProjectService.deleteSellZh(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("删除装潢信息失败！");
		}
		super.writeJson(msg);
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=sellZhProjectService.isRefundment(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断装潢单是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断装潢单是否已审核失败！");
		}
		super.writeJson(msg);
	}
	/**判断是否未审核*/
	public void isNotRefundment(){
		Msg msg = new Msg();
		try {
			sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=sellZhProjectService.isNotRefundment(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断装潢单是否未审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断装潢单是否未审核失败！");
		}
		super.writeJson(msg);
	}
	
	//审核
	public void updateAudit(){
		Msg msg = new Msg();
		try {
			sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellZhProjectService.updateExamin(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("审核装潢信息失败！");
		}
		super.writeJson(msg);
	}
	//转结算
	public void updatesum(){
		Msg msg = new Msg();
		try {
				sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellZhProjectService.updateSum(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("转结算失败！");
		}
		super.writeJson(msg);
	}
	//查询销售
	public void getSellList(){
		try {
				sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellZhProjectService.getSellList(sellZhProjectVo));
		} catch (Exception e) {
			logger.debug("查询销售信息失败！");
		}	
		
	}
	public void isUseDocument(){
		Msg msg = new Msg();
		try {
			sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=sellZhProjectService.isUse(sellZhProjectVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断装潢单是否已使用失败！", e);
			msg.setMsg("判断装潢单是否已使用失败！");
		}
		super.writeJson(msg);
	}
	//查询装潢
	public void getZhInfor(){
		try {
				sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellZhProjectService.getZhInfor(sellZhProjectVo));
		} catch (Exception e) {
			logger.debug("查询装潢信息失败！");
		}	
		
	}
	
	//查询装潢
	public void getZhDelInfor(){
		try {
				sellZhProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellZhProjectService.getZhDelInfor(sellZhProjectVo));
		} catch (Exception e) {
			logger.debug("查询装潢明细信息失败！");
		}	
		
	}
	
}
