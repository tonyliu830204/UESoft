package com.syuesoft.menu.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.MenuVo;
import com.syuesoft.menu.service.IMenuService;
import com.syuesoft.model.BasUsers;

/**
 * 菜单管理
 * 
 * @author HeXin
 * 
 */
@ParentPackage(value = "basePackage")
@Action("menuAction")
public class MenuAction extends BaseAction implements ModelDriven<MenuVo>
{
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private MenuVo menuVo = new MenuVo();
    @Autowired
    private IMenuService menuService = null;
   

    /**
     * 加载父菜单
     */
    public void treegrid()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
                this.writeJson(menuService.getTreegrid(user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("加载菜单异常", e);
        }
    }

    /**
     * 加载子菜单
     */
    public void getTreegridChild()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
                this.writeJson(menuService.getTreegridChild(menuVo, user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("加载子菜单异常", e);
        }
    }

    /**
     * 条件查询
     */
    public void getTreegridByQueryParams()
    {
        try
        {
            this.writeJson(menuService.getTreegridByQueryParams(menuVo));
        }
        catch(Exception e)
        {
            logger.error("条件查询异常", e);
        }
    }

    /**
     * 生成comboTree下拉框
     */
    public void comboTreeMenu()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
                this.writeJson(menuService.getComboTreeMenu(user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("生成comboTree下拉框异常", e);
        }

    }

    /**
     * 生成ComboBox下拉框
     */
    public void getMenus()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
                this.writeJson(menuService.getMenus(user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("生成comboTree下拉框异常", e);
        }

    }

    /**
     * 保持更新
     */
    public void savaOrUpdata()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
                this.writeJson(menuService.save(menuVo, user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("保持更新异常", e);
        }
    }

    /**
     * 删除菜单
     */
    public void delete()
    {
        try
        {
            this.writeJson(menuService.delete(menuVo));
        }
        catch(Exception e)
        {
            logger.error("删除菜单异常", e);
        }
    }

    /**
     * 异步加载树菜单
     */
    public void menutree()
    {
        try
        {
            BasUsers user = this.getUsers();
            if (user != null)
            {
                this.writeJson(menuService.menutree(menuVo, user));
            }
            else
            {
                this.writeJson(null);
            }
        }
        catch(Exception e)
        {
            logger.error("异步加载树菜单异常", e);
        }
    }
    
    
    public MenuVo getModel()
    {
        return menuVo;
    }
}