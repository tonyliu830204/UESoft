package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasMaterialIntegralrule;

public interface BasMaterialIntegralruleService
{
    public boolean add(BasMaterialIntegralrule basMaterialIntegralrule)
            throws Exception;

    public void delete(BasMaterialIntegralrule basMaterialIntegralrule)
            throws Exception;

    public void update(BasMaterialIntegralrule basMaterialIntegralrule)
            throws Exception;

    public List<BasMaterialIntegralrule> findAll(int page, int rows)
            throws Exception;

    public List<BasMaterialIntegralrule> getTotle() throws Exception;

    public List<BasMaterialIntegralrule> findByCondition(int page, int rows,
            BasMaterialIntegralrule basMaterialIntegralrule) throws Exception;
}
