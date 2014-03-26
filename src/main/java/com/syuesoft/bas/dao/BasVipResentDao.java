package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasVipResentVO;
import com.syuesoft.model.BasVipResent;

public interface BasVipResentDao extends BaseDaoI<BasVipResent>
{
    public void add(BasVipResent basVipResent) throws Exception;

    public void delte(BasVipResent basVipResent) throws Exception;

    public void update(BasVipResent basVipResent) throws Exception;

    public List<BasVipResentVO> findAll(int page, int rows, String order,
            String sort) throws Exception;

    public int getTotle() throws Exception;

    public boolean getByName(String name) throws Exception;
}
