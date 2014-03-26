package com.syuesoft.menu.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.Tag.Log;
import com.syuesoft.bas.dao.BasPersonnelInformationSetDao;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.MenuVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasMenuInfo;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.menu.dao.IMenuDao;
import com.syuesoft.menu.service.IMenuService;
import com.syuesoft.role.dao.IRoleDao;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.ComboTreeJson;
import com.syuesoft.util.Msg;
import com.syuesoft.util.TreeJson;

/**
 * 菜单管理
 * 
 * @author HeXin
 * 
 */
@Repository("menuServiceImpl")
public class MenuServiceImpl extends BaseLogServiceImpl implements IMenuService
{
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    public IMenuDao menuDao;

    @Autowired
    private BasPersonnelInformationSetDao basPersonnelInformationSetDao;

    @Autowired
    private IRoleDao roleDao;

    /**
     * 加载treegrid菜单树父节点
     */
    public Datagrid getTreegrid(BasUsers user) throws Exception
    {
        Datagrid dg = new Datagrid();
        try
        {
            StringBuffer hql = new StringBuffer();
            if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR)){
                hql.append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') ");
            }
            else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL)){
                hql.append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') and menu.systemMenu ='"+ user.getSystemId() + "'");
            }
            else{
                hql.append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') ");
            }
            List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString());
            List<MenuVo> rows = new ArrayList<MenuVo>();
            if (basMenuInfos != null && basMenuInfos.size() > 0){
                for (BasMenuInfo menu : basMenuInfos){
                    MenuVo fVo = new MenuVo();
                    fVo.setMenuId(menu.getMenuId());
                    fVo.setMenuName(menu.getMenuName());
                    fVo.setMenuCode(menu.getMenuCode());
                    fVo.setIconCls(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "": "icon-blank");
                    fVo.setCaeateTime(menu.getCaeateTime() != null ? menu.getCaeateTime() : null);
                    fVo.setRemark(menu.getRemark() != null ? menu.getRemark(): null);
                    fVo.setUrl(menu.getUrl() != null ? menu.getUrl() : null);
                    fVo.setSystemMenu(menu.getSystemMenu() != null ? menu.getSystemMenu() : null);
                    fVo.setChildMenu(menu.getChildMenu() != null ? menu.getChildMenu() : null);
                    fVo.setState(menu.getChildMenu().equals(Contstants.CHILDMENU.CHILDMENU_YES) ? "closed": "open");
                    if (menu.getPerson() != null)
                    {
                        BasStuff basStuff = (BasStuff) basPersonnelInformationSetDao.getObject(BasStuff.class, menu.getPerson());
                        if(basStuff!=null){
                        	fVo.setPerson(basStuff.getStfId());
                        	fVo.setPersonName(basStuff.getStfName());
                        }
                    }
                    rows.add(fVo);
                }
            }
            dg.setTotal(rows.size());
            dg.setRows(rows);
        }
        catch(Exception e)
        {
            logger.error("查询菜单失败", e);
        }
        return dg;
    }

    /**
     * 展开树节点查询
     */
    public List<MenuVo> getTreegridChild(MenuVo menuVo, BasUsers user)
            throws Exception
    {
        List<MenuVo> rows = new ArrayList<MenuVo>();
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql.append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid ");
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql.append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid and menu.systemMenu ='"
                            + user.getSystemId() + "'");
        }
        else
        {
            hql.append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid ");
        }
        params.put("menuPid", menuVo.getMenuId());
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString(), params);
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            for (BasMenuInfo menu : basMenuInfos)
            {
                MenuVo fVo = new MenuVo();
                fVo.setMenuId(menu.getMenuId());
                fVo.setMenuCode(menu.getMenuCode());
                fVo.setMenuName(menu.getMenuName());
                fVo.setCaeateTime(menu.getCaeateTime() != null ? menu
                        .getCaeateTime() : null);
                fVo.setRemark(menu.getRemark() != null ? menu.getRemark()
                        : null);
                fVo.setUrl(menu.getUrl() != null ? menu.getUrl() : null);
                fVo.setSystemMenu(menu.getSystemMenu() != null ? menu
                        .getSystemMenu() : null);
                fVo.setChildMenu(menu.getChildMenu() != null ? menu
                        .getChildMenu() : null);

                if (menu.getMenuPid() != null)
                {
                    fVo.setMenuPid(menu.getMenuPid());
                    BasMenuInfo pmenu = getBasMenuInfo(menu.getMenuPid());
                    fVo.setMenuPname(pmenu.getMenuName());
                }

                fVo
                        .setState(menu.getChildMenu().equals(
                                Contstants.CHILDMENU.CHILDMENU_YES) ? "closed"
                                : "open");
                if (menu.getPerson() != null)
                {
                    BasStuff basStuff = (BasStuff) basPersonnelInformationSetDao
                            .getObject(BasStuff.class, menu.getPerson());
                    if(basStuff!=null){
                    	fVo.setPerson(basStuff.getStfId());
                    	fVo.setPersonName(basStuff.getStfName());
                    }
                }
                fVo
                        .setIconCls(menu.getChildMenu().equals(
                                Contstants.CHILDMENU.CHILDMENU_YES) ? ""
                                : "icon-blank");
                rows.add(fVo);
            }
        }
        return rows;
    }

    /**
     * 查询菜单对象
     */
    private List<BasMenuInfo> getBasMenuInfoByMenuPid(Long menuPid) throws Exception
    {
        return menuDao.find("from BasMenuInfo bmi where bmi.menuPid="+menuPid);
    }
    /**
     * 查询菜单对象
     * 
     * @param MenuPid
     * @return
     */
    public BasMenuInfo getBasMenuInfo(Long Menuid) throws Exception
    {
        return menuDao.get(BasMenuInfo.class, Menuid);
    }

    /**
     * 查询菜单ComboTree结构下拉框
     */
    public List<ComboTreeJson> getComboTreeMenu(BasUsers user) throws Exception
    {
        List<ComboTreeJson> list = new ArrayList<ComboTreeJson>();
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') ");
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') and menu.systemMenu ='"
                            + user.getSystemId() + "'");
        }
        else
        {
            hql
            .append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') ");
        }
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString());
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            for (BasMenuInfo menu : basMenuInfos)
            {
                ComboTreeJson json = new ComboTreeJson();
                json.setId(String.valueOf(menu.getMenuId()));
                json.setText(menu.getMenuName());
                getComboTreeMenuChild(json, menu, user);
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询菜单ComboTree结构下拉框子级节点
     * 
     * @param json
     * @param menuVo
     */
    private void getComboTreeMenuChild(ComboTreeJson json, BasMenuInfo menuVo,
            BasUsers user) throws Exception
    {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid) ");
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid) and menu.systemMenu ='"
                            + user.getSystemId() + "'");
        }
        else
        {
            hql
            .append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid) ");
        }
        params.put("menuPid", menuVo.getMenuId());
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString(), params);
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            List<ComboTreeJson> rows = new ArrayList<ComboTreeJson>();
            for (BasMenuInfo menu : basMenuInfos)
            {
                ComboTreeJson json_ = new ComboTreeJson();
                json_.setId(String.valueOf(menu.getMenuId()));
                json_.setText(menu.getMenuName());
                getComboTreeMenuChild(json_, menu, user);
                rows.add(json_);
            }
            json.setChildren(rows);
            json.setState("closed");
        }
    }

    public List<ComboBoxJson> getMenus(BasUsers user) throws Exception
    {
        List<ComboBoxJson> list = new ArrayList<ComboBoxJson>();
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql.append("from BasMenuInfo menu where 1=1 ");
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql.append("from BasMenuInfo menu where 1=1 and menu.systemMenu ='"
                    + user.getSystemId() + "'");
        }
        else
        {
            hql.append("from BasMenuInfo menu where 1=1 ");
        }
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString());
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            for (BasMenuInfo menu : basMenuInfos)
            {
                ComboBoxJson json = new ComboBoxJson();
                json.setId(String.valueOf(menu.getMenuId()));
                json.setText(menu.getMenuName());
                list.add(json);
            }
        }
        return list;
    }

    @Log(moduleName = "菜单管理", opertype = "新增菜单项", content = "菜单管理-->新增菜单项")
    public Msg save(MenuVo menuVo, BasUsers user) throws Exception
    {
        boolean tag = false;
        Msg msg = new Msg();
        if (menuVo.getMenuName() == null)
        {
            msg.setMsg("菜单名称不能为空");
            msg.setSuccess(false);
            tag = true;
        }
        if (menuVo.getMenuCode() == null)
        {
            msg.setMsg("CODE不能为空");
            msg.setSuccess(false);
            tag = true;
        }
        if(!tag){
            BasMenuInfo basMenuInfo = getBasMenuInfo(menuVo);
            if (basMenuInfo.getMenuId() == null
                    || "".equals(basMenuInfo.getMenuId()))
            {
                basMenuInfo.setPerson(user.getBasStuff().getStfId());
                if ("".equals(basMenuInfo.getSystemMenu()))
                {
                    basMenuInfo.setSystemMenu(user.getSystemId());
                }
                basMenuInfo.setChildMenu(Contstants.CHILDMENU.CHILDMENU_NO);
                basMenuInfo.setCaeateTime(new Date());
            }
            if (getCode(basMenuInfo.getMenuCode(),
                    basMenuInfo.getMenuId() == null ? null : basMenuInfo
                            .getMenuId(), basMenuInfo.getSystemMenu()))
            {
                msg.setMsg("CODE不能重复");
                msg.setSuccess(false);
                return msg;
            }
            else
            {
                menuDao.saveOrUpdate(basMenuInfo);
                if (menuVo.getMenuPid() != null)
                {
                    BasMenuInfo pmenu = getBasMenuInfo(menuVo.getMenuPid());
                    updateChildMenu(pmenu);
                    //deleteMenuRole(pmenu);
                }
                msg.setMsg("添加菜单成功");
                msg.setSuccess(true);
            }
            setContent("新增菜单项【" + menuVo.getMenuName() + "】信息");
        }
        return msg;
    }

    /**
     * 更新父菜单是否有子菜单状态
     * 
     * @param pmenu
     */
    @Log(moduleName = "菜单管理", opertype = "更新菜单项", content = "菜单管理-->更新菜单项")
    private void updateChildMenu(BasMenuInfo pmenu) throws Exception
    {
        pmenu.setChildMenu(Contstants.CHILDMENU.CHILDMENU_YES);
        menuDao.update(pmenu);
        setContent("更新菜单项【" + pmenu.getMenuName() + "】信息");
    }

    /**
     * 如果提前为父菜单分配了角色，添加子菜单将父菜单角色关系删除
     * 
     * @param pmenu
     */
    @Log(moduleName = "菜单管理", opertype = "删除菜单角色关系", content = "菜单管理-->删除菜单角色关系")
    private void deleteMenuRole(BasMenuInfo pmenu) throws Exception
    {
        List<Object[]> list = roleDao.getRoleMenu(pmenu.getMenuId(), null);
        if (list != null && list.size() > 0)
        {
            for (Object[] obj : list)
            {
                roleDao.deleteMenuRoleByMenuId(Long
                        .parseLong(obj[0].toString()));
            }
        }
        if (pmenu.getMenuPid() != null)
        {
            BasMenuInfo menu = getBasMenuInfo(pmenu.getMenuPid());
            deleteMenuRole(menu);
        }
        setContent("删除菜单角色关系【" + pmenu.getMenuName() + "】信息");
    }

    /**
     * 验证CODE是否已经存在
     * @param menuCode
     * @param menuId
     * @return
     */
    private boolean getCode(String menuCode, Long menuId, String systemType)
            throws Exception
    {
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer hql = new StringBuffer();
        if (menuId == null)
        {
            hql.append("from BasMenuInfo menu where 1=1 and menu.menuCode =:menuCode and menu.systemMenu=:systemType ");
        }
        else
        {
            hql.append("from BasMenuInfo menu where 1=1 and menu.menuCode =:menuCode and menu.menuId <>:menuId and menu.systemMenu=:systemType");
            params.put("menuId", menuId);
        }
        params.put("menuCode", menuCode);
        params.put("systemType", systemType);
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString(), params);
        return basMenuInfos != null ? true : false;
    }

    /**
     * 删除菜单
     */
    @Log(moduleName = "菜单管理", opertype = "删除父菜单", content = "菜单管理-->删除父菜单")
    public Msg delete(MenuVo menuVo) throws Exception
    {
        Msg msg = new Msg();
        BasMenuInfo menu = getBasMenuInfo(menuVo.getMenuId());
        if (menu == null)
        {
            msg.setMsg("要删除的菜单不存在");
            msg.setSuccess(false);
        }
        else
        {
            Long menuId_ = menu.getMenuId();
//            Long pmenuId = menu.getMenuPid();
//            if (pmenuId != null)
//                checkPerMenuRole(pmenuId, menuId_);
            deleteChild(menuId_);
            roleDao.deleteMenuRoleByMenuId(menuId_);
            menuDao.delete(menu);
            changMenuState(menuVo);
            msg.setMsg("要删除的菜单成功");
            msg.setSuccess(true);
        }
        setContent("删除父菜单【" + menuVo.getMenuName() + "】信息");
        return msg;
    }

    /**
     * 删除子菜单
     * 
     * @param menuId
     */
    @Log(moduleName = "菜单管理", opertype = "删除子菜单", content = "菜单管理-->删除子菜单")
    private void deleteChild(Long menuId) throws Exception
    {
        List<BasMenuInfo> basMenuInfos = getPmenu(menuId);
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            for (BasMenuInfo menu : basMenuInfos)
            {
                Long menuId_ = menu.getMenuId();
                roleDao.deleteMenuRoleByMenuId(menuId_);
                menuDao.delete(menu);
                deleteChild(menuId_);
            }
        }
        setContent("删除子菜单【" + menuId + "】信息");
    }

    /**
     * 如果无子节点修改子节点状态
     * 
     * @param menuVo
     */
    private void changMenuState(MenuVo menuVo) throws Exception
    {
        List<BasMenuInfo> basMenuInfos = getPmenu(menuVo.getMenuPid());
        if (basMenuInfos == null)
        {
            BasMenuInfo menu = getBasMenuInfo(menuVo.getMenuPid());
            List<BasMenuInfo> list=getBasMenuInfoByMenuPid(menuVo.getMenuPid());
            if(!(list!=null&&list.size()>0))
            	menu.setChildMenu(Contstants.CHILDMENU.CHILDMENU_NO);
            menuDao.saveOrUpdate(menu);
        }
    }

    /**
     * 查询菜单是否有角色关系 1. 如果没有角色关系，查询所有角色中与删除菜单是同级菜单是否全选中，如果全选中，将父菜单配置角色，否则退出 2.
     * 如果有角色关系，查询配置角色中与删除菜单是同级菜单是否全选中，如果全选中，将父菜单配置角色，否则退出
     * 
     * @param pmenuId
     * @param menuId
     */
    private void checkPerMenuRole(Long pmenuId, Long menuId) throws Exception
    {
        boolean all = true; // 默认全部选中
        List<Object[]> menurole = roleDao.getMenuRoleType(menuId);
        List<Object[]> menuroles = null;
        List<BasMenuInfo> list = null;
        Set<Long> set = new HashSet<Long>();
        if (menurole == null && menurole.size() == 0)
        {
            menurole = roleDao.getMenuRoleType(null);
            if (menurole != null && menurole.size() > 0)
            {
                list = getPmenu(pmenuId);
                removeDelMenu(list, menuId);
                if (list != null && list.size() > 0)
                {
                    for (Object roleId : menurole)
                    {
                        menuroles = roleDao.getRoleMenu(null, Long
                                .parseLong(roleId.toString()));
                        ListToSet(set, menuroles);
                        all = true;
                        for (BasMenuInfo menu : list)
                        {
                            if (!set.contains(menu.getMenuId()))
                            {
                                all = false; // 如果任意一个菜单没有配置权限，将跳出检查
                                break;
                            }
                        }
                        if (all)
                        {
                            BasMenuInfo menu = getBasMenuInfo(pmenuId);
                            updateMenuRole(menu, Long.parseLong(roleId
                                    .toString()));
                        }
                    }
                }
            }
        }
    }

    /**
     * 更新菜单角色关系
     * 
     * @param menu_id
     * @param role_id
     */
    @Log(moduleName = "菜单管理", opertype = "更新菜单角色关系", content = "菜单管理-->更新菜单角色关系")
    private void updateMenuRole(BasMenuInfo menu, Long role_id)
            throws Exception
    {
        roleDao.savaMenuRole(menu.getMenuId(), role_id);
        if (menu.getMenuPid() != null)
        {
            BasMenuInfo pmenu = getBasMenuInfo(menu.getMenuPid());
            updateMenuRole(pmenu, role_id);
        }
        setContent("更新菜单角色关系【" + menu.getMenuName() + "】信息");
    }

    /**
     * object[]
     * 
     * @param set
     * @param list
     */
    private void ListToSet(Set<Long> set, List<Object[]> list) throws Exception
    {
        for (Object[] obj : list)
        {
            set.add(Long.parseLong(obj[0].toString()));
        }
    }

    /**
     * 
     * @param list
     * @param menuId
     */
    private void removeDelMenu(List<BasMenuInfo> list, Long menuId)
            throws Exception
    {
        for (BasMenuInfo menu : list)
        {
            if (menu.getMenuId() == menuId)
            {
                list.remove(menu);
                break;
            }
        }
    }

    /**
     * 将MenuVo转化BasMenuInfo
     * 
     * @param menuVo
     * @return
     */
    private BasMenuInfo getBasMenuInfo(MenuVo menuVo) throws Exception
    {
        BasMenuInfo basMenuInfo = new BasMenuInfo();
        basMenuInfo.setMenuId(menuVo.getMenuId() == null ? null : menuVo
                .getMenuId());
        basMenuInfo.setMenuName(menuVo.getMenuName() == null ? null : menuVo
                .getMenuName());
        basMenuInfo.setMenuCode(menuVo.getMenuCode() == null ? null : menuVo
                .getMenuCode());
        basMenuInfo.setMenuPid(menuVo.getMenuPid() == null ? null : menuVo
                .getMenuPid());
        basMenuInfo.setCaeateTime(menuVo.getCaeateTime() == null ? new Date()
                : menuVo.getCaeateTime());
        basMenuInfo.setPerson(menuVo.getPerson() == null ? null : menuVo
                .getPerson());
        basMenuInfo.setUrl(menuVo.getUrl() == null ? null : menuVo.getUrl());
        basMenuInfo.setSystemMenu(menuVo.getSystemMenu() == null ? null
                : menuVo.getSystemMenu());
        basMenuInfo.setChildMenu(menuVo.getChildMenu() == null ? null : menuVo
                .getChildMenu());
        basMenuInfo.setRemark(menuVo.getRemark() == null ? null : menuVo
                .getRemark());
        return basMenuInfo;
    }

    /**
     * 条件查询
     */
    public Datagrid getTreegridByQueryParams(MenuVo menuVo) throws Exception
    {
        Datagrid dg = new Datagrid();
        try
        {
            StringBuffer hql = new StringBuffer();
            Map<String, Object> params = new HashMap<String, Object>();
            hql.append("from BasMenuInfo menu where 1=1 ");
            hql = addWhere(menuVo, hql, params);
            List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString(),
                    params);
            List<MenuVo> rows = new ArrayList<MenuVo>();
            if (basMenuInfos != null && basMenuInfos.size() > 0)
            {
                for (BasMenuInfo menu : basMenuInfos)
                {
                    MenuVo fVo = new MenuVo();
                    fVo.setMenuId(menu.getMenuId());
                    fVo.setState("closed");
                    fVo.setMenuName(menu.getMenuName());
                    fVo.setMenuCode(menu.getMenuCode());
                    fVo.setIconCls("");
                    fVo.setCaeateTime(menu.getCaeateTime() != null ? menu
                            .getCaeateTime() : null);
                    fVo.setRemark(menu.getRemark() != null ? menu.getRemark()
                            : null);
                    fVo.setUrl(menu.getUrl() != null ? menu.getUrl() : null);
                    if (menu.getPerson() != null)
                    {
                        BasStuff basStuff = (BasStuff) basPersonnelInformationSetDao
                                .getObject(BasStuff.class, menu.getPerson());
                        fVo.setPerson(basStuff.getStfId());
                        fVo.setPersonName(basStuff.getStfName());
                    }
                    rows.add(fVo);
                }
            }
            dg.setTotal(rows.size());
            dg.setRows(rows);
        }
        catch(Exception e)
        {
            logger.error("查询菜单失败", e);
        }
        return dg;
    }

    private StringBuffer addWhere(MenuVo menuVo, StringBuffer hql,
            Map<String, Object> params) throws Exception
    {
        if (menuVo.getMenuCode() != null && !"".equals(menuVo.getMenuCode()))
        {

        }
        if (menuVo.getMenuName() != null && !"".equals(menuVo.getMenuName()))
        {

        }
        if (menuVo.getStartcaeateTime() != null
                && !"".equals(menuVo.getStartcaeateTime()))
        {

        }
        if (menuVo.getEndcaeateTime() != null
                && !"".equals(menuVo.getEndcaeateTime()))
        {

        }
        return hql;
    }

    public List<TreeJson> menutree(MenuVo menuVo, BasUsers user)
            throws Exception
    {
        List<TreeJson> list = null;
        try
        {
            if (menuVo.getMenuId() == null || "".equals(menuVo.getMenuId()))
            {
                list = getTreeMenu(user);
            }
            else
            {
                BasMenuInfo menu = new BasMenuInfo();
                menu.setMenuId(menuVo.getMenuId());
                list = getTreeMenuChild(menu, user);
            }
        }
        catch(Exception e)
        {
            logger.error("查询菜单 失败", e);
        }
        return list;
    }

    /**
     * 查询父菜单tree结构
     * 
     * @param user
     * @return
     * @throws Exception
     */
    private List<TreeJson> getTreeMenu(BasUsers user) throws Exception
    {
        List<TreeJson> list = new ArrayList<TreeJson>();
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') ");
        }
        else
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and (menu.menuPid is null or menu.menuPid ='') and menu.systemMenu ='"
                            + user.getSystemId() + "'");
        }
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString());
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            for (BasMenuInfo menu : basMenuInfos)
            {
                TreeJson json = new TreeJson();
                json.setId(String.valueOf(menu.getMenuId()));
                json.setText(menu.getMenuName());
                json
                        .setIconCls(menu.getChildMenu().equals(
                                Contstants.CHILDMENU.CHILDMENU_YES) ? ""
                                : "icon-blank");
                json.setChecked(false);
                json
                        .setState(menu.getChildMenu().equals(
                                Contstants.CHILDMENU.CHILDMENU_YES) ? "closed"
                                : "open");
                list.add(json);
            }
        }
        return list;
    }

    /**
     * 查询子菜单树结构
     * 
     * @param menuVo
     * @param user
     * @return
     */
    private List<TreeJson> getTreeMenuChild(BasMenuInfo menuVo, BasUsers user)
            throws Exception
    {
        List<TreeJson> rows = new ArrayList<TreeJson>();
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer hql = new StringBuffer();
        if (user.getLeavl().equals(Contstants.EMPLOYEELEVEL.ADMINISTRATOR))
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid) ");
        }
        else if(!user.getSystemId().equals(Contstants.SYSTEMTYPE.ALL))
        {
            hql
                    .append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid) and menu.systemMenu ='"
                            + user.getSystemId() + "'");
        }
        else
        {
            hql
            .append("from BasMenuInfo menu where 1=1 and menu.menuPid =:menuPid) ");
        }
        params.put("menuPid", menuVo.getMenuId());
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString(), params);
        if (basMenuInfos != null && basMenuInfos.size() > 0)
        {
            for (BasMenuInfo menu : basMenuInfos)
            {
                TreeJson json_ = new TreeJson();
                json_.setId(String.valueOf(menu.getMenuId()));
                json_.setText(menu.getMenuName());
                json_
                        .setIconCls(menu.getChildMenu().equals(
                                Contstants.CHILDMENU.CHILDMENU_YES) ? ""
                                : "icon-blank");
                json_.setChecked(false);
                json_
                        .setState(menu.getChildMenu().equals(
                                Contstants.CHILDMENU.CHILDMENU_YES) ? "closed"
                                : "open");
                rows.add(json_);
            }
        }
        return rows;
    }

    /**
     * 查询所以的子菜单
     * 
     * @param menuId
     * @return
     */
    public List<BasMenuInfo> getPmenu(Long menuId) throws Exception
    {
        StringBuffer hql = new StringBuffer();
        hql.append("from BasMenuInfo menu where 1=1 and menu.menuPid=" + menuId
                + " GROUP BY menuId");
        return menuDao.find(hql.toString());
    }

    
    public List<BasMenuInfo> getMenu() throws Exception
    {
    	StringBuffer sb=new StringBuffer("SELECT temp.menu_id FROM bas_menu_info temp WHERE temp.menu_name IN ('维修系统','销售系统','系统设置')");
    	sb.append(" UNION");
    	sb.append(" SELECT aa.menu_id FROM bas_menu_info aa WHERE aa.menu_pid IN (");
    	sb.append(" SELECT temp.menu_id FROM bas_menu_info temp WHERE temp.menu_name IN ('系统设置')");
    	sb.append(" ) AND aa.menu_id NOT IN(65,195,1366)");
    	sb.append(" UNION ");
    	sb.append(" SELECT bb.menu_id FROM bas_menu_info bb WHERE bb.menu_pid IN (");
    	sb.append(" SELECT aa.menu_id FROM bas_menu_info aa WHERE aa.menu_pid IN (");
    	sb.append(" SELECT temp.menu_id FROM bas_menu_info temp WHERE temp.menu_name IN ('系统设置')");
    	sb.append(" ) AND aa.menu_id NOT IN(65,195,1366)");
    	sb.append(" ) AND bb.menu_id NOT IN(1378)");
    	List list=menuDao.createSQLQuery(sb.toString());
    	StringBuffer ss=new StringBuffer();
    	if(list!=null&&list.size()>0)
    		for (Object obj : list) {
				ss.append(obj.toString()+",");
			}
    	String source=null;
    	if(ss.length()>0)
    		source=ss.substring(0, ss.length()-1);
    	
        StringBuffer hql = new StringBuffer("select temp from BasMenuInfo temp ");
        if(Contstants.administratorTag==true)
        	hql.append("where temp.menuId in("+source+")");
        List<BasMenuInfo> basMenuInfos = menuDao.find(hql.toString());
        return basMenuInfos;
    }
}