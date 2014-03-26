package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.st.service.MonthlyStatementService;
import com.syuesoft.st.vo.MonthlyStatementVo;
import com.syuesoft.util.Msg;

@SuppressWarnings("serial")
@Action("MonthlyStatementAction")
public class MonthlyStatementAction extends BaseAction implements
ModelDriven<MonthlyStatementVo>{
    private Logger logger = Logger.getLogger(this.getClass());
	MonthlyStatementVo monthlyStatementVo=new MonthlyStatementVo();
	@Autowired 
	MonthlyStatementService monthlyStatementService;

	/**
     * 加载月结汇总数据
     */
    public void findAllMonthlyStatemont(){
        try {
        	monthlyStatementVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(monthlyStatementService.findAllMonthlyStatemont(monthlyStatementVo));
        } catch (Exception e) {
            logger.error("加载月结数据   异常", e);
        } 
    }
    
    /**
     * 获取月结表中本次月结开始时间
     */
    public void loadStratTime(){
        try {
        	monthlyStatementVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(monthlyStatementService.loadStratTime(monthlyStatementVo));
        } catch (Exception e) {
            logger.error("获取月结表中本次月结开始时间   异常", e);
        }
    }
	
	/**
	 * 添加月结
	 */
	public void add(){
	    Msg msg=new Msg();
		try {
			monthlyStatementVo.setEnterpriseId(getNowEnterpriseId());
			String id = monthlyStatementService.add(monthlyStatementVo, this.getUsers());
			msg.setSuccess(true);
			if(!"-1".equals(id))
    			msg.setMsg("库存月结转完成。月结转后，请将系统退出后重新进入!");
			else
			    msg.setMsg("库存月结转已经是最新的，不需要月结!");
			msg.setObj(id);
			super.writeJson(msg);
		} catch (Exception e) {
		    logger.error("添加月结   异常", e);
		    msg.setSuccess(false);
            msg.setMsg("添加月结失败");
            super.writeJson(msg);
		}
	}
	
	/**
     * 加载月结汇总数据
     */
    public void findMonthlyDetail(){
        try {
        	monthlyStatementVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(monthlyStatementService.findMonthlyDetail(monthlyStatementVo));
        } catch (Exception e) {
            logger.error("加载月结数据   异常", e);
        } 
    }
    
	/**
	 * 反月结
	 */
	public void delete(){
	    Msg msg=new Msg();
		try {
			monthlyStatementService.delete(monthlyStatementVo);
			msg.setSuccess(true);
            msg.setMsg("反月结成功");
			super.writeJson(msg);
		} catch (Exception e) {
		    logger.error("反月结   异常", e);
		    msg.setSuccess(false);
            msg.setMsg("反月结失败");
            super.writeJson(msg);
		}
	}
	
	
	public MonthlyStatementVo getModel() {
		return monthlyStatementVo;
	}
}