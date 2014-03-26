package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasClaimsType;

public interface BasClaimsTypeService
{
    public boolean add(BasClaimsType basClaimsType) throws Exception;

    public void delete(BasClaimsType basClaimsType) throws Exception;

    public void update(BasClaimsType basClaimsType) throws Exception;

    public List<BasClaimsType> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception;

    public List<BasClaimsType> findByCondition(int page, int rows,
            BasClaimsType basClaimsType) throws Exception;

}
