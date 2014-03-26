package com.syuesoft.fin.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fin.service.WorkOrderPartsQueryService;
import com.syuesoft.fin.vo.WorkOrderPartsQueryVo;

/**
 * 工单配件查询Action
 * 
 * @author SuMing
 */
@ParentPackage(value = "basePackage")
@Action("workOrderPartsQueryAction")
public class WorkOrderPartsQueryAction extends BaseAction implements
        ModelDriven<WorkOrderPartsQueryVo>
{
	Logger logger =Logger.getLogger(this.getClass());

    WorkOrderPartsQueryVo workOrderPartsQueryVo = new WorkOrderPartsQueryVo();
    @Autowired
    WorkOrderPartsQueryService workOrderPartsQueryService;

    /**
     * 工单结算信息 (按工单)
     */
    public void loadFrtPreClearing()
    {
        try {
        	workOrderPartsQueryVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(workOrderPartsQueryService.loadFrtPreClearing(workOrderPartsQueryVo));
		} catch (Exception e) {
			logger.error("查找工单结算信息失败！", e);
		}
    }
    /**
     * 工单结算信息-子项信息(按工单)
     */
    public void loadStOutAndStRtPartsDetail()
    {
        try
        {
            super.writeJson(workOrderPartsQueryService.loadStOutAndStRtPartsDetail(workOrderPartsQueryVo));
        }
        catch(Exception e)
        {
        	logger.error("查找工单结算信息-子项信息失败！", e);
        }
    }
    /**
     * 工单结算信息 (按配件)
     */
    public void loadFrtPreClearingForParts()
    {
        try {
			super.writeJson(workOrderPartsQueryService.loadFrtPreClearingForParts(workOrderPartsQueryVo));
		} catch (Exception e) {
			logger.error("查找工单结算信息失败！", e);
		}
    }
    /**
     * 工单结算信息-子项信息(按配件)
     */
    public void loadStOutAndStRtPartsDetailForParts()
    {
        try
        {
            List<WorkOrderPartsQueryVo> resultList = workOrderPartsQueryService
                    .loadStOutAndStRtPartsDetailForParts(workOrderPartsQueryVo);
            super.writeJson(resultList);
        }
        catch(Exception e)
        {
        	logger.error("查找工单结算信息-子项信息失败！", e);
        }
    }
  

    /**
     * 未确认索赔配件信息 预加载
     */
    public void loadFinClaimantParts()
    {
        try {
			super.writeJson(workOrderPartsQueryService.loadFinClaimantParts(workOrderPartsQueryVo));
		} catch (Exception e) {
			logger.error("查找未确认索赔配件信息失败！", e);
		}
    }

    
    public WorkOrderPartsQueryVo getModel()
    {
        return workOrderPartsQueryVo;
    }

}
