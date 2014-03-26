package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasVipmaterialDiscount;

public interface BasVipmaterialDiscountService
{
    public void add(BasVipmaterialDiscount basVipmaterialDiscount)
            throws Exception;

    public void delete(BasVipmaterialDiscount basVipmaterialDiscount)
            throws Exception;

    public void update(BasVipmaterialDiscount basVipmaterialDiscount)
            throws Exception;

    public List<BasVipmaterialDiscount> findAll(int page, int rows)
            throws Exception;

    public List<BasVipmaterialDiscount> getTotle() throws Exception;

    public List<BasVipmaterialDiscount> findByCondition(int page, int rows,
            BasVipmaterialDiscount basVipmaterialDiscount) throws Exception;
}
