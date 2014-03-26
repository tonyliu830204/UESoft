package com.syuesoft.fin.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.FinMaintenanceReceivablesService;
import com.syuesoft.fin.vo.MaintenanceReceivablesVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 维修应收款,索赔应收款,索赔应收款Action
 * 
 * @author SuMing
 */

public class ReceivablesAction extends BaseAction implements
        ModelDriven<MaintenanceReceivablesVo>
{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    BaseService baseService;

    FinMaintenanceReceivablesService logic;

    MaintenanceReceivablesVo maintenanceReceivablesVo = new MaintenanceReceivablesVo();

    /**
     * 维修应收款信息 预加载
     */
    public void loadFinMaintenanceReceivables()
    {
        try
        {        
        	maintenanceReceivablesVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(logic.loadFinMaintenanceReceivables(maintenanceReceivablesVo));
        }
        catch(Exception e)
        {
            logger.error("维修应收款信息   预加载失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }

    }

    /**
     * 维修应收款信息 综合查询
     */
    public void searchFinMaintenanceReceivablesByCondition()
    {
        try
        {
            List<MaintenanceReceivablesVo> resultList = logic
                    .searchFinMaintenanceReceivablesByCondition(maintenanceReceivablesVo);
            if (resultList != null)
            {
                Json json = new Json();
                json.setRows(resultList);
                json.setTotal(resultList.size());
                super.writeJson(json);
            }
        }
        catch(Exception e)
        {
            logger.error("维修应收款信息  综合查询失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 索赔应收款信息 预加载
     */
    public void loadFinClaimsReceivables()
    {
        try
        {      
        	maintenanceReceivablesVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(logic.loadFinClaimsReceivables(maintenanceReceivablesVo));
        }
        catch(Exception e)
        {
            logger.error("索赔应收款信息  预加载 失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 索赔应收款信息 综合查询
     */
    public void searchFinClaimsReceivablesByCondition()
    {
        try
        {
            List<MaintenanceReceivablesVo> resultList = logic
                    .searchFinClaimsReceivablesByCondition(maintenanceReceivablesVo);
            if (resultList != null)
            {
                Json json = new Json();
                json.setRows(resultList);
                json.setTotal(resultList.size());
                super.writeJson(json);
            }
        }
        catch(Exception e)
        {
            logger.error("索赔应收款信息  综合查询 失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售应收款信息 预加载
     */
    public void loadStSellCharge()
    {
        try
        {        
        	maintenanceReceivablesVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(logic.loadStSellCharge(maintenanceReceivablesVo));
        }
        catch(Exception e)
        {
            logger.error("销售应收款信息  预加载失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售应收款信息 综合查询
     */
    public void searchStSellChargeByCondition()
    {
        try
        {
            List<MaintenanceReceivablesVo> resultList = logic
                    .searchStSellChargeByCondition(maintenanceReceivablesVo);
            Json json = new Json();
            json.setRows(resultList);
            json.setTotal(resultList.size());
            super.writeJson(json);
        }
        catch(Exception e)
        {
            logger.error("销售应收款信息  综合查询失败", e);
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 结算状态
     */
    public void loadReceivablesStyle()
    {
        try
        {
            super.writeJson(baseService.loadDataByChildId("13"));
        }
        catch(Exception e)
        {
            logger.error("结算状态失败", e);
        }
    }

    /**
     * 车辆品牌
     */
    public void loadCarBrand()
    {
        try
        {
            super.writeJson(baseService
                    .findAllPartsBrand(maintenanceReceivablesVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("车辆品牌失败", e);
        }
    }

    /**
     * 接车分部
     */
    public void loadReceptParts()
    {
        try
        {
            super.writeJson(baseService.loadDataByChildId("42"));
        }
        catch(Exception e)
        {
            logger.error("接车分部失败", e);
        }
    }

    /**
     * 应收账龄
     */
    public void loadReceivablesBillAge()
    {
        try
        {
            super.writeJson(baseService.loadDataByChildId("43"));
        }
        catch(Exception e)
        {
            logger.error("应收账龄失败", e);
        }
    }

    /**
     * 发票号码
     */
    public void BillNum()
    {
        try
        {
            super.writeJson(baseService.loadDataByChildId("44"));
        }
        catch(Exception e)
        {
            logger.error("发票号码失败", e);
        }
    }

    /**
     * 索赔厂商
     */
    public void loadRelcamp()
    {
        try
        {
            super.writeJson(baseService.findAllClaimManufacturers());
        }
        catch(Exception e)
        {
            logger.error("索赔厂商失败", e);
        }
    }

    /**
     * 付款方式
     */
    public void loadPaidStyle()
    {
        try
        {
            super.writeJson(baseService.loadDataByChildId("37"));
        }
        catch(Exception e)
        {
            logger.error("付款方式失败", e);
        }
    }

    
    public MaintenanceReceivablesVo getModel()
    {
        return maintenanceReceivablesVo;
    }

    public FinMaintenanceReceivablesService getLogic()
    {
        return logic;
    }

    public void setLogic(FinMaintenanceReceivablesService logic)
    {
        this.logic = logic;
    }

}
