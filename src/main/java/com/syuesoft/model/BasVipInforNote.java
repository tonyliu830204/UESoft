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
* @Title: BasVipInforNote.java 
* @Package com.syuesoft.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-12-9 下午06:11:48 
* @version V1.0   
*/
package com.syuesoft.model;

import java.util.Date;

/** 
 * @ClassName: BasVipInforNote 
 * @Description: TODO(会员操作记录) 
 * @author HeXin 
 * @date 2013-12-9 下午06:11:48 
 * @version 1.0 
 */
public class BasVipInforNote extends BaseBean{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer adjournmentId;                               //日志编号
    private BasVipInfor basVipInfor;                             //会员
    private String adjournmentManage;                            //经办人
    private Date adjournmentDate;                                //日期
    private String adjournmentType;                              //类型
    private Double adjournmentRep;                               //积分
    private String adjournmentMemo;                              //缘由
    
    public Integer getAdjournmentId()
    {
        return adjournmentId;
    }
    public void setAdjournmentId(Integer adjournmentId)
    {
        this.adjournmentId = adjournmentId;
    }
    public BasVipInfor getBasVipInfor()
    {
        return basVipInfor;
    }
    public void setBasVipInfor(BasVipInfor basVipInfor)
    {
        this.basVipInfor = basVipInfor;
    }
    public String getAdjournmentManage()
    {
        return adjournmentManage;
    }
    public void setAdjournmentManage(String adjournmentManage)
    {
        this.adjournmentManage = adjournmentManage;
    }
    public Date getAdjournmentDate()
    {
        return adjournmentDate;
    }
    public void setAdjournmentDate(Date adjournmentDate)
    {
        this.adjournmentDate = adjournmentDate;
    }
    public String getAdjournmentType()
    {
        return adjournmentType;
    }
    public void setAdjournmentType(String adjournmentType)
    {
        this.adjournmentType = adjournmentType;
    }
    public Double getAdjournmentRep()
    {
        return adjournmentRep;
    }
    public void setAdjournmentRep(Double adjournmentRep)
    {
        this.adjournmentRep = adjournmentRep;
    }
    public String getAdjournmentMemo()
    {
        return adjournmentMemo;
    }
    public void setAdjournmentMemo(String adjournmentMemo)
    {
        this.adjournmentMemo = adjournmentMemo;
    }
}