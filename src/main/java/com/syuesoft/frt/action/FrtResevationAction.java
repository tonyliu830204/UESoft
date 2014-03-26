package com.syuesoft.frt.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FrtResevationService;
import com.syuesoft.frt.service.FrtService;
import com.syuesoft.frt.vo.FrtResevationVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.model.FrtResevation;
import com.syuesoft.model.FrtResvVehicleStructure;
import com.syuesoft.util.Msg;

@ParentPackage(value = "basePackage")
@Action("frtResevationAction")
public class FrtResevationAction extends BaseAction implements
        ModelDriven<FrtResevationVo>
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(FrtResevationAction.class);

    FrtResevationVo freVo = new FrtResevationVo();

    private FrtResevationService frtResevationService;

    private FrtService frtService;

    public FrtResevationService getFrtResevationService()
    {
        return frtResevationService;
    }

    @Autowired
    public void setFrtResevationService(
            FrtResevationService frtResevationService)
    {
        this.frtResevationService = frtResevationService;
    }

    public FrtService getFrtService()
    {
        return frtService;
    }

    @Autowired
    public void setFrtService(FrtService frtService)
    {
        this.frtService = frtService;
    }
    //根据预约单id查询给预约单信息是否还存在
    public  void  findResevationById(){
    	String  resvId=freVo.getResvId();
        try
        {
        	if(freVo.getPage()==0){
        		freVo.setPage(1);
        	}
        	if(freVo.getRows()==0){
        		freVo.setRows(10);
        	}
            super.writeJson(frtResevationService.datagridFrtResevation(false,freVo));
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询预约单信息失败！", e);
        }
    	
    }
    /**
     * 判断预约单是否取消
     * */
    public void modifyIsBespeakOff(FrtResevationVo freVo)
    {
        try
        {
            frtResevationService.modifyIsBespeakOff( freVo);
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("判断预约单是否取消失败！", e);
        }
    }

    /**
     * 取消预约单
     * */
    public void modifyIsBespeakOffById()
    {
        try
        {
            Msg msg = frtResevationService.modifyIsBespeakOffById(freVo);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("判断某个预约单是否取消失败！", e);
        }
    }
    
    /**
     * 判断某个预约单是否取消
     * */
    public void findBespeakOffById()
    {
        try
        {
            Msg msg = frtResevationService.findBespeakOffById(freVo);
            super.writeJson(msg);
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("判断某个预约单是否取消失败！", e);
        }
    }

    /**
     * 预约申请单汇总datagrid
     */
    public void datagridFrtResevation()
    {
    	freVo.setEnterpriseId(getNowEnterpriseId())	;
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.datagridFrtResevation(false,
                    freVo));
            // logger.info("预约申请单汇总datagrid成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("预约申请单汇总datagrid失败！", e);
        }
    }

    /**
     * 预约申请/保险估价单汇总datagrid
     */
    public void datagridFrtResevationAndInsurePrize()
    {
    	
    	freVo.setEnterpriseId(getNowEnterpriseId());
        try
        {
            super.writeJson(frtResevationService.datagridFrtResevation(null,
                    freVo));
            // logger.info("预约申请/保险估价单汇总datagrid成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("预约申请/保险估价单汇总datagrid失败！", e);
        }
    }

    /**
     * 根据车档案编号查询车档案
     */
    public void getFrtCar()
    {
        try
        {
            super.writeJson(frtResevationService.getFrtCar(freVo.getCarId()));
            // logger.info("查询车档案成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询车档案失败！", e);
        }
    }

    /**
     * 从数据库中查询维修配件
     */
    public void findPartsByResvId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.findPartsByResvId(freVo
                    .getResvId(),freVo.getEnterpriseId()));
            // logger.info("数据库中查询维修配件成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询维修配件失败！", e);
        }
    }

    /**
     * 从数据库中查询维修项目
     */
    public void findItemByResvId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.findItemByResvId(freVo
                    .getResvId(),freVo.getEnterpriseId()));
            // logger.info("数据库中查询维修项目成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询维修项目失败！", e);
        }
    }

    /**
     * 从数据库中查询抢修信息
     */
    public void findServiceByResvId()
    {
        try
        {
            super.writeJson(frtResevationService.findServiceByResvId(freVo
                    .getResvId()));
            // logger.info("数据库中查询抢修信息成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询抢修信息失败！", e);
        }
    }

    /**
     * 增加自定义维修项目
     * */
    public void addFrtResevationItem()
    {
        try
        {
            super.writeJson(frtResevationService.addFrtResevationItem(freVo));
        }
        catch(Exception e)
        {
            logger.error("增加自定义维修项目失败！", e);
        }
    }

    /**
     * 保存预约申请单
     */
    public void save()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.save(false, freVo));
            // logger.info("保存预约申请单成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("保存预约申请单失败！", e);
        }
    }

    /**
     * 删除预约申请单
     */
    public void remove()
    {
        try
        {
            super.writeJson(frtResevationService.remove(false, freVo
                    .getResvId()));
            // logger.info("删除预约申请单成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("删除预约申请单失败！", e);
        }
    }

    /**
     * 更新预约申请单
     */
    public void edit()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.edit(false, freVo));
            // logger.info("更新预约申请单成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("更新预约申请单失败！", e);
        }
    }

    
    public FrtResevationVo getModel()
    {
        return freVo;
    }

    /**
     * 获取已有车身结构编号
     * */
    public void findAllIdVehicleStructure()
    {
        try
        {
            String vehicle = freVo.getVehicle();
            if (vehicle != null && vehicle.length() > 0)
            {
                JSONObject jsVehicle = JSON.parseObject(vehicle);
                List<FrtResvVehicleStructure> vehicleList = JSON.parseArray(
                        jsVehicle.get("rows").toString(),
                        FrtResvVehicleStructure.class);
                StringBuffer sb = new StringBuffer();
                for (FrtResvVehicleStructure frtResvVehicleStructure : vehicleList)
                {
                    sb.append(frtResvVehicleStructure.getId() + ",");
                }
                if (sb.toString().length() > 0)
                    super
                            .writeJson(sb.substring(0,
                                    sb.toString().length() - 1));
            }
            else
            {
                super.writeJson(null);
            }
            // logger.info("获取已有车身结构编号成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("获取已有车身结构编号失败！", e);
        }
    }

    /**
     * 添加车身结构
     */
    public void addVehicleStructure()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId()); 
           List<FrtResvVehicleStructure> list=frtResevationService.findAllCarFrameWorks(freVo);
        	super.writeJson(list);
//             logger.info("添加车身结构成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("添加车身结构失败！", e);
        }
    }

    /**
     * 移除车身结构
     */
    public void removeVehicleStructure()
    {
        try
        {
            super.writeJson(frtResevationService.removeVehicleStructure(freVo));
            // logger.info("移除车身结构成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("移除车身结构失败！", e);
        }
    }

    /**
     * 清空车身结构
     */
    public void clearVehicleStructure()
    {
        try
        {
            List<FrtResvVehicleStructure> list = null;
            String vehicle = freVo.getVehicle();
            if (vehicle != null && vehicle.length() > 0)
            {
                JSONObject jsVehicle = JSON.parseObject(vehicle);
                list = JSON.parseArray(jsVehicle.get("rows").toString(),
                        FrtResvVehicleStructure.class);
            }
            if (list == null)
            {
                list = new ArrayList();
            }
            list.clear();
            super.writeJson(list);
            // logger.info("清空车身结构成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("清空车身结构失败！", e);
        }
    }

    /**
     * 数据库中查询车身状况
     */
    public void findvehicleStructureList()
    {
        try
        {
            super.writeJson(frtResevationService.findVehicleStructure(freVo));
            // logger.info("数据库中查询车身状况成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("数据库中查询车身状况失败！", e);
        }
    }

    /**
     * 获取抢修信息
     * */
    public void findrushToRepair()
    {
        try
        {
            super.writeJson(frtService.findrushToRepair(freVo.getResvId()));
            // logger.info("获取抢修信息成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("获取抢修信息失败！", e);
        }
    }

    /**
     * 查询套餐项目并添加到预约单中
     */
    public void findItemListByRpId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.findItemListByRpId(freVo));
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询套餐项目并添加到预约单中失败！", e);
        }
    }

    /**
     * 查询套餐配件并添加到预约单中
     */
    public void findPartsListByRpId()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.findPartsListByRpId(freVo));
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("查询套餐配件并添加到预约单中失败！", e);
        }

    }

    /**
     * 转换预约信息为前台接车单
     * */
    public void switchFrt()
    {
        try
        {
        	freVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtResevationService.saveSwitchFrt(false, freVo));
            // logger.info("转换预约信息为前台接车单成功！");
        }
        catch(Exception e)
        {
            // TODO: handle exception
            logger.error("转换预约信息为前台接车单失败！", e);
        }
    }
    /**
     * 模板打印
     */
    public void print()
    {
        try
        {
            BasUsers user = this.getUsers();
            this.sendJson(frtResevationService.getPrintHtml(false,freVo,
                    user));
        }
        catch(Exception e)
        {
            logger.error("生成打印数据失败", e);
        }
    }
}
