package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.syuesoft.bas.service.BasSumscoreRegulationService;
import com.syuesoft.model.BasSumscoreRegulation;
import com.syuesoft.util.*;

public class BasSumscoreRegulationAction extends ActionSupport
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private BasSumscoreRegulationService logic = null;

    private int page;

    private int rows;

    private BasSumscoreRegulation basSumscoreRegulation;

    // 新增方法
    public String doAdd() throws Exception
    {
        logic.add(basSumscoreRegulation);
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        Message J = new Message();
        Gson gson = new Gson();
        try
        {
            J.setSuccess(true);
            J.setMsg("新增数据成功!");
        }
        catch(Exception e)
        {
            J.setMsg("新增数据失败!");
        }
        out.write(gson.toJson(J));
        return null;
    }

    // 删除方法
    public String doDelete() throws Exception
    {

        try
        {
            logic.delete(basSumscoreRegulation);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    // 修改方法
    public String doUpdate() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        logic.update(basSumscoreRegulation);
        Message J = new Message();
        Gson gson = new Gson();
        try
        {
            J.setSuccess(true);
            J.setMsg("修改数据成功!");
        }
        catch(Exception e)
        {
            J.setMsg("修改数据失败!");
        }
        out.write(gson.toJson(J));
        return null;

    }

    // 查询方法
    @SuppressWarnings("unchecked")
	public String doFindAll() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        List<BasSumscoreRegulation> list = logic.findAll(page, rows);
        List listtotle = logic.getTotle();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Json json = new Json();
        json.setTotal(listtotle.size());
        json.setRows(list);
        out.write(gson.toJson(json));
        return null;

    }

    // 通过条件 查询
    public String doFindByName() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession sesssion = ServletActionContext.getRequest().getSession();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<BasSumscoreRegulation> list = logic.findByCondition(page, rows,
                basSumscoreRegulation);
        if (list.size() != 0)
        {
            Gson gson = new Gson();
            Json json = new Json();
            int totlesize = (Integer) sesssion.getAttribute("totlesize");
            json.setTotal(totlesize);
            json.setRows(list);
            out.write(gson.toJson(json));
        }
        return null;
    }
    
    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public BasSumscoreRegulationService getLogic()
    {
        return logic;
    }

    public void setLogic(BasSumscoreRegulationService logic)
    {
        this.logic = logic;
    }

    public BasSumscoreRegulation getBasSumscoreRegulation()
    {
        return basSumscoreRegulation;
    }

    public void setBasSumscoreRegulation(
            BasSumscoreRegulation basSumscoreRegulation)
    {
        this.basSumscoreRegulation = basSumscoreRegulation;
    }
}
