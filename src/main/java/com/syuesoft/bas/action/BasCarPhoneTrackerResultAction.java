package com.syuesoft.bas.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarPhoneTrackerResultService;
import com.syuesoft.base.vo.BasCarPhonetrackerresultVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->车辆性质：电话跟踪结果Action
 * 
 * @author SuMing
 */
public class BasCarPhoneTrackerResultAction extends BaseAction implements
        ModelDriven<BasCarPhonetrackerresultVo>
{
    private static final long serialVersionUID = 1L;
    BasCarPhoneTrackerResultService logic = null;
    /** 电话跟踪结果Service对象 */
    BasCarPhonetrackerresultVo basCarPhonetrackerresultVo = new BasCarPhonetrackerresultVo();
    /** 电话跟踪结果Model对象 */

    /**
     * 基础资料-->车辆性质：电话跟踪结果 增加
     */
    public void add()
    {
        Msg msg = new Msg();

        try
        {
        	basCarPhonetrackerresultVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarPhonetrackerresultVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的电话跟踪结果已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.add(basCarPhonetrackerresultVo);
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
     * 基础资料-->车辆性质：电话跟踪结果 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            logic.delete(basCarPhonetrackerresultVo);
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
     * 基础资料-->车辆性质：电话跟踪结果 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basCarPhonetrackerresultVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarPhonetrackerresultVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的电话跟踪结果已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basCarPhonetrackerresultVo);
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
     * 基础资料-->车辆性质：电话跟踪结果 全部显示
     */
    public void view()
    {
        try
        {
        	basCarPhonetrackerresultVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasCarPhonetrackerresultVo> BasCarPhonetrackerresultrecord = logic
                    .getAllByPage(basCarPhonetrackerresultVo);

            /** BasCarPhonetrackerresultrecord用于存储当前页面数据 */
            List<BasCarPhonetrackerresultVo> BasCarPhonetrackerresultall = logic
                    .findAll(basCarPhonetrackerresultVo);
            /** BasCarPhonetrackerresultall用于存储当前页面数据 */
            Json json = new Json();
            json.setTotal(BasCarPhonetrackerresultall.size());
            json.setRows(BasCarPhonetrackerresultrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasCarPhoneTrackerResultService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCarPhoneTrackerResultService logic)
    {
        this.logic = logic;
    }

    public BasCarPhonetrackerresultVo getModel()
    {
        return basCarPhonetrackerresultVo;
    }
}
