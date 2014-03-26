package com.syuesoft.bas.service;

import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.util.Json;

public interface FbkDcserveyNameService
{
    public boolean add(FbkDcserveyName fbkDcserveyName) throws Exception;

    public void delete(FbkDcserveyName fbkDcserveyName) throws Exception;

    public boolean update(FbkDcserveyName fbkDcserveyName) throws Exception;

    public Json findAll(int page, int rows, String sort,
            String order,final int enterprise_id) throws Exception;
}
