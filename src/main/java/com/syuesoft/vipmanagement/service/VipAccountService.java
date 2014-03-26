package com.syuesoft.vipmanagement.service;

import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.vipmanagement.vo.VipAccountVo;

public interface VipAccountService
{

    /** 
    *
    * @Title: findAll 
    * @Description: TODO(查询所有对账单) 
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json findAll(VipAccountVo vipAccountVo, BasUsers users) throws Exception;

    /** 
    *
    * @Title: getMaxAccountEndDate 
    * @Description: TODO(查询最大对账单日期) 
    * @param @param vipAccountVo
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Message getMaxAccountEndDate(VipAccountVo vipAccountVo, BasUsers users) throws Exception;

    /** 
    *
    * @Title: getAccountEndDetail 
    * @Description: TODO(对账单明细查询) 
    * @param @param vipAccountVo
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getAccountDetail(VipAccountVo vipAccountVo, BasUsers users) throws Exception;
    
    /** 
    *
    * @Title: getAccountEndDetail 
    * @Description: TODO(对账单收款明细查询) 
    * @param @param vipAccountVo
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json getAccountMoneyDetail(VipAccountVo vipAccountVo, BasUsers users) throws Exception;
    
    /** 
    *
    * @Title: saveAccountDetail 
    * @Description: TODO(保存对账单) 
    * @param @param vipAccountVo
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Message saveAccount(VipAccountVo vipAccountVo, BasUsers users) throws Exception;

    /** 
    *
    * @Title: deleteAccount 
    * @Description: TODO(撤销对账单) 
    * @param @param vipAccountVo
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Message deleteAccount(VipAccountVo vipAccountVo, BasUsers users) throws Exception;

    /** 
    *
    * @Title: savePayMent 
    * @Description: TODO(收款) 
    * @param @param vipAccountVo
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Message savePayMent(VipAccountVo vipAccountVo, BasUsers users) throws Exception;

    /** 
    *
    * @Title: doUpdateState 
    * @Description: TODO(审核) 
    * @param @param vipAccountVo
    * @param @param users
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Message doUpdateState(VipAccountVo vipAccountVo, BasUsers users) throws Exception;
}