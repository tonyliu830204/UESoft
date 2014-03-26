package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasVipService;

public interface BasVipServiceService
{
    public void add(BasVipService basVipService) throws Exception;

    public void delete(BasVipService basVipService) throws Exception;

    public void update(BasVipService basVipService) throws Exception;

    public List<BasVipService> findAll(int page, int rows) throws Exception;

    public List<BasVipService> getTotle() throws Exception;
}
