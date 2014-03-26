package com.syuesoft.qx.vo;

public class ComonPageBth
{

    public String pageId;

    public String pageName;

    public String serBth;

    public String addBth;

    public String editBth;

    public String removeBth;

    public String printBth;

    public String exportBth;// 导出

    public String importBth;// 导入

    public String changeBth;// 变更

    public String formalchangeBth;// 转出

    public String initializeBth;// 初始化

    public String sendBth;// 短信发送

    public String undosendBth;// 取消发送

    public String monthBth;// 月结

    public String undomonthBth;// 反月结

    public String backupBth;// 备份

    public String cancel2Bth;// 注销

    public String examineBth;// 审核

    public String ycashBth;// 预收

    public String scashBth;// 收银

    public String setBth; // 设置set

    public String callBth; // 回访call

    public String tocashBth; // 转收银tocash

    public String toreceiptBth; // 转前台toreceipt

    public String choiceBth; // 选择choice

    public String okBth; // 确定ok

    public String helpBth; // 帮助help

    public String storeBth; // 储值store

    public String remindBth; // 提醒remind

    public String stasticsBth; // 统计 stastics

    public String modifypasswodBth; // 密码修改modifypasswod

    public String membershipBth; // 入会membership

    public ComonPageBth()
    {

    }

    public ComonPageBth(String pageId, String pageName, String serBth,
            String addBth, String editBth, String removeBth, String printBth,
            String exportBth)
    {
        super();
        this.pageId = pageId;
        this.pageName = pageName;
        this.serBth = serBth;
        this.addBth = addBth;
        this.editBth = editBth;
        this.removeBth = removeBth;
        this.printBth = printBth;
        this.exportBth = exportBth;
    }

    public String getPageId()
    {
        return pageId;
    }

    public void setPageId(String pageId)
    {
        this.pageId = pageId;
    }

    public String getPageName()
    {
        return pageName;
    }

    public void setPageName(String pageName)
    {
        this.pageName = pageName;
    }

    public String getSerBth()
    {
        return serBth;
    }

    public void setSerBth(String serBth)
    {
        this.serBth = serBth;
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

    public String getRemoveBth()
    {
        return removeBth;
    }

    public void setRemoveBth(String removeBth)
    {
        this.removeBth = removeBth;
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
