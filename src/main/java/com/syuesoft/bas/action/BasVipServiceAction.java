package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasVipServiceService;
import com.syuesoft.model.BasVipService;
import com.syuesoft.util.*;

public class BasVipServiceAction extends BaseAction implements
        ModelDriven<BasVipService>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasVipServiceService logic;
    private BasVipService basVipService = new BasVipService();
    private int page;
    private int rows;

    // 新增方法
    public String doAdd()
    {
        Message J = new Message();
        try
        {
            logic.add(basVipService);
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
    public String doDelete() throws Exception
    {
        try
        {
            logic.delete(basVipService);
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
        logic.update(basVipService);
        Message J = new Message();
        Gson gson = new Gson();
        try
        {
            J.setSuccess(true);
        }
        catch(Exception e)
        {
            J.setSuccess(false);
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
        List<BasVipService> list = logic.findAll(page, rows);
        List listtotle = logic.getTotle();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Json json = new Json();
        json.setTotal(listtotle.size());
        json.setRows(list);
        out.write(gson.toJson(json));
        return null;

    }

    
    public BasVipService getModel()
    {
        return basVipService;
    }
    
    public BasVipServiceService getLogic()
    {
        return logic;
    }

    public void setLogic(BasVipServiceService logic)
    {
        this.logic = logic;
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
}
