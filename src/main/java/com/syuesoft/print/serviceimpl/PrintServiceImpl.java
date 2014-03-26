package com.syuesoft.print.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import com.syuesoft.Tag.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syuesoft.model.BasUsers;
import com.syuesoft.model.PrintGroup;
import com.syuesoft.model.PrintNotes;
import com.syuesoft.model.PrintTemplet;
import com.syuesoft.bas.serviceimpl.BaseLogServiceImpl;
import com.syuesoft.base.vo.PrintVo;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.print.dao.PrintGroupDao;
import com.syuesoft.print.dao.PrintNotesDao;
import com.syuesoft.print.dao.PrintTempletDao;
import com.syuesoft.print.service.PrintService;
import com.syuesoft.util.Msg;

@Repository("printServiceImpl")
public class PrintServiceImpl extends BaseLogServiceImpl implements
        PrintService
{

    @Autowired
    private PrintGroupDao printGroupDao;

    @Autowired
    private PrintNotesDao printNotesDao;

    @Autowired
    private PrintTempletDao printTempletDao;

    public Datagrid findPrintGroup(PrintVo print, BasUsers user) throws Exception
    {
        Datagrid dg = new Datagrid();
        StringBuffer buf = new StringBuffer();
        buf.append("From PrintGroup where 1=1 and tableName = '"
                + print.getTableName() + "' and systemType='"+user.getSystemId()+"'");
        List<PrintGroup> list = printGroupDao.find(buf.toString());
        List<PrintVo> list_ = new ArrayList<PrintVo>();
        if (list != null && list.size() > 0)
        {
            for (PrintGroup printGroup : list)
            {
                PrintVo print_ = new PrintVo();
                BeanUtils.copyProperties(printGroup, print_);
                list_.add(print_);
            }
            dg.setRows(list_);
            dg.setTotal(list.size());
        }
        return dg;
    }

    public Datagrid findPrintNotes(PrintVo print) throws Exception
    {
        Datagrid dg = new Datagrid();
        StringBuffer buf = new StringBuffer();
        buf.append("From PrintNotes where 1=1 and printgroup.groupId = "
                + print.getGroupId() + " group by fieldSort");
        List<PrintNotes> list = printNotesDao.find(buf.toString());
        List<PrintVo> list_ = new ArrayList<PrintVo>();
        if (list != null && list.size() > 0)
        {
            for (PrintNotes printNotes : list)
            {
                PrintVo print_ = new PrintVo();
                BeanUtils.copyProperties(printNotes, print_);
                list_.add(print_);
            }
            dg.setRows(list_);
            dg.setTotal(list.size());
        }
        return dg;
    }

    @Log(moduleName = "套打管理", opertype = "新增/更新套打表格字段")
    public Msg savePrint(PrintVo print, BasUsers user) throws Exception
    {
        Msg msg = new Msg();
        Integer index = 0;
        PrintGroup group = new PrintGroup();
        StringBuffer buf1 = new StringBuffer();
        buf1
                .append("SELECT MAX(group_sort) From printgroup where 1=1 and table_name = '"
                        + print.getTableName() + "' ORDER BY group_sort DESC");
        List<Object[]> list1 = printGroupDao.createSQLQuery(buf1.toString());
        for (Object se : list1)
        {
            index = Integer.parseInt(se.toString());
        }
        index = index + 1;
        group.setGroupId(print.getGroupId() != null ? !"".equals(print
                .getGroupId()) ? print.getGroupId() : null : null);
        group.setGroupName(print.getGroupName() != null ? !"".equals(print
                .getGroupName()) ? print.getGroupName() : null : null);
        group.setTableName(print.getTableName() != null ? !"".equals(print
                .getTableName()) ? print.getTableName() : null : null);
        group.setGroupSort(print.getGroupSort() != null ? !"".equals(print
                .getGroupSort()) ? print.getGroupSort() : index : index);
        group.setChecked("checked");
        group.setSystemType(user.getSystemId());
        group.setRemark(print.getGroupRemark() != null ? print.getGroupRemark()
                : null);
        printGroupDao
                .deleteBySql("UPDATE printgroup SET checked = '' WHERE table_name='"
                        + print.getTableName() + "'");
        if (print.getGroupId() != null)
            printGroupDao.update(group);
        else
            group.setGroupId((Integer) printGroupDao.save(group));
        String fieldNames = print.getFieldNames();
        String fieldLables = print.getFieldLables();
        if (fieldNames != null && !"".equals(fieldNames))
        {
            if (print.getGroupId() != null)
            {
                StringBuffer buf = new StringBuffer();
                buf
                        .append("DELETE FROM PrintNotes where 1=1 and printgroup.groupId = "
                                + print.getGroupId());
                printNotesDao.deleteByHql(buf.toString());
            }
            String[] fieldNames_ = fieldNames.split(",");
            String[] fieldLables_ = fieldLables.split(",");
            int number = fieldNames_.length;
            if (number > 0)
            {
                int i = 0;
                for (String fieldName : fieldNames_)
                {
                    PrintNotes note = new PrintNotes();
                    note.setFieldId(print.getFieldId() != null ? !""
                            .equals(print.getFieldId()) ? print.getFieldId()
                            : null : null);
                    note.setFieldName(fieldName);
                    note.setFieldLable(fieldLables_[i]);
                    note.setFieldSort(number);
                    note.setRemark(print.getRemark());
                    note.setPrintgroup(group);
                    i++;
                    number--;
                    printNotesDao.save(note);
                }
            }
        }
        msg.setSuccess(true);
        msg.setMsg("保存成功!");
        if (print.getGroupId() != null)
            this.setContent("【更新了套打字段分组为：" + print.getGroupId() + "的信息】");
        else
            this.setContent("【新增了套打字段分组为:" + print.getGroupId() + " 的信息】");
        return msg;
    }

    @Log(moduleName = "套打管理", opertype = "删除套打表格字段")
    public Msg deletePrint(PrintVo print) throws Exception
    {
        Msg msg = new Msg();
        StringBuffer buf = new StringBuffer();
        buf.append("From PrintGroup where 1=1 and groupId = "
                + print.getGroupId());
        List<PrintGroup> list = printGroupDao.find(buf.toString());
        PrintGroup group = list.get(0);
        group.getPrintnoteses().clear();
        printGroupDao.delete(group);
        msg.setSuccess(true);
        msg.setMsg("删除成功!");
        this.setContent("【删除了套打字段分组为：" + print.getGroupId() + "的信息】");
        return msg;
    }

    
    public String getHtmlTemplet(Integer id) throws Exception
    {
        String result = "";
        StringBuffer buf = new StringBuffer();
        buf.append("From PrintTemplet where 1=1 and id = " + id);
        List<PrintTemplet> list = printTempletDao.find(buf.toString());
        if (list != null && list.size() > 0)
        {
            PrintTemplet pt = list.get(0);
            result = pt.getContext();
        }
        return result;
    }

    public PrintTemplet getTemplet(PrintVo print, BasUsers user) throws Exception
    {
        PrintTemplet pt = null;
        StringBuffer buf = new StringBuffer();
        buf.append("From PrintTemplet where 1=1 and id = "
                + print.getTempletId());
        List<PrintTemplet> list = printTempletDao.find(buf.toString());
        if (list != null && list.size() > 0)
        {
            pt = list.get(0);
        }
        return pt;
    }

    public Datagrid getTempletType(PrintVo print, BasUsers user) throws Exception
    {
        Datagrid dg = new Datagrid();
        StringBuffer buf = new StringBuffer();
        buf.append("From PrintTemplet where 1=1 and key = '"
                + print.getTempletKey() + "' and systemType='"+user.getSystemId()+"'");
        List<PrintTemplet> list = printTempletDao.find(buf.toString());
        List<PrintVo> list_ = new ArrayList<PrintVo>();
        if (list != null && list.size() > 0)
        {
            for (PrintTemplet printTemplet : list)
            {
                PrintVo print_ = new PrintVo();
                print_.setTempletId(printTemplet.getId());
                print_.setTempletName(printTemplet.getName());
                print_.setTempletKey(printTemplet.getKey());
                // print_.setTempletContext(printTemplet.getContext());
                print_.setTempletChecked(printTemplet.getChecked());
                print_.setTempletRemark(printTemplet.getRemark());
                list_.add(print_);
            }
            dg.setRows(list_);
            dg.setTotal(list.size());
        }
        return dg;
    }

    @Log(moduleName = "套打管理", opertype = "删除套打模板")
    public Msg deleteTemplet(PrintVo print) throws Exception
    {
        Msg msg = new Msg();
        StringBuffer buf = new StringBuffer();
        buf.append("DELETE FROM PrintTemplet where 1=1 and id = "
                + print.getTempletId());
        printTempletDao.deleteByHql(buf.toString());
        msg.setSuccess(true);
        msg.setMsg("删除成功!");
        this.setContent("【删除了套打模板为：" + print.getTempletId() + "的信息】");
        return msg;
    }

    @Log(moduleName = "套打管理", opertype = "新增/更新套打模板")
    public Msg saveTemplet(PrintVo print, BasUsers user) throws Exception
    {
        Msg msg = new Msg();
        printTempletDao
                .deleteBySql("UPDATE printtemplet SET checked = '' WHERE key_='"
                        + print.getTempletKey() + "'");
        PrintTemplet printTemplet = new PrintTemplet();
        printTemplet.setId(print.getTempletId() != null ? !"".equals(print
                .getTempletId()) ? print.getTempletId() : null : null);
        printTemplet.setName(print.getTempletName() != null ? !"".equals(print
                .getTempletName()) ? print.getTempletName() : null : null);
        printTemplet.setKey(print.getTempletKey() != null ? !"".equals(print
                .getTempletKey()) ? print.getTempletKey() : null : null);
        printTemplet.setContext(print.getTempletContext() != null ? !""
                .equals(print.getTempletContext()) ? print.getTempletContext()
                .trim() : null : null);
        printTemplet.setSystemType(user.getSystemId());
        printTemplet.setChecked("checked");
        printTempletDao.saveOrUpdate(printTemplet);
        msg.setSuccess(true);
        msg.setMsg("保存成功!");
        if (print.getTempletId() != null)
            this.setContent("【更新了套打模板为：" + print.getTempletId() + "的信息】");
        else
            this.setContent("【新增了套打模板为:" + print.getTempletId() + " 的信息】");
        return msg;
    }
}