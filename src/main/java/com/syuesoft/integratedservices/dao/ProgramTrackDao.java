package com.syuesoft.integratedservices.dao;
import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fbk.vo.ProgramTrackVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.FbkDcserveyName;

public interface ProgramTrackDao extends BaseDaoI<FbkDcserveyName>
{
    public Datagrid findSameThing(ProgramTrackVo programTrackVo) throws Exception;

    public List findSameThing2(String dcNameIds) throws Exception;
}
