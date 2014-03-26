package com.syuesoft.fin.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jfree.chart.ChartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fin.service.TimeManageService;
import com.syuesoft.fin.vo.TimeManageVo;

/**
 * 日期经营查询Action
 */
@ParentPackage(value = "basePackage")
@Action("timeManageAction")
public class TimeManageAction extends BaseAction implements
        ModelDriven<TimeManageVo>
{
	Logger logger = Logger.getLogger(this.getClass());
	TimeManageVo timeManageVo=new TimeManageVo();
    @Autowired
    private TimeManageService timeManageService;
    /**
     * 日营业情况查询
     * */
    public void findDayBusinessThing(){
    	try {
    		timeManageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(timeManageService.findDayBusinessThing(timeManageVo,false));
		} catch (Exception e) {
			logger.error("日营业情况查询失败！", e);
		}
    }
    /**
     * 获取日营业情况折线图信息
     * */
    public void findDayBusinessThingSnapMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findDayBusinessThingSnapMap(timeManageVo), 730, 360);
		} catch (Exception e) {
			logger.error("获取日营业情况折线图信息失败！", e);
		}
    }
    /**
     * 获取日营业情况柱状图信息
     * */
    public void findDayBusinessThingPoleMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findDayBusinessThingPoleMap(timeManageVo), 730, 360);
		} catch (Exception e) {
			logger.error("获取日营业情况柱状图信息失败！", e);
		}
    }
    
    /**
     * 查询日经营情况
     * */
    public void findDayManage(){
    	try {
    		timeManageVo.setEnterpriseId(getNowEnterpriseId());
    		if(timeManageVo.getFlag()!=null&&timeManageVo.getFlag()==true){
				String ss=timeManageService.findDayManage(timeManageVo);
				super.writeJson(JSON.parseObject(ss));
			}else{				
				super.writeJson(timeManageService.findDayManage(timeManageVo));
			}
		} catch (Exception e) {
			logger.error("查询日经营情况失败！", e);
		}
    }
    /**
     * 获取日经营情况折线图信息
     * */
    public void findDayManageSnapMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findDayManageSnapMap(timeManageVo), 1100, 360);
		} catch (Exception e) {
			logger.error("获取日经营情况折线图信息失败！", e);
		}
    }
    /**
     * 获取日经营情况饼图信息
     * */
    public void findDayManageCakeMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findDayManageCakeMap(timeManageVo), 380, 360);
		} catch (Exception e) {
			logger.error("获取日经营情况饼图信息失败！", e);
		}
    }
    
    /**
     * 查询月经营情况
     * */
    public void findMonthManage(){
    	try {
    		timeManageVo.setEnterpriseId(getNowEnterpriseId());
    		if(timeManageVo.getFlag()!=null&&timeManageVo.getFlag()==true){
				String ss=timeManageService.findMonthManage(timeManageVo);
				super.writeJson(JSON.parseObject(ss));
			}else{				
				super.writeJson(timeManageService.findMonthManage(timeManageVo));
			}
		} catch (Exception e) {
			logger.error("查询月经营情况失败！", e);
		}
    }
    /**
     * 获取月经营情况折线图信息
     * */
    public void findMonthManageSnapMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findMonthManageSnapMap(timeManageVo), 1100, 360);
		} catch (Exception e) {
			logger.error("获取月经营情况折线图信息失败！", e);
		}
    }
    /**
     * 获取月经营情况饼图信息
     * */
    public void findMonthManageCakeMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findMonthManageCakeMap(timeManageVo), 380, 360);
		} catch (Exception e) {
			logger.error("获取月经营情况饼图信息失败！", e);
		}
    }
    /**
     * 查询年经营情况
     * */
    public void findYearManage(){
    	try {
    		timeManageVo.setEnterpriseId(getNowEnterpriseId());
    		if(timeManageVo.getFlag()!=null&&timeManageVo.getFlag()==true){
				String ss=timeManageService.findYearManage(timeManageVo);
				super.writeJson(JSON.parseObject(ss));
			}else{				
				super.writeJson(timeManageService.findYearManage(timeManageVo));
			}
		} catch (Exception e) {
			logger.error("查询年经营情况失败！", e);
		}
    }
    /**
     * 获取年经营情况折线图信息
     * */
    public void findYearManageSnapMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findYearManageSnapMap(timeManageVo), 1100, 360);
		} catch (Exception e) {
			logger.error("获取年经营情况折线图信息失败！", e);
		}
    }
    /**
     * 获取年经营情况饼图信息
     * */
    public void findYearManageCakeMap(){
    	try {
    		this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),timeManageService.findYearManageCakeMap(timeManageVo), 380, 360);
		} catch (Exception e) {
			logger.error("获取年经营情况饼图信息失败！", e);
		}
    }
    
    
    public TimeManageVo getModel()
    {
        return timeManageVo;
    }
}
