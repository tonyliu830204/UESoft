package com.syuesoft.bas.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.FbkDcserveyNameService;
import com.syuesoft.model.FbkDcserveyName;
import com.syuesoft.util.Message;

@ParentPackage(value = "basePackage")
@Action("fbkDcserveyNameAction")
public class FbkDcserveyNameAction extends BaseAction implements
        ModelDriven<FbkDcserveyName>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private FbkDcserveyName fbkDcserveyName = new FbkDcserveyName();
    @Autowired
    private FbkDcserveyNameService fbkDcserveyNameService;
    private int page;
    private int rows;
    private String order;
    private String sort;
   
    // 新增方法
    public void doAdd()
    {
        Message J = new Message();
        try{
        	fbkDcserveyName.setEnterpriseId(getUserEnterpriseId());
            boolean bool = fbkDcserveyNameService.add(fbkDcserveyName);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户回访项目已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 删除方法
    public void doDelete(){
        Message J = new Message();
        try{
            fbkDcserveyNameService.delete(fbkDcserveyName);
            J.setSuccess(true);
        }catch(Exception e){
        	J.setSuccess(false);
            e.printStackTrace();
        }finally{
        	super.writeJson(J);
        }
    }

    // 修改方法
    public void doUpdate(){
        Message J = new Message();
        try
        {
        	fbkDcserveyName.setEnterpriseId(getUserEnterpriseId());
            boolean bool = fbkDcserveyNameService.update(fbkDcserveyName);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户回访项目已存在，请重新输入！");
            }else
                J.setSuccess(true);
        } catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);

    }

    // 查询方法
    public void doFindAll()
    {
        try{
            super.writeJson(fbkDcserveyNameService.findAll(page, rows, order, sort,getNowEnterpriseId()));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    
    public FbkDcserveyName getModel()
    {
        return fbkDcserveyName;
    }
    
    public FbkDcserveyNameService getFbkDcserveyNameService()
    {
        return fbkDcserveyNameService;
    }

    public void setFbkDcserveyNameService(
            FbkDcserveyNameService fbkDcserveyNameService)
    {
        this.fbkDcserveyNameService = fbkDcserveyNameService;
    }

    public int getPage()
    {
        return page;
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
