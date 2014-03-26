package com.syuesoft.bas.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPartsUnitService;
import com.syuesoft.base.vo.BasPartsUnitVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->配件性质：计量单位Action
 * 
 * @author SuMing
 */

public class BasPartsUnitAction extends BaseAction implements
        ModelDriven<BasPartsUnitVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    BasPartsUnitService logic = null;

    /** 计量单位Service对象 */
    BasPartsUnitVo basPartsUnitVo = new BasPartsUnitVo();

    /** 计量单位Model对象 */

    /**
     * 基础资料-->配件性质：计量单位 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsUnitVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basPartsUnitVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的计量单位已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
                logic.add(basPartsUnitVo);
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
     * 基础资料-->配件性质：计量单位 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            logic.delete(basPartsUnitVo);
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
     * 基础资料-->配件性质：计量单位 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsUnitVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basPartsUnitVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的计量单位已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basPartsUnitVo);
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
     * 基础资料-->配件性质：计量单位 全部显示
     */
    public void view()
    {
        try
        {
        	basPartsUnitVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasPartsUnitVo> baspartsunitrecord = logic.getAllByPage(basPartsUnitVo);
            /** baspartsunitrecord用于存储当前页面数据 */
            List<BasPartsUnitVo> baspartsunitall = logic.findAll(basPartsUnitVo);
            /** baspartsunitall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(baspartsunitall.size());
            json.setRows(baspartsunitrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasPartsUnitVo getModel()
    {
        return basPartsUnitVo;
    }

    public BasPartsUnitService getLogic()
    {
        return logic;
    }

    public void setLogic(BasPartsUnitService logic)
    {
        this.logic = logic;
    }

}
