package com.syuesoft.base.vo;

/*
 * 员工信息    Vo  
 */
public class BasStuffVo2
{

    private Long userId; // 登录编号

    private String oldPassword; // 登录密码

    private String systemId; // 企业网点编号

    private String loginName; // 登录帐号

    private String loginPassword; // 登录密码

    private String accountState; // 访问状态

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginPassword()
    {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword)
    {
        this.loginPassword = loginPassword;
    }

    public String getAccountState()
    {
        return accountState;
    }

    public void setAccountState(String accountState)
    {
        this.accountState = accountState;
    }
}