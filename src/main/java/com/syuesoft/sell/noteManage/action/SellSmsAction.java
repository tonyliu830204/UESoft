package com.syuesoft.sell.noteManage.action;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;


import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.noteManage.service.SellSmsService;
import com.syuesoft.sell.noteManage.vo.SellSmsVo;
@ParentPackage(value="basePackage")
@Action("sellSmsAction")
public class SellSmsAction extends BaseAction implements ModelDriven<SellSmsVo>{
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private SellSmsService sellSmsService;
	private SellSmsVo smsVo=new SellSmsVo();
	
	public SellSmsVo getModel() {
		// TODO Auto-generated method stub
		return smsVo;
	}
	/**
	 * 短信发送管理调用短信接口
	 */
	public void smsSend(){
	    try{
	    	smsVo.setEnterprise_id(getNowEnterpriseId());
            super.writeJson(sellSmsService.saveSmsSend(smsVo));
        }catch(Exception e){
            logger.error("短信发送失败", e);
        }
	}
	
	public void getSmsInfo() {
		try {
			smsVo.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(sellSmsService.getSmsInfo(smsVo));
		} catch (Exception e) {
			logger.debug("查询短信信息失败！");
		}
	}
}
