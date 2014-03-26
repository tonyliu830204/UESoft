package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasVipResentVO;

public interface BasVipResentService
{
    public void add(BasVipResentVO basVipResentVO) throws Exception;

    public void delete(BasVipResentVO basVipResentVO) throws Exception;

    public void update(BasVipResentVO basVipResentVO) throws Exception;

    public List<BasVipResentVO> findAll(int page, int rows, String order,
            String sort) throws Exception;

    public int getTotle() throws Exception;

    public boolean getByName(String name) throws Exception;
}
