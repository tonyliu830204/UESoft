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
* @Title: IControlRoleService.java 
* @Package com.syuesoft.role.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-8-7 上午10:00:18 
* @version V1.0   
*/
package com.syuesoft.role.service;

import java.util.List;

import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.TreeJson;

/** 
 * @ClassName: IControlRoleService 
 * @Description: TODO(业务权限控制) 
 * @author yanzhangan 
 * @date 2013-8-7 上午10:00:18 
 * @version 1.0 
 */
public interface IControlRoleService
{   
    /**
    *
    * @Title: findAllCBFL 
    * @Description: TODO(查询仓别) 
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List    返回类型 
    * @throws
     */
    public List<String> findCheckCBFL(BasCompanyInformationSetVo companyInformationSetVo) throws Exception;
    
    /**
    *
    * @Title: getBusinessUser 
    * @Description: TODO(树形结构部门员工) 
    * @param @param companyInformationSetVo
    * @param @param softType
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List<TreeJson>    返回类型 
    * @throws
     */
    public  List<TreeJson> getBusinessUser(BasCompanyInformationSetVo companyInformationSetVo) throws Exception;

    /** 
    *
    * @Title: saveOrUpdate 
    * @Description: TODO() 
    * @param @param cis
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Object saveOrUpdate(BasCompanyInformationSetVo cis) throws Exception;

    /** 
    *
    * @Title: searchParameterOne 
    * @Description: TODO() 
    * @param @param cis
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json searchWorkParameter(BasCompanyInformationSetVo cis) throws Exception;
    
    /** 
    *
    * @Title: searchParameterOne 
    * @Description: TODO() 
    * @param @param cis
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public Json searchParameter(BasCompanyInformationSetVo cis) throws Exception;
}
