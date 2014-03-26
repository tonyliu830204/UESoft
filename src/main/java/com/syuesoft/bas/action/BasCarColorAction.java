package com.syuesoft.bas.action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarColorService;
import com.syuesoft.base.vo.BasCarColorVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import java.util.List;
/**
 * 基础资料-->车辆性质：车身颜色Action
 * 
 * @author SuMing
 */
public class BasCarColorAction extends BaseAction implements
        ModelDriven<BasCarColorVo>
{
    private static final long serialVersionUID = 1L;
    private BasCarColorService logic = null;
    BasCarColorVo basCarColorVo = new BasCarColorVo();

    /**
     * 基础资料-->车辆性质：车身颜色 添加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basCarColorVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarColorVo);
            if (isno){
                msg.setMsg("对不起，您输入的车身颜色已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.add(basCarColorVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }catch(Exception e){
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->车辆性质：车身颜色 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try{
            logic.delete(basCarColorVo);
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
     * 基础资料-->车辆性质：车身颜色 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basCarColorVo.setEnterpriseId(getUserEnterpriseId());
            boolean isno = logic.isExist(basCarColorVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的车身颜色已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basCarColorVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，车身颜色修改失败！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->车辆性质：车身颜色 全部显示
     */
    public void view()
    {
        try
        {
        	basCarColorVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasCarColorVo> carbrandvorecord = logic
                    .getAllByPage(basCarColorVo);
            List<BasCarColorVo> carbrandvoall = logic.findAll(basCarColorVo);
            Json json = new Json();
            json.setTotal(carbrandvoall.size());
            json.setRows(carbrandvorecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasCarColorVo getModel()
    {
        return basCarColorVo;
    }

    public BasCarColorService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCarColorService logic)
    {
        this.logic = logic;
    }
}
