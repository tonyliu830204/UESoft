package com.syuesoft.bas.action;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasClaimsTypeService;
import com.syuesoft.model.BasClaimsType;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasClaimsTypeAction extends BaseAction implements
        ModelDriven<BasClaimsType>
{
    private static final long serialVersionUID = 1L;
    private BasClaimsTypeService logic = null;
    private int page;
    private int rows;
    private String order;
    private String sort;
    private BasClaimsType basClaimsType = new BasClaimsType();

    // 新增方法
    public void doAdd(){
        Message J = new Message();
        try{
        	basClaimsType.setEnterpriseId(getNowEnterpriseId());
            boolean bool = logic.add(basClaimsType);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的索赔性质已存在，请重新输入！");
            }else
                J.setSuccess(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 删除方法
    public void doDelete()
    {
        Message J = new Message();
        try{
            logic.delete(basClaimsType);
            J.setSuccess(true);
        } catch(Exception e){
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 修改方法
    public void doUpdate()
    {
        Message J = new Message();
        try{
        	basClaimsType.setEnterpriseId(getNowEnterpriseId());
            logic.update(basClaimsType);
            J.setSuccess(true);
        }catch(Exception e){
        	  J.setSuccess(false);
              J.setMsg("对不起，您输入的索赔性质已存在，请重新输入！");
        }finally{
        	 super.writeJson(J);
        }
    }

    // 查询方法
    public void doFindAll()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        Json json = new Json();
        List<BasClaimsType> list = null;
        try
        {
            list = logic.findAll(page, rows, sort, order,getNowEnterpriseId());
            json.setTotal(Integer.parseInt(session.getAttribute("size")
                    .toString()));
            json.setRows(list);
            super.writeJson(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // 通过条件 查询 //此方法未用
    public void doFindByName() throws Exception
    {
        HttpSession sesssion = ServletActionContext.getRequest().getSession();
        basClaimsType.setEnterpriseId(getNowEnterpriseId());
        List<BasClaimsType> list = logic.findByCondition(page, rows, basClaimsType);
        if (list.size() != 0){
            Json json = new Json();
            int totlesize = (Integer) sesssion.getAttribute("totlesize");
            json.setTotal(totlesize);
            json.setRows(list);
        }
    }

    
    public BasClaimsType getModel(){
        return basClaimsType;
    }
    
    public String getOrder(){
        return order;
    }

    public void setOrder(String order){
        this.order = order;
    }

    public String getSort(){
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

    public BasClaimsTypeService getLogic()
    {
        return logic;
    }

    public void setLogic(BasClaimsTypeService logic)
    {
        this.logic = logic;
    }
}
