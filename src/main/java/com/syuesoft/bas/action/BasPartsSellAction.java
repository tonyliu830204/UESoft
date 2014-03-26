package com.syuesoft.bas.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPartsSellService;
import com.syuesoft.base.vo.BasPartsSellVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->配件性质：配件销售分类Action类
 * 
 * @author SuMing
 * 
 */

public class BasPartsSellAction extends BaseAction implements
        ModelDriven<BasPartsSellVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    BasPartsSellService logic = null;

    /** 配件销售分类Service对象 */
    BasPartsSellVo basPartsSellVo = new BasPartsSellVo();

    /** 配件销售分类Model对象 */

    /**
     * 基础资料-->配件性质：配件销售分类 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsSellVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basPartsSellVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的配件销售分类已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.add(basPartsSellVo);
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
     * 基础资料-->配件性质：配件销售分类 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            logic.delete(basPartsSellVo);
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
     * 基础资料-->配件性质：配件销售分类 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsSellVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basPartsSellVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的配件销售分类已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basPartsSellVo);
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
     * 基础资料-->配件性质：配件销售分类 全部显示
     */
    public void view()
    {
        try{
        	basPartsSellVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasPartsSellVo> baspartssellrecord = logic.getAllByPage(basPartsSellVo);
            /** baspartssellrecord用于存储当前页面数据 */
            List<BasPartsSellVo> baspartssellall = logic.findAll(basPartsSellVo);
            /** baspartssellall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(baspartssellall.size());
            json.setRows(baspartssellrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasPartsSellVo getModel()
    {
        return basPartsSellVo;
    }

    public BasPartsSellService getLogic()
    {
        return logic;
    }

    public void setLogic(BasPartsSellService logic)
    {
        this.logic = logic;
    }

}
