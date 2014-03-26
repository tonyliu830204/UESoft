package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.fbk.vo.InsuranceTypeVo;

public interface InsuranceTypeService
{
    public boolean add(InsuranceTypeVo insuranceTypeVo) throws Exception;

    public void delete(InsuranceTypeVo insuranceTypeVo) throws Exception;

    public boolean update(InsuranceTypeVo insuranceTypeVo) throws Exception;

    public List findAll(int page, int rows, String sort, String order,int enterpriseId)
            throws Exception;
}
