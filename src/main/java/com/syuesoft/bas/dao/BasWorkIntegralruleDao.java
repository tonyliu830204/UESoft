package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasWorkIntegralrule;

public interface BasWorkIntegralruleDao extends BaseDaoI<BasWorkIntegralrule>
{

    public void Add(BasWorkIntegralrule basWorkIntegralrule);

    public void Delete(BasWorkIntegralrule basWorkIntegralrule);

    public void Update(BasWorkIntegralrule basWorkIntegralrule);

    public List<BasWorkIntegralrule> findAll(int page, int rows);

    public List<BasWorkIntegralrule> getTotle();

    public List<BasWorkIntegralrule> findByCondition(int page, int rows,
            BasWorkIntegralrule basWorkIntegralrule);
}