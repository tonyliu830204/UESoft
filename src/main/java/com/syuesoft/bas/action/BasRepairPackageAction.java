package com.syuesoft.bas.action;

import java.io.Serializable;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.service.BasRepairPackageService;
import com.syuesoft.bas.service.BaseService;
import com.syuesoft.base.vo.BasRepairPackageVo;
import com.syuesoft.base.vo.BasStorehouseVo;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.ParentPackage;
/**
 * 维修套餐Action
 * @author Liujian
 */
@ParentPackage(value = "basePackage")
@Action("basRepairPackageAction")
public class BasRepairPackageAction extends BaseAction implements
        ModelDriven<BasRepairPackageVo>
{
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;

    /**
     * Logger for this class
     */
    private final Logger logger = Logger.getLogger(this.getClass());
    BasRepairPackageVo brpVo = new BasRepairPackageVo();
    private BasRepairPackageService basRepairPackageService;
    private BaseService baseService;

    /**
     * 查询所有仓库
     */
    public void findAllStorehouse()
    {
        try
        {
        	BasStorehouseVo basStorehouseVo = new BasStorehouseVo();
	        basStorehouseVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(baseService.findAllStorehouse(basStorehouseVo));
        }catch(Exception e){
            logger.error("查询所有仓库失败", e);
        }
    }

    /**
     * 查询配件产地
     */
    public void findAllPartsState()
    {
        try
        {
            super.writeJson(baseService.findAllPartsState(getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询配件产地失败", e);
        }
    }

    /**
     * 查询车品牌
     */
    public void findAllCarBrand()
    {
        try
        {
            super.writeJson(baseService.findAllCarBrand(brpVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询车品牌失败", e);
        }
    }

    /**
     * 查询配件品牌
     */
    public void findAllPartsBrand()
    {
        try
        {
            super.writeJson(baseService.findAllPartsBrand(brpVo.getQ(),getNowEnterpriseId()));
        }catch(Exception e){
            logger.error("查询配件品牌失败", e);
        }
    }

    /**
     * 查询待选配件
     */
    public void findAllSelectionParts()
    {
        try
        {
        	brpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basRepairPackageService.datagridParts(brpVo));
        }catch(Exception e){
            logger.error("查询待选配件失败", e);
        }
    }

    /**
     * 查询待选项目
     */
    public void findAllSelectionItem()
    {
        try
        {
        	brpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basRepairPackageService.datagridItem(brpVo));
        } catch(Exception e){
            logger.error("查询待选项目失败", e);
        }
    }

    /**
     * 维修套餐datagrid
     */
    public void findAllRepairPackage()
    {
        try
        {
        	brpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basRepairPackageService.datagrid(brpVo));
        }catch(Exception e){
            logger.error("维修项目datagrid失败", e);
        }
    }

    /**
     * 从数据库中查询已选配件
     */
    public void getSelectedParts()
    {
        try
        {
        	brpVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(basRepairPackageService.getSelectedParts(brpVo));
        }catch(Exception e){
            logger.error("从数据库中查询已选配件并放入session失败", e);
        }
    }

    /**
     * 从数据库中查询已选项目
     */
    public void getSelectedItem()
    {
        try
        {
            super.writeJson(basRepairPackageService.getSelectedItem(brpVo));
        }catch(Exception e){
            logger.error("从数据库中查询已选项目并放入session失败", e);
        }
    }

    /**
     * 计算费用合计
     */
    public void amount()
    {
        try
        {
            super.writeJson(basRepairPackageService.amount(brpVo));
        }catch(Exception e){
            logger.error("计算费用合计失败！", e);
        }
    }

    /**
     * 保存维修套餐
     */
    public void save()
    {
        Msg msg = new Msg();
        try
        {
        	brpVo.setEnterpriseId(getNowEnterpriseId());
            Serializable s = basRepairPackageService.save(brpVo);
            msg.setSuccess(true);
            msg.setMsg("保存维修套餐[" + brpVo.getRpName() + "]成功！");
            msg.setObj(s);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            msg.setMsg("对不起，保存维修套餐[" + brpVo.getRpName() + "]失败！");
            logger.error("对不起，保存维修套餐[" + brpVo.getRpName() + "]失败！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 更新维修套餐
     */
    public void edit()
    {
        Msg msg = new Msg();
        try
        {
        	brpVo.setEnterpriseId(getNowEnterpriseId());
            basRepairPackageService.update(brpVo);
            msg.setSuccess(true);
            msg.setMsg("更新维修套餐[" + brpVo.getRpName() + "]成功！");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            msg.setMsg("对不起，更新维修套餐[" + brpVo.getRpName() + "]失败！");
            logger.error("对不起，更新维修套餐[" + brpVo.getRpName() + "]失败！", e);
        }
        super.writeJson(msg);
    }

    /**
     * 删除维修套餐
     */
    public void remove()
    {
        Msg msg = new Msg();
        try
        {
            basRepairPackageService.remove(brpVo);
            msg.setSuccess(true);
            if(brpVo.getRpName()!=null && !("".equals(brpVo.getRpName()))){
            	brpVo.setRpName(new String(brpVo.getRpName().getBytes("ISO-8859-1"),"UTF-8"));
            }
            msg.setMsg("删除维修套餐[" + brpVo.getRpName() + "]成功!");
        }
        catch(Exception e)
        {
            msg.setMsg("对不起，删除维修套餐[" + brpVo.getRpName() + "]失败!");
            logger.error("对不起，删除维修套餐[" + brpVo.getRpName() + "]失败!", e);
        }
        super.writeJson(msg);
    }

    
    public BasRepairPackageVo getModel()
    {
        return brpVo;
    }

    public BasRepairPackageService getBasRepairPackageService()
    {
        return basRepairPackageService;
    }

    @Autowired
    public void setBasRepairPackageService(
            BasRepairPackageService basRepairPackageService)
    {
        this.basRepairPackageService = basRepairPackageService;
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

}