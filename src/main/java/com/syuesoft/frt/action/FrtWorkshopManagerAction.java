package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FrtReceptionService;
import com.syuesoft.frt.service.FrtWorkshopManagerService;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;

@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("frtWorkshopManagerAction")
public class FrtWorkshopManagerAction extends BaseAction implements
        ModelDriven<FrtReceptionVo>
{
    private static final Logger logger = Logger.getLogger(FrtWorkshopManagerAction.class);
    FrtReceptionVo reVo = new FrtReceptionVo();
    @Autowired private FrtWorkshopManagerService frtWorkshopManagerService;
    @Autowired private FrtReceptionService frtReceptionService;

    /**
     * 工单汇总datagrid
     * */
    public void datagridFrtWorkshop()
    {
        try{
        	reVo.setEnterpriseId(getNowEnterpriseId());
            super .writeJson(frtWorkshopManagerService.datagridFrtWorkshop(reVo));
        }
        catch(Exception e){
            logger.error("工单汇总datagrid失败！", e);
        }
    }

    /**
     * 删除接车单
     */
    public void remove()
    {
        try
        {
            super.writeJson(frtWorkshopManagerService.remove(reVo
                    .getReceptionId()));
            // logger.info("删除接车单成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("删除接车单失败！", e);
        }
    }

    /**
     * 更新车间单
     */
    public void edit()
    {
        try
        {
            Boolean flag = frtReceptionService.isDistanceTrue(reVo);
            if (flag == true)
            {
                Msg msg = new Msg();
                msg.setMsg("对不起，本次里程数不能小于上次里程数！");
                super.writeJson(msg);
            }
            else
            {
            	reVo.setEnterpriseId(getNowEnterpriseId());
                super.writeJson(frtWorkshopManagerService.edit(reVo));
            }
            // logger.info("更新车间单成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("更新车间单失败！", e);
        }
    }

    /**
     * 从数据库中查询维修配件
     */
    public void findPartsByRcptId()
    {
        try
        {
            super.writeJson(frtWorkshopManagerService.findPartsByRcptId(reVo
                    .getReceptionId()));
            // logger.info("数据库中查询维修配件成功！");
        }
        catch(Exception e)
        {
            logger.error("数据库中查询维修配件失败！", e);
        }
    }

    /**
     * 从数据库中查询维修项目
     */
    public void findItemByRcptId()
    {
        try
        {
            super.writeJson(frtWorkshopManagerService.findItemByRcptId(reVo
                    .getReceptionId()));
        }
        catch(Exception e)
        {
            logger.error("数据库中查询维修项目失败！", e);
        }
    }

    /**
     * 从数据库中查询车身状况放入session
     */
    public void findvehicleStructureList()
    {
        try
        {
            super.writeJson(frtWorkshopManagerService.findVehicleStructure(reVo));
        }
        catch(Exception e)
        {
            logger.error("数据库中查询车身状况失败！", e);
        }
    }

    /**
     * 增加自定义维修项目
     * */
    public void addFrtWorkshopManagerItem()
    {
        try
        {
        	reVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtWorkshopManagerService
                    .addFrtWorkshopManagerItem(reVo));
        }
        catch(Exception e)
        {
            logger.error("增加自定义维修项目失败！", e);
        }
    }

    /**
     * 出料信息显示
     */
    public void datagridEmerge()
    {
        try{
            super.writeJson(frtWorkshopManagerService.datagridEmerge(reVo));
        }catch(Exception e){
            logger.error("出料查询失败！", e);
        }
    }

    /**
     * 转前台
     * */
    public void modifyCastProcenium()
    {
        try
        {
            super.writeJson(frtWorkshopManagerService.modifyCastProcenium(reVo));
        }
        catch(Exception e)
        {
            logger.error("转前台失败！", e);
        }
    }
    /**
     * 更改工单状态
     * */
    public void modifyReceptionStatus(){
    	try
        {
    		reVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(frtWorkshopManagerService.modifyReceptionStatus(reVo));
        }
        catch(Exception e)
        {
            logger.error("更改工单状态失败！", e);
        }
    }
    
    
    public FrtReceptionVo getModel()
    {
        return reVo;
    }
    
    public FrtReceptionVo getReVo()
    {
        return reVo;
    }

    public void setReVo(FrtReceptionVo reVo)
    {
        this.reVo = reVo;
    }

    
}
