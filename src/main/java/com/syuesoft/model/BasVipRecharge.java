package com.syuesoft.model;

import java.util.Date;

/** 
 * @ClassName: BasVipInforDefray 
 * @Description: TODO(会员资金收入) 
 * @author HeXin 
 * @date 2013-12-19 上午09:38:01 
 * @version 1.0 
 */
public class BasVipRecharge extends BaseBean {
    private static final long serialVersionUID = 1L;
    private Integer vipRecId;                           //充值编号
	private BasVipInfor basVipInfor;                    //会员
	private Double recAmount;                           //储值金额
	private Double giveAmount;                          //赠送金额
	private Double giveInte;                            //赠送积分
	private String operator;                            //经办人
	private String vipRecNote;                          //经办缘由
	private Date vipRecDate;                            //经办日期
	private String recAuditOper;                        //审核经办人
	private Date recAuditDate;                          //审核日期
	private String recAuditStatus;                      //审核状态
	private String recPayType;                          //付款方式
	private Integer enterpriseId;                       //经办企业
    private Integer parentEnterpriseId;                 //父企业序号
    
	public BasVipRecharge() {
	}

	public Integer getVipRecId() {
		return this.vipRecId;
	}

	public void setVipRecId(Integer vipRecId) {
		this.vipRecId = vipRecId;
	}

	public BasVipInfor getBasVipInfor() {
		return this.basVipInfor;
	}

	public void setBasVipInfor(BasVipInfor basVipInfor) {
		this.basVipInfor = basVipInfor;
	}

	public Double getRecAmount() {
		return this.recAmount;
	}

	public void setRecAmount(Double recAmount) {
		this.recAmount = recAmount;
	}

	public Double getGiveAmount() {
		return this.giveAmount;
	}

	public void setGiveAmount(Double giveAmount) {
		this.giveAmount = giveAmount;
	}

	public Double getGiveInte() {
		return this.giveInte;
	}

	public void setGiveInte(Double giveInte) {
		this.giveInte = giveInte;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getVipRecNote() {
		return this.vipRecNote;
	}

	public void setVipRecNote(String vipRecNote) {
		this.vipRecNote = vipRecNote;
	}

	public Date getVipRecDate() {
		return this.vipRecDate;
	}

	public void setVipRecDate(Date vipRecDate) {
		this.vipRecDate = vipRecDate;
	}

	public String getRecAuditOper() {
		return recAuditOper;
	}

	public void setRecAuditOper(String recAuditOper) {
		this.recAuditOper = recAuditOper;
	}

	public Date getRecAuditDate() {
		return recAuditDate;
	}

	public void setRecAuditDate(Date recAuditDate) {
		this.recAuditDate = recAuditDate;
	}

	public String getRecAuditStatus() {
		return recAuditStatus;
	}

	public void setRecAuditStatus(String recAuditStatus) {
		this.recAuditStatus = recAuditStatus;
	}

	public String getRecPayType() {
		return recPayType;
	}

	public void setRecPayType(String recPayType) {
		this.recPayType = recPayType;
	}

    public Integer getEnterpriseId()
    {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId)
    {
        this.enterpriseId = enterpriseId;
    }

    public Integer getParentEnterpriseId()
    {
        return parentEnterpriseId;
    }

    public void setParentEnterpriseId(Integer parentEnterpriseId)
    {
        this.parentEnterpriseId = parentEnterpriseId;
    }
}