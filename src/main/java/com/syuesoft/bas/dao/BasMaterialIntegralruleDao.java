package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.model.BasMaterialIntegralrule;

public interface BasMaterialIntegralruleDao extends
        BaseDaoI<BasMaterialIntegralrule>
{
    public boolean Add(BasMaterialIntegralrule basMaterialIntegralrule)
            throws Exception;

    public void Delete(BasMaterialIntegralrule basMaterialIntegralrule);

    public void Update(BasMaterialIntegralrule basMaterialIntegralrule);

    public List<BasMaterialIntegralrule> findAll(int page, int rows);

    public List<BasMaterialIntegralrule> getTotle();

    public List<BasMaterialIntegralrule> findByCondition(int page, int rows,
            BasMaterialIntegralrule basMaterialIntegralrule);
}