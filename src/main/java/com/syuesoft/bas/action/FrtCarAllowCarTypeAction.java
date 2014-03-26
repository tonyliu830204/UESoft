package com.syuesoft.bas.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.FrtCarAllowCarTypeService;
import com.syuesoft.base.vo.FrtCarAllowCarTypeVo;
import com.syuesoft.util.Msg;

@ParentPackage(value = "basePackage")
@Action("frtCarAllowCarTypeAction")
public class FrtCarAllowCarTypeAction extends BaseAction implements
        ModelDriven<FrtCarAllowCarTypeVo>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private FrtCarAllowCarTypeService frtCarAllowCarTypeService;
    FrtCarAllowCarTypeVo fcactVo = new FrtCarAllowCarTypeVo();
    /**
     * 基础资料-->车辆性质：准驾车型 添加
     */
    public void add()
    {
        Msg msg = new Msg();
        try
        {
            boolean isno = frtCarAllowCarTypeService.isExist(fcactVo);
            if (isno)
            {
                msg.setMsg("对不起，您输入的准驾车型已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            }else
            {
                frtCarAllowCarTypeService.add(fcactVo);
                msg.setMsg("success");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            logger.error("准驾车型保存失败！", e);
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->车辆性质：准驾车型 删除
     */
    public void del()
    {
        Msg msg = new Msg();
        try
        {
            frtCarAllowCarTypeService.delete(fcactVo);
            msg.setMsg("准驾车型删除成功！");
            msg.setSuccess(true);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            logger.error("准驾车型删除失败！", e);
            msg.setMsg("对不起，该数据已经被使用，不允许删除！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     *基础资料-->车辆性质：准驾车型 修改
     */
    public void update()
    {
        Msg msg = new Msg();
        try
        {
            boolean isno = frtCarAllowCarTypeService.isExist(fcactVo);
            if (isno){
                msg.setMsg("对不起，您输入的准驾车型已存在，请重新输入！");
                msg.setSuccess(false);
                super.writeJson(msg);
            } else{
                frtCarAllowCarTypeService.update(fcactVo);
                msg.setMsg("准驾车型修改成功！");
                msg.setSuccess(true);
                super.writeJson(msg);
            }
        }
        catch(Exception e)
        {
            logger.error("准驾车型修改失败！", e);
            msg.setMsg("对不起，记录无法保存，请确认内容及格式是否正确！");
            msg.setSuccess(false);
            super.writeJson(msg);
        }
    }

    /**
     * 基础资料-->车辆性质：准驾车型 分页显示
     */
    public void view()
    {
        try
        {
            super.writeJson(frtCarAllowCarTypeService.findAll(fcactVo));
        }
        catch(Exception e)
        {
            logger.error("准驾车型查询失败！", e);
        }
    }

    
    public FrtCarAllowCarTypeVo getModel()
    {
        return fcactVo;
    }

}
