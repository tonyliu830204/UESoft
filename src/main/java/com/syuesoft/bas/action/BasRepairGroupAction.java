package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasRepairGroupService;
import com.syuesoft.model.BasRepairGroup;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasRepairGroupAction extends BaseAction implements
        ModelDriven<BasRepairGroup>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasRepairGroupService logic = null;
    private int page;
    private int rows;
    private String order;
    private String sort;

    // 新增方法
    public void doAdd()
    {
        Message J = new Message();
        try
        {
            boolean bool = logic.add(basRepairGroup,getUserEnterpriseId());
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的车间部门已存在，请重新输入！");
            }
            else{
                J.setSuccess(true);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
        	super.writeJson(J);
        }
    }

    // 删除方法
    public void doDelete()
    {
        Message J = new Message();
        try{
            logic.delete(basRepairGroup);
            J.setSuccess(true);
        }catch(Exception e){
        	 J.setSuccess(false);
        }finally{
        	 super.writeJson(J);
        }
    }

    // 修改方法
    public String doUpdate()
    {
        Message J = new Message();
        try{
        	basRepairGroup.setEnterpriseId(getUserEnterpriseId());
            boolean bool = logic.update(basRepairGroup,getNowEnterpriseId());
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的车间部门已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;

    }

    // 查询方法
    public String doFindAll()
    {
        HttpSession sesion = ServletActionContext.getRequest().getSession();
        Json json = new Json();
        try
        {
            List<BasRepairGroup> list = logic.findAll(page, rows, sort, order,getNowEnterpriseId());
            json.setTotal(Integer.parseInt(sesion.getAttribute("size")
                    .toString()));
            json.setRows(list);
            super.writeJson(json);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
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
        List<BasRepairGroup> list = logic.findByCondition(page, rows,basRepairGroup,getNowEnterpriseId());
        if (list.size() != 0){
            Gson gson = new Gson();
            Json json = new Json();
            int totlesize = (Integer) sesssion.getAttribute("totlesize");
            json.setTotal(totlesize);
            json.setRows(list);
            out.write(gson.toJson(json));
        }
        return null;
    }

    
    public BasRepairGroup getModel()
    {
        return basRepairGroup;
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

    private BasRepairGroup basRepairGroup = new BasRepairGroup();

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

    public BasRepairGroupService getLogic()
    {
        return logic;
    }

    public void setLogic(BasRepairGroupService logic)
    {
        this.logic = logic;
    }
}
