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
* @Title: FrtAddItem.java 
* @Package com.syuesoft.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-8-29 下午02:59:47 
* @version V1.0   
*/
package com.syuesoft.model;

/** 
 * @ClassName: FrtAddItem 
 * @Description: TODO(加装项目) 
 * @author HeXin 
 * @date 2013-8-29 下午02:59:47 
 * @version 1.0 
 */
public class FrtAddItem extends BaseBean
{
    private static final long serialVersionUID = 1L;
    private Integer itemId;
    private String itemName;
    private Double itemCost;
    private Double itemMoney;
    private String remark;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public Integer getItemId()
    {
        return itemId;
    }
    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }
    public String getItemName()
    {
        return itemName;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    public Double getItemCost()
    {
        return itemCost;
    }
    public void setItemCost(Double itemCost)
    {
        this.itemCost = itemCost;
    }
    public Double getItemMoney()
    {
        return itemMoney;
    }
    public void setItemMoney(Double itemMoney)
    {
        this.itemMoney = itemMoney;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}