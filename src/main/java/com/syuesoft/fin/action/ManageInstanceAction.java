package com.syuesoft.fin.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fin.service.ManageInstanceService;
import com.syuesoft.fin.vo.ManageInstanceVo;

/**
 * 经营情况查询（车辆结算排行，成本分析）Action
 * */
@Action("manageInstanceAction")
public class ManageInstanceAction extends BaseAction implements ModelDriven<ManageInstanceVo>{
	Logger logger=Logger.getLogger(this.getClass());
	ManageInstanceVo miVo=new ManageInstanceVo();
	@Autowired
	private ManageInstanceService manageInstanceService;
	
	/**车辆结算排行查询*/
	public void findCarBalanceUntangle(){
		try {
			miVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(manageInstanceService.findCarBalanceUntangle(miVo));
		} catch (Exception e) {
			logger.error("车辆结算排行查询失败！", e);
		}
	}
	/**成本分析查询*/
	public void findCostAanalyse(){
		try {
			miVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(manageInstanceService.findCostAanalyse(miVo));
		} catch (Exception e) {
			logger.error("成本分析查询失败！", e);
		}
	}
	
	
	public ManageInstanceVo getModel() {
		// TODO Auto-generated method stub
		return miVo;
	}
}
