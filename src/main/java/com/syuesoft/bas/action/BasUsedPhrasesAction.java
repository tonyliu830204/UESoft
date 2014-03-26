package com.syuesoft.bas.action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasUsedPhrasesService;
import com.syuesoft.model.BasUsedPhrases;
import com.syuesoft.util.Message;

public class BasUsedPhrasesAction extends BaseAction implements
        ModelDriven<BasUsedPhrases>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasUsedPhrasesService logic;
    private BasUsedPhrases basUsedPhrases = new BasUsedPhrases();

    /*
     * 基础数据常用短语新增方法
     */
    public String doAdd(){
        Message J = new Message();
        try{
        	basUsedPhrases.setEnterpriseId(getUserEnterpriseId());
            boolean bool = logic.add(basUsedPhrases);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的常用短语已存在，请重新输入！");
            }else
                J.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 修改方法
    public String doUpdate(){
        Message J = new Message();
        try{
        	basUsedPhrases.setEnterpriseId(getUserEnterpriseId());
            boolean bool = logic.update(basUsedPhrases);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的常用短语已存在，请重新输入！");
            }else
                J.setSuccess(true);
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 查询所有
	public String doFindAll()
    {
        try{
            super.writeJson(logic.findAll(page, rows, sort, order,getNowEnterpriseId()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 删除方法
    public String doDelete() throws Exception
    {
        Message J = new Message();
        try{
            logic.delete(basUsedPhrases);
            J.setSuccess(true);
        }catch(Exception e){
            J.setSuccess(false);
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 按条件查询
	public String findByCondition(){
        try{
        	basUsedPhrases.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(logic.findByCondition(basUsedPhrases, page, rows));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    
    public BasUsedPhrases getModel()
    {
        return basUsedPhrases;
    }
    
    private int page;

    private int rows;

    private String order;

    private String sort;

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

    public BasUsedPhrasesService getLogic()
    {
        return logic;
    }

    public void setLogic(BasUsedPhrasesService logic)
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
