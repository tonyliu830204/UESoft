package com.syuesoft.bas.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCustomTypeService;
import com.syuesoft.base.vo.BasCustomTypeVo;
import com.syuesoft.model.BasCustomType;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasCustomTypeAction extends BaseAction implements
        ModelDriven<BasCustomTypeVo>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private BasCustomTypeService logic = null;

    // 新增方法
    public void doAdd()
    {
        Message J = new Message();
        BasCustomType b = new BasCustomType();
        b.setCstId(basCustomTypeVo.getCstId());
        b.setCstName(basCustomTypeVo.getCstName());
        b.setMemo(basCustomTypeVo.getMemo());
        b.setEnterpriseId(getNowEnterpriseId());
        try
        {
            boolean bool = logic.add(b);
            if (bool){
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户类型已存在，请重新输入！");
            }else {
                J.setSuccess(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
    }

    // 删除方法
    public void doDelete()
    {
        Message J = new Message();
        BasCustomType b = new BasCustomType();
        b.setCstId(basCustomTypeVo.getCstId());
        try{
            logic.delete(b);
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
        BasCustomType b = new BasCustomType();
        b.setCstId(basCustomTypeVo.getCstId());
        b.setCstName(basCustomTypeVo.getCstName());
        b.setMemo(basCustomTypeVo.getMemo());
        b.setEnterpriseId(getNowEnterpriseId());
        try{
            boolean bool = logic.update(b);
            if (bool) {
                J.setSuccess(false);
                J.setMsg("对不起，您输入的客户类型已存在，请重新输入！");
            } else {
                J.setSuccess(true);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        super.writeJson(J);

    }

    // 查询方法
    public void doFindAll()
    {
        Json json = new Json();
        List<BasCustomTypeVo> list = new ArrayList<BasCustomTypeVo>();
        try
        {
        	basCustomTypeVo.setEnterpriseId(getNowEnterpriseId());
            List<BasCustomType> rlist = logic.findAll(page, rows,getNowEnterpriseId());
            List<BasCustomType> listtotle = logic.getTotle(basCustomTypeVo);
            for (BasCustomType b : rlist){
                BasCustomTypeVo v = new BasCustomTypeVo();
                v.setCstId(b.getCstId());
                v.setCstName(b.getCstName());
                v.setMemo(b.getMemo());
                list.add(v);
            }
            json.setTotal(listtotle.size());
            json.setRows(list);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(json);
    }

    
    public BasCustomTypeVo getModel()
    {
        return basCustomTypeVo;
    }

    public BasCustomTypeService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCustomTypeService logic)
    {
        this.logic = logic;
    }

    private int page;

    private int rows;

    private BasCustomTypeVo basCustomTypeVo = new BasCustomTypeVo();

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
