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
* @Title: FrtAddItmeAction.java 
* @Package com.syuesoft.bas.action 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-8-29 下午03:22:01 
* @version V1.0   
*/
package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.FrtAddItemService;
import com.syuesoft.base.vo.FrtRepairItemVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Msg;

/** 
 * @ClassName: FrtAddItmeAction 
 * @Description: TODO(加装项目) 
 * @author HeXin 
 * @date 2013-8-29 下午03:22:01 
 * @version 1.0 
 */
@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("frtAddItmeAction")
public class FrtAddItmeAction extends BaseAction implements ModelDriven<FrtRepairItemVo>
{
    private Logger logger = Logger.getLogger(this.getClass());
    private FrtRepairItemVo frtRepairItemVo = new FrtRepairItemVo();
    @Autowired
    private FrtAddItemService frtAddItemService;
   
    /**
     * 查询加装项目
     * */
    public void findAll()
    {
        try{
        	frtRepairItemVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtAddItemService.getTreegrid(frtRepairItemVo));
        }catch(Exception e){
            logger.error("查询加装项目失败！", e);
        }
    }
    
    /**
    * @Title: delete 
    * @Description: TODO(删除) 
    * @param     设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void delete(){
        Msg msg = new Msg();
        try
        {
            frtAddItemService.delete(frtRepairItemVo);
            msg.setMsg("删除成功");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("删除失败");
            logger.error("删除加装项目失败！", e);
        }
        super.writeJson(msg);
    }
    
    public void doAdd(){
        Msg msg = new Msg();
        try{
        	frtRepairItemVo.setEnterpriseId(getNowEnterpriseId());
            frtAddItemService.save(frtRepairItemVo);
            msg.setMsg("保存成功");
            msg.setSuccess(true);
        }catch(Exception e)
        {
            msg.setMsg("保存失败");
            logger.error("保存加装项目失败！", e);
        }
        super.writeJson(msg);
    }
    
    public void doUpdate(){
        Msg msg = new Msg();
        try{
            frtAddItemService.update(frtRepairItemVo);
            msg.setMsg("更新成功");
            msg.setSuccess(true);
        }
        catch(Exception e)
        {
            msg.setMsg("更新失败");
            logger.error("更新加装项目失败！", e);
        }
        super.writeJson(msg);
    }

    public FrtRepairItemVo getModel(){
        return frtRepairItemVo;
    }
    
}