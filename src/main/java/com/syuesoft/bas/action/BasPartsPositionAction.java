package com.syuesoft.bas.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasPartsPositionService;
import com.syuesoft.base.vo.BasPartsPositionVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Json;
import com.syuesoft.util.Msg;

/**
 * 基础资料-->配件性质：配件部位Action
 * 
 * @author SuMing
 */
@Action("BasPartsPositionAction")
public class BasPartsPositionAction extends BaseAction implements
        ModelDriven<BasPartsPositionVo>
{

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    BasPartsPositionVo basPartsPositionVo = new BasPartsPositionVo();

    /** 配件部位Service对象 */
    @Autowired
    BasPartsPositionService basPartsPositionService;

    /** 配件部位Model对象 */

    /**
     * 基础资料-->配件性质：配件部位 增加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsPositionVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = basPartsPositionService.isExist(basPartsPositionVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的配件部位已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                basPartsPositionService.add(basPartsPositionVo);
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
     * 基础资料-->配件性质：配件部位 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            basPartsPositionService.delete(basPartsPositionVo);
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
     * 基础资料-->配件性质：配件部位 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
        	basPartsPositionVo.setEnterpriseId(getUserEnterpriseId());//当前系统所属公司序号
            boolean isno = basPartsPositionService.isExist(basPartsPositionVo);
            if (isno){
                msg.setMsg("对不起，您输入的配件部位已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }
            else
            {
                basPartsPositionService.update(basPartsPositionVo);
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
     * 基础资料-->配件性质：配件部位 全部显示
     */
    public void view()
    {
        try
        {
        	basPartsPositionVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            List<BasPartsPositionVo> baspartspositionrecord = basPartsPositionService
                    .getAllByPage(basPartsPositionVo);
            /** baspartspositionrecord用于存储当前页面数据 */
            List<BasPartsPositionVo> baspartspositionall = basPartsPositionService.findAll(basPartsPositionVo);
            /** baspartspositionall用于存储所有页面数据 */
            Json json = new Json();
            json.setTotal(baspartspositionall.size());
            json.setRows(baspartspositionrecord);
            super.writeJson(json);
        }
        catch(Exception e)
        {
        }
    }

    public BasPartsPositionVo getModel()
    {
        return basPartsPositionVo;
    }

}
