package com.syuesoft.role.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.base.vo.RoleVo;
import com.syuesoft.model.BasRoleInfo;
import com.syuesoft.model.BasUsers;

public interface IRoleDao extends BaseDaoI<BasRoleInfo>
{
    /**
     * 查询没有选择的人员
     * 
     * @param roleVo
     * @param user
     * @return
     */
    public List findnoselected(RoleVo roleVo, BasUsers user) throws Exception;

    /**
     * 查询已经选择的人员
     * 
     * @param roleVo
     * @param user
     * @return
     */
    public List findselected(RoleVo roleVo, BasUsers user) throws Exception;

    /**
     * 确认此角色是否包含菜单
     * 
     * @param menuId
     * @param roleId
     * @return
     */
    public boolean getMenutoRole(Long menuId, Long roleId) throws Exception;

    /**
     * 保存、更新返回菜单Id
     * 
     * @param basRoleInfo
     * @return
     */
    public Long saveOrUpdateToId(BasRoleInfo basRoleInfo) throws Exception; // 新增或更新

    /**
     * 保存角色菜单关系
     * 
     * @param menu_id
     * @param role_id
     */
    public void savaMenuRole(Long menu_id, Long role_id) throws Exception;

    /**
     * 保存人员菜单关系
     * 
     * @param USER_ID
     * @param ROLE_ID
     */
    public void savaUserRole(Long USER_ID, Long ROLE_ID) throws Exception;

    /**
     * 根据角色编号删除菜单角色关系
     * 
     * @param role_id
     */
    public void deleteMenuRoleByRoleId(Long role_id) throws Exception;

    /**
     * 根据菜单编号删除角色菜单关系
     * 
     * @param menu_id
     */
    public void deleteMenuRoleByMenuId(Long menu_id) throws Exception;

    /**
     * 根据角色编号删除用户角色关系
     * 
     * @param ROLE_ID
     */
    public void deleteUserRoleByRoleId(Long ROLE_ID) throws Exception;

    /**
     * 根据用户编号删除用户角色关系
     * 
     * @param user_id
     */
    public void deleteUserRoleByUserId(Long user_id) throws Exception;

    /**
     * 删除角色对象
     * 
     * @param role
     */
    public void deleteRole(BasRoleInfo role) throws Exception;

    /**
     * 查询有几种菜单种类
     * 
     * @param roleId
     * @return
     */
    public List<Object[]> getMenuRoleType(Long menuId) throws Exception;

    /**
     * 查询菜单被那些角色引用
     * 
     * @param menuId
     * @return
     */
    public List<Object[]> getRoleMenu(Long menuId, Long roleId)
            throws Exception;

    /**
     * 查询用户有哪些菜单权限
     * 
     * @param roleId
     * @return
     */
    public List<Object[]> getMenuByRoleId(Long roleId) throws Exception;
    /**
     * 查询用户有哪些菜单权限Code
     * 
     * @param roleId
     * @return
     */
    public List getMenuCodeByRoleId(Long roleId) throws Exception;

    /**
     * 查询用户被那些角色引用
     * 
     * @param menuId
     * @return
     */
    public List<Object[]> getRoleByUserId(Long stfId) throws Exception;
    /**
     * 指定用户菜单关系是否存在
     * 
     * @param roleId
     * @param userId
     * @return
     */
    public boolean isExistsRoleAndUser(Long roleId, Long userId) throws Exception;
    /**
     * 角色菜单关系批量更新
     * @param selecteds
     * @param roleId
     * return boolean 
     * */
    public boolean updateOfBatch(String[] selecteds,String roleId)throws Exception;
    /**
     * 用户菜单关系批量更新
     * @param checkeds
     * @param stfId
     * @param enterpriseId
     * return boolean 
     * */
    public boolean updateOfBatch(String[] checkeds,String stfId,String enterpriseId)throws Exception;
    /**
     * 用户菜单关系批量删除
     * @param stfId
     * @param enterpriseId
     * return boolean 
     * */
    public boolean deleteOfBatch(String stfId,String enterpriseId)throws Exception;
    /**
     * 用户角色关系批量更新
     * @param selecteds
     * @param roleId
     * return boolean 
     * */
    public boolean updateOfBatchRoleAndUser(String[] selecteds,String roleId)throws Exception;
}