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
* @Title: ControlRoleServiceImpl.java 
* @Package com.syuesoft.role.serviceimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-8-7 下午12:05:11 
* @version V1.0   
*/
package com.syuesoft.role.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasDept;
import com.syuesoft.role.dao.IControlRoleDao;
import com.syuesoft.role.service.IControlRoleService;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import com.syuesoft.util.TreeJson;

/** 
 * @ClassName: ControlRoleServiceImpl 
 * @Description: TODO(业务权限控制) 
 * @author yanzhangan
 * @date 2013-8-7 下午12:05:11 
 * @version 1.0 
 */
@Repository("controlRoleServiceImpl")
public class ControlRoleServiceImpl extends BaseLogServiceImpl implements IControlRoleService
{
    @Autowired
    private IControlRoleDao controlRoleDao;
    
 // 仓别分类
    public List<String> findCheckCBFL(BasCompanyInformationSetVo companyInformationSetVo) throws Exception
    {
        List<String> list = null;
        List<Object[]> bsh = controlRoleDao.findCheckCBFL(companyInformationSetVo.getEmployee());
        if(bsh != null && bsh.size() > 0){
            list = new ArrayList<String>();
            for (Object objs : bsh)
            {
                list.add(objs != null ? String.valueOf(objs) : null);
            }
        }
        return list;
    }
    
    public  List<TreeJson> getBusinessUser(BasCompanyInformationSetVo companyInformationSetVo) throws Exception{
        List<TreeJson> list = null;
        //查询部门列表
        List<BasDept> depts = controlRoleDao.getTotle(companyInformationSetVo.getCiType());
        if(depts != null && depts.size() > 0){
            for(BasDept dept : depts){
                if(list == null){
                    list = new ArrayList<TreeJson>();
                }
                TreeJson tree = new TreeJson();
                tree.setId(String.valueOf(dept.getDeptId()));
                tree.setText(dept.getDeptName());
                //tree.setChecked(false);
                getBusinessUserToDept(tree, dept, companyInformationSetVo.getSoftType());
                list.add(tree);
            }
        }
        return list;
    }
    
    private void getBusinessUserToDept(TreeJson tree, BasDept dept, String softType) throws Exception{
        List<Object[]> list = controlRoleDao.getDeptBasStuff(dept.getDeptId(), softType);
        if(list != null && list.size() > 0){
            List<TreeJson> rows = null;
            for (Object[] objs : list)
            {
                if(rows == null){
                    rows = new ArrayList<TreeJson>();
                }
                TreeJson tree_ = new TreeJson();
                String stfid = objs[0] != null ? String.valueOf(objs[0]) : null;
                tree_.setId(stfid);
                tree_.setText(objs[1] != null ? String.valueOf(objs[1]) : null);
                //tree_.setChecked(false);
                rows.add(tree_);
            }
            tree.setChildren(rows);
            tree.setState("closed");
        }
    }

    /** (非 Javadoc) 
    * <p>Title: saveOrUpdate</p> 
    * <p>Description: </p> 
    * @param cis
    * @return 
     * @throws Exception 
    * @see com.syuesoft.role.service.IControlRoleService#saveOrUpdate(com.syuesoft.base.vo.BasCompanyInformationSetVo) 
    */
    
    public Msg saveOrUpdate(BasCompanyInformationSetVo cisVo) throws Exception
    {
        Msg msg = new Msg();
        /**
         * 保存业务员与仓别关系
         */
        String STUFF_ID = cisVo.getEmployee();
        String storeHouse = cisVo.getStoreHouse();
        List<BasCompanyInformationSetVo> ciStore = new ArrayList<BasCompanyInformationSetVo>();
        if(STUFF_ID != null && !STUFF_ID.equals("")){
            if(storeHouse != null && !storeHouse.equals("")){
                String[] storeHouses = storeHouse.split(",");
                for(String STORE_ID : storeHouses){
                    BasCompanyInformationSetVo cis = new BasCompanyInformationSetVo();
                    cis.setCiId(STORE_ID);
                    cis.setEmployee(STUFF_ID);
                    ciStore.add(cis);
                }
            }
        }
        /**
         * 保存业务控制权限
         */
        
        List<BasCompanyInformationSetVo> cisVos = new ArrayList<BasCompanyInformationSetVo>();
        String[] checkCiIds = cisVo.getCiCheckCiIds();
        String[] checkNames = cisVo.getCiCheckNames();
        String[] checkValues = cisVo.getCiCheckValues();

        String[] ciNames = cisVo.getCiNames();
        String[] ciCiIds = cisVo.getCiCiIds();
        String[] ciValues = cisVo.getCiValues();
        
        if (checkNames != null && !checkNames.equals("") && (checkNames.length > 0 || checkValues.length > 0))
        {
            List<String> list = null;
            if (checkValues.length > 0)
            {
                list = new ArrayList<String>();
                for (int j = 0; j < checkValues.length; j++)
                {
                    list.add(checkValues[j]);
                }
            }
            for (int i = 0; i < checkNames.length; i++)
            {
                if(list.contains(checkNames[i])){
                    BasCompanyInformationSetVo cis = new BasCompanyInformationSetVo();
                    cis.setEmployee(STUFF_ID);
                    cis.setCiId(checkCiIds[i]);
                    cis.setCiName(checkNames[i]);
                    cis.setCiValue("checked");
                    cisVos.add(cis);
                }
            }
        }
        if (ciNames != null && (ciNames.length > 0 || ciCiIds.length > 0 || ciValues.length > 0))
        {
            for (int i = 0; i < ciNames.length; i++)
            {
                if(!ciValues[i].equals("0") && !ciValues[i].equals("")){
                    BasCompanyInformationSetVo cis = new BasCompanyInformationSetVo();
                    cis.setEmployee(STUFF_ID);
                    cis.setCiId(ciCiIds[i]);
                    cis.setCiName(ciNames[i]);
                    cis.setCiValue(!"".equals(ciValues[i]) ? ciValues[i] : null);
                    cisVos.add(cis);
                }
            }
        }
        controlRoleDao.deleteStuffStore(STUFF_ID);
        controlRoleDao.saveOrUpdateUS(ciStore);
        controlRoleDao.deleteStuffParameter(STUFF_ID);
        controlRoleDao.saveStuffParameter(cisVos);
        
        msg.setMsg("保存成功");
        msg.setSuccess(true);
        return msg;
    }
    
    /**
     * @throws Exception 
    *
    * @Title: searchParameterOne 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param cis
    * @param @return    设定文件 
    * @return Json    返回类型 
    * @throws
     */
    public Json searchWorkParameter(BasCompanyInformationSetVo cis) throws Exception
    {
        Json json = null;
        List<Object[]> list = controlRoleDao.findAllWork(cis);
        List<BasCompanyInformationSetVo> list_ = null;
        if(list != null && list.size() > 0){
            list_ = new ArrayList<BasCompanyInformationSetVo>();
            for (Object[] objs : list)
            {
                BasCompanyInformationSetVo vo = new BasCompanyInformationSetVo();
                vo.setCiName(objs[0] != null ? objs[0].toString() : null);
                vo.setCiValue(objs[1] != null ? objs[1].toString() : null);
                list_.add(vo);
            }
            json = new Json();
            json.setTotal(list != null ? list.size() : 0);
            json.setRows(list_);
        }
        return json;
    }
    
    /**
    *
    * @Title: searchParameterOne 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param cis
    * @param @return    设定文件 
    * @return Json    返回类型 
    * @throws
     */
    public Json searchParameter(BasCompanyInformationSetVo cis) throws Exception
    {
        Json json = null;
        List<Object[]> list = controlRoleDao.findAll(cis);
        List<BasCompanyInformationSetVo> list_ = null;
        if(list != null && list.size() > 0){
            list_ = new ArrayList<BasCompanyInformationSetVo>();
            for (Object[] objs : list)
            {
                BasCompanyInformationSetVo vo = new BasCompanyInformationSetVo();
                vo.setCiId(objs[0] != null ? objs[0].toString() : null);
                vo.setCiName(objs[1] != null ? objs[1].toString() : null);
                vo.setCiLable(objs[2] != null ? objs[2].toString() : null);
                vo.setCiPutType(objs[3] != null ? objs[3].toString() : null);
                vo.setCiType(objs[4] != null ? objs[4].toString() : null);
                list_.add(vo);
            }
            json = new Json();
            json.setTotal(list != null ? list.size() : 0);
            json.setRows(list_);
        }
        return json;
    }
}