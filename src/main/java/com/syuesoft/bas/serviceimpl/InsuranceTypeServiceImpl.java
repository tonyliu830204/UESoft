package com.syuesoft.bas.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.bas.dao.InsuranceTypeDao;
import com.syuesoft.bas.service.InsuranceTypeService;
import com.syuesoft.fbk.vo.InsuranceTypeVo;

@Service("insuranceTypeService")
public class InsuranceTypeServiceImpl implements InsuranceTypeService
{
    @Autowired
    private InsuranceTypeDao insuranceTypeDao;

    public InsuranceTypeDao getInsuranceTypeDao()
    {
        return insuranceTypeDao;
    }

    public void setInsuranceTypeDao(InsuranceTypeDao insuranceTypeDao)
    {
        this.insuranceTypeDao = insuranceTypeDao;
    }

    
    public boolean add(InsuranceTypeVo insuranceTypeVo) throws Exception
    {
        return insuranceTypeDao.Add(insuranceTypeVo);

    }

    
    public void delete(InsuranceTypeVo insuranceTypeVo) throws Exception
    {
        insuranceTypeDao.Delete(insuranceTypeVo);

    }

    
    public boolean update(InsuranceTypeVo insuranceTypeVo) throws Exception
    {
        return insuranceTypeDao.Update(insuranceTypeVo);

    }

    
    public List findAll(int page, int rows, String sort, String order,int enterpriseId)
            throws Exception
    {
        return insuranceTypeDao.findAll(page, rows, sort, order,enterpriseId);
    }

}
