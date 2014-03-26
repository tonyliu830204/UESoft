package com.syuesoft.sell.synthesis_assay.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.synthesis_assay.service.PurchaseandsaleDailysheetService;
import com.syuesoft.sell.synthesis_assay.vo.PurchaseandsaleDailysheetVo;

@Action("purchaseandsaleDailysheetAction")
public class PurchaseandsaleDailysheetAction extends BaseAction implements ModelDriven<PurchaseandsaleDailysheetVo> {

	private Logger log = Logger.getLogger(this.getClass());
	private PurchaseandsaleDailysheetVo purchaseandsaleDailysheetVo = new PurchaseandsaleDailysheetVo();
	
	public PurchaseandsaleDailysheetVo getModel() {
		return purchaseandsaleDailysheetVo;
	}
	@Resource
	private PurchaseandsaleDailysheetService purchaseandsaleDailysheetService;
	/**
	 * 获取該品牌的统计数量  (品牌) 
	 */
	public void getDailysheetParent(){
		try {
				purchaseandsaleDailysheetVo.setEnterprise_id(getUserEnterpriseId());
			super.writeJson(purchaseandsaleDailysheetService.getPurchaseandsaleDailysheet(purchaseandsaleDailysheetVo));
		} catch (Exception e) {
			log.error("获取品牌的统计数量失败",e);
			e.printStackTrace();
		}
	}
	/**
	 * 获取該品牌下的型号的统计数量  
	 */
	public void getDailysheetChild(){
		try {
				purchaseandsaleDailysheetVo.setEnterprise_id(getUserEnterpriseId());
			List rowlist = purchaseandsaleDailysheetService.getDailysheetChild(purchaseandsaleDailysheetVo);
			super.writeJson(rowlist);
		} catch (Exception e) {
			log.error("获取统计数量失败",e);
			e.printStackTrace();
		}
	}

}
