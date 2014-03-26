package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasCustomArea;

public interface BasCustomAreaDao extends BaseDaoI<BasCustomArea>
{

    public boolean Add(BasCustomArea basCustomArea) throws Exception;

    public boolean Delete(BasCustomArea basCustomArea) throws Exception;

    public boolean Update(BasCustomArea basCustomArea) throws Exception;

    public List<BasCustomArea> findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception;

    public List<BasCustomArea> getTotle(BasCustomArea basCustomArea);

}
