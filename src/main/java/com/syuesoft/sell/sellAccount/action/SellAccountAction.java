package com.syuesoft.sell.sellAccount.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.financemanage.service.GatheringManageService;
import com.syuesoft.sell.model.XsCarSellInfo;
import com.syuesoft.sell.model.XsSellAccount;
import com.syuesoft.sell.sellAccount.service.SellAccountService;
import com.syuesoft.sell.sellAccount.vo.SellAccountVo;
import com.syuesoft.util.Msg;
@Action("sellAccountAction")
public class SellAccountAction extends BaseAction implements
		ModelDriven<SellAccountVo> {
	private Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private SellAccountService sellAccountService;
	@Resource
	private GatheringManageService gatheringManageService;
	private SellAccountVo account = new SellAccountVo();


	
	public SellAccountVo getModel() {
		return account;
	}

	/**
	 * 查询结算清单
	 */
	public void getSellAccountList() {
		try {
				account.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellAccountService.getSellAccount(account));
		} catch (Exception e) {
			logger.error("查询结算清单失败！", e);
		}

	}
	public void modifyAccount(){
		Msg msg = new Msg();
		try {
			account.setEnterprise_id(getNowEnterpriseId());
			sellAccountService.updateRedoSum(account);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("删除结算单失败！");
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 转收银
	 * */
	public void saveAccount(){
		try {
				account.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellAccountService.modifyBalance(account));			
		} catch (Exception e) {
			logger.error("转收银失败！", e);
		}
	}
}
