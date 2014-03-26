package com.syuesoft.menu.service;

import java.util.List;

import com.syuesoft.base.vo.MenuVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasMenuInfo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.ComboTreeJson;
import com.syuesoft.util.Msg;
import com.syuesoft.util.TreeJson;

public interface IMenuService
{
    /**
     * 菜单Treegrid
     * 
     * @return
     */
    public Datagrid getTreegrid(BasUsers user) throws Exception;

    /**
     * 查询子菜单
     * 
     * @param menuVo
     * @return
     */
    public List<MenuVo> getTreegridChild(MenuVo menuVo, BasUsers user)
            throws Exception;

    /**
     * 条件查询
     * 
     * @return
     * @throws Exception
     */
    public Datagrid getTreegridByQueryParams(MenuVo menuVo) throws Exception;

    /**
     * ComboTree
     * 
     * @return
     */
    public List<ComboTreeJson> getComboTreeMenu(BasUsers user) throws Exception;

    public List<ComboBoxJson> getMenus(BasUsers user) throws Exception;

    /**
     * 保存/修改菜单
     * 
     * @param menuVo
     * @return
     */
    public Msg save(MenuVo menuVo, BasUsers user) throws Exception;

    /**
     * 删除菜单
     * 
     * @param menuVo
     * @return
     */
    public Msg delete(MenuVo menuVo) throws Exception;

    /**
     * 异步加载树菜单
     * 
     * @param menuVo
     * @param user
     * @return
     */
    public List<TreeJson> menutree(MenuVo menuVo, BasUsers user)
            throws Exception;

    /**
     * 通过菜单编号查询
     * 
     * @param Menuid
     * @return
     */
    public BasMenuInfo getBasMenuInfo(Long Menuid) throws Exception;

    /**
     * 查询所以的子菜单
     * 
     * @param menuId
     * @return
     */
    public List<BasMenuInfo> getPmenu(Long menuId) throws Exception;

    /**
     * 查询所有的才是信息
     * 
     * @return
     * @throws Exception
     */
    public List<BasMenuInfo> getMenu() throws Exception;
}