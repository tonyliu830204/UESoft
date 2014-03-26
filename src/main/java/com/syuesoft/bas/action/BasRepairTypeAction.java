package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasRepairTypeService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasRepairType;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasRepairTypeAction extends BaseAction implements
        ModelDriven<BasRepairType>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasRepairTypeService logic = null;
    private int page;
    private int rows;
    private String order;
    private String sort;

    // 新增方法
    public void doAdd()
    {
        Message J = new Message();
        try{
        	basRepairType.setEnterpriseId(getNowEnterpriseId());
            boolean bool = logic.add(basRepairType);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的收费性质已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 删除方法
    public void doDelete()
    {
        Message J = new Message();
        try{
            logic.delete(basRepairType);
            J.setSuccess(true);
        }
        catch(Exception e){
            J.setSuccess(false);
        }
        super.writeJson(J);
    }

    // 修改方法
    public void doUpdate()
    {
        Message J = new Message();
        try{
            logic.update(basRepairType);
            J.setSuccess(true);
        }
        catch(Exception e){
        	J.setSuccess(false);
            J.setMsg("对不起，您输入的收费性质已存在，请重新输入！");
        }finally{
        	super.writeJson(J);
        }
    }

    // 查询方法
    public void doFindAll()
    {
        Json json = new Json();
        List<BasRepairType> list = null;
        try
        {
            list = logic.findAll(page, rows, sort, order,getNowEnterpriseId());
            HttpSession sesion = ServletActionContext.getRequest().getSession();
            json.setTotal(Integer.parseInt(sesion.getAttribute("totlesize").toString()));
            json.setRows(list);
            super.writeJson(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // 通过条件 查询
    public void doFindByName() throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession sesssion = ServletActionContext.getRequest().getSession();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        basRepairType.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
        List<BasRepairType> list = logic.findByCondition(page, rows,basRepairType);
        if (list.size() != 0){
            Gson gson = new Gson();
            Json json = new Json();
            int totlesize = (Integer) sesssion.getAttribute("totlesize");
            json.setTotal(totlesize);
            json.setRows(list);
            out.write(gson.toJson(json));
        }
    }

    
    public BasRepairType getModel()
    {
        return basRepairType;
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

    private BasRepairType basRepairType = new BasRepairType();

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

    public BasRepairTypeService getLogic()
    {
        return logic;
    }

    public void setLogic(BasRepairTypeService logic)
    {
        this.logic = logic;
    }


}
