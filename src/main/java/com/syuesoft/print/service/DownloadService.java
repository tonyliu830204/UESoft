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
* @Title: DownloadService.java 
* @Package com.syuesoft.print.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-10-29 上午10:42:34 
* @version V1.0   
*/
package com.syuesoft.print.service;

import java.util.List;

import com.syuesoft.model.BasUsers;

/** 
 * @ClassName: DownloadService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-10-29 上午10:42:34 
 * @version 1.0 
 */
public interface DownloadService
{
    /**
    * @Title: saveDownLoad 
    * @Description: TODO(将解析的excel保存到临时解析表) 
    * @param @param list
    * @param @throws Exception    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void saveDownLoad(String jsonStr, String type, BasUsers user) throws Exception;
    
    /**
    * @Title: findDownLoad 
    * @Description: TODO(根据类型查询解析值) 
    * @param @param type
    * @param @return
    * @param @throws Exception    设定文件 
    * @return List    返回类型 
    * @throws
     */
    public List<Object> findDownLoad(String type, Object bean, BasUsers user) throws Exception;
}
