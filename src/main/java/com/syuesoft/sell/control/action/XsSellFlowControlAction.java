package com.syuesoft.sell.control.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.control.service.XsSellFlowControlService;
import com.syuesoft.sell.control.vo.XsSellFlowControlVo;
/**
 * 流程控制Action
 * */
@Action("xsSellFlowControlAction")
public class XsSellFlowControlAction extends BaseAction implements
		ModelDriven<XsSellFlowControlVo> {
	Logger logger=Logger.getLogger(this.getClass());
	private XsSellFlowControlVo xsfcVo=new XsSellFlowControlVo();
	
	@Autowired
	private XsSellFlowControlService xsSellFlowControlService;
	/**
	 * 增加流程控制信息
	 * */
	public void save(){
		try {
			super.writeJson(xsSellFlowControlService.save(xsfcVo));
		} catch (Exception e) {
			logger.error("增加流程控制信息失败！", e);
		}
	}
	/**
	 * 删除流程控制信息
	 * */
	public void delete(){
		try {
			super.writeJson(xsSellFlowControlService.delete(xsfcVo));
		} catch (Exception e) {
			logger.error("删除流程控制信息失败！", e);
		}
	}
	
	
	public XsSellFlowControlVo getModel() {
		// TODO Auto-generated method stub
		return xsfcVo;
	}

}
