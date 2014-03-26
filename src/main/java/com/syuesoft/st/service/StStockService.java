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
* @Title: StStockService.java 
* @Package com.syuesoft.st.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-9-27 下午05:00:29 
* @version V1.0   
*/
package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.st.vo.StockPartsVo;
import com.syuesoft.util.Json;

/** 
 * @ClassName: StStockService 
 * @Description: TODO(进销存) 
 * @author HeXin 
 * @date 2013-9-27 下午05:00:29 
 * @version 1.0 
 */
public interface StStockService
{
    
    public Json loadStockSummary(StockPartsVo stockPartsVo) throws Exception;
    /** 
    *
    * @Title: loadStock 
    * @Description: TODO(查询进销存) 
    * @param @param stockPartsVo
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    public List<StockPartsVo> loadStock(StockPartsVo stockPartsVo) throws Exception;

}
