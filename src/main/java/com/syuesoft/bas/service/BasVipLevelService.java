package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasVipLevelVO;

public interface BasVipLevelService
{
    public void add(BasVipLevelVO bvlVO) throws Exception;

    public void delete(BasVipLevelVO bvlVO) throws Exception;

    public void update(BasVipLevelVO bvlVO) throws Exception;

    public List<BasVipLevelVO> findAll(int page, int rows, String order,
            String sort,int enterprise_id) throws Exception;

    public int getTotle(int enterprise_id) throws Exception;

    public boolean getByName(BasVipLevelVO bvlVO) throws Exception;
}
