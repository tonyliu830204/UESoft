package com.syuesoft.integratedservices.service;

import java.util.List;

import com.syuesoft.fbk.vo.ProgramTrackVo;
import com.syuesoft.fin.vo.Datagrid;

public interface ProgramTrackService
{
    public Datagrid findSameThing(ProgramTrackVo programTrackVo) throws Exception;

    public List findSameThing2(String dcNameIds) throws Exception;
}
