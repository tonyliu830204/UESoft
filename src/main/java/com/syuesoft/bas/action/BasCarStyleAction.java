package com.syuesoft.bas.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarStyleService;
import com.syuesoft.base.vo.BasCarStyleVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

public class BasCarStyleAction extends BaseAction implements
        ModelDriven<BasCarStyleVo>
{
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass());
    BasCarStyleVo basCarStyleVo = new BasCarStyleVo();
    BasCarStyleService logic = null;

    /**
     * 基础资料-->配件性质：配件款式 增加
     */
    public void add(){
        Msg msg = new Msg();
        try
        {
        	basCarStyleVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarStyleVo);
            if (isno){
                msg.setMsg("对不起，您输入的车辆款式已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
                logic.add(basCarStyleVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            logger.error("增加配件款式失败！", e);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件款式 删除
     */
    public void del(){
        Msg msg = new Msg();
        try
        {
        	basCarStyleVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isUsed(basCarStyleVo);
            if (isno)
            {
                msg.setMsg("对不起，该数据已经被使用，不允许删除！");
                msg.setSuccess(false);
            }
            else
            {
                logic.delete(basCarStyleVo);
                msg.setMsg("success");
                msg.setSuccess(true);
            }
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            msg.setSuccess(false);
            logger.error("删除配件款式失败！", e);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件款式 修改
     */
    public void update(){
        Msg msg = new Msg();
        try
        {
        	basCarStyleVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = logic.isExist(basCarStyleVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的车辆款式已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                logic.update(basCarStyleVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            logger.error("修改配件款式失败！", e);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->配件性质：配件款式 全部显示
     */
    public void view(){
        try
        {
        	basCarStyleVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasCarStyleVo> searchConditionRecord = logic.getAllByPage(basCarStyleVo);
            List<BasCarStyleVo> basCarStyleAll = logic.findAll(basCarStyleVo);
            Json json = new Json();
            json.setTotal(basCarStyleAll.size());
            json.setRows(searchConditionRecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
            logger.error("查找配件款式失败！", e);
            e.printStackTrace();
        }
    }

    /**
     * 基础资料-->车辆性质：车辆款式 查询
     * */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
    public void findAllCarType(){
        try{
        	basCarStyleVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(logic.findAllCarType(basCarStyleVo));
        }catch(Exception e){
            logger.error("查询车辆款式失败！", e);
        }
    }

    public BasCarStyleService getLogic(){
        return logic;
    }

    public void setLogic(BasCarStyleService logic){
        this.logic = logic;
    }

    public BasCarStyleVo getModel(){
        return basCarStyleVo;
    }
}
