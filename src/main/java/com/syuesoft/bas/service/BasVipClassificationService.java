package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasVipClassification;

public interface BasVipClassificationService
{
    public void add(BasVipClassification basVipClassification) throws Exception;

    public void delete(BasVipClassification basVipClassification)
            throws Exception;

    public void update(BasVipClassification basVipClassification)
            throws Exception;

    public List<BasVipClassification> findAll(int page, int rows)
            throws Exception;

    public List<BasVipClassification> getTotle() throws Exception;

    public List<BasVipClassification> findByCondition(int page, int rows,
            BasVipClassification basVipClassification) throws Exception;
}
