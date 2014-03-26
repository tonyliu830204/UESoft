package com.syuesoft.model;

import java.util.Date;

public class BasMenuInfo extends BaseBean{

	private static final long serialVersionUID = 1L;
	private Long menuId;                                                      //菜单ID
	private String menuName;                                                  //菜单名称
	private String menuCode;                                                  //菜单CODE
	private Long menuPid;                                                     //菜单父ID
	private String url;                                                       //菜单URL
	private String childMenu;                                                 //是否有子菜单
	private Date caeateTime;                                                  //创建日期
	private Long person;                                                      //创建人
	private String systemMenu;                                                //菜单属于那个系统
	private String remark;                                                    //备注

	public BasMenuInfo() {
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public Long getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(Long menuPid) {
		this.menuPid = menuPid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(String childMenu) {
		this.childMenu = childMenu;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCaeateTime() {
		return caeateTime;
	}

	public void setCaeateTime(Date caeateTime) {
		this.caeateTime = caeateTime;
	}

	public Long getPerson() {
		return person;
	}

	public void setPerson(Long person) {
		this.person = person;
	}

	public String getSystemMenu() {
		return systemMenu;
	}

	public void setSystemMenu(String systemMenu) {
		this.systemMenu = systemMenu;
	}
}