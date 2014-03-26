package com.syuesoft.sell.sellwork.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.sellwork.service.SellDbProjectService;
import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;


@ParentPackage(value="basePackage")
@Action("sellDbProjectAction")
public class SellDbProjectAction extends BaseAction implements ModelDriven<SellDbProjectVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private SellDbProjectVo sellDbProjectVo=new SellDbProjectVo();
	private SellDbProjectService sellDbProjectService;
	/**
	 * 查询销售单汇总
	 */
	@SuppressWarnings("unchecked")
	public void findSellInfor(){
		try {
			sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellDbProjectService.findSellInfor(sellDbProjectVo));
		} catch (Exception e) {
			logger.error("查询销售单汇总信息异常", e);
		}
		
	}
	public void getInvoSellInfor(){
		try {
				sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellDbProjectService.getInvoSellInfor(sellDbProjectVo));
		} catch (Exception e) {
			logger.error("查询销售单汇总信息异常", e);
		}
		
	}
	/**
	 * 查询与某个销售单想关联的代办项目信息
	 */
	public void findSellDb(){
		try {
				sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellDbProjectService.findSellDb(sellDbProjectVo));
		} catch (Exception e) {
			logger.error("查询销售代办信息异常", e);
		}
	}
	/**
	 * 代办项目保存方法
	 */
	public void saveSellPro(){
		Message msg = new Message();
		try {
			sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellDbProjectVo.setDbProjectPerson(Integer.parseInt(String.valueOf(this.getUsers().getBasStuff().getStfId())));
			sellDbProjectService.saveSellPro(sellDbProjectVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("代办项目保存失败",e);
		}
		super.writeJson(msg);
	}
	/**
	 * 代办项目删除方法
	 */
	public void deleteSellPro(){
		Message msg = new Message();
		try {
			sellDbProjectService.deleteSellPro(sellDbProjectVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("删除代办项目保存失败",e);
		}
		super.writeJson(msg);
	}
	/**
	 * 代办项目更新方法
	 */
	public void updateSellPro(){
		Message msg = new Message();
		try {
			sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellDbProjectService.updateSellPro(sellDbProjectVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("删除代办项目保存失败",e);
		}
		super.writeJson(msg);
	}
	/**
	 * 代办项目审核
	 */
	public void updateExamin(){
		Message msg = new Message();
		try {
			sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			sellDbProjectService.updateExamin(sellDbProjectVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("审核代办项目保存失败",e);
		}
		super.writeJson(msg);
	}
	/**判断是否已审核*/
	public void isRefundment(){
		Msg msg = new Msg();
		try {
			sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=sellDbProjectService.isRefundment(sellDbProjectVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断代办单是否已审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断装代办是否已审核失败！");
		}
		super.writeJson(msg);
	}
	/**判断是否未审核*/
	public void isNotRefundment(){
		Msg msg = new Msg();
		try {
			sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=sellDbProjectService.isNotRefundment(sellDbProjectVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断代办单是否未审核失败！", e);
			msg.setSuccess(false);
			msg.setMsg("判断代办单是否未审核失败！");
		}
		super.writeJson(msg);
	}
	
	/**
	 * 转结算
	 */
	public void updateSellAcount(){
		Message msg = new Message();
		try {
				sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());	
			sellDbProjectService.updateSellAcount(sellDbProjectVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("转结算失败",e);
		}
		super.writeJson(msg);
	}
	public void isUseDocument(){
		Msg msg = new Msg();
		try {
			Boolean flag=sellDbProjectService.isUse(sellDbProjectVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断装潢单是否已使用失败！", e);
			msg.setMsg("判断装潢单是否已使用失败！");
		}
		super.writeJson(msg);
	}
	/**
	 *代办项目查询模块，查询父菜单信息功能
	 */
	public void queryDbInfor(){
		try {
				sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellDbProjectService.queryDbInfor(sellDbProjectVo));
		} catch (Exception e) {
			logger.debug("查询代办项目信息失败！");
		}	
	}
	/**
	 *代办项目查询模块，查询子菜单信息功能
	 */
	public void queryDbChildInfor(){
		try {
				sellDbProjectVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellDbProjectService.queryDbChildInfor(sellDbProjectVo));
		} catch (Exception e) {
			logger.debug("查询代办项目信息失败！");
		}	
	}
	
	public SellDbProjectService getSellDbProjectService() {
		return sellDbProjectService;
	}
	@Autowired
	public void wService(SellDbProjectService sellDbProjectService) {
		this.sellDbProjectService = sellDbProjectService;
	}
	
	public SellDbProjectVo getModel() {
		return sellDbProjectVo;
	}

}
