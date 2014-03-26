package com.syuesoft.vipmanagement.action;
/**
 * 会员档案管理
 */
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.util.GetDateByString;
import com.syuesoft.vipmanagement.service.VipRecordMessageService;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("VipRecordMessageAction")
public class VipRecordMessageAction extends BaseAction implements ModelDriven<VipRecordMessageVo>{
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    //工具类 getDateByString获取时间格式的时间
	GetDateByString g = new GetDateByString();
	private VipRecordMessageVo vipRecordMessageVo = new VipRecordMessageVo();
	@Autowired
	private VipRecordMessageService vipRecordMessageService;
	
	public VipRecordMessageVo getModel() {
		return vipRecordMessageVo;
	}
	
	/*
	 * 查询方法
	 */
	public void doFindAll(){
		try {
			super.writeJson(vipRecordMessageService.findAll(vipRecordMessageVo, getUsers()));
		} catch (Exception e) {
		    logger.error("会员档案查询失败", e);
		}
	}
	//会员入会 添加 方法
    public void doAdd(){
        try {
            super.writeJson(vipRecordMessageService.add(vipRecordMessageVo, getUsers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	//修改会员信息
	public void doUpdate(){
		try {
		    super.writeJson(vipRecordMessageService.update(vipRecordMessageVo, getUsers()));
		} catch (Exception e) {
			logger.error("修改会员档案数据失败", e);
		}
	}
	
	//会员登陆
	public void doVipLogin(){
	    try {
            super.writeJson(vipRecordMessageService.findVip(vipRecordMessageVo));
        } catch (Exception e) {
            logger.error("会员登陆失败", e);
        }
	}
	
	//修改会员卡状态
	public void doVipState(){
        try {
            super.writeJson(vipRecordMessageService.updateVipState(vipRecordMessageVo, getUsers()));
        } catch (Exception e) {
            logger.error("会员登陆失败", e);
        }
    }
	
	//会员卡挂失
    public void doLossOfUpdate(){
        try {
            super.writeJson(vipRecordMessageService.updateLoss(vipRecordMessageVo, getUsers()));
        } catch (Exception e) {
            logger.error("修改会员档案数据失败", e);
        }
    }
    
    //会员分组
    public void getBasVipGroup(){
        try {
            super.writeJson(vipRecordMessageService.getBasVipGruop(getUsers()));
        } catch (Exception e) {
            logger.error("查询会员分组失败", e);
        }
    }
    
    //会员级别
    public void getBasVipLevel(){
        try {
            super.writeJson(vipRecordMessageService.getBasVipLevel(getUsers()));
        } catch (Exception e) {
            logger.error("查询会员级别失败", e);
        }
    }
    
    //查询会员信息汇总
    public void getVipInfo(){
        try {
            super.writeJson(vipRecordMessageService.getVipInfo(vipRecordMessageVo, getUsers()));
        } catch (Exception e) {
            logger.error("查询会员信息汇总失败", e);
        }
    }
    
    //会员续会保存
    public void doUpdateAdjournment(){
        try {
            super.writeJson(vipRecordMessageService.updateVipAdjournment(vipRecordMessageVo, getUsers()));
        } catch (Exception e) {
            logger.error("查询会员信息汇总失败", e);
        }
    }
    
    //会员退会保存
    public void doUpdateExitMember(){
        try {
            super.writeJson(vipRecordMessageService.updateVipExitMember(vipRecordMessageVo, getUsers()));
        } catch (Exception e) {
            logger.error("查询会员信息汇总失败", e);
        }
    }
}