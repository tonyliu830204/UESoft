package com.syuesoft.vipmanagement.action;
/**
 * 积分赠送管理
 */
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.VipScorePresentManagementService;
import com.syuesoft.vipmanagement.vo.VipScorePresentManagementVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("vipScorePresentManagementAction")  
public class VipScorePresentManagementAction extends BaseAction implements ModelDriven<VipScorePresentManagementVo> {
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private VipScorePresentManagementVo vipScorePresentManagementVo = new VipScorePresentManagementVo();
	@Autowired
	private VipScorePresentManagementService vipScorePresentManagementService;
	
	public VipScorePresentManagementVo getModel() {
		return vipScorePresentManagementVo;
	}
	
	/**
	 *获取积分总送汇总信息 
	 * 
	 */
	public void getHzGiveIntegral(){
    	try {
    	    super.writeJson(vipScorePresentManagementService.getHzGiveIntegral(vipScorePresentManagementVo, this.getUsers()));
    	} catch (Exception e){
    	    logger.error("获取积分总送汇总信息失败", e);
    	}
    }
	
     /**
      *获取 积分赠送项目信息
      */
    public void getBasVipGiveIntegralProject(){
         try {
             super.writeJson(vipScorePresentManagementService.getBasVipGiveIntegralProject());
         } catch (Exception e) {
             logger.error("获取 积分赠送项目信息失败", e);
         }
    }
	
    /**
     * 
     * 将实际赠分 与 赠送积分表的id 和 赠送项目表的id 存入中间表中 bas_vip_relationship16
     */
    public void doAddRelationTable(){
        try {
            super.writeJson(vipScorePresentManagementService.addRelationTable(vipScorePresentManagementVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("积分赠送保存失败", e);
        }
    }
    
	/**
	 * 通过赠送积分单号获取赠送项目及赠送积分
	 * @return
	 */
	public void getGiveIntegralByVipId(){
		try {
			super.writeJson(vipScorePresentManagementService.getGiveIntegralByVipId(vipScorePresentManagementVo));
		} catch (Exception e){
		    logger.error("获取赠送项目失败", e);
		}
	}

	/**
     * 修改赠送积分单号对应的 赠送积分
     * 
     */
    public void doUpdateRelationTable(){
        try {
            super.writeJson(vipScorePresentManagementService.updateRelationTable(vipScorePresentManagementVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("修改赠送积分失败", e);
        }
    }
    
    /**
     * 删除中间表里的记录单号
     * 
     */
    public void doRemove(){
        try {
            super.writeJson(vipScorePresentManagementService.delete(vipScorePresentManagementVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("删除积分赠送失败", e);
        }
    }
    
	/**
	 * 审核
	 * @return
	 */
	public void doShenhe(){
		try {
		    super.writeJson(vipScorePresentManagementService.updateShenhe(vipScorePresentManagementVo, this.getUsers()));
		} catch (Exception e) {
		    logger.error("审核积分赠送失败", e);
		}
	}
}