package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.st.service.StGoodsStorageRtGoodsSearchService;
import com.syuesoft.st.vo.StGoodsStorageRtGoodsSearchVo;

@SuppressWarnings("serial")
@Action("StGoodsStorageRtGoodsSearchAction")
public class StGoodsStorageRtGoodsSearchAction extends BaseAction implements
ModelDriven<StGoodsStorageRtGoodsSearchVo>{

	StGoodsStorageRtGoodsSearchVo stGoodsStorageRtGoodsSearchVo=new StGoodsStorageRtGoodsSearchVo();
	@Autowired StGoodsStorageRtGoodsSearchService stGoodsStorageRtGoodsSearchService;
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 入退单查询模块   入退单信息预加载
	 */
	public void loadStGoodsStorageRtGoodsSearchInfo(){
		try {
			stGoodsStorageRtGoodsSearchVo.setEnterprise_id(getNowEnterpriseId()+"");
			super.writeJson(stGoodsStorageRtGoodsSearchService.loadStGoodsStorageRtGoodsSearchInfo(stGoodsStorageRtGoodsSearchVo));
		} catch (Exception e) {
			logger.error("入退单查询模块   入退单信息预加载    异常", e);
		}
	}
	
	
	/**
	 * 入退单明细   通过入退时间获取
	 */
	public void loadStGoodsStorageRtGoodsDetailSearchInfo(){
		try {
			super.writeJson(stGoodsStorageRtGoodsSearchService.loadStGoodsStorageRtGoodsDetailSearchInfo(stGoodsStorageRtGoodsSearchVo));
		} catch (Exception e) {
			logger.error("入退单查询模块   入退单信息预加载    异常", e);
		}
	}
	
	
	
	public StGoodsStorageRtGoodsSearchVo getModel() {
		return stGoodsStorageRtGoodsSearchVo;
	}

	
}
