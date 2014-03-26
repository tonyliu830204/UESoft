package com.syuesoft.vipmanagement.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.SmsRecordManagementService;
import com.syuesoft.vipmanagement.vo.SmsRecordManagementVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("smsRecordManagementAction")
public class SmsRecordManagementAction extends BaseAction implements
		ModelDriven<SmsRecordManagementVo> {
    private static final long serialVersionUID = 1L;
    private SmsRecordManagementVo smsRecordManagementVo = new SmsRecordManagementVo();
    private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SmsRecordManagementService smsRecordManagementService;
	
	
	public SmsRecordManagementVo getModel() {
		return smsRecordManagementVo;
	}
	
	/**
	 * 获取发送后的短信记录
	 */
	public void getSmsSended(){
	    try {
	        super.writeJson(smsRecordManagementService.getSmsSended(smsRecordManagementVo));
        } catch (Exception e) {
            logger.error("查询短信记录", e);
        }
	}

	/**
	 * 
	 * 删除短信记录
	 */
	public void doDelete(){
		try {
		    super.writeJson(smsRecordManagementService.doDelete(smsRecordManagementVo));
		} catch (Exception e) {
		    logger.error("删除短信记录", e);
		}
	}
}