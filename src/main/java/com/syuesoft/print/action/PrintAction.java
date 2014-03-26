package com.syuesoft.print.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.base.vo.PrintVo;
import com.syuesoft.print.service.PrintService;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * 打印分组设置
 * 
 * @author HeXin
 * 
 */

@ParentPackage(value = "basePackage")
@Action("printAction")
public class PrintAction extends BaseAction implements ModelDriven<PrintVo>
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private PrintService printService;

    PrintVo print = new PrintVo();

    
    public PrintVo getModel()
    {
        return print;
    }

    /**
     * 查询打印分组
     */
    public void getGroup()
    {
        try
        {
            this.writeJson(printService.findPrintGroup(print, getUsers()));
        }
        catch(Exception e)
        {
            logger.error("查询打印分组失败", e);
        }
    }

    /**
     * 查询打印列
     */
    public void getNotes()
    {
        try
        {
            this.writeJson(printService.findPrintNotes(print));
        }
        catch(Exception e)
        {
            logger.error("查询打印列失败", e);
        }
    }

    /**
     * 保存或者修改打印列
     */
    public void save()
    {
        try
        {
            this.writeJson(printService.savePrint(print, getUsers()));
        }
        catch(Exception e)
        {
            logger.error("保存或者修改打印列失败", e);
        }
    }

    /**
     * 删除打印列
     */
    public void delete()
    {
        try
        {
            this.writeJson(printService.deletePrint(print));
        }
        catch(Exception e)
        {
            logger.error("保存或者修改打印列失败", e);
        }
    }
}