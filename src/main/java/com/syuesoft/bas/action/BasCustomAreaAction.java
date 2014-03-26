package com.syuesoft.bas.action;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCustomAreaService;
import com.syuesoft.base.vo.BasCustomAreaVo;
import com.syuesoft.model.BasCustomArea;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;

public class BasCustomAreaAction extends BaseAction implements
        ModelDriven<BasCustomArea>
{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    private BasCustomArea basCustomArea = new BasCustomArea();
    private BasCustomAreaService logic = null;
    private int page;
    private int rows;
    private String order;
    private String sort;

    // 新增方法
    public String doAdd()
    {
        Message J = new Message();
        try
        {
            if (basCustomArea.getPareaZip().length() > 6){
                J.setSuccess(false);
                J.setMsg("对不起，你输入的邮政编码已超过六位，请重新输入！");
            }else{
            	basCustomArea.setEnterpriseId(getUserEnterpriseId());
                boolean bool = logic.add(basCustomArea);
                if (bool){
                    J.setSuccess(false);
                    J.setMsg("对不起，您输入的区域名称已存在，请重新输入！");
                }else{
                    J.setSuccess(true);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        super.writeJson(J);
        return null;
    }

    // 删除方法
    public String doDelete()
    {
        Message J = new Message();
        try{
            logic.delete(basCustomArea);
            J.setSuccess(true);
        }catch(Exception e){
        	J.setSuccess(false);
            e.printStackTrace();
        }finally{
        	super.writeJson(J);
        }
        return null;
    }

    // 修改方法
    public String doUpdate()
    {
        Message J = new Message();
        try
        {
            if (basCustomArea.getPareaZip().length() > 6){
                J.setSuccess(false);
                J.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            }else{
            	basCustomArea.setEnterpriseId(getUserEnterpriseId());
                boolean bool = logic.update(basCustomArea);
                if (bool){
                    J.setSuccess(false);
                    J.setMsg("对不起，您输入的区域名称已存在，请重新输入！");
                }else
                    J.setSuccess(true);
            }
        }
        catch(Exception e){
        }
        super.writeJson(J);
        return null;
    }

    // 查询方法
    public String doFindAll()
    {
        Json json = new Json();
        List<BasCustomAreaVo> list = new ArrayList<BasCustomAreaVo>();
        try
        {
        	basCustomArea.setEnterpriseId(getNowEnterpriseId());
            List<BasCustomArea> basCustomArealist = logic.findAll(page, rows,
                    sort, order,getNowEnterpriseId());
            List<BasCustomArea>  listtotle = logic.getTotle(basCustomArea);
            json.setTotal(listtotle.size());
            for (BasCustomArea basCustomArea : basCustomArealist){
                BasCustomAreaVo btmvo = new BasCustomAreaVo();
                btmvo.setPareaId(basCustomArea.getPareaId() + "");
                btmvo.setPareaName(basCustomArea.getPareaName());
                btmvo.setPareaZip(basCustomArea.getPareaZip());
                list.add(btmvo);
            }

            json.setRows(list);
            super.writeJson(json);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public BasCustomArea getModel()
    {
        return basCustomArea;
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

    public BasCustomAreaService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCustomAreaService logic)
    {
        this.logic = logic;
    }
}
