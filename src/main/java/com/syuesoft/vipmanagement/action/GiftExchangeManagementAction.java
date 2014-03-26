package com.syuesoft.vipmanagement.action;
/**
 * 礼品兑换管理
 */
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.GiftExchangeManagementService;
import com.syuesoft.vipmanagement.vo.GiftExchangeManagementVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("giftExchangeManagementAction")
public class GiftExchangeManagementAction extends BaseAction implements	ModelDriven<GiftExchangeManagementVo> {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private GiftExchangeManagementVo giftExchangeManagementVo = new GiftExchangeManagementVo();
	public GiftExchangeManagementVo getModel() {
		return giftExchangeManagementVo;
	}
	@Autowired
	private GiftExchangeManagementService giftExchangeManagementService;
	
	/**
     * 礼品兑换汇总查询信息
     */
    public void getGiftExchangeInfo(){
        try {
            super.writeJson(giftExchangeManagementService.getGiftExchanJsongeInfo(giftExchangeManagementVo, getUsers()));
        } catch (Exception e) {
            logger.error("礼品兑换汇总查询信息失败", e);
        }
    }
    
    /** 
     * 礼品兑换管理 的 可兑换礼品查询   
     */
    public void getExchangeable(){
        try {
            super.writeJson(giftExchangeManagementService.getExchangeable(giftExchangeManagementVo, getUsers()));
        } catch (Exception e) {
            logger.error("礼品兑换管理 的 可兑换礼品查询失败 ", e);
        }
    }
    
    /**
     * 礼品兑换 保存礼品兑换记录
     */
    public void doAdd(){
        try {
            super.writeJson(giftExchangeManagementService.doAdd(giftExchangeManagementVo, getUsers()));
        } catch (Exception e) {
            logger.error("礼品新增保存失败 ", e);
        }
    }
    
    /**
     * 通过兑换单号 获取兑换明细信息
     */
    public void getGiftExchangeDetail(){
        try {
            super.writeJson(giftExchangeManagementService.getGiftExchangeDetail(giftExchangeManagementVo));
        } catch (Exception e) {
            logger.error("查询已经兑换礼品失败 ", e);
        }
    }
    
    /**
     * 修改礼品兑换记录
     */
    public void doUpdate(){
        try {
            super.writeJson(giftExchangeManagementService.doUpdate(giftExchangeManagementVo, getUsers()));
        } catch (Exception e) {
            logger.error("礼品修改保存失败 ", e);
        }
    }
    
    /**
     * 
     * 删除兑换记录及兑换记录单下的兑换明细信息
     */
    public void doDelete(){
        try {
            super.writeJson(giftExchangeManagementService.doDelete(giftExchangeManagementVo, getUsers()));
        } catch (Exception e) {
            logger.error("礼品删除失败 ", e);
        }
    }
    
    /**
     * 礼品兑换审核
     */
    public void doAudit(){
        try {
            super.writeJson(giftExchangeManagementService.doAudit(giftExchangeManagementVo, getUsers()));
        } catch (Exception e) {
            logger.error("礼品兑换审核失败 ", e);
        }
    }
}