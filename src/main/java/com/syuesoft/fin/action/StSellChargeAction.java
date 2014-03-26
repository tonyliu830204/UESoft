package com.syuesoft.fin.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.StSellChargeService;
import com.syuesoft.fin.vo.StSellChargeVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 销售应收款Action
 * 
 * @author SuMing
 */

public class StSellChargeAction extends BaseAction implements
        ModelDriven<StSellChargeVo>
{

    StSellChargeVo stSellChargeVo = new StSellChargeVo();

    StSellChargeService logic;

    @Autowired
    BaseService baseService;

    /**
     * 销售结算单信息 预加载
     */
    public void loadStSellPreclr()
    {
        try
        {
            List<StSellChargeVo> resultList = logic.loadStSellPreclr();
            Json json = new Json();
            json.setRows(resultList);
            json.setTotal(resultList.size());
            super.writeJson(json);
        }
        catch(Exception e)
        {
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 是否出库 信息加载
     */
    public void loadIsNoStOut()
    {
        try
        {
            super.writeJson(baseService.loadDataByChildId("22"));
        }
        catch(Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 审核状态 信息加载
     */
    public void loadExamine()
    {
        try
        {
            super.writeJson(baseService.loadDataByChildId("41"));
        }
        catch(Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 收款人信息 预加载
     */
    public void loadBasStuff()
    {
        try
        {
            super.writeJson(baseService.findAllStuff(stSellChargeVo.getQ(),getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 销售结算单明细信息 添加
     */
    public void addStSellChargeItem()
    {
        Msg msg = new Msg();
        try
        {
            logic.addStSellChargeItem(stSellChargeVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售应收款明细 预加载
     */
    public void loadStSellchargeItemByChargeId()
    {
        try
        {
        	stSellChargeVo.setEnterpriseId(getNowEnterpriseId());
            List<StSellChargeVo> resultList = logic
                    .loadStSellchargeItemByChargeId(stSellChargeVo);
            Json json = new Json();
            json.setRows(resultList);
            json.setTotal(resultList.size());
            super.writeJson(json);
        }
        catch(Exception e)
        {
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 修改审核状态为审核
     */
    public void examine()
    {
        Msg msg = new Msg();
        try
        {
            logic.examine(stSellChargeVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 修改审核状态为未审核
     */
    public void cancelExamine()
    {
        Msg msg = new Msg();
        try
        {
            logic.cancelExamine(stSellChargeVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 取消付款
     */
    public void deletePaid()
    {
        Msg msg = new Msg();
        try
        {
            logic.deletePaid(stSellChargeVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 删除销售应收款信息
     */
    public void deleteStSellCharge()
    {
        Msg msg = new Msg();
        try
        {
            logic.deleteStSellCharge(stSellChargeVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售营收款汇总信息 预加载
     */
    public void loadStSellCharge()
    {
        try
        {
            List<StSellChargeVo> resultList = logic
                    .loadStSellCharge(stSellChargeVo);
            Json json = new Json();
            json.setRows(resultList);
            if (super.getSession().getAttribute("ssc_StSellCharge_resultSize") != null)
                json.setTotal(Integer.parseInt(super.getSession().getAttribute(
                        "ssc_StSellCharge_resultSize")
                        + ""));
            else
                json.setTotal(0);
            super.writeJson(json);
        }
        catch(Exception e)
        {
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 销售营收款汇总信息 预加载
     */
    public void searchStSellChargeByCondition()
    {
        try
        {
            List<StSellChargeVo> resultList = logic
                    .searchStSellChargeByCondition(stSellChargeVo);
            Json json = new Json();
            json.setRows(resultList);
            json.setTotal(resultList.size());
            super.writeJson(json);
        }
        catch(Exception e)
        {
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 获取销售应收款汇总已收款金额合计
     */
    public void searchStSellChargeByChargerId()
    {
        try
        {
            StSellChargeVo stSellCharge = logic
                    .searchStSellChargeByChargerId(stSellChargeVo);
            super.writeJson(stSellCharge);
        }
        catch(Exception e)
        {
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    
    public StSellChargeVo getModel()
    {
        return stSellChargeVo;
    }

    public StSellChargeService getLogic()
    {
        return logic;
    }

    public void setLogic(StSellChargeService logic)
    {
        this.logic = logic;
    }

}
