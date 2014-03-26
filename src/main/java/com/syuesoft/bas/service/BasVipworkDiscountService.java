package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasVipworkDiscount;

public interface BasVipworkDiscountService
{
    public void add(BasVipworkDiscount basVipworkDiscount) throws Exception;

    public void delete(BasVipworkDiscount basVipworkDiscount) throws Exception;

    public void update(BasVipworkDiscount basVipworkDiscount) throws Exception;

    public List<BasVipworkDiscount> findAll(int page, int rows)
            throws Exception;

    public List<BasVipworkDiscount> getTotle() throws Exception;

    public List<BasVipworkDiscount> findByCondition(int page, int rows,
            BasVipworkDiscount basVipworkDiscount) throws Exception;
}
