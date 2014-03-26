package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasMaterialIntegralruleService;
import com.syuesoft.model.BasMaterialIntegralrule;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@Controller
@Scope("prototype")
public class BasMaterialIntegralruleAction extends BaseAction implements
        ModelDriven<BasMaterialIntegralrule>
{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private BasMaterialIntegralruleService basMaterialIntegralruleService = null;

    private int page;

    private int rows;

    private BasMaterialIntegralrule basMaterialIntegralrule = new BasMaterialIntegralrule();

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

    public BasMaterialIntegralruleService getbasMaterialIntegralruleService()
    {
        return basMaterialIntegralruleService;
    }

    public void setbasMaterialIntegralruleService(
            BasMaterialIntegralruleService basMaterialIntegralruleService)
    {
        this.basMaterialIntegralruleService = basMaterialIntegralruleService;
    }

    // 新增方法
    public String doAdd()
    {
        Message J = new Message();
        try
        {
            basMaterialIntegralruleService.add(basMaterialIntegralrule);
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

    // 删除方法
    public String doDelete() throws Exception
    {

        try
        {
            basMaterialIntegralruleService.delete(basMaterialIntegralrule);
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
        basMaterialIntegralruleService.update(basMaterialIntegralrule);
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
	public String doFindAll()
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json");
        try
        {
            List<BasMaterialIntegralrule> list = basMaterialIntegralruleService
                    .findAll(page, rows);
            List listtotle = basMaterialIntegralruleService.getTotle();
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            Json json = new Json();
            json.setTotal(listtotle.size());
            json.setRows(list);
            out.write(gson.toJson(json));
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
        List<BasMaterialIntegralrule> list = basMaterialIntegralruleService
                .findByCondition(page, rows, basMaterialIntegralrule);
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

    
    public BasMaterialIntegralrule getModel()
    {
        return basMaterialIntegralrule;
    }
}
