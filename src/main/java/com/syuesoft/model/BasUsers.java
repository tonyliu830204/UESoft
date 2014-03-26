package com.syuesoft.model;


/**
 * BasUsers entity. @author MyEclipse Persistence Tools
 */

public class BasUsers  extends BaseBean{

	// Fields
	private static final long serialVersionUID = 1L;
	private Long userId;                          //系统登录编号
	private String systemId;                     //系统类型
	private BasStuff basStuff;                    //员工
	private String userName;                      //登录名称
	private String userPasswd;                    //登录密码
    private String leavl;                         //员工级别                               超级管理员    企业管理员    员工
	// Constructors

	/** default constructor */
	public BasUsers() {
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BasStuff getBasStuff() {
		return this.basStuff;
	}

	public void setBasStuff(BasStuff basStuff) {
		this.basStuff = basStuff;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return this.userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getLeavl() {
		return leavl;
	}

	public void setLeavl(String leavl) {
		this.leavl = leavl;
	}
}