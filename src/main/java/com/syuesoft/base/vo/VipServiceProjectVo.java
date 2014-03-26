package com.syuesoft.base.vo;

public class VipServiceProjectVo
{

    private int page;

    private int rows;

    private String sort;

    private String order;

    private String meal_Name;

    private String note;

    private String d_Id;

    private String meal_Id;

    private String memo;

    private String meal_Context;
    
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getMeal_Context()
    {
        return meal_Context;
    }

    public void setMeal_Context(String mealContext)
    {
        meal_Context = mealContext;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getMeal_Id()
    {
        return meal_Id;
    }

    public void setMeal_Id(String mealId)
    {
        meal_Id = mealId;
    }

    public String getD_Id()
    {
        return d_Id;
    }

    public void setD_Id(String dId)
    {
        d_Id = dId;
    }

    public String getMeal_Name()
    {
        return meal_Name;
    }

    public void setMeal_Name(String meal_Name)
    {
        this.meal_Name = meal_Name;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
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

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

}
