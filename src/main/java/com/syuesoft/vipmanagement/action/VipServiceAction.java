package com.syuesoft.vipmanagement.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.VipServiceService;
import com.syuesoft.vipmanagement.vo.VipServiceTreeGridVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("vipServiceAction")
public class VipServiceAction extends BaseAction implements ModelDriven<VipServiceTreeGridVo> {
    private static final long serialVersionUID = 1L;
    
    @Autowired
	private VipServiceService vipServiceService;
	private VipServiceTreeGridVo vipServiceTreeGridVo = new VipServiceTreeGridVo();
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	public VipServiceTreeGridVo getModel() {
		return vipServiceTreeGridVo;
	}
    
	/**
     * 查询会员卡服务项目父记录
     */
    public void findVipMeal(){
        try {
            super.writeJson(vipServiceService.findVipMeal(vipServiceTreeGridVo, getUsers()));
        } catch (Exception e) {
            logger.error("查询会员服务项目失败", e);
        }
    }
    /**
     * 查询该父项下面的子项
     */
    public void getChildMenu(){
        try {
            super.writeJson(vipServiceService.getChildMenu(vipServiceTreeGridVo));
        } catch (Exception e) {
            logger.error("查询会员服务子项目失败", e);
        }
    }
    
	/**
     * 获取会员卡编号 下拉框
     */
    public void getVipInfo(){
        try{
            super.writeJson(vipServiceService.getVipInfo(vipServiceTreeGridVo));
        }catch(Exception e){
            logger.error("查询会员失败", e);
        }
    }
    
    public void getVipId(){
        try{
            super.writeJson(vipServiceService.getVipId(vipServiceTreeGridVo));
        }catch(Exception e){
            logger.error("查询会员失败", e);
        }
    }
    
    /**
     * 获取套餐名称用于下拉框
     */
    public void getMealName(){
        try {
        	
            super.writeJson(vipServiceService.getMealName(getNowEnterpriseId()));
        } catch (Exception e) {
            logger.error("查询会员套餐失败", e);
        }
    }
    
    /**
     * 新增会员卡项目 是将会员编号和套餐名称加入 套餐表和会员表的关系表中
     */
    public void doAdd(){
        try {
            super.writeJson(vipServiceService.doAdd(vipServiceTreeGridVo, getUsers()));
        } catch (Exception e) {
            logger.error("新增会员卡项目失败", e);
        }
    }
    
	/**
     * 修改会员套餐
     */
    public void doUpdate(){
        try {
            super.writeJson(vipServiceService.doUpdate(vipServiceTreeGridVo, getUsers()));
        } catch (Exception e) {
            logger.error("修改会员套餐失败", e);
        }
    }
    
	/**
	 * 删除会员卡项目套餐
	 */
	public void doDelete(){
		try {
		    super.writeJson(vipServiceService.doDelete(vipServiceTreeGridVo));
		} catch (Exception e) {
		    logger.error("删除会员套餐失败", e);
		}
	}
}