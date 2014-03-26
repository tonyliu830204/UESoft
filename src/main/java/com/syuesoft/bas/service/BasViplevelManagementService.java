package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasViplevelManagement;

public interface BasViplevelManagementService
{
    public void add(BasViplevelManagement basViplevelManagement)
            throws Exception;

    public void delete(BasViplevelManagement basViplevelManagement)
            throws Exception;

    public void update(BasViplevelManagement basViplevelManagement)
            throws Exception;

    public List<BasViplevelManagement> findAll(int page, int rows)
            throws Exception;

    public List<BasViplevelManagement> getTotle() throws Exception;

    public List<BasViplevelManagement> findByCondition(int page, int rows,
            BasViplevelManagement basViplevelManagement) throws Exception;
}
