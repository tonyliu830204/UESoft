package com.syuesoft.bas.action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarPartsService;
import com.syuesoft.base.vo.BasCarPartsVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import java.util.List;

/**
 * 基础资料-->车辆性质：车辆部位Action
 * 
 * @author SuMing
 */
public class BasCarPartsAction extends BaseAction implements
        ModelDriven<BasCarPartsVo>
{
    private static final long serialVersionUID = 1L;
    BasCarPartsService logic = null;
    /** 车辆部位Service对象 */
    BasCarPartsVo basCarPartsVo = new BasCarPartsVo();
    /** 车辆部位Model对象 */

    /**
     * 基础资料-->车辆性质：车辆部位 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try{
        	basCarPartsVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarPartsVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的车辆部位已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.add(basCarPartsVo);
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
     * 基础资料-->车辆性质：车辆部位 删除
     */
    public void del()
    {
        Msg msg = new Msg();

        try
        {
            logic.delete(basCarPartsVo);
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
     * 基础资料-->车辆性质：车辆部位 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basCarPartsVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarPartsVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的车辆部位已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basCarPartsVo);
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
     * 基础资料-->车辆性质：车辆部位 全部显示
     */
    public void view()
    {
        try{
        	basCarPartsVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasCarPartsVo> bascarpartsrecord = logic.getAllByPage(basCarPartsVo);
            /** bascarpartsrecord用于存储当前页面数据 */
            List<BasCarPartsVo> bascarpartsall = logic.findAll(basCarPartsVo);
            /** bascarpartsall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(bascarpartsall.size());
            json.setRows(bascarpartsrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasCarPartsVo getModel()
    {
        return basCarPartsVo;
    }

    public BasCarPartsService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCarPartsService logic)
    {
        this.logic = logic;
    }
}
