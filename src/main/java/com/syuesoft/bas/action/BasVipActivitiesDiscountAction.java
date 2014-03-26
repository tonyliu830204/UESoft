package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipActivitiesDiscountService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasVipActivitiesDiscount;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
/**
 * @author mulangtao
 * */


@ParentPackage(value = "basePackage")
@Action("BasVipActivitiesDiscountAction")
public class BasVipActivitiesDiscountAction extends BaseAction implements
        ModelDriven<BasVipActivitiesDiscount>
{
    private static final long serialVersionUID = 1L;
    // 会员活动折扣
    private BasVipActivitiesDiscount basVipActivitiesDiscount = new BasVipActivitiesDiscount();
    @Autowired
    private BasVipActivitiesDiscountService basVipActivitiesDiscountService;
    // 当前页
    private int page;
    // 每页显示记录数
    private int rows;
    // 排序类型
    private String order;
    // 排序字段
    private String sort;
    /**
     * 添加活动折扣
     * */
    public void add() throws Exception
    {
        Message J = new Message();
        try{
        	basVipActivitiesDiscount.setEnterpriseId(getUserEnterpriseId());
            // 根据活动名称判断活动是否存在
            if (basVipActivitiesDiscountService.getByName(basVipActivitiesDiscount)){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的活动名称已存在，请重新输入！");
            }else{
                basVipActivitiesDiscountService.add(basVipActivitiesDiscount);
                J.setSuccess(true);
            }
        }catch(Exception e){
            J.setSuccess(false);
            J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    /**
     * 删除活动折扣
     * */
    public void delete()
    {
        Message J = new Message();
        try{
            basVipActivitiesDiscountService.delete(basVipActivitiesDiscount);
            J.setSuccess(true);
        }catch(Exception e){
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
    }
    
    /**
     * 编辑活动折扣
     * 
     * @throws Exception
     * */
    public void update() throws Exception
    {
        Message J = new Message();
        try
        {
        	basVipActivitiesDiscount.setEnterpriseId(getUserEnterpriseId());
            if (basVipActivitiesDiscountService.getByName(basVipActivitiesDiscount)){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的活动名称已存在，请重新输入！");
            }else{
                basVipActivitiesDiscountService.update(basVipActivitiesDiscount);
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

    /**
     * 获取所有活动折扣
     * 
     * @throws Exception
     * */
    public void findAll() throws Exception
    {
        Json json = new Json();
        try
        {
            List<BasVipActivitiesDiscount> list = basVipActivitiesDiscountService.findAll(page, rows, order, sort,getNowEnterpriseId());
            int rowsCount = basVipActivitiesDiscountService.getTotle(getNowEnterpriseId());
            if (list.size() > 0){
                json.setRows(list);
                json.setTotal(rowsCount);
                super.writeJson(json);
            }else{
                json.setRows(list);
                super.writeJson(json);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
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

    public BasVipActivitiesDiscount getBasVipActivitiesDiscount()
    {
        return basVipActivitiesDiscount;
    }

    
    public BasVipActivitiesDiscount getModel()
    {
        return basVipActivitiesDiscount;
    }

    public int getPage()
    {
        return page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setBasVipActivitiesDiscount(
            BasVipActivitiesDiscount basVipActivitiesDiscount)
    {
        this.basVipActivitiesDiscount = basVipActivitiesDiscount;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

}
