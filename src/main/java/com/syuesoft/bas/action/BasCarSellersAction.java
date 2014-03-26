package com.syuesoft.bas.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarSellersService;
import com.syuesoft.base.vo.BasCarSellersVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->车辆性质：其他销售店Action
 * 
 * @author SuMing
 */
public class BasCarSellersAction extends BaseAction implements
        ModelDriven<BasCarSellersVo>
{
	BasCarSellersVo basCarSellersVo = new BasCarSellersVo();
    private static final long serialVersionUID = 1L;
    BasCarSellersService logic = null;

    /**
     * 基础资料-->车辆性质：其他销售店 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basCarSellersVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarSellersVo);
            if (isno){
                msg.setMsg("对不起，您输入的店名已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
                logic.add(basCarSellersVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->车辆性质：其他销售店 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            logic.delete(basCarSellersVo);
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
     *基础资料-->车辆性质：其他销售店 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try{
        	basCarSellersVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarSellersVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的店名已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basCarSellersVo);
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
     * 基础资料-->车辆性质：其他销售店 全部显示
     */
    public void view()
    {
        try
        {
        	basCarSellersVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasCarSellersVo> list = logic.getAllByPage(basCarSellersVo);
            List<BasCarSellersVo> list1 = logic.findAll(basCarSellersVo);
            Json json = new Json();
            json.setTotal(list1.size());
            json.setRows(list);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasCarSellersVo getModel()
    {
        return basCarSellersVo;
    }

    public BasCarSellersService getLogic()
    {
        return logic;
    }

    public void setLogic(BasCarSellersService logic)
    {
        this.logic = logic;
    }
}
