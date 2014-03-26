package com.syuesoft.base.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.syuesoft.model.BasRoleInfo;

public class MenuVo extends BaseBeanVo
{
    private static final long serialVersionUID = 1L;

    private Long menuId; // 菜单ID

    private String menuName; // 菜单名称

    private String menuCode; // 菜单CODE

    private Long menuPid; // 菜单父ID

    private String menuPname; // 菜单父名称

    private String url; // 菜单URL

    private String childMenu; // 是否有子菜单

    private Date caeateTime; // 创建日期

    private Long person; // 创建人id

    private String personName; // 创建人名称

    private String systemMenu;

    private String remark; // 备注

    private Set<BasRoleInfo> basEmptRoles = new HashSet<BasRoleInfo>(0); // 菜单角色

    private String state; // treegrid打开还是关闭

    private String iconCls; // treegrid图标样式

    private String sid;

    private String text;

    private Long menuPid1; // 菜单父ID

    private String startcaeateTime; // 查询开始日期

    private String endcaeateTime; // 查询结束日期

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getMenuCode()
    {
        return menuCode;
    }

    public void setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
    }

    public Long getMenuPid()
    {
        return menuPid;
    }

    public void setMenuPid(Long menuPid)
    {
        this.menuPid = menuPid;
    }

    public String getMenuPname()
    {
        return menuPname;
    }

    public void setMenuPname(String menuPname)
    {
        this.menuPname = menuPname;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getChildMenu()
    {
        return childMenu;
    }

    public void setChildMenu(String childMenu)
    {
        this.childMenu = childMenu;
    }

    public Date getCaeateTime()
    {
        return caeateTime;
    }

    public void setCaeateTime(Date caeateTime)
    {
        this.caeateTime = caeateTime;
    }

    public Long getPerson()
    {
        return person;
    }

    public void setPerson(Long person)
    {
        this.person = person;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Set<BasRoleInfo> getBasEmptRoles()
    {
        return basEmptRoles;
    }

    public void setBasEmptRoles(Set<BasRoleInfo> basEmptRoles)
    {
        this.basEmptRoles = basEmptRoles;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getIconCls()
    {
        return iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Long getMenuPid1()
    {
        return menuPid1;
    }

    public void setMenuPid1(Long menuPid1)
    {
        this.menuPid1 = menuPid1;
    }

    public String getStartcaeateTime()
    {
        return startcaeateTime;
    }

    public void setStartcaeateTime(String startcaeateTime)
    {
        this.startcaeateTime = startcaeateTime;
    }

    public String getEndcaeateTime()
    {
        return endcaeateTime;
    }

    public void setEndcaeateTime(String endcaeateTime)
    {
        this.endcaeateTime = endcaeateTime;
    }

    public String getSystemMenu()
    {
        return systemMenu;
    }

    public void setSystemMenu(String systemMenu)
    {
        this.systemMenu = systemMenu;
    }
}