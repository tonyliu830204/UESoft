package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasRepairWork;

public interface BasRepairWorkDao extends BaseDaoI<BasRepairWork>
{
    public boolean Add(BasRepairWork basRepairWork) throws Exception;

    public void Delete(BasRepairWork basRepairWork) throws Exception;

    public boolean Update(BasRepairWork basRepairWork) throws Exception;

    public List<BasRepairWork> findAll(int page, int rows, String sort,
            String order,final int enterpriseId) throws Exception;

    public List<BasRepairWork> findByCondition(int page, int rows,
            BasRepairWork basRepairWork) throws Exception;
}
