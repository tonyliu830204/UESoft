package com.syuesoft.bas.service;

import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.util.Json;

/** 
 * @ClassName: FrtAddItemService 
 * @Description: TODO(加装项目) 
 * @author HeXin 
 * @date 2013-8-29 下午03:28:00 
 * @version 1.0 
 */
public interface FrtAddItemService
{
    public Datagrid getTreegrid(FrtRepairItemVo frtRepairItemVo)  throws Exception;

    /** 
    *
    * @Title: delete 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param frtRepairItemVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public void delete(FrtRepairItemVo frtRepairItemVo)  throws Exception;

    /** 
    *
    * @Title: save 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param frtRepairItemVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void save(FrtRepairItemVo frtRepairItemVo) throws Exception;

    /** 
    *
    * @Title: update 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param frtRepairItemVo    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void update(FrtRepairItemVo frtRepairItemVo) throws Exception;
}