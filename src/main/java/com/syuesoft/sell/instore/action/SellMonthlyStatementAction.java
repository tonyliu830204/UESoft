package com.syuesoft.sell.instore.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.instore.service.SellMonthlyStatementService;
import com.syuesoft.sell.instore.vo.SellMonthlyStatementVo;
import com.syuesoft.util.Message;

@Action("sellMonthlyStatementAction")
public class SellMonthlyStatementAction extends BaseAction implements
		ModelDriven<SellMonthlyStatementVo> {

	private Logger log = Logger.getLogger(this.getClass());
	
	private SellMonthlyStatementVo sellMonthlyStatementVo = new SellMonthlyStatementVo();
	
	@Resource
	private SellMonthlyStatementService sellMonthlyStatementService;
	
	
	public SellMonthlyStatementVo getModel() {
		return sellMonthlyStatementVo;
	}
	
	/**
	 * 获取月结信息
	 */
	public void getStock(){
		try {
				sellMonthlyStatementVo.setEnterprise_id(getNowEnterpriseId());	
			super.writeJson(sellMonthlyStatementService.getStock(sellMonthlyStatementVo));
		} catch (Exception e) {
			log.error("获取库存信息失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 月结
	 */
	public void doStock(){
		Message msg = new Message();
		try {
				sellMonthlyStatementVo.setEnterprise_id(getUserEnterpriseId());	
			boolean bool = sellMonthlyStatementService.doStock(sellMonthlyStatementVo);
			if(bool){
				msg.setSuccess(true);
				msg.setMsg("月结成功!");
			}else{
				msg.setSuccess(false);
				msg.setMsg("月结失败,该时间点已月结！");
			}
			
		} catch (Exception e) {
			log.error("月结失败！",e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 反月结
	 */
	public void doReturnStock(){
		Message msg = new Message();
		try {
				sellMonthlyStatementVo.setEnterprise_id(getUserEnterpriseId());	
			boolean bool = sellMonthlyStatementService.doReturnStock(sellMonthlyStatementVo);
			if(bool){
				msg.setSuccess(true);
				msg.setMsg("反月结成功!");
			}else{
				msg.setMsg("反月结失败,该月节点不可反月结！");
				msg.setSuccess(false);
			}
			} catch (Exception e) {
			log.error("反月结失败",e);
			e.printStackTrace();
		}
			super.writeJson(msg);
	}

}
