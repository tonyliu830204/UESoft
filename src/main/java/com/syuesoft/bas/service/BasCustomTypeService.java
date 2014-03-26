package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.base.vo.BasCustomTypeVo;
import com.syuesoft.model.BasCustomType;

public interface BasCustomTypeService
{
    public boolean add(BasCustomType basCustomType) throws Exception;

    public void delete(BasCustomType basCustomType) throws Exception;

    public boolean update(BasCustomType basCustomType) throws Exception;

    public List<BasCustomType> findAll(int page, int rows,int interprise_id) throws Exception;

    public List<BasCustomType> getTotle(BasCustomTypeVo basCustomTypeVo) throws Exception;

    public List<BasCustomType> findByCondition(int page, int rows,
            BasCustomType basCustomType) throws Exception;
}
