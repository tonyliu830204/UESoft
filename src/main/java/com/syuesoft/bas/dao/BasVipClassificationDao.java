package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasVipClassification;

public interface BasVipClassificationDao extends BaseDaoI<BasVipClassification>
{
    public void Add(BasVipClassification basVipClassification) throws Exception;

    public void Delete(BasVipClassification basVipClassification)
            throws Exception;

    public void Update(BasVipClassification basVipClassification)
            throws Exception;

    public List<BasVipClassification> findAll(int page, int rows)
            throws Exception;

    public List<BasVipClassification> getTotle() throws Exception;

    public List<BasVipClassification> findByCondition(int page, int rows,
            BasVipClassification basVipClassification) throws Exception;
}
