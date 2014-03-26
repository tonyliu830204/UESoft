package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasCountassociationType;
import com.syuesoft.util.Json;

public interface BasCountassociationTypeService
{

    public boolean add(BasCountassociationType basCountassociationType)
            throws Exception;

    public void delete(BasCountassociationType basCountassociationType)
            throws Exception;

    public boolean update(BasCountassociationType basCountassociationType)
            throws Exception;

    public Json findAll(int page, int rows,
            String sort, String order,int enterprise_id) throws Exception;

    public List<BasCountassociationType> getTotle() throws Exception;

    public List<BasCountassociationType> findByCondition(int page, int rows,
            BasCountassociationType basCountassociationType) throws Exception;

}
