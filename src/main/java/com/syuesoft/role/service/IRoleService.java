package com.syuesoft.role.service;

import java.util.List;
import java.util.Set;
import com.syuesoft.base.vo.MenuVo;
import com.syuesoft.base.vo.RoleVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;
import com.syuesoft.util.TreeJson;

public interface IRoleService
{

    /**
     * 查询角色列表
     * @param user
     * @return
     * @throws Exception
     */
    public Datagrid getDatagrid(RoleVo roleVo, BasUsers user) throws Exception;

    /**
     * 删除角色
     * 
     * @param roleVo
     * @return
     * @throws Exception
     */
    public Msg delete(RoleVo roleVo) throws Exception;
    /**
     * 指定角色中是否存在用户
     * 
     * @param roleId
     * @return
     * @throws Exception
     */
    public boolean isExistsUserOfRole(Long roleId) throws Exception;
    /**
     * 查询没有选择的人员
     * 
     * @param roleVo
     * @param user
     * @return
     * @throws Exception
     */
    public Object findnoselectedBasStuff(RoleVo roleVo, BasUsers user)
            throws Exception;

    /**
     * 查询已经被选择的人员
     * 
     * @param roleVo
     * @param user
     * @return
     * @throws Exception
     */
    public Object findselectedBasStuff(RoleVo roleVo, BasUsers user)
            throws Exception;

    /**
     * 加载权限树
     * 
     * @param menuVo
     * @param users
     * @return
     */
    public List<TreeJson> menuRoleTree(RoleVo roleVo, BasUsers users) throws Exception;

    /**
     * 保存角色
     * 
     * @param roleVo
     * @param user
     * @return
     * @throws Exception
     */
    public Msg saveAndUpdata(RoleVo roleVo, BasUsers user) throws Exception;

    /**
     * 查询用户的对应的角色菜单
     * 
     * @param stfId
     * @return
     */
    public Set<MenuVo> getMenuByRole(Long stfId) throws Exception;
    /**
     * 查询用户的对应的角色菜单Code
     * 
     * @param stfId
     * @return
     */
    public Set<String> getMenuCodeByRole(Long stfId) throws Exception;
    /**
     * 查询指定角色的菜单
     * 
     * @param roleId
     * @return Set<MenuVo>
     */
    public Set<MenuVo> getMenuCodeByRoleId(Long roleId) throws Exception;

    /**
     * 加载用户拥有的菜单树
     */
    public Object userRoleMenuTree(RoleVo roleVo, BasUsers user)
            throws Exception;

    /**
     * 查询用户角色
     */
    public Msg userRoleInfo(RoleVo roleVo) throws Exception;
    /**
     * 动态菜单树
     * */
    public Object dynamicMenuRoleTree(RoleVo roleVo, BasUsers users) throws Exception;
    /**
     * 分布点权限管理菜单树
     * */
    public Object distributePurviewMenuRoleTree(RoleVo roleVo, BasUsers users) throws Exception;
    /**
     * 设置系统默认角色
     * */
    public boolean modifyDefaultRole(RoleVo roleVo) throws Exception;
    /**
     * 指定企业中指定用户与菜单关系是否存在
     * */
    public boolean isExistsMenuStuff(String enterpriseId,String stfId,String menuId) throws Exception;
    /**
     * 保存分布点权限信息
     * */
    public boolean saveDistributePurviewMenuRoleTree(RoleVo roleVo) throws Exception;
}