package com.syuesoft.print.service;

import com.syuesoft.base.vo.PrintVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.PrintTemplet;
import com.syuesoft.util.Msg;

public interface PrintService
{
    /**
     * 查询打印分组
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public Datagrid findPrintGroup(PrintVo print, BasUsers user) throws Exception;

    /**
     * 查询打印分组字段
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public Datagrid findPrintNotes(PrintVo print) throws Exception;

    /**
     * 保存分组
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public Msg savePrint(PrintVo print, BasUsers user) throws Exception;

    /**
     * 删除分组
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public Msg deletePrint(PrintVo print) throws Exception;

    /**
     * 获取套打模板
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public String getHtmlTemplet(Integer id) throws Exception;

    /**
     * 获取套打模板
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public PrintTemplet getTemplet(PrintVo print, BasUsers user) throws Exception;

    /**
     * 查询套打模板
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public Datagrid getTempletType(PrintVo print, BasUsers user) throws Exception;

    /**
     * 删除套打模板
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public Msg deleteTemplet(PrintVo print) throws Exception;

    /**
     * 保存套打模板
     * 
     * @param print
     * @return
     * @throws Exception
     */
    public Msg saveTemplet(PrintVo print, BasUsers user) throws Exception;
}
