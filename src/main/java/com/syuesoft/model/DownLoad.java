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
* @Title: DownLoad.java 
* @Package com.syuesoft.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-10-29 上午10:46:07 
* @version V1.0   
*/
package com.syuesoft.model;

/** 
 * @ClassName: DownLoad 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-10-29 上午10:46:07 
 * @version 1.0 
 */
public class DownLoad
{
    private Integer id;
    private String title;
    private String value;
    private Integer rows;
    private String type;
    private Integer enterpriseId;
    
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public Integer getRows()
    {
        return rows;
    }
    public void setRows(Integer rows)
    {
        this.rows = rows;
    }
    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }
    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    } 
}