package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jfree.chart.ChartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.frt.service.BespeakAnalyseService;
import com.syuesoft.frt.vo.FrtQueryVo;

@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("bespeakAnalyseAction")
public class BespeakAnalyseAction extends BaseAction implements
        ModelDriven<FrtQueryVo>
{
    private static final Logger logger = Logger
            .getLogger(BespeakAnalyseAction.class);

    FrtQueryVo fqVo=new FrtQueryVo();
    @Autowired
    private BespeakAnalyseService bespeakAnalyseService;
    
    /**
     * 查找预约分析数据
     * */
    public void findBespeakAnalyse(){
    	try {
    		fqVo.setEnterpriseId(getNowEnterpriseId());
    		super.writeJson(bespeakAnalyseService.findBespeakAnalyse(fqVo));
		} catch (Exception e) {
			logger.error("查找预约分析数据失败！", e);
		}
    }
    
	/**
	 * 查找预约分析折线图
	 * */
	public void findSnapMap(){
		try {
			fqVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),bespeakAnalyseService.findSnapMap(fqVo), 1100, 360);
		} catch (Exception e) {
			logger.error("查找预约分析折线图失败！", e);
		}
	}
	/**
	 * 查找预约分析饼图
	 * */
	public void findCakeMap(){
		try {
			fqVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
			ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),bespeakAnalyseService.findCakeMap(fqVo), 380, 360);
		} catch (Exception e) {
			logger.error("查找预约分析饼图失败！", e);
		}
	}
    
    
	
	public FrtQueryVo getModel() {
		// TODO Auto-generated method stub
		return fqVo;
	}
    
}
