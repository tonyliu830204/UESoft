package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasVipActivitiesDiscount;

/**
 * @author mulangtao 会员活动折扣
 */
public interface BasVipActivitiesDiscountDao extends
        BaseDaoI<BasVipActivitiesDiscount>
{
    public void add(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception; // 添加活动折扣

    public void delte(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception; // 删除活动折扣

    public void update(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception; // 编辑活动折扣

    public List<BasVipActivitiesDiscount> findAll(int page, int rows,
            String order, String sort,int enterprise_id ) throws Exception; // 获取所有活动折扣

    public int getTotle(int enterprise_id) throws Exception;

    public boolean getByName(BasVipActivitiesDiscount basVipActivitiesDiscount)
            throws Exception;
}
