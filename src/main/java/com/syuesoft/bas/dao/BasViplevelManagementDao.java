package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasViplevelManagement;

public interface BasViplevelManagementDao extends
        BaseDaoI<BasViplevelManagement>
{
    public void Add(BasViplevelManagement basViplevelManagement);

    public void Delete(BasViplevelManagement basViplevelManagement);

    public void Update(BasViplevelManagement basViplevelManagement);

    public List<BasViplevelManagement> findAll(int page, int rows);

    public List<BasViplevelManagement> getTotle();

    public List<BasViplevelManagement> findByCondition(int page, int rows,
            BasViplevelManagement basViplevelManagement);
}
