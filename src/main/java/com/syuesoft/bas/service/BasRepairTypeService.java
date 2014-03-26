package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasRepairType;

public interface BasRepairTypeService
{
    public boolean add(BasRepairType basRepairType) throws Exception;

    public void delete(BasRepairType basRepairType) throws Exception;

    public void update(BasRepairType basRepairType) throws Exception;

    public List<BasRepairType> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception;

    public List<BasRepairType> findByCondition(int page, int rows,
            BasRepairType basRepairType) throws Exception;
}
