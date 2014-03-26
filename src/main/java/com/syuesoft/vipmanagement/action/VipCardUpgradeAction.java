package com.syuesoft.vipmanagement.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.VipCardUpgradeService;
import com.syuesoft.vipmanagement.vo.VipCardUpgradeVo;
import org.apache.struts2.convention.annotation.ParentPackage;
/**
 * 会员升级、降级
* @ClassName: VipCardUpgradeAction 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author HeXin 
* @date 2013-12-6 下午03:03:20 
* @version 1.0
 */
@ParentPackage(value="basePackage")
@Action("vipCardUpgradeAction")
public class VipCardUpgradeAction extends BaseAction implements ModelDriven<VipCardUpgradeVo>{
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private VipCardUpgradeVo vipCardUpgradeVo = new VipCardUpgradeVo();
	public VipCardUpgradeVo getModel() {
		return vipCardUpgradeVo;
	}
	@Autowired
	private VipCardUpgradeService vipCardUpgradeService;
	
	/**
     * 会员卡升级汇总
     */
    public void doVipUpCollectFind(){
        try {
            super.writeJson(vipCardUpgradeService.doVipUpCollectFind(vipCardUpgradeVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("会员卡升级汇总查询失败", e);
        }
    }
	
    /**
     * 会员卡升级 明细的 查询方法
     * 
     */
    public void doFindVipUpInfo(){
        try {
            super.writeJson(vipCardUpgradeService.doFindVipUpInfo(vipCardUpgradeVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("会员查询失败", e);
        }
    }
	
    /**
     * 通过升级单号 查询 该升级单号下的所有 升级后会员记录
     */
    public void findInfoByVipUpId(){
        try {
            super.writeJson(vipCardUpgradeService.findInfoByVipUpId(vipCardUpgradeVo));
        }catch (Exception e) {
            logger.error("查询升级会员记录失败", e);
        }
    }
	
    /**
     * 将已选升级列表的 传过来的数据 更新到 会员升级表中
     * 
     */
    public void doAddCardUpgrade(){
        try {
            super.writeJson(vipCardUpgradeService.doAddCardUpgrade(vipCardUpgradeVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("会员升级保存失败", e);
        }
    }
	
    /**
     * 删除会员卡升级汇总信息 即 删除中间表的会员卡升级编号对应的数据 以及 升级表中的升级编号对应的记录
     * doRemoveVipUp
     */
    public void doRemoveVipUp(){
        try {
            super.writeJson(vipCardUpgradeService.doRemoveVipUp(vipCardUpgradeVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("删除会员卡升级失败", e);
        }
    }
	
    /**
     * 会员卡升级 修改未审核的升级信息
     */
    public void doEditCardUpgrade(){
        try {
            super.writeJson(vipCardUpgradeService.doEditCardUpgrade(vipCardUpgradeVo, this.getUsers()));
        } catch (Exception e) {
            logger.error("修改会员卡升级失败", e);
        }
    }
	
	/**
	 * 会员升级  审核
	 */
	public void doAudit(){
		try {
		    super.writeJson(vipCardUpgradeService.doAudit(vipCardUpgradeVo, this.getUsers()));
		} catch (Exception e) {
		    logger.error(" 审核会员卡升级失败", e);
		}
	}
}