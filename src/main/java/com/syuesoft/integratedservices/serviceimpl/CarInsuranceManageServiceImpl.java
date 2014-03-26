package com.syuesoft.integratedservices.serviceimpl;

import java.util.List;

import com.syuesoft.Tag.Log;
import com.syuesoft.fbk.vo.CarInsuranceManagerVo;
import com.syuesoft.integratedservices.dao.CarInsuranceManageDAO;
import com.syuesoft.integratedservices.service.CarInsuranceManageService;
import com.syuesoft.model.CarInsuranceManage;
import com.syuesoft.model.CenterCarinInty;
import com.syuesoft.model.InsuranceType;

public class CarInsuranceManageServiceImpl implements CarInsuranceManageService
{
    private CarInsuranceManageDAO dao;

    public CarInsuranceManageDAO getDao()
    {
        return dao;
    }

    public void setDao(CarInsuranceManageDAO dao)
    {
        this.dao = dao;
    }

    @Log(moduleName = "客户服务", content = "新增车辆保单信息", opertype = "新增")
    public int doAdd(CarInsuranceManage carInsuranceManage) throws Exception
    {
        return dao.doAdd(carInsuranceManage);
    }

    
    public List doFindAll(int page, int rows, String startime, String endtime,
            CarInsuranceManage carInsuranceManage) throws Exception
    {
        return dao.doFindAll(page, rows, startime, endtime, carInsuranceManage);
    }

    
    public List<InsuranceType> findAllByInsuranceType() throws Exception
    {
        return dao.findAllByInsuranceType();
    }

    
    public int doCenterTableAdd(CenterCarinInty centerCarinInty)
            throws Exception
    {
        dao.doCenterTableAdd(centerCarinInty);
        return 1;
    }

    
    public void doCenterTableDelete(CenterCarinInty centerCarinInty)
            throws Exception
    {
        dao.doCenterTableDelete(centerCarinInty);
    }

    
    public void doDelete(CarInsuranceManage carInsuranceManage)
            throws Exception
    {
        dao.doDelete(carInsuranceManage);
    }

    
    public List<CenterCarinInty> getID(CarInsuranceManage carInsuranceManage)
            throws Exception
    {

        return dao.getID(carInsuranceManage);
    }

    /*
     * (non-Javadoc) 对车辆保单修改的方法
     * 
     * @see
     * com.syuesoft.integratedservices.dao.CarInsuranceManageDAO#doUpdate(com
     * .syuesoft.model.CarInsuranceManage)
     */
    @Log(moduleName = "客户服务", content = "修改车辆保单信息", opertype = "修改")
    public void doUpdate(CarInsuranceManage carInsuranceManage)
            throws Exception
    {
        dao.doUpdate(carInsuranceManage);
    }

    // 修改时通过carInsuranceManagerid查询InsuranceType的信息
    
    public List<InsuranceType> findByCarInsuranceManageid(
            CarInsuranceManage carInsuranceManage) throws Exception
    {
        return dao.findByCarInsuranceManageid(carInsuranceManage);
    }

    
    public void doCenterTableUpdate(CenterCarinInty centerCarinInty)
            throws Exception
    {
        dao.doCenterTableUpdate(centerCarinInty);
    }

    @Log(moduleName = "客户服务", content = "删除车辆保单信息", opertype = "删除")
    public void deleteCenterTable(int id) throws Exception
    {
        dao.deleteCenterTable(id);

    }

    
    public List onlyFind(int page, int rows) throws Exception
    {
        return dao.onlyFind(page, rows);
    }

    
    public List getCenterTableById(int id) throws Exception
    {
        return dao.getCenterTableById(id);
    }

    
    public List getBasStuff() throws Exception
    {
        return dao.getBasStuff();
    }

    
    public List getbasDept() throws Exception
    {
        return dao.getbasDept();
    }

}
