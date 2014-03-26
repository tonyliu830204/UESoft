package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasVipActivitiesDiscount;

public interface BasVipActivitiesDiscountService
{
    public void add(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception; // 添加活动折扣

    public void delete(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception; // 删除活动折扣

    public void update(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception; // 编辑活动折扣

    public List<BasVipActivitiesDiscount> findAll(int page, int rows,
            String order, String sort,int enterprise_id ) throws Exception; // 获取所有活动折扣

    public int getTotle(int enterprise_id ) throws Exception; // 获取总记录数

    public boolean getByName(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception;
}
