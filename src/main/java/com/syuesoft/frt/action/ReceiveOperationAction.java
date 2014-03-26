package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.ReceiveOperationService;
import com.syuesoft.frt.vo.ReceiveOperationVo;

@ParentPackage(value = "basePackage")
@Action("receiveOperationAction")
public class ReceiveOperationAction extends BaseAction implements
        ModelDriven<ReceiveOperationVo>
{
    private static final Logger logger = Logger
            .getLogger(ReceiveOperationAction.class);
    private ReceiveOperationVo roVo=new ReceiveOperationVo();
    
    @Autowired
    private ReceiveOperationService receiveOperationService;
    
    /**
     * 接待员业绩统计汇总
     * */
    public void receiveOperationMain(){
    	try {
    		roVo.setEnterpriseId(getNowEnterpriseId());
    		super.writeJson(receiveOperationService.receiveOperationMain(roVo));
		} catch (Exception e) {
			logger.error("查询接待员业绩统计汇总失败！", e);
		}
    }
    /**
     * 接待员业绩统计明细
     * */
    public void receiveOperationDetail(){
    	try {
    		roVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(receiveOperationService.receiveOperationDetail(roVo));
		} catch (Exception e) {
			logger.error("查询接待员业绩统计明细失败！", e);
		}
    }
    
	
	public ReceiveOperationVo getModel() {
		// TODO Auto-generated method stub
		return roVo;
	}
    
    
}
