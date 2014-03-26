package com.syuesoft.bas.service;

import com.syuesoft.base.vo.BasRelationCampanyVo;
import com.syuesoft.fin.vo.Datagrid;

public interface BasRelationCampanyService
{

    public Datagrid datagridSupplierArchives(BasRelationCampanyVo brcVo)
            throws Exception; // 供应商档案datagrid

    public void saveSupplierArchives(BasRelationCampanyVo brcVo)
            throws Exception; // 保存供应商档案

    public void remove(Short id) throws Exception; // 删除供应商档案 or 保险(汽厂)档案

    public void editSupplierArchives(BasRelationCampanyVo brcVo)
            throws Exception; // 修改供应商档案

    public Datagrid datagridInsuranceCarArchives(BasRelationCampanyVo brcVo)
            throws Exception; // 保险(汽厂)档案

    public void saveInsuranceCarArchives(BasRelationCampanyVo brcVo)
            throws Exception; // 保存保险(汽厂)档案

    public void editInsuranceCarArchives(BasRelationCampanyVo brcVo)
            throws Exception; // 修改保险(汽厂)档案

    public boolean isExist(BasRelationCampanyVo brcVo, int i) throws Exception;

    public void saveCarCompanyProperties(BasRelationCampanyVo brcVo)
            throws Exception;// 增加保险（汽厂）属性

    public void editCarCompanyProperties(BasRelationCampanyVo brcVo)
            throws Exception;// 修改保险（汽厂）属性

    public void removeCarCompanyProperties(BasRelationCampanyVo brcVo)
            throws Exception;// 删除保险（汽厂）属性

    public Datagrid findAllCarCompanyProperties(BasRelationCampanyVo brcVo)
            throws Exception;// 查询保险（汽厂）属性

	public boolean findCarCompany(BasRelationCampanyVo brcVo)throws Exception;
}
