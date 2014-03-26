package com.syuesoft.bas.dao;

import java.util.List;
import com.syuesoft.model.BasCompanyInformationSet;

public interface BasCompanyInformationSetDAO extends BaseDaoI<BasCompanyInformationSet>{

    public void delete(String id);

    public void update(BasCompanyInformationSet cis);

    public Object saveOrUpdate(List<BasCompanyInformationSet> cisVos);

    public List<BasCompanyInformationSet> findAll(String param);

    public List<BasCompanyInformationSet> findAll(String param, int page,
            int rows);
    
    public BasCompanyInformationSet getBasCompanyInformationSet(String type,String name);
    
    public BasCompanyInformationSet getBasCompanyInformationSet(String type,
            String name,int enterpriseId);
    
    /** 系统参数获取保留小数位数*/
    public String repairPersistFigure(String name,String type,String value);
    
}
