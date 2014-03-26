package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasCountassociationType;
import com.syuesoft.util.Json;

public interface BasCountassociationTypeDao extends
        BaseDaoI<BasCountassociationType>
{
    public boolean Add(BasCountassociationType basCountassociationType)
            throws Exception;

    public void Delete(BasCountassociationType basCountassociationType)
            throws Exception;

    public boolean Update(BasCountassociationType basCountassociationType)
            throws Exception;

    public Json findAll(int page, int rows,
            String sort, String order,int enterprise_id) throws Exception;

    public List<BasCountassociationType> getTotle() throws Exception;

    public List<BasCountassociationType> findByCondition(int page, int rows,
            BasCountassociationType basCountassociationType) throws Exception;
}