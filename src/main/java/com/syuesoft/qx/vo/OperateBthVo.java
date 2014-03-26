package com.syuesoft.qx.vo;

/*
 * 此Vo包含简单的按钮
 */
public class OperateBthVo
{

    public String addBth;

    public String editBth;

    public String delBth;

    public String sercBth;

    public String printBth;

    public String exportBth;

    public OperateBthVo(String addBth, String editBth, String delBth,
            String sercBth, String printBth, String exportBth)
    {
        super();
        this.addBth = addBth;
        this.editBth = editBth;
        this.delBth = delBth;
        this.sercBth = sercBth;
        this.printBth = printBth;
        this.exportBth = exportBth;
    }

    public OperateBthVo()
    {

    }

    public String getAddBth()
    {
        return addBth;
    }

    public void setAddBth(String addBth)
    {
        this.addBth = addBth;
    }

    public String getEditBth()
    {
        return editBth;
    }

    public void setEditBth(String editBth)
    {
        this.editBth = editBth;
    }

    public String getDelBth()
    {
        return delBth;
    }

    public void setDelBth(String delBth)
    {
        this.delBth = delBth;
    }

    public String getSercBth()
    {
        return sercBth;
    }

    public void setSercBth(String sercBth)
    {
        this.sercBth = sercBth;
    }

    public String getPrintBth()
    {
        return printBth;
    }

    public void setPrintBth(String printBth)
    {
        this.printBth = printBth;
    }

    public String getExportBth()
    {
        return exportBth;
    }

    public void setExportBth(String exportBth)
    {
        this.exportBth = exportBth;
    }

}
