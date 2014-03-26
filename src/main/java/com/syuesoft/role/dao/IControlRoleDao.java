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
* @Title: IControlRoleDao.java 
* @Package com.syuesoft.role.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-8-7 下午01:42:06 
* @version V1.0   
*/
package com.syuesoft.role.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.model.BasDept;

/** 
 * @ClassName: IControlRoleDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yanzhangan
 * @date 2013-8-7 下午01:42:06 
 * @version 1.0 
 */
public interface IControlRoleDao extends BaseDaoI<Object>
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
    public List<Object[]> findCheckCBFL(String stfid) throws Exception;
    
    /**
    *
    * @Title: getDeptBasStuff 
    * @Description: TODO(根据部门编号查询员工) 
    * @param @param deptId
    * @param @param softType
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List<Object>    返回类型 
    * @throws
     */
   public List<Object[]> getDeptBasStuff(Short deptId, String softType) throws Exception;
   
   /**
   * 
   * @Title: getTotle 
   * @Description: TODO(查询部门信息) 
   * @param @param type
   * @param @return
   * @param @throws Exception    设定文件 
   * @return List<BasDept>    返回类型 
   * @throws
    */
   public List<BasDept> getTotle(String type) throws Exception;

    /** 
    *
    * @Title: saveOrUpdateUS 
    * @Description: TODO(保存业务员与仓别关系) 
    * @param @param employee
    * @param @param snu    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void saveOrUpdateUS(List<BasCompanyInformationSetVo> ciStore) throws Exception;

    /** 
    *
    * @Title: deleteStuffStore 
    * @Description: TODO(删除业务的仓别关系) 
    * @param @param sTUFFID    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void deleteStuffStore(String STUFF_ID) throws Exception;

    /** 
    *
    * @Title: findAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param param
    * @param @return    设定文件 
    * @return List<BasCompanyInformationSet>    返回类型 
    * @throws 
    */
    public List<Object[]> findAllWork(BasCompanyInformationSetVo cis) throws Exception;
    
    /** 
    *
    * @Title: findAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param param
    * @param @return    设定文件 
    * @return List<BasCompanyInformationSet>    返回类型 
    * @throws 
    */
    public List<Object[]> findAll(BasCompanyInformationSetVo cis) throws Exception;
    
    /**
    *
    * @Title: deleteStuffParameter 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param STUFF_ID
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void deleteStuffParameter(String STUFF_ID) throws Exception;
    
    /**
    *
    * @Title: saveStuffParameter 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param STUFF_ID
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void saveStuffParameter(List<BasCompanyInformationSetVo> cisVos) throws Exception;
}
