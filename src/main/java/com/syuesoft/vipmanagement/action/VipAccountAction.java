package com.syuesoft.vipmanagement.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.VipAccountService;
import com.syuesoft.vipmanagement.vo.VipAccountVo;

/** 
 * @ClassName: VipAccountAction 
 * @Description: TODO(会员对账单) 
 * @author HeXin 
 * @date 2013-12-19 下午03:18:02 
 * @version 1.0 
 */
@ParentPackage(value="basePackage")
@Action("vipAccountAction")
public class VipAccountAction extends BaseAction implements ModelDriven<VipAccountVo> {
    
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private VipAccountVo vipAccountVo = new VipAccountVo();
    @Autowired
    private VipAccountService vipAccountService;
    
    public VipAccountVo getModel()
    {
        return vipAccountVo;
    }
    
    //查询所有对账单
    public void findAll(){
        try{
            super.writeJson(vipAccountService.findAll(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("查询所有对账单失败", e);
        }
    }
    
    //查询最后的对账日期
    public void getMaxAccountEndDate(){
        try{
            super.writeJson(vipAccountService.getMaxAccountEndDate(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("查询最后的对账日期失败", e);
        }
    }
    
    //对账单明细查询
    public void getAccountDetail(){
        try{
            super.writeJson(vipAccountService.getAccountDetail(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("对账单收款明细查询失败", e);
        }
    }
    
    //对账单收款明细查询
    public void getAccountMoneyDetail(){
        try{
            super.writeJson(vipAccountService.getAccountMoneyDetail(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("对账单收款明细查询失败", e);
        }
    }
    
    //保存对账单
    public void doAdd(){
        try{
            super.writeJson(vipAccountService.saveAccount(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("保存对账单失败", e);
        }
    }
    
    //撤销对账单
    public void doDelete(){
        try{
            super.writeJson(vipAccountService.deleteAccount(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("撤销对账单失败", e);
        }
    }
    
    //收款
    public void doPayMent(){
        try{
            super.writeJson(vipAccountService.savePayMent(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("收款失败", e);
        }
    }
    
    //审核
    public void doUpdateState(){
        try{
            super.writeJson(vipAccountService.doUpdateState(vipAccountVo, getUsers()));
        } catch(Exception e){
            logger.error("审核失败", e);
        }
    }
}