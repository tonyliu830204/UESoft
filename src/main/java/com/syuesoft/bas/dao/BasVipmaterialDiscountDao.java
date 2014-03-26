package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasVipmaterialDiscount;

public interface BasVipmaterialDiscountDao extends
        BaseDaoI<BasVipmaterialDiscount>
{
    public void Add(BasVipmaterialDiscount basVipmaterialDiscount);

    public void Delete(BasVipmaterialDiscount basVipmaterialDiscount);

    public void Update(BasVipmaterialDiscount basVipmaterialDiscount);

    public List<BasVipmaterialDiscount> findAll(int page, int rows);

    public List<BasVipmaterialDiscount> getTotle();

    public List<BasVipmaterialDiscount> findByCondition(int page, int rows,
            BasVipmaterialDiscount basVipmaterialDiscount);
}
