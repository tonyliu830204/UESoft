package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasCustomGroupVo;
import com.syuesoft.model.BasCustomGroup;

public interface BasCustomGroupService
{
    public boolean add(BasCustomGroup basCustomGroup) throws Exception;

    public void delete(BasCustomGroup basCustomGroup) throws Exception;

    public boolean update(BasCustomGroup basCustomGroup) throws Exception;

    public List<BasCustomGroup> findAll(int page, int rows, String sort,
            String order,int enterprise_id) throws Exception;

    public List<BasCustomGroup> getTotle(BasCustomGroupVo basCustomGroupVo) throws Exception;

    public List<BasCustomGroup> findByCondition(int page, int rows,
            BasCustomGroup basCustomGroup) throws Exception;
}
