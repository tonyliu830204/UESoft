package com.syuesoft.vipmanagement.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.vipmanagement.service.IntegralIntegratedQueryService;
import com.syuesoft.vipmanagement.vo.IntegralIntegratedQueryVo;
import org.apache.struts2.convention.annotation.ParentPackage;
/**
* @ClassName: InfomationSendManageAction 
* @Description: TODO(会员积分综合查询) 
* @author HeXin 
* @date 2013-12-9 下午03:54:16 
* @version 1.0
 */
@ParentPackage(value="basePackage")
@Action("integralIntegratedQueryAction")
public class IntegralIntegratedQueryAction extends BaseAction implements ModelDriven<IntegralIntegratedQueryVo> {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
    private IntegralIntegratedQueryVo integralIntegratedQueryVo = new IntegralIntegratedQueryVo();
	public IntegralIntegratedQueryVo getModel() {
		return integralIntegratedQueryVo;
	}
	@Autowired
	private IntegralIntegratedQueryService integralIntegratedQueryService;
	/**
	 * 积分综合查询中的  积分汇总查询
	 */
	public void getIntegralIntegratedQuery(){
		try {
		    super.writeJson(integralIntegratedQueryService.getIntegralIntegratedQuery(integralIntegratedQueryVo, getUsers()));
		} catch (Exception e) {
		    logger.error("积分汇总查询失败", e);
		}
	}
	
	/**
	 * 积分综合查询中的  维修积分查询
	 */
	public void getMaintenancePointsInquiry(){
		try {
		    super.writeJson(integralIntegratedQueryService.getMaintenancePointsInquiry(integralIntegratedQueryVo, getUsers()));
		} catch (Exception e) {
		    logger.error("维修积分查询失败", e);
		}
	}
	
	/**
     * 积分综合查询中的  销售积分查询
     */
    public void getSellPointsInquiry(){
        try {
            super.writeJson(integralIntegratedQueryService.getSellPointsInquiry(integralIntegratedQueryVo, getUsers()));
        } catch (Exception e) {
            logger.error("销售积分查询失败", e);
        }
    }
    
	/**
	 * 积分综合查询中的  储值赠分查询
	 */
	public void getStoredValuePoints(){
		try {
		    super.writeJson(integralIntegratedQueryService.getStoredValuePoints(integralIntegratedQueryVo, getUsers()));
		} catch (Exception e) {
		    logger.error("储值赠分查询失败", e);
		}
	} 
	/**
	 * 积分综合查询中的  赠送积分查询
	 * 
	 */
	public void getPresentationPoints(){
		try {
		    super.writeJson(integralIntegratedQueryService.getPresentationPoints(integralIntegratedQueryVo, getUsers()));
		} catch (Exception e) {
		    logger.error("赠送积分查询失败", e);
		}
	}
	
	public void getConvertPoints(){
        try {
            super.writeJson(integralIntegratedQueryService.getConvertPoints(integralIntegratedQueryVo, getUsers()));
        } catch (Exception e) {
            logger.error("赠送积分查询失败", e);
        }
    }
	
	/**
	 * 积分 查询的    会员卡升级 
	 * 
	 */
	public void getVipCardUpgrade(){
		try {
		    super.writeJson(integralIntegratedQueryService.getVipCardUpgrade(integralIntegratedQueryVo, getUsers()));
		} catch (Exception e) {
		    logger.error("会员卡升级查询失败", e);
		}
	}
	
	/**
	 * 积分综合查询的续会赠分
	 */
	public void getAdjournmentFind(){
		try {
		    super.writeJson(integralIntegratedQueryService.getAdjournmentFind(integralIntegratedQueryVo, getUsers()));
		} catch (Exception e) {
		    logger.error("续会赠分查询失败", e);
		}
	}
	
	public void getExitMemberFind(){
		try {
		    super.writeJson(integralIntegratedQueryService.getExitMemberFind(integralIntegratedQueryVo, getUsers()));
		} catch (Exception e) {
		    logger.error("退会扣分查询失败", e);
		}
	}
}