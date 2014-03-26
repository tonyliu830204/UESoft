package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasVipworkDiscount;

public interface BasVipworkDiscountDao extends BaseDaoI<BasVipworkDiscount>
{
    public void Add(BasVipworkDiscount basVipworkDiscount);

    public void Delete(BasVipworkDiscount basVipworkDiscount);

    public void Update(BasVipworkDiscount basVipworkDiscount);

    public List<BasVipworkDiscount> findAll(int page, int rows);

    public List<BasVipworkDiscount> getTotle();

    public List<BasVipworkDiscount> findByCondition(int page, int rows,
            BasVipworkDiscount basVipworkDiscount);
}
