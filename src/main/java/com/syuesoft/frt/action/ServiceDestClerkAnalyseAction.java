package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jfree.chart.ChartUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.ServiceDestClerkAnalyseService;
import com.syuesoft.frt.vo.FrtQueryVo;

@ParentPackage(value = "basePackage")
@Action("serviceDestClerkAnalyseAction")
public class ServiceDestClerkAnalyseAction extends BaseAction implements
        ModelDriven<FrtQueryVo>
{
    private static final Logger logger = Logger
            .getLogger(BespeakAnalyseAction.class);

    FrtQueryVo fqVo=new FrtQueryVo();
    @Autowired
    private ServiceDestClerkAnalyseService serviceDestClerkAnalyseService;
    /**
     * 查找维修接待员分析数据
     * */
    public void findServiceDestClerkAnalyse(){
    	try {
    		fqVo.setEnterpriseId(getNowEnterpriseId());
    		if(fqVo.getFlag()!=null&&fqVo.getFlag()==true){
				String ss=serviceDestClerkAnalyseService.findServiceDestClerkAnalyse(fqVo);
				super.writeJson(JSON.parseObject(ss));
			}else{
				super.writeJson(serviceDestClerkAnalyseService.findServiceDestClerkAnalyse(fqVo));
			}
		} catch (Exception e) {
			logger.error("查找维修接待员分析数据失败！", e);
		}
    }
	/**
	 * 查找维修接待员分析折线图
	 * */
	public void findSnapMap(){
		try {
			fqVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),serviceDestClerkAnalyseService.findSnapMap(fqVo), 1100, 360);
		} catch (Exception e) {
			logger.error("查找维修接待员分析折线图失败！", e);
		}
	}
	/**
	 * 查找维修接待员分析饼图
	 * */
	public void findCakeMap(){
		try {
			fqVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),serviceDestClerkAnalyseService.findCakeMap(fqVo), 380, 360);
		} catch (Exception e) {
			logger.error("查找维修接待员分析饼图失败！", e);
		}
	}
    
	 /**
     * 查找维修类别分析数据
     * */
    public void findServiceClassAnalyse(){
    	try {
    		fqVo.setEnterpriseId(getNowEnterpriseId());
    		if(fqVo.getFlag()!=null&&fqVo.getFlag()==true){
				String ss=serviceDestClerkAnalyseService.findServiceClassAnalyse(fqVo);
				super.writeJson(JSON.parseObject(ss));
			}else{				
				super.writeJson(serviceDestClerkAnalyseService.findServiceClassAnalyse(fqVo));
			}
		} catch (Exception e) {
			logger.error("查找维修类别分析数据失败！", e);
		}
    }
	/**
	 * 查找维修类别分析折线图
	 * */
	public void findClassSnapMap(){
		try {
			fqVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),serviceDestClerkAnalyseService.findClassSnapMap(fqVo), 1100, 360);
		} catch (Exception e) {
			logger.error("查找维修类别分析折线图失败！", e);
		}
	}
	/**
	 * 查找维修类别分析饼图
	 * */
	public void findClassCakeMap(){
		try {
			fqVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),serviceDestClerkAnalyseService.findClassCakeMap(fqVo), 380, 360);
		} catch (Exception e) {
			logger.error("查找维修类别分析饼图失败！", e);
		}
	}
	 /**
     * 查找保险送修人分析汇总数据
     * */
    public void findInsurePersonAnalyseMain(){
    	try {
    		fqVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(serviceDestClerkAnalyseService.findInsurePersonAnalyseMain(fqVo));
		} catch (Exception e) {
			logger.error("查找保险送修人分析汇总数据失败！", e);
		}
    }
    /**
     * 查找保险送修人分析明细数据
     * */
    public void findInsurePersonAnalyseDetail(){
    	try {
			super.writeJson(serviceDestClerkAnalyseService.findInsurePersonAnalyseDetail(fqVo));
		} catch (Exception e) {
			logger.error("查找保险送修人分析明细数据失败！", e);
		}
    }
	
	
	public FrtQueryVo getModel() {
		// TODO Auto-generated method stub
		return fqVo;
	}
    
}
