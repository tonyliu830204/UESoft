package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.fbk.vo.InsuranceTypeVo;
import com.syuesoft.model.InsuranceType;

public interface InsuranceTypeDao extends BaseDaoI<InsuranceType>
{
    public boolean Add(InsuranceTypeVo insuranceTypeVo) throws Exception;

    public void Delete(InsuranceTypeVo insuranceTypeVo) throws Exception;

    public boolean Update(InsuranceTypeVo insuranceTypeVo) throws Exception;

    public List findAll(final int page,final int rows,final String sort,final String order,final int enterpriseId)
            throws Exception;
}
