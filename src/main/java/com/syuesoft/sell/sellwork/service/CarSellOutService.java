package com.syuesoft.sell.sellwork.service;

import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.sellwork.vo.CarFixVo;
import com.syuesoft.util.Json;

public interface CarSellOutService {

    /**
     * @throws Exception  
    *
    * @Title: getSellOut 
    * @Description: TODO(查询加装出库单) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getSellOut(CarFixVo carFixVo) throws Exception;

    /** 
    *
    * @Title: getSellOutPart 
    * @Description: TODO(查询加装出库配件) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getSellOutPart(CarFixVo carFixVo) throws Exception;

    /** 
    *
    * @Title: getSellOutProject 
    * @Description: TODO(查询加装出库项目) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getSellOutProject(CarFixVo carFixVo) throws Exception;

    /** 
    *
    * @Title: getOutInstall 
    * @Description: TODO(查询审核过的加装单) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getOutInstall(CarFixVo carFixVo) throws Exception;
    
    /** 
    *
    * @Title: getOutInstallDetail 
    * @Description: TODO(查询审核过的加装单明细) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getOutInstallDetail(CarFixVo carFixVo) throws Exception;
    
    /** 
    *
    * @Title: saveOutInstall 
    * @Description: TODO(保存出库) 
    * @param @param carFixVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public void saveOutInstall(CarFixVo carFixVo, BasUsers user) throws Exception;

    /** 
    *
    * @Title: deleteInstall 
    * @Description: TODO(删除出库) 
    * @param @param carFixVo
    * @param @param users    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void deleteInstall(CarFixVo carFixVo) throws Exception;
}	