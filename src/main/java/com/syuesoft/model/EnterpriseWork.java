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
* @Title: EnterpriseWork.java 
* @Package com.syuesoft.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-9-6 下午04:52:28 
* @version V1.0   
*/
package com.syuesoft.model;

import java.io.Serializable;

/** 
 * @ClassName: EnterpriseWork 
 * @Description: TODO(企业业务信息) 
 * @author HeXin 
 * @date 2013-9-6 下午04:52:28 
 * @version 1.0 
 */
public class EnterpriseWork extends BaseBean implements Serializable{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer workId;                     //企业业务序号
    private Integer enterpriseId;               //企业信息
    private String enterpriseGjFootnote;        //估价单注脚
    private String enterpriseJSISONO;           //派工单ISO编号
    private String enterprisePgHead;            //派工单抬头
    private String enterpriseSpFootnot;         //索赔单注脚
    private String enterpriseJcFootnote;        //接车单注脚
    private String enterpriseJsHead;            //结算单抬头
    private Integer enterpriseLoginLimit;       //最大登录上限
    private Integer enterpriseSMSLimit;         //可用短信条数
    private String outboundnumber;              //出库单ISO编号
    private String enterprisePorint;            //网点编号
    private String enterpriseRemark;            //车档案备注格式
    private String enterprisePath;              //最新文件目录
    private String enterpriseJsFootnote;        //结算单注脚
    
    public Integer getWorkId()
    {
        return workId;
    }
    public void setWorkId(Integer workId)
    {
        this.workId = workId;
    }
    
    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }
    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }
    public String getEnterpriseGjFootnote()
    {
        return enterpriseGjFootnote;
    }
    public void setEnterpriseGjFootnote(String enterpriseGjFootnote)
    {
        this.enterpriseGjFootnote = enterpriseGjFootnote;
    }
    public String getEnterpriseJSISONO()
    {
        return enterpriseJSISONO;
    }
    public void setEnterpriseJSISONO(String enterpriseJSISONO)
    {
        this.enterpriseJSISONO = enterpriseJSISONO;
    }
    public String getEnterprisePgHead()
    {
        return enterprisePgHead;
    }
    public void setEnterprisePgHead(String enterprisePgHead)
    {
        this.enterprisePgHead = enterprisePgHead;
    }
    public String getEnterpriseSpFootnot()
    {
        return enterpriseSpFootnot;
    }
    public void setEnterpriseSpFootnot(String enterpriseSpFootnot)
    {
        this.enterpriseSpFootnot = enterpriseSpFootnot;
    }
    public String getEnterpriseJcFootnote()
    {
        return enterpriseJcFootnote;
    }
    public void setEnterpriseJcFootnote(String enterpriseJcFootnote)
    {
        this.enterpriseJcFootnote = enterpriseJcFootnote;
    }
    public String getEnterpriseJsHead()
    {
        return enterpriseJsHead;
    }
    public void setEnterpriseJsHead(String enterpriseJsHead)
    {
        this.enterpriseJsHead = enterpriseJsHead;
    }
    public Integer getEnterpriseLoginLimit()
    {
        return enterpriseLoginLimit;
    }
    public void setEnterpriseLoginLimit(Integer enterpriseLoginLimit)
    {
        this.enterpriseLoginLimit = enterpriseLoginLimit;
    }
    public Integer getEnterpriseSMSLimit()
    {
        return enterpriseSMSLimit;
    }
    public void setEnterpriseSMSLimit(Integer enterpriseSMSLimit)
    {
        this.enterpriseSMSLimit = enterpriseSMSLimit;
    }
    public String getOutboundnumber()
    {
        return outboundnumber;
    }
    public void setOutboundnumber(String outboundnumber)
    {
        this.outboundnumber = outboundnumber;
    }
    public String getEnterprisePorint()
    {
        return enterprisePorint;
    }
    public void setEnterprisePorint(String enterprisePorint)
    {
        this.enterprisePorint = enterprisePorint;
    }
    public String getEnterpriseRemark()
    {
        return enterpriseRemark;
    }
    public void setEnterpriseRemark(String enterpriseRemark)
    {
        this.enterpriseRemark = enterpriseRemark;
    }
    public String getEnterprisePath()
    {
        return enterprisePath;
    }
    public void setEnterprisePath(String enterprisePath)
    {
        this.enterprisePath = enterprisePath;
    }
    public String getEnterpriseJsFootnote()
    {
        return enterpriseJsFootnote;
    }
    public void setEnterpriseJsFootnote(String enterpriseJsFootnote)
    {
        this.enterpriseJsFootnote = enterpriseJsFootnote;
    }
}