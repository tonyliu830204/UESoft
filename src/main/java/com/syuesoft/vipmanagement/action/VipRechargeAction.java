package com.syuesoft.vipmanagement.action;
/**
 * 会员卡储值
 */
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.model.BasVipRechargeRegulation;
import com.syuesoft.vipmanagement.service.VipRechargeService;
import com.syuesoft.vipmanagement.vo.VipRecordMessageVo;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("VipRechargeAction")
public class VipRechargeAction extends BaseAction implements ModelDriven<VipRecordMessageVo> {
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private VipRechargeService vipRechargeService;
	
	private VipRecordMessageVo vrmVO = new VipRecordMessageVo();
	
	HttpSession session = ServletActionContext.getRequest().getSession();
	
	private Integer recMoney;

	public Integer getRecMoney() {
		return recMoney;
	}

	public void setRecMoney(Integer recMoney) {
		this.recMoney = recMoney;
	}

	public VipRecordMessageVo getVrmVO() {
		return vrmVO;
	}

	public void setVrmVO(VipRecordMessageVo vrmVO) {
		this.vrmVO = vrmVO;
	}

	
	public VipRecordMessageVo getModel() {
		return vrmVO;
	}
	
	/**
     * 获取所有充值记录
     * */
    public void findAll(){
        try {
            super.writeJson(vipRechargeService.findAll(vrmVO, getUsers()));
        } catch (Exception e) {
            logger.error("充值记录查询失败", e);
        }
    }
    
	/**
	 * 读卡
	 * */
	public void readCard(){
		try {
			super.writeJson(vipRechargeService.readCard(vrmVO, getUsers()));
		} catch (Exception e) {
		    logger.error("读卡失败", e);
		}
	}
	
	/**
     * 获取充值信息
     * */
    public void readVipRedInfo(){
        try {
            super.writeJson(vipRechargeService.readVipRedInfo(vrmVO));
        } catch (Exception e) {
            logger.error("读卡失败", e);
        }
    }
    
	/**
	 * 会员充值
	 * */
	public void recharge(){
		try {
			super.writeJson(vipRechargeService.add(vrmVO, getUsers()));
		} catch (Exception e) {
			logger.error("会员充值失败", e);
		}
	}
	/**
     * 更新会员充值
     */
    public void update(){
        try {
            super.writeJson(vipRechargeService.update(vrmVO, getUsers()));
        } catch (Exception e) {
            logger.error("更新会员充值失败", e);
        }
    }
    
    /**
     * 删除会员储值
     * */
    public void delete(){
        try {
            super.writeJson(vipRechargeService.delete(vrmVO, getUsers()));
        } catch (Exception e) {
            logger.error("删除会员储值失败", e);
        }
    }
    
	/**
	 * 审核
	 */
	public void auditRecharge(){
		try {
			super.writeJson(vipRechargeService.updateVipRecharge(vrmVO, getUsers()));
		} catch (Exception e) {
		    logger.error("审核会员储值失败", e);
		}
	}
	
	/**
     * 获取充值规则
     * */
    public void getByRecAmount(){
        try {
            BasVipRechargeRegulation bvr = new BasVipRechargeRegulation();
            bvr = vipRechargeService.getByRecAmount(recMoney);
            super.writeJson(bvr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}