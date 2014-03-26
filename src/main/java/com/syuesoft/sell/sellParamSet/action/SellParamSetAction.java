package com.syuesoft.sell.sellParamSet.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.sellParamSet.service.SellParamSetService;
import com.syuesoft.sell.sellParamSet.vo.SellParamSetVo;
import com.syuesoft.util.Msg;
@Action("sellParamSetAction")
public class SellParamSetAction extends BaseAction implements ModelDriven<SellParamSetVo>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SellParamSetService sellParamSetService;
	private SellParamSetVo params=new SellParamSetVo();

	
	public SellParamSetVo getModel() {
		// TODO Auto-generated method stub
		return params;
	}
	/**
     * 查询参数一
     */
    public void searchParameterOne()
    {
        try
        {
        	params.setEnterprise_id(getNowEnterpriseId());


            super.writeJson( sellParamSetService.searchParameterOne(params));
        }
        catch(Exception e)
        {
            logger.error("带条件查询数据的方法失败", e);
        }
    }
	/**
     * 查询参数一
     */
    public void searchParameter()
    {
        try
        {
        		params.setEnterprise_id(getNowEnterpriseId());
            super.writeJson( sellParamSetService.searchParameter(params));
        }
        catch(Exception e)
        {
            logger.error("带条件查询数据的方法失败", e);
        }
    }
    /**
     * 保存新增参数
     */
	public void saveParam(){
		Msg msg = new Msg();
		try {
			params.setEnterprise_id(getUserEnterpriseId());
			sellParamSetService.saveOrUpdate(params);
			msg.setSuccess(true);
			msg.setMsg("配置参数信息保存成功！");
		} catch (Exception e) {
			logger.debug("保存参数信息失败！");
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
     * 更新参数
     */
	public void updateParam(){
		Msg msg = new Msg();
		try {
			sellParamSetService.updateParam(params);
			msg.setSuccess(true);
			msg.setMsg("更新参数信息保存成功！");
		} catch (Exception e) {
			logger.debug("更新参数信息失败！");
			e.printStackTrace();
		}
		super.writeJson(msg);
	}

}
