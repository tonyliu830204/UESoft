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
* @Title: XsSellProjectOut.java 
* @Package com.syuesoft.sell.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-9-11 下午06:24:08 
* @version V1.0   
*/
package com.syuesoft.sell.model;

import java.io.Serializable;

/** 
 * @ClassName: XsSellProjectOut 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-9-11 下午06:24:08 
 * @version 1.0 
 */
public class XsSellProjectOut implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer outItemId;                         //加装项目编号
    private XsSellCarinstall xsSellCarinstall;             //加装序号
    private String outCode;                              //出库单号
    private Integer itemId;                                //项目名称
    private String itemName;                               //加装项目名称
    private Double itemMoney;                              //项目金额
    private Double itemCose;                               //项目成本
    private String remark;
    
    
    public Integer getOutItemId()
    {
        return outItemId;
    }
    public void setOutItemId(Integer outItemId)
    {
        this.outItemId = outItemId;
    }
    public XsSellCarinstall getXsSellCarinstall()
    {
        return xsSellCarinstall;
    }
    public void setXsSellCarinstall(XsSellCarinstall xsSellCarinstall)
    {
        this.xsSellCarinstall = xsSellCarinstall;
    }
    public String getOutCode()
    {
        return outCode;
    }
    public void setOutCode(String outCode)
    {
        this.outCode = outCode;
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
    public Double getItemMoney()
    {
        return itemMoney;
    }
    public void setItemMoney(Double itemMoney)
    {
        this.itemMoney = itemMoney;
    }
    public Double getItemCose()
    {
        return itemCose;
    }
    public void setItemCose(Double itemCose)
    {
        this.itemCose = itemCose;
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