package com.syuesoft.bas.service;

import java.util.List;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.model.FrtCar;
import com.syuesoft.util.ComboBoxJson;

public interface FrtBaseService extends BaseService
{
    public List<ComboBoxJson> findAllCarLicenseByReceptionId(String q,int enterprise_id)
            throws Exception; // 查找有工单的车辆牌照

    public List<ComboBoxJson> findAllCarLicenseByReceptionIdAsCarLicense(
            String q,int enterprise_id) throws Exception; // 查找有工单的车辆牌照

    public List<ComboBoxJson> findAllCarLicenseAsCarLicense(String q,Integer id)
            throws Exception; // 查找车辆牌照
    public List<ComboBoxJson> findUseAllCarLicense(String q,Integer id)
    		throws Exception; // 查找可使用的车辆牌照

    public List<ComboBoxJson> findAllCustomAsCustomName(String q,int enterprise_id)
    		throws Exception; // 查找客户
    
    public List<ComboBoxJson> findAllReptype(String q,Integer id) throws Exception; // 查询维修类别

    public List<ComboBoxJson> findAllRepairWork(String q,Integer id) throws Exception; // 查询维修工位

    public List<ComboBoxJson> findAllRepairGroup(String q,Integer id) throws Exception; // 查询维修班组
    
    public List<ComboBoxJson> findAllClaimManufacturers(String q,Integer id) throws Exception; // 查询索赔厂商
    
    public List<ComboBoxJson> findAllCarTypeAsName(String q,Integer id) throws Exception;//查询车型号
    
    public List<ComboBoxJson> findAllWorkHouse(String q,int enterprise_id) throws Exception;//查询仓别

	public void changecarId(FrtCarVo fcVo)throws Exception;//车牌照变更
    public List<FrtCar> getCarId(FrtCarVo fcVo) throws Exception; // 查询车牌照是否存在
    
}
