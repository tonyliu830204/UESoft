package com.syuesoft.qx.vo;

/*
 *  用户  Vo 
 */
public class BasUsersVo
{

    private String userId;

    private String userName;

    private String userPasswd;

    private String stfId;

    private String userState;

    private String accountState;

    public BasUsersVo()
    {
        super();
    }

    public BasUsersVo(String userId, String userName, String userPasswd)
    {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPasswd()
    {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd)
    {
        this.userPasswd = userPasswd;
    }

    public String getStfId()
    {
        return stfId;
    }

    public void setStfId(String stfId)
    {
        this.stfId = stfId;
    }

    public String getUserState()
    {
        return userState;
    }

    public void setUserState(String userState)
    {
        this.userState = userState;
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
