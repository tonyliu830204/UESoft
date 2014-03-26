package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasRepairWork;

public interface BasRepairWorkService
{
    public boolean add(BasRepairWork basRepairWork) throws Exception;

    public void delete(BasRepairWork basRepairWork) throws Exception;

    public void update(BasRepairWork basRepairWork) throws Exception;

    public List<BasRepairWork> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception;

    public List<BasRepairWork> findByCondition(int page, int rows,
            BasRepairWork basRepairWork) throws Exception;

}
