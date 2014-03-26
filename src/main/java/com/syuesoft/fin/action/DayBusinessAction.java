package com.syuesoft.fin.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fin.service.DayBusinessService;
import com.syuesoft.fin.vo.DayBusinessVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 日经营情况查询
 */

import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value = "basePackage")
@Action("dayBusinessAction")
public class DayBusinessAction extends BaseAction implements
        ModelDriven<DayBusinessVo>
{
	Logger logger =Logger.getLogger(this.getClass());
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    DayBusinessVo dayBusinessVo = new DayBusinessVo();

    @Autowired
    DayBusinessService dayBusinessService;

    /**
     * 财务模块 日收款查询 父节点
     */
    public void loadPreclrDate()
    {
        try
        {
        	dayBusinessVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(dayBusinessService.loadPreclrDate(dayBusinessVo));
        }
        catch(Exception e)
        {
        	logger.error("日收款查询（父节点）失败！", e);
        }
    }

    /**
     * 财务模块 日收款查询 二级节点
     */
    public void loadPaidResource()
    {
        try
        {
        	dayBusinessVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(dayBusinessService.loadPaidResource(dayBusinessVo));
        }
        catch(Exception e)
        {
        	logger.error("日收款查询（二级节点）失败！", e);
        }
    }

    /**
     * 财务模块 日收款查询 三级节点信息加载
     */
    public void loadDayPaid()
    {
        try
        {
        	dayBusinessVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(dayBusinessService.loadDayPaid(dayBusinessVo));
        }
        catch(Exception e)
        {
        	logger.error("日收款查询（三级节点）失败！", e);
        }
    }

    
    public DayBusinessVo getModel()
    {
        return dayBusinessVo;
    }

}
