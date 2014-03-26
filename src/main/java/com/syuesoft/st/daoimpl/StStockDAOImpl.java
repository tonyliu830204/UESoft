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
* @Title: StStockDAOImpl.java 
* @Package com.syuesoft.st.daoimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-9-27 下午05:03:21 
* @version V1.0   
*/
package com.syuesoft.st.daoimpl;

import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.st.dao.StStockDAO;

/** 
 * @ClassName: StStockDAOImpl 
 * @Description: TODO(进销存) 
 * @author HeXin 
 * @date 2013-9-27 下午05:03:21 
 * @version 1.0 
 */

@Repository("stStockDAOImpl")
public class StStockDAOImpl extends BaseDaoImpl<Object> implements StStockDAO
{

}
