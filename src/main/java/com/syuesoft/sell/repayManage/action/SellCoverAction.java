package com.syuesoft.sell.repayManage.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.repayManage.service.SellCoverService;
import com.syuesoft.sell.repayManage.vo.SellCoverVo;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jfree.chart.ChartUtilities;

@SuppressWarnings("serial")
@ParentPackage(value="basePackage")
@Action("sellCoverAction")
public class SellCoverAction extends BaseAction implements
		ModelDriven<SellCoverVo> {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SellCoverService sellCoverService;
	private SellCoverVo sellCoverVo = new SellCoverVo();
	 HttpSession session = ServletActionContext.getRequest().getSession();

	
	public SellCoverVo getModel() {
		return sellCoverVo;
	}

	public void getSellList(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getSellList(sellCoverVo));
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.debug("查询销售信息失败！");
		}
		
	}
	public void getSellCover(){
		try {
			sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getSellCover(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询售后回访信息失败！");
		}
			
	}
	
	//修改
	public void modifySellCover(){
		Msg msg = new Msg();
		try {
			sellCoverService.modifySellCover(sellCoverVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("操作失败！");
		}
		super.writeJson(msg);
	
	}
	public void getInfo(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getInfo(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询回访进度字段失败！");
		}
		}
	public void getResearch(){
		try {
			super.writeJson(sellCoverService.getResearch(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询调查表失败！");
		}
	}
	//保存调查表信息
	public void savaResearchInfo(){
		Msg msg = new Msg();
		try {
			sellCoverService.addResearchList(sellCoverVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("保存调查表信息失败！");
		}
		super.writeJson(msg);
	
	}
	public void getInSellList(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getInSellList(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询销售信息失败！");
		}
		
	}
	
	/**
	 * 售后回访分析模块：查询回访记录
	 */
	public void getSellCoverMange(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getSellCoverMange(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询售后回访信息失败！");
		}
		
	}
	/**
	 * 获取客户度统计动态时间列
	 */
	public void getDateColumn(){
		try {
			super.writeJson(sellCoverService.getDateColumn(sellCoverVo));
		} catch (Exception e) {
			logger.debug("获取动态时间列失败！");
		}
		
	}
	
	/**
	 * 获取售后回访分析折线图
	 */
	public void getMapbyTime(){
        try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			this.getResponse().setContentType("image/jpeg; charset=UTF-8");
    		ChartUtilities.writeChartAsJPEG(this.getResponse().getOutputStream(),sellCoverService.getMapbyTime(sellCoverVo), 730, 360);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 /*** 
	  * 获取跟踪满意度统计数据
	  * */
    public void getDateInfomation()
    {
        try
        {
            session.setAttribute("consultActualDate", sellCoverVo
                    .getConsultActualDate());
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
            List list = sellCoverService.getDateInfomation(sellCoverVo);
            super.writeJson(list);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
//查询跟踪项目统计
	public void getProjectStatistics(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getProjectStatistics(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询跟踪项目统计信息失败！");
		}
	}
	/**查询回访及时性分析信息*/
	public void geTtimelyAnalysis(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.geTtimelyAnalysis(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询回访及时性分析信息失败！");
		}
	}

	/**
	 * 获取datagrid拼接字符串返回
	 */
	public void getDatagridString(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getDatagridString(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询跟踪记录汇总信息失败！");
		}
	}
	/**
	 * 获取datagrid拼接字符串返回
	 */
	public void getDatagridValue(){
		try {
				sellCoverVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(sellCoverService.getDatagridValue(sellCoverVo));
		} catch (Exception e) {
			logger.debug("查询跟踪记录汇总信息失败！");
		}
	}
}
