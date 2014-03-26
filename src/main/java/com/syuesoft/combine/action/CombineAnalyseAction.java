package com.syuesoft.combine.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jfree.chart.ChartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.combine.service.CombineAnalyseService;
import com.syuesoft.combine.vo.CombineAnalyseVo;
@ParentPackage(value="basePackage")
@Action("combineAnalyseAction")
public class CombineAnalyseAction extends BaseAction implements
		ModelDriven<CombineAnalyseVo> {
	private final Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private CombineAnalyseService combineAnalyseService;
	
	private CombineAnalyseVo combineAnalyseVo  = new CombineAnalyseVo();
	
	public CombineAnalyseVo getModel() {
		return combineAnalyseVo;
	}
	/**
	 * 接待台次分析信息
	 * */
	public void findReceptionCountAnalyse(){
		try {
			combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
			if(combineAnalyseVo.getFlag()!=null&&combineAnalyseVo.getFlag()==true){
				String ss=combineAnalyseService.receptionCountAnalyse(combineAnalyseVo);
				super.writeJson(JSON.parseObject(ss));
			}else{
				String string=combineAnalyseService.receptionCountAnalyse(combineAnalyseVo);
				super.writeJson(string);				
			}
		} catch (Exception e) {
			logger.error("接待台次分析信息失败！", e);
		}
	}
	/**
	 *接待台次分析折线图信息
	 * @throws Exception 
	 * */
	public void findReceptionCountAnalyseSnapMap() throws Exception{
		try {
			combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),combineAnalyseService.findReceptionCountAnalyseSnapMap(combineAnalyseVo), 1400, 360);			
		} catch (Exception e) {
			logger.error("获取接待台次分析折线图信息失败！", e);
		}
	}
	/**
	 * 结算台次分析信息
	 * */
	public void findBalanceCountAnalyse(){
		try {
			combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
			if(combineAnalyseVo.getFlag()!=null&&combineAnalyseVo.getFlag()==true){
				String ss=combineAnalyseService.balanceCountAnalyse(combineAnalyseVo);
				super.writeJson(JSON.parseObject(ss));
			}else{
				String string=combineAnalyseService.balanceCountAnalyse(combineAnalyseVo);
				super.writeJson(string);				
			}
		} catch (Exception e) {
			logger.error("结算台次分析信息失败！", e);
		}
	}
	/**
	 *结算台次分析折线图信息
	 * @throws Exception 
	 * */
	public void findBalanceCountAnalyseSnapMap() throws Exception{
		try {
			combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),combineAnalyseService.findBalanceCountAnalyseSnapMap(combineAnalyseVo), 1400, 360);			
		} catch (Exception e) {
			logger.error("获取结算台次分析折线图信息失败！", e);
		}
	}
	/**
	 * 结算费用分析信息
	 * */
	public void findBalanceRateAnalyse(){
		try {
			combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
			super.writeJson(combineAnalyseService.findBalanceRateAnalyse(combineAnalyseVo));				
		} catch (Exception e) {
			logger.error("结算费用分析信息失败！", e);
		}
	}
	/**
     * 获取结算费用分析柱状图信息
     * */
    public void findBalanceRateAnalysePoleMap(){
    	try {
    		combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),combineAnalyseService.findBalanceRateAnalysePoleMap(combineAnalyseVo), 1400, 360);
		} catch (Exception e) {
			logger.error("获取结算费用分析柱状图信息失败！", e);
		}
    }
    
    /**
	 * 应收账款分析信息
	 * */
	public void findAccountReceivableAnalyse(){
		try {
			combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
			if(combineAnalyseVo.getFlag()!=null&&combineAnalyseVo.getFlag()==true){
				String ss=combineAnalyseService.findAccountReceivableAnalyse(combineAnalyseVo);
				super.writeJson(JSON.parseObject(ss));
			}else{
				String string=combineAnalyseService.findAccountReceivableAnalyse(combineAnalyseVo);
				super.writeJson(string);				
			}
		} catch (Exception e) {
			logger.error("应收账款分析信息失败！", e);
			e.printStackTrace();
		}
	}
	/**
	 * 应收账款分析折线图信息
	 * @throws Exception 
	 * */
	public void findAccountReceivableAnalyseSnapMap() throws Exception{
		try {
			combineAnalyseVo.setParentEnterpriseId(getNowEnterpriseId().toString());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),combineAnalyseService.findAccountReceivableAnalyseSnapMap(combineAnalyseVo), 1400, 360);			
		} catch (Exception e) {
			logger.error("获取应收账款分析折线图信息失败！", e);
		}
	}
}

