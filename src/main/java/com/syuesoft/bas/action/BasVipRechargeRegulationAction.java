package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipRechargeRegulationService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasVipRechargeRegulation;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("BasVipRechargeRegulationAction")
public class BasVipRechargeRegulationAction extends BaseAction implements
        ModelDriven<BasVipRechargeRegulation>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    @Autowired
    private BasVipRechargeRegulationService basVipRechargeRegulationService;
    private BasVipRechargeRegulation bvr = new BasVipRechargeRegulation();
    private int page;
    private int rows;
    private String order;
    private String sort;

    public void add()
    {
        Message J = new Message();
        try
        {
        	bvr.setEnterpriseId(getUserEnterpriseId());
            if (basVipRechargeRegulationService.getByRecRu(bvr)){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的储值规则已存在，请重新输入！");
            }
            else
            {
                basVipRechargeRegulationService.add(bvr);
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(J);
    }

    public void delete()
    {
        Message J = new Message();
        try
        {
            basVipRechargeRegulationService.delete(bvr);
            J.setSuccess(true);
        }
        catch(Exception e)
        {
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    public void update()
    {
        Message J = new Message();
        try
        {
        	bvr.setEnterpriseId(getUserEnterpriseId());
            if (basVipRechargeRegulationService.getByRecRu(bvr)){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的储值规则已存在，请重新输入！");
            }
            else{
                basVipRechargeRegulationService.update(bvr);
                J.setSuccess(true);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
        }
        super.writeJson(J);
    }

    public void findAll()
    {
        Json json = new Json();
        try{
            List<BasVipRechargeRegulation> list = basVipRechargeRegulationService
                    .findAll(page, rows, order, sort,getNowEnterpriseId());
            int totleRow = basVipRechargeRegulationService.getTotle(getNowEnterpriseId());
            json.setTotal(totleRow);
            json.setRows(list);
            super.writeJson(json);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    
    public BasVipRechargeRegulation getModel()
    {
        return bvr;
    }
    
    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public BasVipRechargeRegulation getBvr()
    {
        return bvr;
    }

    public void setBvr(BasVipRechargeRegulation bvr)
    {
        this.bvr = bvr;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }
}
