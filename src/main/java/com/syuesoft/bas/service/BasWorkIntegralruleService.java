package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasWorkIntegralrule;

public interface BasWorkIntegralruleService
{
    public void add(BasWorkIntegralrule basWorkIntegralrule) throws Exception;

    public void delete(BasWorkIntegralrule basWorkIntegralrule)
            throws Exception;

    public void update(BasWorkIntegralrule basWorkIntegralrule)
            throws Exception;

    public List<BasWorkIntegralrule> findAll(int page, int rows)
            throws Exception;

    public List<BasWorkIntegralrule> getTotle() throws Exception;

    public List<BasWorkIntegralrule> findByCondition(int page, int rows,
            BasWorkIntegralrule basWorkIntegralrule) throws Exception;
}
