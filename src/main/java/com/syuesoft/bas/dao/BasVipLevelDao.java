package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasVipLevelVO;
import com.syuesoft.model.BasVipLevel;

public interface BasVipLevelDao extends BaseDaoI<BasVipLevel>
{
    public void add(BasVipLevel bvl) throws Exception;

    public void delte(BasVipLevel bvl) throws Exception;

    public void update(BasVipLevel bvl) throws Exception;

    public List<BasVipLevelVO> findAll(int page, int rows, String order,
            String sort,final int enterprise_id) throws Exception;

    public int getTotle(int enterprise_id) throws Exception;

    public boolean getByName(BasVipLevelVO bvlVO) throws Exception;
}
