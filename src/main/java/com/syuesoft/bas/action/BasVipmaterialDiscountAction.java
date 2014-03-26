package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipmaterialDiscountService;
import com.syuesoft.model.BasVipmaterialDiscount;
import com.syuesoft.util.*;

public class BasVipmaterialDiscountAction extends BaseAction implements
        ModelDriven<BasVipmaterialDiscount>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasVipmaterialDiscountService logic = null;
    private int page;
    private int rows;
    private BasVipmaterialDiscount basVipmaterialDiscount = new BasVipmaterialDiscount();

    // 新增方法
    public String doAdd()
    {

        Message J = new Message();
        try
        {
            logic.add(basVipmaterialDiscount);
            J.setSuccess(true);
        }
        catch(Exception e)
        {
            J.setSuccess(false);
        }
        super.writeJson(J);
        return null;
    }

    // 删除方法
    public String doDelete()
    {
        try
        {
            logic.delete(basVipmaterialDiscount);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    // 修改方法
    public String doUpdate()
    {
        Message J = new Message();
        try
        {
            logic.update(basVipmaterialDiscount);
            J.setSuccess(true);
        }
        catch(Exception e)
        {
            J.setSuccess(false);
        }
        super.writeJson(J);
        return null;

    }

    // 查询方法
    @SuppressWarnings("unchecked")
	public String doFindAll() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        List<BasVipmaterialDiscount> list = logic.findAll(page, rows);
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
        List<BasVipmaterialDiscount> list = logic.findByCondition(page, rows,
                basVipmaterialDiscount);
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

    
    public BasVipmaterialDiscount getModel()
    {
        return basVipmaterialDiscount;
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

    public BasVipmaterialDiscountService getLogic()
    {
        return logic;
    }

    public void setLogic(BasVipmaterialDiscountService logic)
    {
        this.logic = logic;
    }
}
