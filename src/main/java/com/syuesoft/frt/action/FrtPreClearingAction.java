package com.syuesoft.frt.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.frt.service.FrtPreClearingService;
import com.syuesoft.frt.vo.FrtPreClearingVo;
import com.syuesoft.model.BasUsers;
import com.syuesoft.util.Msg;
/**
 * 交车结算action
 * @author Liujian
 */
@SuppressWarnings("serial")
@Scope("prototype")
@ParentPackage(value = "basePackage")
@Action("frtPreClearingAction")
public class FrtPreClearingAction extends BaseAction implements
        ModelDriven<FrtPreClearingVo>
{
    private static final Logger logger = Logger.getLogger(FrtPreClearingAction.class);
    FrtPreClearingVo fpcVo = new FrtPreClearingVo();
    @Autowired private FrtPreClearingService frtPreClearingService;

    /**
     * 交车结算datagrid
     */
    public void datagridFrtPreClearing()
    {
        try{
        	fpcVo.setEnterpriseId(getNowEnterpriseId());
            super.writeJson(frtPreClearingService.datagridFrtPreClearing(fpcVo));
        }catch(Exception e){
            logger.error("交车结算datagrid失败！", e);
        }
    }

    /**
     * 删除交车结算单
     */
    public void remove()
    {
        try{
            super.writeJson(frtPreClearingService.remove(fpcVo.getPreclrId()));
        }catch(Exception e){
            logger.error("删除交车结算单失败！", e);
        }
    }

    /**
     * 更新交车结算单
     */
    public void edit()
    {
        try{
        	BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
			if(user!=null&&!user.equals(""))
				fpcVo.setStfId(Short.parseShort(user.getBasStuff().getStfId()+""));
            super.writeJson(frtPreClearingService.edit(fpcVo));
        }catch(Exception e){
            logger.error("更新交车结算单失败！", e);
        }
    }

    /**
     * 从数据库中查询维修配件
     */
    public void findPrePartsById()
    {
        try{
            super.writeJson(frtPreClearingService.findPrePartsById(fpcVo.getPreclrId()));
        } catch(Exception e){
            logger.error("根据交车结算单id查询材料清单失败！", e);
        }
    }

    /**
     * 根据交车结算单id查询维修清单
     */
    public void findPreItemById()
    {
        try{
            super.writeJson(frtPreClearingService.findPreItemById(fpcVo
                    .getPreclrId()));
        }catch(Exception e){
            logger.error("根据交车结算单id查询维修清单失败！", e);
        }
    }

    /**
     * 根据交车结算单id查询费用情况清单
     */
    public void findPreCostById()
    {
        try{
            super.writeJson(frtPreClearingService.findPreCostById(fpcVo
                    .getPreclrId()));
        }catch(Exception e){
            logger.error("根据交车结算单id查询费用情况清单失败！", e);
        }
    }

    /**
     * 返工
     * 工单：返工到车间
     * 销售单：返工到销售单
     * */
    public void back()
    {
        try{
            super.writeJson(frtPreClearingService.modifyBack(fpcVo));
        }
        catch(Exception e){
            logger.error("返工至车间失败！", e);
        }
    }

    /**
     * 结算转收银
     * */
    public void transformMoney()
    {
        Msg msg = new Msg();
        try{
        	fpcVo.setEnterpriseId(getNowEnterpriseId());
            msg = frtPreClearingService.modifyTransformMoney(fpcVo);
            super.writeJson(msg);
        }catch(Exception e){
            logger.error("结算转收银失败！", e);
        }
    }

    /**
     * 洗车或待交车
     * */
    public void modifyTransFormReceptionState()
    {
        try{
            super.writeJson(frtPreClearingService
                    .modifyTransFormReceptionState(fpcVo));
        }catch(Exception e){
            logger.error("转洗车或待交车失败！", e);
        }
    }

    /**
     *查询费用情况
     * */
    public void totemoney()
    {
        try{
            super.writeJson(frtPreClearingService.totemoney(fpcVo));
        }catch(Exception e){
            logger.error("计算费用总和失败！", e);
        }
    }
    
    
    /**
     * 判断此结算单是否已经转收银
     */
    public void doIsExist(){
    	try{
    		Msg msg=new Msg();
    		boolean isno=frtPreClearingService.doIsExist(fpcVo);
    		if(isno){
    			msg.setSuccess(false);
    		    msg.setMsg("对不起，该结算单已转收银！不能删除修改操作");
    	    }else
    			msg.setSuccess(true);
    		super.writeJson(msg);
        }catch(Exception e){
            logger.error("判断此结算单是否已经转收银  异常！", e);
        }
    }
    
    
    
    
    public FrtPreClearingVo getModel()
    {
        return fpcVo;
    }

}
