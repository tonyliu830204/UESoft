package com.syuesoft.base.vo;

import com.syuesoft.model.BasStuff;

public class BasUserVO extends BaseBeanVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;                          //系统登录编号
	private String systemId;                      //企业网点编号
	private BasStuff basStuff;                     //员工
	private String userName;                       //登录名称
	private String userPasswd;                     //登录密码
	//private String userState;                    //用户状态
	private String accountState;                   //访问状态

	private String newuserPasswd;                  //新登录密码
	private String checkuserPasswd;                //确认登录密码
	private String passWordLeval;                  //新密码级别
	private String leavl;                         //员工级别                               超级管理员    企业管理员    员工
	
	private Long stfId;								//员工序号
	
	private Boolean storeTag;						//本公司与其他公司区别标志
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    public String getSystemId()
    {
        return systemId;
    }

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public BasStuff getBasStuff() {
		return basStuff;
	}

	public void setBasStuff(BasStuff basStuff) {
		this.basStuff = basStuff;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public String getAccountState() {
		return accountState;
	}

	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	public String getNewuserPasswd() {
		return newuserPasswd;
	}

	public void setNewuserPasswd(String newuserPasswd) {
		this.newuserPasswd = newuserPasswd;
	}

	public String getCheckuserPasswd() {
		return checkuserPasswd;
	}

	public void setCheckuserPasswd(String checkuserPasswd) {
		this.checkuserPasswd = checkuserPasswd;
	}

	public String getPassWordLeval() {
		return passWordLeval;
	}

	public void setPassWordLeval(String passWordLeval) {
		this.passWordLeval = passWordLeval;
	}

	public String getLeavl() {
		return leavl;
	}

	public void setLeavl(String leavl) {
		this.leavl = leavl;
	}

   private Integer enterpriseId;

   public Integer getEnterpriseId() {
       return enterpriseId;
   }

   public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
   }
	public Long getStfId() {
		return stfId;
	}

	public void setStfId(Long stfId) {
		this.stfId = stfId;
	}

	public Boolean getStoreTag() {
		return storeTag;
	}

	public void setStoreTag(Boolean storeTag) {
		this.storeTag = storeTag;
	}
	
}