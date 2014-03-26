package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.model.BasCustomGroup;

public interface BasCustomGroupDao extends BaseDaoI<BasCustomGroup>
{

    public boolean Add(BasCustomGroup basCustomGroup) throws Exception;

    public void Delete(BasCustomGroup basCustomGroup) throws Exception;

    public boolean Update(BasCustomGroup basCustomGroup) throws Exception;

    public List<BasCustomGroup> findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception;

    public List<BasCustomGroup> getTotle(int enterprise_id);

    public List<BasCustomGroup> findByCondition(int page, int rows,
            BasCustomGroup basCustomGroup) throws Exception;

}