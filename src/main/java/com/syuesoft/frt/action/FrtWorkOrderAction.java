package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FrtWorkOrderService;
import com.syuesoft.frt.vo.FrtWorkOrderVo;

@ParentPackage(value = "basePackage")
@Action("frtWorkOrderAction")
public class FrtWorkOrderAction extends BaseAction implements
        ModelDriven<FrtWorkOrderVo>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private final Logger logger = Logger.getLogger(this.getClass());

    FrtWorkOrderVo fwoVo = new FrtWorkOrderVo();

    private FrtWorkOrderService frtWorkOrderService;

    public FrtWorkOrderService getFrtWorkOrderService()
    {
        return frtWorkOrderService;
    }

    @Autowired
    public void setFrtWorkOrderService(FrtWorkOrderService frtWorkOrderService)
    {
        this.frtWorkOrderService = frtWorkOrderService;
    }

    /**
     * 工单-基本信息
     * */
    public void datagridFrtWorkOrderBase()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super .writeJson(frtWorkOrderService.datagridFrtWorkOrderBase(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-基本信息查询失败", e);
        }
    }

    /**
     * 工单-基本信息子项
     * */
    public void datagridFrtWorkOrderBaseByReceptionId()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderBaseByReceptionId(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-维修项目子项查询失败", e);
        }
    }

    /**
     * 工单-维修项目
     * */
    public void datagridFrtWorkOrderItem()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService.datagridFrtWorkOrderItem(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-维修项目查询失败", e);
        }
    }

    /**
     * 工单-维修项目子项
     * */
    public void datagridFrtWorkOrderItemByReceptionId()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderItemByReceptionId(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-维修项目子项查询失败", e);
        }
    }

    /**
     * 工单-维修配件
     * */
    public void datagridFrtWorkOrderParts()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderParts(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-维修配件查询失败", e);
        }

    }

    /**
     * 工单-维修配件子项
     * */
    public void datagridFrtWorkOrderPartsByReceptionId()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderPartsByReceptionId(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-维修配件子项查询失败", e);
        }

    }

    /**
     * 结算单-工时清单
     * */
    public void datagridFrtWorkOrderPreClearingItem()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderPreClearingItem(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("结算单-工时清单查询失败", e);
        }
    }

    /**
     * 结算单-工时清单-子项
     * */
    public void datagridFrtWorkOrderPreClearingItemByPreclrId()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderPreClearingItemByPreclrId(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("结算单-工时清单查询失败", e);
        }
    }

    /**
     * 结算单-材料清单
     * */
    public void datagridFrtWorkOrderPreClearingParts()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderPreClearingParts(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("结算单-材料清单查询失败", e);
        }
    }

    /**
     * 结算单-材料清单-子项
     * */
    public void datagridFrtWorkOrderPreClearingPartsByPreclrId()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderPreClearingPartsByPreclrId(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("结算单-材料清单查询失败", e);
        }
    }

    /**
     * 索赔-配件查询
     * */
    public void datagridFrtWorkOrderClaimParts()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderClaimParts(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("索赔-配件查询查询失败", e);
        }
    }

    /**
     * 维修业务统计报表
     * */
    public void datagridFrtWorkOrderRepaiStatisticalStatement()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderRepaiStatisticalStatement(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("维修业务统计报表查询失败", e);
        }
    }

    /**
     * 索赔出库查询
     * */
    public void datagridFrtWorkOrderclaimExwarehouse()
    {
        try
        {
        	fwoVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderclaimExwarehouse(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("索赔出库查询失败", e);
        }
    }

    /**
     * 索赔出库-子项查询
     * */
    public void datagridFrtWorkOrderclaimExwarehouseByStomId()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderclaimExwarehouseByStomId(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("索赔出库-子项查询失败", e);
        }
    }

    /**
     * 工单-结算情况-工时清单
     * */
    public void datagridFrtWorkOrderSettlementSituationItem()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderSettlementSituationItem(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-结算情况-工时清单查询失败", e);
        }
    }

    /**
     * 工单-结算情况-材料清单
     * */
    public void datagridFrtWorkOrderSettlementSituationParts()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderSettlementSituationParts(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-结算情况-材料清单查询失败", e);
        }
    }

    /**
     * 工单-结算情况-费用
     * */
    public void datagridFrtWorkOrderSettlementSituationBalance()
    {
        try
        {
            super.writeJson(frtWorkOrderService
                    .datagridFrtWorkOrderSettlementSituationBalance(fwoVo));
        }
        catch(Exception e)
        {
            logger.error("工单-结算情况-费用查询失败", e);
        }
    }

    
    public FrtWorkOrderVo getModel()
    {
        return fwoVo;
    }

}
