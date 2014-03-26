package com.syuesoft.qx.vo;

import java.util.List;

/*
 * 此Vo包含页面操作 
 */
public class PageBthVo
{
    public Integer CATEGORY_ID;

    public String CATEGORY_NAME;

    public Integer MODULE_ID;

    public String MODULE_NAME;

    public Integer PAGE_ID; // 项目Id

    public String PAGE_NAME; // 项目名称

    public Integer OPER_ID;

    public String OPER_NAME;

    public Integer getMODULE_ID()
    {
        return MODULE_ID;
    }

    public void setMODULE_ID(Integer mODULEID)
    {
        MODULE_ID = mODULEID;
    }

    public String getMODULE_NAME()
    {
        return MODULE_NAME;
    }

    public void setMODULE_NAME(String mODULENAME)
    {
        MODULE_NAME = mODULENAME;
    }

    public Integer getPAGE_ID()
    {
        return PAGE_ID;
    }

    public void setPAGE_ID(Integer pAGEID)
    {
        PAGE_ID = pAGEID;
    }

    public String getPAGE_NAME()
    {
        return PAGE_NAME;
    }

    public void setPAGE_NAME(String pAGENAME)
    {
        PAGE_NAME = pAGENAME;
    }

    public Integer getOPER_ID()
    {
        return OPER_ID;
    }

    public void setOPER_ID(Integer oPERID)
    {
        OPER_ID = oPERID;
    }

    public String getOPER_NAME()
    {
        return OPER_NAME;
    }

    public void setOPER_NAME(String oPERNAME)
    {
        OPER_NAME = oPERNAME;
    }

    public Integer getCATEGORY_ID()
    {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(Integer cATEGORYID)
    {
        CATEGORY_ID = cATEGORYID;
    }

    public String getCATEGORY_NAME()
    {
        return CATEGORY_NAME;
    }

    public void setCATEGORY_NAME(String cATEGORYNAME)
    {
        CATEGORY_NAME = cATEGORYNAME;
    }

    public PageBthVo(Integer cATEGORYID, String cATEGORYNAME, Integer mODULEID,
            String mODULENAME, Integer pAGEID, String pAGENAME, Integer oPERID,
            String oPERNAME)
    {
        super();
        CATEGORY_ID = cATEGORYID;
        CATEGORY_NAME = cATEGORYNAME;
        MODULE_ID = mODULEID;
        MODULE_NAME = mODULENAME;
        PAGE_ID = pAGEID;
        PAGE_NAME = pAGENAME;
        OPER_ID = oPERID;
        OPER_NAME = oPERNAME;
    }

    public PageBthVo()
    {

    }

}
