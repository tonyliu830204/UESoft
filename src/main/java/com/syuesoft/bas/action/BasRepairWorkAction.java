package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasRepairWorkService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasRepairWork;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasRepairWorkAction extends BaseAction implements
        ModelDriven<BasRepairWork>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private BasRepairWorkService logic = null;

    private int page;

    private int rows;

    private String order;

    private String sort;

    // 新增方法
    public String doAdd()
    {
        Message J = new Message();
        try
        {
        	basRepairWork.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            boolean bool = logic.add(basRepairWork);
            if (bool)
            {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的维修工位已存在，请重新输入！");
            }
            else
            {
                J.setSuccess(true);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 删除方法
    public String doDelete()
    {
        Message J = new Message();
        try
        {
            logic.delete(basRepairWork);
            J.setSuccess(true);
        }
        catch(Exception e)
        {
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;

    }

    // 修改方法
    public void doUpdate()
    {
        Message J = new Message();
        try{
            logic.update(basRepairWork);
            J.setSuccess(true);
        }catch(Exception e){
        	  J.setSuccess(false);
              J.setMsg("对不起，您输入的维修工位已存在，请重新输入！");
        }finally{
        	super.writeJson(J);
        }
    }

    // 查询方法
    public String doFindAll()
    {
        HttpSession sesion = ServletActionContext.getRequest().getSession();
        Json json = new Json();
        List<BasRepairWork> list = null;
        try
        {
            list = logic.findAll(page, rows, sort, order,getNowEnterpriseId());
            if (list.size() > 0)
            {
                json.setTotal(Integer.parseInt(sesion.getAttribute("size").toString()));
                json.setRows(list);
                super.writeJson(json);
            }
            else
            {
                json.setRows(list);
                super.writeJson(json);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
        basRepairWork.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
        List<BasRepairWork> list = logic.findByCondition(page, rows,
                basRepairWork);
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

    
    public BasRepairWork getModel()
    {
        return basRepairWork;
    }
    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    private BasRepairWork basRepairWork = new BasRepairWork();

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

    public BasRepairWorkService getLogic()
    {
        return logic;
    }

    public void setLogic(BasRepairWorkService logic)
    {
        this.logic = logic;
    }
}
