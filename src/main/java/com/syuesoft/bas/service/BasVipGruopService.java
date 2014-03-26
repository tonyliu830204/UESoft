package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasVipGruopVO;

public interface BasVipGruopService
{
    public void add(BasVipGruopVO basVipGruopVO) throws Exception;

    public void delete(BasVipGruopVO basVipGruopVO) throws Exception;

    public void update(BasVipGruopVO basVipGruopVO) throws Exception;

    public List<BasVipGruopVO> findAll(int page, int rows, String order,
            String sort,int  enterprise_id) throws Exception;

    public int getTotle(int enterprise_id) throws Exception;

    public boolean getByName(BasVipGruopVO basVipGruopVO) throws Exception;
}