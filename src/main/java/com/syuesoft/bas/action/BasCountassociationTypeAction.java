package com.syuesoft.bas.action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCountassociationTypeService;
import com.syuesoft.model.BasCountassociationType;
import com.syuesoft.util.Message;

public class BasCountassociationTypeAction extends BaseAction implements
        ModelDriven<BasCountassociationType>
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private BasCountassociationTypeService logic = null;

    private int page;

    private int rows;

    private String order;

    private String sort;

    private BasCountassociationType basCountassociationType = new BasCountassociationType();

    // 新增方法
    public void doAdd()
    {
        Message J = new Message();
        try{
        	basCountassociationType.setEnterpriseId(getNowEnterpriseId());
            boolean bool = logic.add(basCountassociationType);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的技协费分类已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }catch(Exception e){
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
            logic.delete(basCountassociationType);
            J.setSuccess(true);
        }catch(Exception e){
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
        	basCountassociationType.setEnterpriseId(getNowEnterpriseId());
            boolean bool = logic.update(basCountassociationType);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的技协费分类已存在，请重新输入！");
            }else{
                J.setSuccess(true);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 查询方法
    public void doFindAll()
    {
        try{
        	super.writeJson(logic.findAll(page, rows,sort, order,getNowEnterpriseId()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
    public BasCountassociationType getModel(){
        return basCountassociationType;
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

    public BasCountassociationTypeService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCountassociationTypeService logic)
    {
        this.logic = logic;
    }
}
