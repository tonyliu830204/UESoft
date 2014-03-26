package com.syuesoft.fin.action;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.service.PartsTrendsChangeSearchService;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchJson;
import com.syuesoft.fin.vo.PartsTrendsChangeSearchVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 配件动态查询Action
 * 
 * @author SuMing
 */

public class PartsTrendsChangeSearchAction extends BaseAction implements
        ModelDriven<PartsTrendsChangeSearchVo>
{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    PartsTrendsChangeSearchService logic = null;
    PartsTrendsChangeSearchVo ptcsvo = new PartsTrendsChangeSearchVo();
    /**
     * 财务模块 配件动态变化 出库退料配件信息 预加载
     */
    public void loadStOutAndRtPartsInfo()
    {
        try
        {
        	ptcsvo.setEnterpriseId(getNowEnterpriseId());
            List<PartsTrendsChangeSearchVo> resultList = logic
                    .loadStOutAndRtPartsInfo(ptcsvo);
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
     * 财务模块 配件动态变化 出库退料配件信息 综合查询
     */
    public void searchStOutAndRtPartsInfoByCondition()
    {
        try
        {
        	ptcsvo.setEnterpriseId(getNowEnterpriseId());
            List<PartsTrendsChangeSearchVo> resultList = logic
                    .searchStOutAndRtPartsInfoByCondition(ptcsvo);
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
     * 财务模块 配件动态变化 出库退料配件信息 汇总查询
     */
    public void caculateStOutAndRtParts()
    {
        double sumItemCount = 0;
        double sumAmount = 0;
        double sumCastAmount = 0;
        try
        {
        	ptcsvo.setEnterpriseId(getNowEnterpriseId());
            List<PartsTrendsChangeSearchVo> resultList = logic
                    .loadStOutAndRtPartsInfo(ptcsvo);
            for (PartsTrendsChangeSearchVo pvo : resultList)
            {
                sumItemCount += Double.parseDouble(pvo.getItemCount());
                sumAmount += Double.parseDouble(pvo.getAmount());
                sumCastAmount += Double.parseDouble(pvo.getCastAmount());
            }
            PartsTrendsChangeSearchJson json = new PartsTrendsChangeSearchJson();
            json.setSumAmount(super.doubleFormat(sumAmount));
            json.setSumCastAmount(super.doubleFormat(sumCastAmount));
            json.setSumItemCount(super.doubleFormat(sumItemCount));
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }
    
    /**
     * 财务模块 配件动态变化 销售退料单信息 预加载
     */
    public void loadSellPartsAndSellRtParts()
    {
        try
        {
            List<PartsTrendsChangeSearchVo> resultList = logic
                    .loadSellPartsAndStRtParts();
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
     * 财务模块 配件动态变化 销售退料单信息 综合查询
     */
    public void searchSellPartsAndSellRtPartsByCondition()
    {
        try
        {
            List<PartsTrendsChangeSearchVo> resultList = logic
                    .searchSellPartsAndStRtPartsByCondition(ptcsvo);
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
     * 财务模块 配件动态变化 入库退货配件信息 预加载
     */
    public void loadStStorageAndStRtGoods()
    {
        try
        {
            List<PartsTrendsChangeSearchVo> resultList = logic
                    .loadStStorageAndStRtGoods();
            Json json = new Json();
            json.setRows(resultList);
            json.setTotal(resultList.size());
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }
    
    /**
     * 财务模块 配件动态变化 入库退货配件信 综合查询
     */
    public void searchStStorageAndStRtGoodsByCondition()
    {
        try
        {
            List<PartsTrendsChangeSearchVo> resultList = logic
                    .searchStStorageAndStRtGoodsByCondition(ptcsvo);
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
    * @Title: loadPartsDynamicDate 
    * @Description: TODO(财务模块 配件动态变化 配件动态查询) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void loadPartsDynamicDate()
    {
        try
        {
        	ptcsvo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(logic.loadPartsDynamicDate(ptcsvo));
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
     * 车辆品牌信息加载
     */
    public void loadCarBrand()
    {
        try
        {
            List<ComboBoxJson> resultList = logic
                    .findAllCarBrand(ptcsvo.getQ(),getNowEnterpriseId());
            super.writeJson(resultList);
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
     * 车辆型号信心加载
     */
    public void loadCarType()
    {
        try
        {
        	 List<ComboBoxJson> resultList=new ArrayList<ComboBoxJson>();
        	if(ptcsvo.getCbrdId()!=null && !("".equals(ptcsvo.getCbrdId()))){
        		 resultList = logic.findAllCarType(Short.parseShort(ptcsvo.getCbrdId()),ptcsvo.getQ(),getNowEnterpriseId());
        	}else{
        		resultList = logic.findAllCarType(null,ptcsvo.getQ(),getNowEnterpriseId());
        	}
           
            super.writeJson(resultList);
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
     * 配件名称信息加载
     */
    public void loadPartsName()
    {
        try
        {
            List<ComboBoxJson> resultList = logic.findAllPartsName(ptcsvo
                    .getQ());
            super.writeJson(resultList);
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
     * 仓别信息加载
     */
    public void loadStorehouse()
    {
        try
        {
        	BasStorehouseVo basStorehouseVo = new BasStorehouseVo();
	        basStorehouseVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<ComboBoxJson> resultList = logic.findAllStorehouse(basStorehouseVo);
            super.writeJson(resultList);
        }
        catch(Exception e)
        {
            Msg msg = new Msg();
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    public PartsTrendsChangeSearchService getLogic()
    {
        return logic;
    }

    public void setLogic(PartsTrendsChangeSearchService logic)
    {
        this.logic = logic;
    }

    
    public PartsTrendsChangeSearchVo getModel()
    {
        return ptcsvo;
    }
}
