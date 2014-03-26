package com.syuesoft.bas.action;

import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasWorkhourSortService;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasWorkhourSort;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("basWorkhourSorAction")
public class BasWorkhourSorAction extends BaseAction implements
        ModelDriven<BasWorkhourSort>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasWorkhourSort basWorkhourSort = new BasWorkhourSort();
    private int page;
    private int rows;
    private String sort;
    private String order;
    @Autowired
    private BasWorkhourSortService basWorkhourSorService;
    
    // 工时分类新增方法
    public void doAdd()
    {
        Message msg = new Message();
        try{
        	basWorkhourSort.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            boolean bool = basWorkhourSorService.doAdd(basWorkhourSort);
            if (bool){
                msg.setSuccess(false);
                msg.setMsg("对不起，您输入的工时分类已存在，请重新输入！");
            }else{
                msg.setSuccess(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(msg);
    }

    // 工时分类修改方法
    public void doUpdate()
    {
        Message msg = new Message();
        try{
            basWorkhourSorService.doUpdate(basWorkhourSort);
            msg.setSuccess(true);
        }catch(Exception e){
        	 msg.setSuccess(false);
             msg.setMsg("对不起，您输入的工时分类已存在，请重新输入！");
        }finally{
        	super.writeJson(msg);
        }
    }

    // 工时分类删除方法
    public void doDelete()
    {
        Message msg = new Message();
        try{
            basWorkhourSorService.doDelete(basWorkhourSort);
            msg.setSuccess(true);
        }catch(Exception e){
            msg.setSuccess(false);
            e.printStackTrace();
        }finally{
        	 super.writeJson(msg);
        }
    }

    // 工时分类查询方法
    public String doFindAll()
    {
        Json J = new Json();
        try{
        	List<BasWorkhourSort> rlist = basWorkhourSorService.doFindAll(page, rows, sort,
                    order,getNowEnterpriseId());
            J.setRows(rlist);
            String size = ServletActionContext.getRequest().getSession().getAttribute("size").toString();
            J.setTotal(Integer.parseInt(size));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
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
    
    public BasWorkhourSortService getBasWorkhourSorService()
    {
        return basWorkhourSorService;
    }

    public void setBasWorkhourSorService(
            BasWorkhourSortService basWorkhourSorService)
    {
        this.basWorkhourSorService = basWorkhourSorService;
    }

    
    public BasWorkhourSort getModel()
    {
        return basWorkhourSort;
    }

}
