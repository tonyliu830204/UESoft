package com.syuesoft.bas.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarStatusService;
import com.syuesoft.base.vo.BasCarStatusVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->车辆性质：流失去向Action
 * 
 * @author SuMing
 */

public class BasCarStatusAction extends BaseAction implements
        ModelDriven<BasCarStatusVo>
{

    private static final long serialVersionUID = 1L;
    BasCarStatusService logic = null;
    /** 流失去向Service对象 */
    BasCarStatusVo basCarStatusVo = new BasCarStatusVo();
    /** 流失去向Model对象 */

    /**
     * 基础资料-->车辆性质：流失去向 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basCarStatusVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarStatusVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的流失去向已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.add(basCarStatusVo);
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
     * 基础资料-->车辆性质：流失去向 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            logic.delete(basCarStatusVo);
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
     * 基础资料-->车辆性质：流失去向 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basCarStatusVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarStatusVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的流失去向已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basCarStatusVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，流失去向修改失败！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->车辆性质：流失去向 全部显示
     */
    public void view()
    {
        try
        {
        	basCarStatusVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasCarStatusVo> bascarstatusrecord = logic.getAllByPage(basCarStatusVo);
            /** bascarstatusrecord用于存储当前页面数据 */
            List<BasCarStatusVo> bascarstatusall = logic.findAll(basCarStatusVo);
            /** bascarstatusall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(bascarstatusall.size());
            json.setRows(bascarstatusrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasCarStatusVo getModel()
    {
        return basCarStatusVo;
    }

    public BasCarStatusService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCarStatusService logic)
    {
        this.logic = logic;
    }
}
