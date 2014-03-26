package com.syuesoft.bas.action;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasCarBodysStatusService;
import com.syuesoft.base.vo.BasCarBodysStatusVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 基础资料-->车辆性质：车身状态Action
 * @author SuMing
 */
@ParentPackage(value = "basePackage")
@Action("basCarBodysStatusAction")
public class BasCarBodysStatusAction extends BaseAction implements
        ModelDriven<BasCarBodysStatusVo>
{
	
    private static final long serialVersionUID = 1L;
    @Autowired
    BasCarBodysStatusService basCarBodysStatusService = null;
    /** 车身状态Service对象 */
    BasCarBodysStatusVo basCarBodysStatusVo = new BasCarBodysStatusVo();

    /** 车身状态Model对象 */

    /**
     * 基础资料-->车辆性质：车身状态 添加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
            boolean isno = basCarBodysStatusService.isExist(basCarBodysStatusVo);
            if (isno){
                msg.setMsg("对不起，您输入的车身编码已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
                basCarBodysStatusService.add(basCarBodysStatusVo);
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
     * 基础资料-->车辆性质：车身状态 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try{
            basCarBodysStatusService.delete(basCarBodysStatusVo);
            msg.setMsg("success");
            msg.setSuccess(true);
            super.writeJson(msg);
        }catch(Exception e){
            msg.setMsg("error");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->车辆性质：车身状态 修改
     */
    public void update(){
        Msg msg = new Msg();
        try
        {
            boolean isno = basCarBodysStatusService.isExist(basCarBodysStatusVo);
            if (isno){
                msg.setMsg("对不起，您输入的车身编码已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else{
                basCarBodysStatusService.update(basCarBodysStatusVo);
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
     * 基础资料-->车辆性质：车身状态 全部显示
     */
    public void view()
    {
        try{
            List<BasCarBodysStatusVo> bascarbodystatusrecord = basCarBodysStatusService.getAllByPage(basCarBodysStatusVo);
            /** bascarbodystatusrecord用于存储当前页面数据 */
            List<BasCarBodysStatusVo> bascarbodystatusall = basCarBodysStatusService.findAll(basCarBodysStatusVo);
            /** bascarbodystatusall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(bascarbodystatusall.size());
            json.setRows(bascarbodystatusrecord);
            super.writeJson(json);
        }catch(Exception e){
        	
        }
    }

    public BasCarBodysStatusVo getModel()
    {
        return basCarBodysStatusVo;
    }
}