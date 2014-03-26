package com.syuesoft.integratedservices.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.fbk.vo.ProgramTrackVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.integratedservices.dao.ProgramTrackDao;
import com.syuesoft.integratedservices.service.ProgramTrackService;

@Service("programTrackService")
public class ProgramTrackServiceImpl implements ProgramTrackService
{

    @Autowired
    private ProgramTrackDao programTrackDao;

    public ProgramTrackDao getProgramTrackDao()
    {
        return programTrackDao;
    }

    public void setProgramTrackDao(ProgramTrackDao programTrackDao)
    {
        this.programTrackDao = programTrackDao;
    }

    
    public Datagrid findSameThing(ProgramTrackVo programTrackVo) throws Exception
    {
        return programTrackDao.findSameThing(programTrackVo);
    }

    
    public List findSameThing2(String dcNameIds) throws Exception
    {
        return programTrackDao.findSameThing2(dcNameIds);
    }

}
