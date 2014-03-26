package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.model.BasRepairGroup;

public interface BasRepairGroupService
{

    public boolean add(BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception;

    public void delete(BasRepairGroup basRepairGroup) throws Exception;

    public boolean update(BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception;

    public List<BasRepairGroup> findAll(int page, int rows, String sort,
            String order,int enterpriseId) throws Exception;

    public List<BasRepairGroup> findByCondition(int page, int rows,
    		BasRepairGroup basRepairGroup,Integer  nowEnterpriseId) throws Exception;

}
