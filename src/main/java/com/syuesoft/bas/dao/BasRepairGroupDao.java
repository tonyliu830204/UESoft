package com.syuesoft.bas.dao;

import java.util.List;

import com.syuesoft.model.BasRepairGroup;

public interface BasRepairGroupDao extends BaseDaoI<BasRepairGroup>
{
    public boolean Add(BasRepairGroup basRepairGroup,Integer nowEnterpriseId) throws Exception;

    public boolean Delete(BasRepairGroup basRepairGroup) throws Exception;

    public boolean Update(BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception;

    public List<BasRepairGroup> findAll(int page, int rows, String sort,
            String order,final int enterpriseId) throws Exception;

    public List<BasRepairGroup> findByCondition(int page, int rows,
    		BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception;
}
