package com.syuesoft.sell.allocateManage.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jfree.chart.ChartUtilities;


import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.allocateManage.service.SellCustomAnalyseService;
import com.syuesoft.sell.allocateManage.vo.SellCustomAnalyseVo;
@ParentPackage(value="basePackage")
@Action("sellCustomAnalyseAction")
public class SellCustomAnalyseAction  extends BaseAction implements ModelDriven<SellCustomAnalyseVo>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SellCustomAnalyseService sellCustomAnalyseService;
	
	private SellCustomAnalyseVo se=new SellCustomAnalyseVo();
	
	public SellCustomAnalyseVo getModel() {
		// TODO Auto-generated method stub
		return se;
	}
	/**
	 * 销售客户分析：查询
	 */
	public void querySellCustomInfor() {
		try {
				se.setEnterprise_id(getNowEnterpriseId());	
			super.writeJson(sellCustomAnalyseService.querySellCustomInfor(se));
		} catch (Exception e) {
			logger.debug("查询销售客户分析信息失败！");
		}
	}
	/**
	 *获取销售客户分析饼图信息  
	 * @throws Exception 
	 * */
	public void findAnalyzeCakeMap() throws Exception{
		try {
				se.setEnterprise_id(getNowEnterpriseId());	
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),sellCustomAnalyseService.findAnalyzeCakeMap(se), 1000, 400);			
		} catch (Exception e) {
			logger.error("获取销售客户分析饼图信息失败！", e);
		}
	}
	/**
	 * 销售毛利分析：查询
	 */
	public void querySellMlAnaly() {
		try {
				se.setEnterprise_id(getNowEnterpriseId());	
			super.writeJson(sellCustomAnalyseService.querySellMlAnaly(se));
		} catch (Exception e) {
			logger.debug("查询销售毛利分析信息失败！");
		}
	}
}
