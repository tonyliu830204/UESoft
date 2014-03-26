package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.model.BasClaimsType;

public interface BasClaimsTypeDao extends BaseDaoI<BasClaimsType>
{

    public boolean Add(BasClaimsType basClaimsType) throws Exception;

    public void Delete(BasClaimsType basClaimsType) throws Exception;

    public boolean Update(BasClaimsType basClaimsType) throws Exception;

    public List<BasClaimsType> findAll(final int page,final int rows,final String sort,
    		final String order,final int enterpriseId) throws Exception;

    public List<BasClaimsType> findByCondition(int page, int rows,
            BasClaimsType basClaimsType) throws Exception;
}