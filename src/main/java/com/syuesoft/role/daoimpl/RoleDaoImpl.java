package com.syuesoft.role.daoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.base.vo.RoleVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasRoleInfo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.role.dao.IRoleDao;

/**
 * 角色管理
 * 
 * @author HeXin
 * 
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<BasRoleInfo> implements IRoleDao
{

    public List findnoselected(RoleVo roleVo, BasUsers user) throws Exception
    {
        String hql = "";
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql = "SELECT stuff.STF_ID AS STF_ID,stuff.STF_NAME AS STF_NAME FROM bas_stuff stuff, bas_users WHERE 1=1 AND bas_users.STF_ID = stuff.STF_ID AND stuff.STF_ZXQK = '"+ Contstants.ZXQKF.INSERVICE
                    + "' AND stuff.STF_ID NOT IN(SELECT user_role.USER_ID FROM bas_user_role user_role WHERE 1=1 AND user_role.ROLE_ID='"+ roleVo.getRoleId() + "')";
            if(roleVo.getSystemTypekey() != null && !roleVo.getSystemTypekey().equals("") && !roleVo.getSystemTypekey().equals(Contstants.SYSTEMTYPE.ALL)){
               hql +=  " AND bas_users.SYSTEMID ='"+ roleVo.getSystemTypekey()+"'";
            }
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql = "SELECT stuff.STF_ID AS STF_ID,stuff.STF_NAME AS STF_NAME FROM bas_stuff stuff, bas_users WHERE 1=1 AND bas_users.SYSTEMID ='"+ user.getSystemId()
                    + "' AND bas_users.STF_ID = stuff.STF_ID AND stuff.STF_ZXQK = '"+ Contstants.ZXQKF.INSERVICE
                    + "' AND stuff.STF_ID NOT IN(SELECT user_role.USER_ID FROM bas_user_role user_role WHERE 1=1 AND user_role.ROLE_ID='"+ roleVo.getRoleId() + "')";
        }else{
            hql = "SELECT stuff.STF_ID AS STF_ID,stuff.STF_NAME AS STF_NAME FROM bas_stuff stuff, bas_users WHERE 1=1 AND bas_users.STF_ID = stuff.STF_ID AND stuff.STF_ZXQK = '" + Contstants.ZXQKF.INSERVICE
                + "' AND stuff.STF_ID NOT IN(SELECT user_role.USER_ID FROM bas_user_role user_role WHERE 1=1 AND user_role.ROLE_ID='"+ roleVo.getRoleId() + "')";
            if(roleVo.getSystemTypekey() != null && !roleVo.getSystemTypekey().equals("") && !roleVo.getSystemTypekey().equals(Contstants.SYSTEMTYPE.ALL)){
                hql +=  " AND bas_users.SYSTEMID ='"+ roleVo.getSystemTypekey()+"'";
            }
        }
        if(roleVo.getEnterpriseId() != null && !"".equals(roleVo.getEnterpriseId())){
            hql +=  " AND stuff.enterprise_id = "+roleVo.getEnterpriseId();
            hql +=  " UNION ";
            hql +=  " SELECT stuff.STF_ID,stuff.STF_NAME FROM bas_stuff stuff,bas_users WHERE stuff.enterprise_id IN( SELECT ei.enterprise_id FROM enterprise_info ei WHERE ei.parentEnterprise_id = "+roleVo.getEnterpriseId()+") AND bas_users.LEVAL_ = 'enterprise-admin' AND stuff.STF_ID = bas_users.STF_ID";
            hql += " AND stuff.STF_ID NOT IN(SELECT user_role.USER_ID FROM bas_user_role user_role WHERE 1=1 AND user_role.ROLE_ID='"
            + roleVo.getRoleId() + "')";
        }
        return createSQLQuery(hql);
    }

    public List findselected(RoleVo roleVo, BasUsers user) throws Exception
    {
        String hql = "";
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql = "SELECT stuff.STF_ID AS STF_ID,stuff.STF_NAME AS STF_NAME FROM bas_stuff stuff, bas_users WHERE 1=1 AND bas_users.STF_ID = stuff.STF_ID AND stuff.STF_ZXQK = '"+ Contstants.ZXQKF.INSERVICE
                  + "' AND stuff.STF_ID IN(SELECT user_role.USER_ID FROM bas_user_role user_role WHERE 1=1 AND user_role.ROLE_ID='"+ roleVo.getRoleId() + "')";
            if(roleVo.getSystemTypekey() != null && !roleVo.getSystemTypekey().equals(Contstants.SYSTEMTYPE.ALL)){
                hql +=  " AND bas_users.SYSTEMID ='" + roleVo.getSystemTypekey() + "'";
            }
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql = "SELECT stuff.STF_ID AS STF_ID,stuff.STF_NAME AS STF_NAME FROM bas_stuff stuff, bas_users WHERE 1=1 AND bas_users.SYSTEMID ='"+ user.getSystemId()
                  + "' AND bas_users.STF_ID = stuff.STF_ID AND stuff.STF_ZXQK = '"+ Contstants.ZXQKF.INSERVICE 
                  + "' AND stuff.STF_ID IN(SELECT user_role.USER_ID FROM bas_user_role user_role WHERE 1=1 AND user_role.ROLE_ID='"+ roleVo.getRoleId() + "')";
        }else{
            hql = "SELECT stuff.STF_ID AS STF_ID,stuff.STF_NAME AS STF_NAME FROM bas_stuff stuff, bas_users WHERE 1=1 AND bas_users.STF_ID = stuff.STF_ID AND stuff.STF_ZXQK = '"+ Contstants.ZXQKF.INSERVICE
                + "' AND stuff.STF_ID IN(SELECT user_role.USER_ID FROM bas_user_role user_role WHERE 1=1 AND user_role.ROLE_ID='"+ roleVo.getRoleId() + "')";
            if(roleVo.getSystemTypekey() != null && !roleVo.getSystemTypekey().equals("")){
                hql +=  " AND bas_users.SYSTEMID ='" + roleVo.getSystemTypekey() + "'";
            }
        }
        return createSQLQuery(hql);
    }

    public boolean getMenutoRole(Long menuId, Long roleId) throws Exception
    {
        SQLQuery query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(
                        "SELECT COUNT(role_id) FROM bas_menu_role WHERE menu_id ="+ menuId + " AND role_id = " + roleId);
        List list = query.list();
        return (list != null || list.size() > 0) ? Integer.parseInt(list.get(0).toString()) == 0 ? false : true : false;
    }

    public Long saveOrUpdateToId(BasRoleInfo basRoleInfo) throws Exception
    {
        saveOrUpdate(basRoleInfo);
        return basRoleInfo.getRoleId();
    }

    public void savaMenuRole(Long menu_id, Long role_id) throws Exception
    {
        executeSQL("INSERT INTO bas_menu_role (menu_id, role_id) VALUES ("+ menu_id + ", " + role_id + ")");
    }

    public void savaUserRole(Long USER_ID, Long ROLE_ID) throws Exception
    {
        executeSQL("INSERT INTO bas_user_role (USER_ID, ROLE_ID) VALUES ("+ USER_ID + ", " + ROLE_ID + ")");
    }

    public void deleteMenuRoleByRoleId(Long roleId) throws Exception
    {
        executeSQL("DELETE FROM bas_menu_role WHERE role_id =" + roleId);
    }

    public void deleteUserRoleByRoleId(Long ROLE_ID) throws Exception
    {
        executeSQL("DELETE FROM bas_user_role WHERE ROLE_ID =" + ROLE_ID);
    }

    public void deleteMenuRoleByMenuId(Long menuId) throws Exception
    {
        executeSQL("DELETE FROM bas_menu_role WHERE menu_id =" + menuId);
    }

    public void deleteUserRoleByUserId(Long userId) throws Exception
    {
        executeSQL("DELETE FROM bas_user_role WHERE USER_ID =" + userId);
    }

    public void deleteRole(BasRoleInfo role) throws Exception
    {
        delete(role);
    }

    public List<Object[]> getRoleMenu(Long menuId, Long roleId)
            throws Exception
    {
        List<Object[]> list = null;
        if (menuId != null)
        {
            list = this.getHibernateTemplate().getSessionFactory()
                    .getCurrentSession().createSQLQuery(
                            "SELECT menu_id, role_id FROM bas_menu_role WHERE 1=1 and menu_id="
                                    + menuId).list();
        }
        else if (roleId != null)
        {
            list = this.getHibernateTemplate().getSessionFactory()
                    .getCurrentSession().createSQLQuery(
                            "SELECT menu_id, role_id FROM bas_menu_role WHERE 1=1 and role_id="
                                    + roleId).list();
        }
        else
        {
            list = this
                    .getHibernateTemplate()
                    .getSessionFactory()
                    .getCurrentSession()
                    .createSQLQuery(
                            "SELECT menu_id, role_id FROM bas_menu_role WHERE 1=1")
                    .list();
        }
        return list;
    }
    
    
	public List<Object[]> getRoleByUserId(Long stfId)
			throws Exception {
		return this.getHibernateTemplate().getSessionFactory()
        .getCurrentSession().createSQLQuery(
                "SELECT bur.USER_ID, bur.ROLE_ID FROM bas_user_role bur inner join bas_role br WHERE br.role_id=bur.role_id and bur.USER_ID=" + stfId).list();
	}

    public List<Object[]> getMenuByRoleId(Long roleId) throws Exception
    {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT menu.`menu_id`,menu.`menu_name`,menu.`menu_code`,menu.`menu_pid`,menu.`url`,menu.`caeateTime`,menu.`person`,menu.`childMenu`,menu.`systemMenu`,menu.`remark` FROM bas_menu_role role, bas_menu_info menu WHERE 1=1 AND role.menu_id = menu.menu_id AND role.role_id = "
                                + roleId).list();
    }
    public List getMenuCodeByRoleId(Long roleId) throws Exception{
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT menu.`menu_code` FROM bas_menu_role role, bas_menu_info menu WHERE 1=1 AND role.menu_id = menu.menu_id AND role.role_id = "
                                + roleId).list();
    }

    
    public List<Object[]> getMenuRoleType(Long menuId) throws Exception
    {
        List<Object[]> list = null;
        if (menuId != null)
        {
            list = this.getHibernateTemplate().getSessionFactory()
                    .getCurrentSession().createSQLQuery(
                            "SELECT role_id FROM bas_menu_role WHERE menu_id = "
                                    + menuId + " GROUP BY role_id").list();
        }
        else
        {
            list = this
                    .getHibernateTemplate()
                    .getSessionFactory()
                    .getCurrentSession()
                    .createSQLQuery(
                            "SELECT role_id FROM bas_menu_role GROUP BY role_id")
                    .list();
        }
        return list;
    }
    /**
     * 指定用户角色关系是否存在
     * */
	
	public boolean isExistsRoleAndUser(Long roleId, Long userId)
			throws Exception {
		 List list = this.getHibernateTemplate().getSessionFactory().getCurrentSession()
         .createSQLQuery("SELECT * FROM bas_user_role WHERE ROLE_ID="+roleId+" and USER_ID="+userId).list();
		 if(list!=null&&list.size()>0)
			 return true;
		return false;
	}
	/**
	 * 角色菜单关系批量更新
	 * */
	
	public boolean updateOfBatch(String[] selecteds,String roleId) throws Exception {
		Connection con=this.getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
		Statement st=con.createStatement();
		if(selecteds!=null&&selecteds.length>0){
			for (int count = 0; count < selecteds.length; count++){
	           st.addBatch("INSERT INTO bas_menu_role (menu_id, role_id) VALUES ("+ selecteds[count] + ", " + roleId+")");
	           if (count % 50 == 0 || count == selecteds.length - 1){
                   st.executeBatch();
               }
	        }
		}
		return true;
	}
	/**
     * 用户菜单关系批量更新
     * @param checkeds
     * @param stfId
     * @param enterpriseId
     * return boolean 
     * */
	
	public boolean updateOfBatch(String[] checkeds, String stfId,String enterpriseId) throws Exception {
		Connection con=this.getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
		Statement st=con.createStatement();
		if(checkeds!=null&&checkeds.length>0){
			for (int count = 0; count < checkeds.length; count++){
	           st.addBatch("INSERT INTO bas_menu_stuff (menu_id, stf_id,enterprise_id) VALUES ("+ checkeds[count] + ", " + stfId+","+enterpriseId+")");
	           if (count % 50 == 0 || count == checkeds.length - 1){
                   st.executeBatch();
               }
	        }
		}
		return true;
	}
	/**
     * 用户菜单关系批量删除
     * @param stfId
     * @param enterpriseId
     * return boolean 
     * */
	
	public boolean deleteOfBatch(String stfId, String enterpriseId)
			throws Exception {
		this.executeSQL("delete from bas_menu_stuff where enterprise_id="+enterpriseId+" and stf_id="+stfId);
		return true;
	}

	/**
	 * 用户角色管理批量更新
	 * */
	
	public boolean updateOfBatchRoleAndUser(String[] selecteds, String roleId)
			throws Exception {
		Connection con=this.getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
		Statement st=con.createStatement();
		int sum=0;
		if(selecteds!=null&&selecteds.length>0){
			for (int count = 0; count < selecteds.length; count++){
	           st.addBatch("INSERT INTO bas_user_role (user_id, role_id) VALUES ("+ selecteds[count] + ", " + roleId+")");
	           if (count % 20 == 0 || count == selecteds.length - 1){
                   st.executeBatch();
               }
	        }
		}
		return true;
	}
}