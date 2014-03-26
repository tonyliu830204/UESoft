package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FinClaimantMainService;
import com.syuesoft.frt.vo.FinClaimantMainVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;

/**
 * 索理赔结算action
 * @author Liujian
 */
@SuppressWarnings("serial")
@ParentPackage(value = "basePackage")
@Action("finClaimantMainAction")
public class FinClaimantMainAction extends BaseAction implements
        ModelDriven<FinClaimantMainVo>
{
    private final Logger logger = Logger.getLogger(this.getClass());
    FinClaimantMainVo fcmVo = new FinClaimantMainVo();
    private FinClaimantMainService finClaimantMainService;

    /**
     * 索理赔单datagrid
     */
    public void datagridFinClaimantMain(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.datagridFinClaimantMain(fcmVo));
        }catch(Exception e){
            logger.error("索理赔单datagrid失败！", e);
        }
    }

    /**
     * 从数据库中查询维修配件
     */
    public void findPartsByFcmId(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());//当前系统所属公司序号
            super.writeJson(finClaimantMainService.findPartsByFcmId(fcmVo));
        }catch(Exception e){
            logger.error("数据库中查询维修配件失败！", e);
        }
    }

    /**
     * 从数据库中查询维修结算配件
     */
    public void findPartsByReceptionId(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.findPartsByReceptionId(fcmVo));
        }catch(Exception e){
            logger.error("数据库中查询维修结算配件失败！", e);
        }
    }

    /**
     * 从数据库中查询维修项目
     * 修改
     */
    public void findItemByFcmId(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.findItemByFcmId(fcmVo));
        }catch(Exception e){
            logger.error("数据库中查询维修项目失败！", e);
        }
    }

    /**
     * 从数据库中查询维修结算项目
     * 
     * 新增
     */
    public void findItemByReceptionId(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.findItemByReceptionId(fcmVo));
        }catch(Exception e){
            logger.error("数据库中查询维修结算项目失败！", e);
        }
    }

    /**
     * 从数据库中查询其他费用明细
     */
    public void findCostByFcmId(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.findCostByFcmId(fcmVo));
        }catch(Exception e){
            logger.error("数据库中查询其他费用明细失败！", e);
        }
    }

    /**
     * 从数据库中查询结算其他费用明细
     */
    public void findCostByReceptionId(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.findCostByReceptionId(fcmVo));
        }catch(Exception e){
            logger.error("数据库中查询结算其他费用明细失败！", e);
        }
    }

    /** 
     * 增加其他费用 
     * */
    public void addFrtReceptionCost(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.addFinClaimantMainCost(fcmVo));
        }catch(Exception e){
            logger.error("增加其他费用失败！", e);
        }
    }

    /**
     * 添加索理赔单
     */
    public void save(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.save(fcmVo));
        }catch(Exception e){
            logger.error("添加索理赔单失败！", e);
        }
    }

    /**
     * 更新索理赔单
     */
    public void edit(){
        try{
            super.writeJson(finClaimantMainService.edit(fcmVo));
        }catch(Exception e){
            logger.error("更新索理赔单失败！", e);
        }
    }

    /**
     * 删除索理赔单
     */
    public void delete(){
        try{
            super.writeJson(finClaimantMainService.delete(fcmVo));
        }catch(Exception e){
            logger.error("删除索理赔单失败！", e);
        }
    }

    /** 计算费用总和 */
    public void totemoney(){
        try{
            super.writeJson(finClaimantMainService.totemoney(fcmVo));
        }catch(Exception e){
            logger.error("计算费用总和失败！", e);
            e.printStackTrace();
        }
    }

    /** 查找未确认的索赔结算单 */
    public void findIdeaByStatus(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.findIdeaByStatus(fcmVo));
        }catch(Exception e){
            logger.error("查找未确认的索赔结算单失败！", e);
        }
    }

    /**
     * 索赔转收银
     * */
    public void transformMoney(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.modifyTransformMoney(fcmVo));
        }catch(Exception e){
            logger.error("索赔转收银失败！", e);
        }
    }

    /**
     * 增加自定义维修项目
     * */
    public void addFinClaimantMainItem(){
        try{
        	fcmVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(finClaimantMainService.addFinClaimantMainItem(fcmVo));
        }catch(Exception e){
            logger.error("增加自定义维修项目失败！", e);
        }
    }

    /**
     * 索赔单审核
     * */
    public void modifyClaimsValidate(){
        try
        {
            if (fcmVo.getFlag() == true)
            {
                if (fcmVo.getClaimantmStatus().equals(
                        Contstants.AUDIT_TAG.AUDITNOS))
                {
                	fcmVo.setClaimantmCheckStfId(Short.parseShort(((BasUsers)this.getSession().getAttribute(Contstants.CUSTOMER)).getBasStuff().getStfId().toString()));
                    super.writeJson(finClaimantMainService.modifyClaimsValidateAsTrue(fcmVo));
                }
                else if (fcmVo.getClaimantmStatus().equals(
                        Contstants.AUDIT_TAG.AUDITYESS))
                {
                    Msg msg = new Msg();
                    msg.setMsg("此单已审核，无需再审核！");
                    super.writeJson(msg);
                }
            }
            else if (fcmVo.getFlag() == false)
            {
                if (fcmVo.getClaimantmStatus().equals(
                        Contstants.AUDIT_TAG.AUDITNOS))
                {
                    Msg msg = new Msg();
                    msg.setMsg("此单未审核，无需取消审核！");
                    super.writeJson(msg);
                }
                else if (fcmVo.getClaimantmStatus().equals(
                        Contstants.AUDIT_TAG.AUDITYESS))
                {
                	fcmVo.setClaimantmCheckStfId(null);
                    super.writeJson(finClaimantMainService.modifyClaimsValidateAsFalse(fcmVo));
                }
            }
        }
        catch(Exception e){
            logger.error("索赔单审核失败！", e);
        }
    }

    /**
     * 取消确认
     * */
    public void modifyClaimsValidateAsFalse(){
        try{
            fcmVo.setClaimantmCheckStfId(Short.parseShort(((BasUsers) this
                    .getSession().getAttribute(Contstants.CUSTOMER))
                    .getBasStuff().getStfId().toString()));
            super.writeJson(finClaimantMainService
                    .modifyClaimsValidateAsFalse(fcmVo));
        }catch(Exception e){
            logger.error("索赔确认失败！", e);
        }
    }

    /**
     * 判断索理赔单有无确认
     * */
    public void isClaimsValidate(){
        try{
            super.writeJson(finClaimantMainService.isClaimsValidate(fcmVo));
        }catch(Exception e){
            logger.error("判断索理赔单有无确认失败！", e);
        }
    }
    /**
     * 索赔成本对比分析
     * */
    public void claimCostContrastAnalyse(){
    	   try{
    		   fcmVo.setEnterpriseId(getNowEnterpriseId());
               super.writeJson(finClaimantMainService.claimCostContrastAnalyse(fcmVo));
           }catch(Exception e){
               logger.error("查找索赔成本对比分析数据失败！", e);
           }
    }
    /**
     * 索赔成本对比分析-子项信息
     * */
    public void claimCostContrastAnalyseChild(){
    	   try
           {
               super.writeJson(finClaimantMainService.claimCostContrastAnalyseChild(fcmVo));
           }catch(Exception e){
               logger.error("查找索赔成本对比分析-子项信息失败！", e);
           }
    }
    
    
    public FinClaimantMainVo getModel(){
        return fcmVo;
    }
    
    public FinClaimantMainService getFinClaimantMainService(){
        return finClaimantMainService;
    }
    
    @Autowired
    public void setFinClaimantMainService(FinClaimantMainService finClaimantMainService){
        this.finClaimantMainService = finClaimantMainService;
    }
}
