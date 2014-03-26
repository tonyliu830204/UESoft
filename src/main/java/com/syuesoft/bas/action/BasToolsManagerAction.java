package com.syuesoft.bas.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasToolsManagerService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.base.vo.ToolsManagerVo;
import com.syuesoft.util.Msg;

/**
 * 工具管理action
 * 
 * @author Liujian
 * 
 */
@Controller
@Scope("prototype")
public class BasToolsManagerAction extends BaseAction implements
        ModelDriven<ToolsManagerVo>
{

    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    ToolsManagerVo tmVo = new ToolsManagerVo();

    private final Logger logger = Logger.getLogger(this.getClass());

    private BasToolsManagerService basToolsManagerService;

    private BaseService baseService;

    public BasToolsManagerService getBasToolsManagerService()
    {
        return basToolsManagerService;
    }

    @Autowired
    public void setBasToolsManagerService(
            BasToolsManagerService basToolsManagerService)
    {
        this.basToolsManagerService = basToolsManagerService;
    }

    public BaseService getBaseService()
    {
        return baseService;
    }

    @Autowired
    public void setBaseService(BaseService baseService)
    {
        this.baseService = baseService;
    }

    /**
     * 查询配件单位
     */
    public void findAllPartsUnit()
    {
        try
        {
        	
            super.writeJson(baseService.findAllPartsUnit(getNowEnterpriseId()));
        }
        catch(Exception e)
        {
            logger.error("查询配件单位失败", e);
        }
    }

    /**
     * 查询供应商
     * 
     * @return
     */
    public String findAllCampany()
    {

        try
        {
            super.writeJson(basToolsManagerService.findAllCampany());
        }
        catch(Exception e)
        {
            logger.error("查询供应商失败", e);
        }

        return null;
    }

    /**
     * 查找采购员
     * 
     * @return
     */
    public String findAllStuff()
    {

        try
        {
            super.writeJson(basToolsManagerService.findAllStuff());
        }
        catch(Exception e)
        {
            logger.error("查找采购员失败", e);
        }

        return null;
    }

    /**
     * 带条件查询数据的方法
     * 
     * @return
     */
    public String findAll()
    {

        try
        {
            super.writeJson(basToolsManagerService.findAll(tmVo));
        }
        catch(Exception e)
        {
            logger.error("带条件查询数据的方法失败", e);
        }

        return null;
    }

    /**
     * 保存数据的方法
     * 
     * @return
     */
    public String save()
    {
        Msg msg = new Msg();
        try
        {
            basToolsManagerService.save(tmVo);
            msg.setSuccess(true);
            msg.setMsg("新增工具[" + tmVo.getToolsName() + "]成功!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(msg);

        return null;
    }

    /**
     * 根据id删除数据的方法
     * 
     * @return
     */
    public String delete()
    {

        Msg msg = new Msg();
        try
        {
            basToolsManagerService.delete(tmVo.getToolsId());
            msg.setSuccess(true);
            msg.setMsg("删除工具[" + tmVo.getToolsName() + "]成功!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(msg);

        return null;
    }

    /**
     * 修改(更新)数据的方法
     * 
     * @return
     */
    public String edit()
    {
        Msg msg = new Msg();
        try
        {
            basToolsManagerService.update(tmVo);
            msg.setSuccess(true);
            msg.setMsg("修改工具[" + tmVo.getToolsName() + "]成功!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        super.writeJson(msg);

        return null;
    }

    
    public ToolsManagerVo getModel()
    {
        return tmVo;
    }
}
