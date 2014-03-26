package com.syuesoft.bas.action;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCustomerNatureService;
import com.syuesoft.base.vo.BasCustomNatureVo;
import com.syuesoft.model.BasCustomNature;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasCustomerNatureAction extends BaseAction implements
        ModelDriven<BasCustomNature>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    private BasCustomNature basCustomNature = new BasCustomNature();
    private BasCustomerNatureService logic;
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
        	basCustomNature.setEnterpriseId(getNowEnterpriseId());
            boolean bool = logic.add(basCustomNature);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户性质已存在，请重新输入！");
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
    public void doDelete() throws Exception
    {
        Message J = new Message();
        try{
            logic.delete(basCustomNature);
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
        try
        {
            boolean bool = logic.update(basCustomNature);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户性质已存在，请重新输入！");
            }else
                J.setSuccess(true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 查询方法
    public void doFindAll()
    {
        List<BasCustomNatureVo> list = new ArrayList<BasCustomNatureVo>();
        Json json = new Json();
        try
        {
        	basCustomNature.setEnterpriseId(getNowEnterpriseId());
        	List<BasCustomNature>  listtotle = logic.getTotle(basCustomNature);
            List<BasCustomNature> rlist = logic
                    .findAll(page, rows, sort, order,getNowEnterpriseId());
            for (BasCustomNature basCustomNature : rlist)
            {
                BasCustomNatureVo vo = new BasCustomNatureVo();
                vo.setNatureId(basCustomNature.getNatureId());
                vo.setMemo(basCustomNature.getMemo());
                vo.setNatureName(basCustomNature.getNatureName());
                list.add(vo);
            }
            json.setTotal(listtotle.size());
            json.setRows(list);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(json);

    }

    
    public BasCustomNature getModel()
    {
        return basCustomNature;
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

    public BasCustomerNatureService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCustomerNatureService logic)
    {
        this.logic = logic;
    }
}
