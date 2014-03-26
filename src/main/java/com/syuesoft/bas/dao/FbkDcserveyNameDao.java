package com.syuesoft.bas.dao;

import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.util.Json;

public interface FbkDcserveyNameDao extends BaseDaoI<FbkDcserveyName>
{
    public boolean Add(FbkDcserveyName fbkDcserveyName) throws Exception;

    public boolean Update(FbkDcserveyName fbkDcserveyName) throws Exception;

    public Json findAll(int page, int rows, String sort,String order,final int enterprise_id) throws Exception;
}
