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
* @Title: IControlRoleDaoImpl.java 
* @Package com.syuesoft.role.daoimpl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author HeXin  
* @date 2013-8-7 下午01:42:36 
* @version V1.0   
*/
package com.syuesoft.role.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.base.vo.BasCompanyInformationSetVo;
import com.syuesoft.model.BasCompanyInformationSet;
import com.syuesoft.model.BasDept;
import com.syuesoft.role.dao.IControlRoleDao;

/** 
 * @ClassName: IControlRoleDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author HeXin 
 * @date 2013-8-7 下午01:42:36 
 * @version 1.0 
 */
@Repository("controlRoleDaoImpl")
public class ControlRoleDaoImpl extends BaseDaoImpl<Object> implements IControlRoleDao
{
    
    /** (非 Javadoc) 
     * <p>Title: findAllCBFL</p> 
     * <p>Description: </p> 
     * @return
     * @throws Exception 
     * @see com.syuesoft.role.dao.IControlRoleDao#findAllCBFL() 
     */
     
     public List<Object[]> findCheckCBFL(String stfid) throws Exception
     {
         String sql = "SELECT STORE_ID FROM WX_STUFF_STORE WHERE STUFF_ID='"+stfid+"'";
         return this.createSQLQuery(sql);
     }
     
    /** (非 Javadoc) 
    * <p>Title: getDeptBasStuff</p> 
    * <p>Description: </p> 
    * @param deptId
    * @return
    * @throws Exception 
    * @see com.syuesoft.role.dao.IControlRoleDao#getDeptBasStuff(java.lang.String) 
    */
    
    public List<Object[]> getDeptBasStuff(Short deptId,String softType) throws Exception
    {   
        String sql = "SELECT a.STF_ID, a.STF_NAME FROM bas_stuff a, bas_users b WHERE b.STF_ID = a.STF_ID AND a.STF_NO = 0 AND b.SYSTEMID ='"+softType+"' AND a.DEPT_ID = '"+deptId+"'";
        return this.createSQLQuery(sql);
    }
    
    public List<BasDept> getTotle(String type)
    {
        String hql = "from BasDept";
        if(type != null){
            hql = " where 1=1 ";
        }
        return this.getHibernateTemplate().find(hql);
    }

    /** (非 Javadoc) 
    * <p>Title: saveOrUpdateUS</p> 
    * <p>Description: </p> 
    * @param employee
    * @param snu 
     * @throws Exception 
    * @see com.syuesoft.role.dao.IControlRoleDao#saveOrUpdateUS(java.lang.String, java.lang.String) 
    */
    
    public void saveOrUpdateUS(final List<BasCompanyInformationSetVo> ciStore) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback()
        {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                if (ciStore != null && ciStore.size() > 0)
                {
                    Connection conn = null;
                    PreparedStatement ps = null;
                    String SQL = "INSERT INTO wx_stuff_store(STUFF_ID,STORE_ID) VALUES (?,?)";
                    try
                    {
                        conn = extracted(session);
                        conn.setAutoCommit(false);
                        ps = conn.prepareStatement(SQL);
                        for (int count = 0; count < ciStore.size(); count++)
                        {
                            BasCompanyInformationSetVo enteyC = ciStore.get(count);
                            ps.setString(1, enteyC.getEmployee());
                            ps.setString(2, enteyC.getCiId());
                            ps.addBatch();
                            if (count != 0
                                    && (count % 20 == 0 || count == ciStore
                                            .size() - 1))
                            {
                                ps.executeBatch();
                                try
                                {
                                    conn.commit();
                                }
                                catch(SQLException exc)
                                {
                                    conn.rollback();
                                }
                            }
                        }
                    }
                    catch(SQLException e)
                    {
                        if (conn != null)
                            try
                            {
                                conn.close();
                            }
                            catch(SQLException esql)
                            {
                                esql.printStackTrace();
                            }
                        ;
                        e.printStackTrace();
                    } finally
                    {
                        try
                        {
                            if (ps != null)
                                ps.close();
                            if (conn != null)
                                conn.close();
                            session.close();
                        }
                        catch(SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            private Connection extracted(Session session)
            {
                return session.connection();
            }
        });
    }

    /** (非 Javadoc) 
    * <p>Title: deleteStuffStore</p> 
    * <p>Description: </p> 
    * @param sTUFFID 
     * @throws Exception 
    * @see com.syuesoft.role.dao.IControlRoleDao#deleteStuffStore(java.lang.String) 
    */
    
    public void deleteStuffStore(String STUFF_ID) throws Exception
    {
        String sql = "DELETE FROM wx_stuff_store WHERE STUFF_ID = '"+STUFF_ID+"'";
        this.createSQLQueryOutFind(sql);
    }
    
    /**
    *
    * @Title: findAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param param
    * @param @return    设定文件 
    * @return List<BasCompanyInformationSet>    返回类型 
    * @throws
     */
    public List<Object[]> findAllWork(BasCompanyInformationSetVo cis) throws Exception
    {
        String sql = "SELECT a.CONTROL_NAME, b.CONTROL_VALUE FROM wx_control a, wx_stuff_control b WHERE 1=1 AND a.CONTROL_TYPE='"+cis.getCiType()+"' AND b.STF_ID='"+cis.getEmployee()+"' AND a.CONTROL_ID = b.CONTROL_ID";
        return this.createSQLQuery(sql);
    }
    
    /**
    *
    * @Title: findAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param param
    * @param @return    设定文件 
    * @return List<BasCompanyInformationSet>    返回类型 
    * @throws
     */
    public List<Object[]> findAll(BasCompanyInformationSetVo cis) throws Exception
    {
        String sql = "SELECT CONTROL_ID, CONTROL_NAME, CONTROL_LABLE, CONTROL_PUT_TYPE, CONTROL_TYPE FROM wx_control WHERE 1=1 AND CONTROL_TYPE='"+cis.getCiType()+"'";
        return this.createSQLQuery(sql);
    }

    /** (非 Javadoc) 
    * <p>Title: deleteStuffParameter</p> 
    * <p>Description: </p> 
    * @param STUFF_ID
    * @throws Exception 
    * @see com.syuesoft.role.dao.IControlRoleDao#deleteStuffParameter(java.lang.String) 
    */
    
    public void deleteStuffParameter(String STUFF_ID) throws Exception
    {
        String sql = "DELETE FROM wx_stuff_control WHERE STF_ID = '"+STUFF_ID+"'";
        this.createSQLQueryOutFind(sql);
    }

    /** (非 Javadoc) 
    * <p>Title: saveStuffParameter</p> 
    * <p>Description: </p> 
    * @param STUFF_ID
    * @throws Exception 
    * @see com.syuesoft.role.dao.IControlRoleDao#saveStuffParameter(java.lang.String) 
    */
    
    public void saveStuffParameter(final List<BasCompanyInformationSetVo> cisVos) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback()
        {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                if (cisVos != null && cisVos.size() > 0)
                {
                    Connection conn = null;
                    PreparedStatement ps = null;
                    String SQL = "INSERT INTO wx_stuff_control (STF_ID, CONTROL_ID, CONTROL_VALUE)VALUES(?, ?, ?)";
                    try
                    {
                        conn = extracted(session);
                        conn.setAutoCommit(false);
                        ps = conn.prepareStatement(SQL);
                        for (int count = 0; count < cisVos.size(); count++)
                        {
                            BasCompanyInformationSetVo enteyC = cisVos.get(count);
                            ps.setString(1, enteyC.getEmployee());
                            ps.setString(2, enteyC.getCiId());
                            ps.setString(3, enteyC.getCiValue());
                            ps.addBatch();
                            if (count != 0
                                    && (count % 20 == 0 || count == cisVos
                                            .size() - 1))
                            {
                                ps.executeBatch();
                                try
                                {
                                    conn.commit();
                                }
                                catch(SQLException exc)
                                {
                                    conn.rollback();
                                }
                            }
                        }
                    }
                    catch(SQLException e)
                    {
                        if (conn != null)
                            try
                            {
                                conn.close();
                            }
                            catch(SQLException esql)
                            {
                                esql.printStackTrace();
                            }
                        ;
                        e.printStackTrace();
                    } finally
                    {
                        try
                        {
                            if (ps != null)
                                ps.close();
                            if (conn != null)
                                conn.close();
                            session.close();
                        }
                        catch(SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }

            private Connection extracted(Session session)
            {
                return session.connection();
            }
        });
    }
}
