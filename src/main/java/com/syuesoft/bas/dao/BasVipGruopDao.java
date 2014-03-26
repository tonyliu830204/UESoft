package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.base.vo.BasVipGruopVO;
import com.syuesoft.model.BasVipGruop;

public interface BasVipGruopDao extends BaseDaoI<BasVipGruop>
{
    public void add(BasVipGruop basVipGruop) throws Exception;

    public void delte(BasVipGruop basVipGruop) throws Exception;

    public void update(BasVipGruop basVipGruop) throws Exception;

    public List<BasVipGruopVO> findAll(int page, int rows, String order,
            String sort,int enterprise_id) throws Exception;

    public int getTotle(int enterprise_id) throws Exception;

    public boolean getByName(BasVipGruopVO basVipGruopVO) throws Exception;
}
