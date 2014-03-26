/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
* @Title: ControlRoleAction.java 
* @Package com.syuesoft.role.action 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-8-7 上午09:46:45 
* @version V1.0   
*/
package com.syuesoft.role.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.role.service.IControlRoleService;

/** 
 * @ClassName: ControlRoleAction 
 * @Description: TODO(业务权限控制) 
 * @author yanzhangan 
 * @date 2013-8-7 上午09:46:45 
 * @version 1.0 
 */
@ParentPackage(value = "basePackage")
@Action("controlRoleAction")
public class ControlRoleAction extends BaseAction implements ModelDriven<BasCompanyInformationSetVo>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private final Logger logger = Logger.getLogger(this.getClass());

    BasCompanyInformationSetVo cis = new BasCompanyInformationSetVo();
    
    public BasCompanyInformationSetVo getModel()
    {
        return cis;
    }
    
    @Autowired
    private IControlRoleService controlRoleService;
    
    /**
    *
    * @Title: getBusinessUser 
    * @Description: TODO(部门员工树形结构) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void getBusinessUser(){
        try
        {
            writeJson(controlRoleService.getBusinessUser(cis));
        }
        catch(Exception e)
        {
            logger.error("", e);
        }
    }
    
    /**
    *
    * @Title: getBusinessUser 
    * @Description: TODO(根据用户查询，用户拥有的仓别) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void getStorehouseByUser(){
        try
        {
            writeJson(controlRoleService.findCheckCBFL(cis));
        }
        catch(Exception e)
        {
            logger.error("", e);
        }
    }
    
    /**
    *
    * @Title: saveOrUpdate 
    * @Description: TODO(保存业务权限) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void saveOrUpdate(){
        try
        {
            writeJson(controlRoleService.saveOrUpdate(cis));
        }
        catch(Exception e)
        {
            logger.error("", e);
        }
    }
    
    /**
     * 查询业务控制权限
     */
    public void searchWorkParameter()
    {
        try
        {
            super.writeJson(controlRoleService.searchWorkParameter(cis));
        }
        catch(Exception e)
        {
            logger.error("带条件查询数据的方法失败", e);
        }
    }
    
    /**
     * 查询业务控制内容
     */
    public void searchParameter()
    {
        try
        {
            super.writeJson(controlRoleService.searchParameter(cis));
        }
        catch(Exception e)
        {
            logger.error("带条件查询数据的方法失败", e);
        }
    }
}