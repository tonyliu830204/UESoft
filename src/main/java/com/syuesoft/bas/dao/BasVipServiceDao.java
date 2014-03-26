package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasVipService;

public interface BasVipServiceDao extends BaseDaoI<BasVipService>
{
    public void Add(BasVipService basVipService) throws Exception;

    public void Delete(BasVipService basVipService) throws Exception;

    public void Update(BasVipService basVipService) throws Exception;

    public List<BasVipService> findAll(int page, int rows) throws Exception;

    public List<BasVipService> getTotle() throws Exception;
}
