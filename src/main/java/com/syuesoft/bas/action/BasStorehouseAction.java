package com.syuesoft.bas.action;

import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasStorehouseService;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->配件性质：仓别分类Action
 * @author SuMing
 */
public class BasStorehouseAction extends BaseAction implements
        ModelDriven<BasStorehouseVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    BasStorehouseVo basStorehouseVo = new BasStorehouseVo();
    /** 仓别分类Service对象 */
    BasStorehouseService logic = null;
    /** 仓别分类Model对象 */

    /**
     * 基础资料-->配件性质：仓别分类 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
            basStorehouseVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basStorehouseVo);
            if (isno){
                msg.setMsg("对不起，您输入的仓别名称已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.add(basStorehouseVo);
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
     * 基础资料-->配件性质：仓别分类 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
        	basStorehouseVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean inso = logic.isUsed(basStorehouseVo);
            if (inso){
                msg.setMsg("对不起，该数据已经被使用，不允许删除！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.delete(basStorehouseVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：仓别分类 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try{
        	basStorehouseVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basStorehouseVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的仓别名称已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basStorehouseVo);
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
     * 基础资料-->配件性质：仓别分类 全部显示
     */
    public void view()
    {
        try
        {
        	basStorehouseVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasStorehouseVo> list = logic.getAllByPage(basStorehouseVo);
            List<BasStorehouseVo> list1 = logic.findAll(basStorehouseVo);
            Json json = new Json();
            json.setTotal(list1.size());
            json.setRows(list);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasStorehouseVo getModel()
    {
        return basStorehouseVo;
    }

    public BasStorehouseService getLogic()
    {
        return logic;
    }

    public void setLogic(BasStorehouseService logic)
    {
        this.logic = logic;
    }
}
