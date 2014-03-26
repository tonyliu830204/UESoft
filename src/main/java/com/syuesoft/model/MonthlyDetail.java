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
* @Title: MonthlyDetail.java 
* @Package com.syuesoft.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-10-14 下午04:47:39 
* @version V1.0   
*/
package com.syuesoft.model;

/** 
 * @ClassName: MonthlyDetail 
 * @Description: TODO(月结明细) 
 * @author HeXin 
 * @date 2013-10-14 下午04:47:39 
 * @version 1.0 
 */
public class MonthlyDetail implements java.io.Serializable 
{
    private Integer indexId;                        //编号
    private MonthlyStatement monthlyStatement;      //月结编号
    private String partsId;                         //配件编码
    private double stinvdCount;                     //个数
    private double stinvdPrice;                     //未税价格
    private double stinvdCost;                      //未税金额
    private double stinvdPrice1;                    //含税价格
    private double stinvdCost1;                     //含税金额
    private String storeId;                          //仓别
    public Integer getIndexId()
    {
        return indexId;
    }
    public void setIndexId(Integer indexId)
    {
        this.indexId = indexId;
    }
    public MonthlyStatement getMonthlyStatement()
    {
        return monthlyStatement;
    }
    public void setMonthlyStatement(MonthlyStatement monthlyStatement)
    {
        this.monthlyStatement = monthlyStatement;
    }
    public String getPartsId()
    {
        return partsId;
    }
    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }
    public double getStinvdCount()
    {
        return stinvdCount;
    }
    public void setStinvdCount(double stinvdCount)
    {
        this.stinvdCount = stinvdCount;
    }
    public double getStinvdPrice()
    {
        return stinvdPrice;
    }
    public void setStinvdPrice(double stinvdPrice)
    {
        this.stinvdPrice = stinvdPrice;
    }
    public double getStinvdCost()
    {
        return stinvdCost;
    }
    public void setStinvdCost(double stinvdCost)
    {
        this.stinvdCost = stinvdCost;
    }
    public double getStinvdPrice1()
    {
        return stinvdPrice1;
    }
    public void setStinvdPrice1(double stinvdPrice1)
    {
        this.stinvdPrice1 = stinvdPrice1;
    }
    public double getStinvdCost1()
    {
        return stinvdCost1;
    }
    public void setStinvdCost1(double stinvdCost1)
    {
        this.stinvdCost1 = stinvdCost1;
    }
    public String getStoreId()
    {
        return storeId;
    }
    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }
}