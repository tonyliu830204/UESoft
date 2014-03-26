package com.syuesoft.qx.vo;

public class DeptVo
{

    public Short DEPT_ID;

    public String DEPT_NAME;

    public Short USER_ID;

    public String USER_NAME;

    public String id;

    public DeptVo()
    {
        super();
    }

    public Short getDEPT_ID()
    {
        return DEPT_ID;
    }

    public void setDEPT_ID(Short dEPTID)
    {
        DEPT_ID = dEPTID;
    }

    public String getDEPT_NAME()
    {
        return DEPT_NAME;
    }

    public void setDEPT_NAME(String dEPTNAME)
    {
        DEPT_NAME = dEPTNAME;
    }

    public Short getUSER_ID()
    {
        return USER_ID;
    }

    public void setUSER_ID(Short uSERID)
    {
        USER_ID = uSERID;
    }

    public String getUSER_NAME()
    {
        return USER_NAME;
    }

    public void setUSER_NAME(String uSERNAME)
    {
        USER_NAME = uSERNAME;
    }

    public DeptVo(Short dEPTID, String dEPTNAME, Short uSERID, String uSERNAME)
    {
        super();
        DEPT_ID = dEPTID;
        DEPT_NAME = dEPTNAME;
        USER_ID = uSERID;
        USER_NAME = uSERNAME;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public DeptVo(Short dEPTID, String dEPTNAME, Short uSERID, String uSERNAME,
            String id)
    {
        super();
        DEPT_ID = dEPTID;
        DEPT_NAME = dEPTNAME;
        USER_ID = uSERID;
        USER_NAME = uSERNAME;
        this.id = id;
    }

}
