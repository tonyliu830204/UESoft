package com.syuesoft.integratedservices.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.CarInsuranceManage;
import com.syuesoft.model.CenterCarinInty;
import com.syuesoft.model.InsuranceType;

public interface CarInsuranceManageDAO extends BaseDaoI<CarInsuranceManage>
{
    public int doAdd(CarInsuranceManage carInsuranceManage) throws Exception;

    public int doCenterTableAdd(CenterCarinInty centerCarinInty)
            throws Exception;

    public List<CarInsuranceManage> doFindAll(int page, int rows,
            String startime, String endtime,
            CarInsuranceManage carInsuranceManage) throws Exception;

    public List<InsuranceType> findAllByInsuranceType() throws Exception;

    public void doDelete(CarInsuranceManage carInsuranceManage)
            throws Exception;

    public void doCenterTableDelete(CenterCarinInty centerCarinInty)
            throws Exception;

    public List<CenterCarinInty> getID(CarInsuranceManage carInsuranceManage)
            throws Exception;

    public void doUpdate(CarInsuranceManage carInsuranceManage)
            throws Exception;

    public List<InsuranceType> findByCarInsuranceManageid(
            CarInsuranceManage carInsuranceManage) throws Exception;// 修改时通过carInsuranceManagerid查询InsuranceType的信息

    public void doCenterTableUpdate(CenterCarinInty centerCarinInty)
            throws Exception;

    public void deleteCenterTable(int id) throws Exception;// 修改前时先对已有的中间表记录删除

    public List getCenterTableById(int id) throws Exception;// 修改前获取id对应的信息

    public List onlyFind(int page, int rows) throws Exception;

    // 获取员工名称 用于combox
    public List getBasStuff() throws Exception;

    // 获取部门名称 用于combox
    public List getbasDept() throws Exception;
}