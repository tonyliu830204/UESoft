package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.st.service.StSellOrderStRtSellOrderSearchService;
import com.syuesoft.st.vo.StSellOrderStRtSellOrderSearchVo;

/**
 * @author Ninghuan
 * 消退单查询Action
 */
@SuppressWarnings("serial")
@Action("StSellOrderStRtSellOrderSearchAction")
public class StSellOrderStRtSellOrderSearchAction extends BaseAction implements
ModelDriven<StSellOrderStRtSellOrderSearchVo>{

	StSellOrderStRtSellOrderSearchVo stSellOrderStRtSellOrderSearchVo=new StSellOrderStRtSellOrderSearchVo();
	@Autowired StSellOrderStRtSellOrderSearchService stSellOrderStRtSellOrderSearchService;
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 消退单查询模块  消退单信息预加载
	 */
	public void loadStSellOrderStRtSellOrderSearchInfo(){
		try {
			stSellOrderStRtSellOrderSearchVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderStRtSellOrderSearchService.loadStSellOrderStRtSellOrderSearchInfo(stSellOrderStRtSellOrderSearchVo));
		} catch (Exception e) {
			logger.error("消退单查询模块  消退单信息预加载    异常", e);
		}
	}
	
	
	public StSellOrderStRtSellOrderSearchVo getModel() {
		return stSellOrderStRtSellOrderSearchVo;
	}
	
}
