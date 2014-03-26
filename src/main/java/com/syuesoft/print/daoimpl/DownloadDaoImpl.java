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
* @Title: DownloadDaoImpl.java 
* @Package com.syuesoft.print.daoimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-10-29 上午10:44:58 
* @version V1.0   
*/
package com.syuesoft.print.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.print.dao.DownloadDao;
import com.syuesoft.model.DownLoad;

/** 
 * @ClassName: DownloadDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-10-29 上午10:44:58 
 * @version 1.0 
 */
@Repository("downloadDaoImpl")
public class DownloadDaoImpl extends BaseDaoImpl<DownLoad> implements DownloadDao
{

}
