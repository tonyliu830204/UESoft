package com.syuesoft.bas.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.StageAddpriceService;
import com.syuesoft.base.vo.StageAddpriceVo;
import com.syuesoft.util.Msg;

/**
 * 剃度加价模块Action
 * @author SuMing
 */
@ParentPackage(value = "basePackage")
@Action("StageAddpriceAction")
public class StageAddpriceAction extends BaseAction implements
        ModelDriven<StageAddpriceVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    StageAddpriceVo stageAddpriceVo = new StageAddpriceVo();

    @Autowired
    StageAddpriceService logic;

    /**
     * 基础资料 配件性质 梯度信息 全查
     */
    public void view()
    {
        try{
        	stageAddpriceVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(logic.findAll(stageAddpriceVo));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 基础资料 配件性质 梯度信息 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	stageAddpriceVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
        	msg=logic.isOld(stageAddpriceVo);
        	if(msg.getSuccess()){
                logic.add(stageAddpriceVo);
                msg.setMsg("success");
                msg.setSuccess(true);
        	}
        	super.writeJson(msg);
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
     * 基础资料 配件性质 梯度信息 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            logic.delete(stageAddpriceVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料 配件性质 梯度信息 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	stageAddpriceVo.setEnterpriseId(getNowEnterpriseId());
            logic.update(stageAddpriceVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }catch(Exception e){
            e.printStackTrace();
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    public StageAddpriceService getLogic(){
        return logic;
    }

    public void setLogic(StageAddpriceService logic){
        this.logic = logic;
    }

    
    public StageAddpriceVo getModel(){
        return stageAddpriceVo;
    }

}
