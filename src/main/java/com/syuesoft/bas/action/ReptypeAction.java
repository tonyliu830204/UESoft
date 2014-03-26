package com.syuesoft.bas.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.ReptypeService;
import com.syuesoft.base.vo.ReptypeVo;
import com.syuesoft.model.Reptype;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("reptypeAction")
public class ReptypeAction extends BaseAction implements ModelDriven<Reptype>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Reptype reptype = new Reptype();
    @Autowired
    private ReptypeService reptypeService;
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
        	reptype.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            boolean bool = reptypeService.add(reptype);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的维修类别已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 删除方法
    public String doDelete()
    {
        Message J = new Message();
        try{
            reptypeService.delete(reptype);
            J.setSuccess(true);
        }catch(Exception e){
            J.setSuccess(false);
        }
        super.writeJson(J);
        return null;
    }

    // 修改方法
    public void doUpdate()
    {
        Message J = new Message();
        try{
            reptypeService.update(reptype);
            J.setSuccess(true);
        }catch(Exception e){
        	e.printStackTrace();
        	 J.setSuccess(false);
             J.setMsg("对不起，您输入的维修类别已存在，请重新输入！");
        }finally{
        	super.writeJson(J);
        }
    }

    // 查询方法
    @SuppressWarnings("unchecked")
	public String doFindAll()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        List list = new ArrayList();
        List<Reptype> Reptypelist = null;
        Json json = new Json();
        try
        {
            Reptypelist = reptypeService.findAll(page, rows, sort, order,getNowEnterpriseId());
            json.setTotal(Integer.parseInt(session.getAttribute("size").toString()));
            for (Reptype reptype : Reptypelist)
            {
                ReptypeVo rpvo = new ReptypeVo();
                rpvo.setReptId(reptype.getReptId().toString());
                rpvo.setReptName(reptype.getReptName());
                rpvo.setReptClass(reptype.getReptClass());
                if (reptype.getSumCreditsRate() != null)
                {
                    String str = reptype.getSumCreditsRate().toString()
                            .substring(
                                    reptype.getSumCreditsRate().toString()
                                            .indexOf(".") + 1);
                    int a = Integer.parseInt(str);
                    if (a > 0)
                    {
                        rpvo.setSumCreditsRate(reptype.getSumCreditsRate()
                                .toString());
                    }
                    else
                    {
                        rpvo.setSumCreditsRate(reptype.getSumCreditsRate()
                                .toString().substring(
                                        0,
                                        reptype.getSumCreditsRate().toString()
                                                .indexOf(".")));
                    }
                }
                if (reptype.getWorkCreditsRate() != null)
                {
                    String str = reptype.getWorkCreditsRate().toString().substring(reptype.getWorkCreditsRate().toString().indexOf(".") + 1);
                    int a = Integer.parseInt(str);
                    if (a > 0)
                        rpvo.setWorkCreditsRate(reptype.getWorkCreditsRate().toString());
                    else
                        rpvo.setWorkCreditsRate(reptype.getWorkCreditsRate().toString().substring(0,reptype.getWorkCreditsRate().toString().indexOf(".")));
                }
                if (reptype.getPartCreditsRate() != null)
                {
                    String str = reptype.getPartCreditsRate().toString()
                            .substring(
                                    reptype.getPartCreditsRate().toString()
                                            .indexOf(".") + 1);
                    int a = Integer.parseInt(str);
                    if (a > 0)
                    {
                        rpvo.setPartCreditsRate(reptype.getPartCreditsRate()
                                .toString());
                    }
                    else
                    {
                        rpvo.setPartCreditsRate(reptype.getPartCreditsRate()
                                .toString().substring(
                                        0,
                                        reptype.getPartCreditsRate().toString()
                                                .indexOf(".")));
                    }
                }
                rpvo.setMemo(reptype.getMemo());
                list.add(rpvo);
            }
            json.setRows(list);
            super.writeJson(json);
        }catch(Exception e){
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
        reptype.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
        List<Reptype> list = reptypeService.findByCondition(page, rows, reptype);
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

    
    public Reptype getModel(){
        return reptype;
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
