package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasRepairType;

public interface BasRepairTypeDao extends BaseDaoI<BasRepairType>
{
    public boolean Add(BasRepairType basRepairType) throws Exception;

    public void Delete(BasRepairType basRepairType) throws Exception;

    public boolean Update(BasRepairType basRepairType) throws Exception;

    public List<BasRepairType> findAll(final int page,final int rows,final String sort,
    		final String order,final int enterpriseId) throws Exception;

    public List<BasRepairType> findByCondition(final int page,final int rows,
    		final BasRepairType basRepairType) throws Exception;
    
}
