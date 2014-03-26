package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPartsStateService;
import com.syuesoft.base.vo.BasPartsStateVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
@Action("BasPartsStateAction")
public class BasPartsStateAction extends BaseAction implements
        ModelDriven<BasPartsStateVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    @Autowired
    BasPartsStateService basPartsStateService;

    BasPartsStateVo basPartsStateVo = new BasPartsStateVo();

    /**
     * 基础资料-->配件性质：配件产地 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsStateVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = basPartsStateService.isExist(basPartsStateVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的配件产地已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
                basPartsStateService.add(basPartsStateVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件产地 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            basPartsStateService.delete(basPartsStateVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件产地 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
            boolean isno = basPartsStateService.isExist(basPartsStateVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的配件产地已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                basPartsStateService.update(basPartsStateVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件产地 全部显示
     */
    public void view()
    {
        try
        {
        	basPartsStateVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasPartsStateVo> baspartssellrecord = basPartsStateService
                    .getAllByPage(basPartsStateVo);
            List<BasPartsStateVo> baspartssellall = basPartsStateService.findAll(basPartsStateVo);
            Json json = new Json();
            json.setTotal(baspartssellall.size());
            json.setRows(baspartssellrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }
    
    public BasPartsStateVo getModel()
    {
        // TODO Auto-generated method stub
        return basPartsStateVo;
    }

}
