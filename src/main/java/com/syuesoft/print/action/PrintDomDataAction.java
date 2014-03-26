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
 * 打印模板设置
 * 
 * @author HeXin
 * 
 */

@ParentPackage(value = "basePackage")
@Action("printDomDataAction")
public class PrintDomDataAction extends BaseAction implements
        ModelDriven<PrintVo>
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

    public void getHtmlTemplet()
    {
        try
        {
            this.sendJson(printService.getHtmlTemplet(print.getTempletId()));
        }
        catch(Exception e)
        {
            logger.error("生成打印数据失败", e);
        }
    }

    public void getTemplet()
    {
        try
        {
            this.sendJson(printService.getTemplet(print, getUsers()));
        }
        catch(Exception e)
        {
            logger.error("生成打印数据失败", e);
        }
    }

    public void getTempletType()
    {
        try
        {
            this.writeJson(printService.getTempletType(print, getUsers()));
        }
        catch(Exception e)
        {
            logger.error("生成打印数据失败", e);
        }
    }

    public void saveTemplet()
    {
        try
        {
            this.writeJson(printService.saveTemplet(print, getUsers()));
        }
        catch(Exception e)
        {
            logger.error("生成打印数据失败", e);
        }
    }

    public void delectTemplet()
    {
        try
        {
            this.writeJson(printService.deleteTemplet(print));
        }
        catch(Exception e)
        {
            logger.error("生成打印数据失败", e);
        }
    }
}