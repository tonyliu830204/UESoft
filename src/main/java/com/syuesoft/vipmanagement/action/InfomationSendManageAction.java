package com.syuesoft.vipmanagement.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.InfomationSendManageService;
import com.syuesoft.vipmanagement.vo.InfomationSendManageVo;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * 
* @ClassName: InfomationSendManageAction 
* @Description: TODO(短信发送) 
* @author HeXin 
* @date 2013-12-24 上午10:12:34 
* @version 1.0
 */
@ParentPackage(value="basePackage")
@Action("infomationSendManageAction")
public class InfomationSendManageAction extends BaseAction implements ModelDriven<InfomationSendManageVo> {
    private static final long serialVersionUID = 1L;
    private InfomationSendManageVo infomationSendManageVo = new InfomationSendManageVo();
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private InfomationSendManageService infomationSendManageService;
    
	public InfomationSendManageVo getModel() {
		return infomationSendManageVo;
	}
	
	/**
	 * 短信发送管理
	 */
	public void doFind(){
		try {
			super.writeJson(infomationSendManageService.doFind(infomationSendManageVo));
		} catch (Exception e) {
		    logger.error("短信发送查询失败", e);
		}
	}
	
	/**
	 * 短信发送管理调用短信接口
	 */
	public void smsSend(){
	    try{
            super.writeJson(infomationSendManageService.saveSmsSend(infomationSendManageVo, getUsers()));
        }catch(Exception e){
            logger.error("短信发送失败", e);
        }
	}
}