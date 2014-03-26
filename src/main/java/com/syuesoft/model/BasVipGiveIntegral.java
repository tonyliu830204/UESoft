package com.syuesoft.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BasVipGiveIntegral entity. @author MyEclipse Persistence Tools
 */

public class BasVipGiveIntegral extends BaseBean{

	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer giveInteId;                                 //赠送积分汇总编号
	private BasVipInfor basVipInfor;                            //会员
	private Double giveInteNum;                                 //赠送总积分
	private String operator;                                    //经办人
	private Date giveInteDate;                                  //赠送日期
	private String shenheQingkuang;                             //审核状态 
	private Date shenheRiqi;                                    //审核日期
	private String shenheYuan;                                  //审核人员
	private String giveInteNote;                                //赠送备注
	private String enterprise_id;                               //兑换企业编号
	private Set<BasVipRelationship16> basVipRelationship16s = new HashSet<BasVipRelationship16>(0);

	/** default constructor */
	public BasVipGiveIntegral() {
	}

	public Integer getGiveInteId() {
		return this.giveInteId;
	}

	public void setGiveInteId(Integer giveInteId) {
		this.giveInteId = giveInteId;
	}

	public BasVipInfor getBasVipInfor() {
		return this.basVipInfor;
	}

	public void setBasVipInfor(BasVipInfor basVipInfor) {
		this.basVipInfor = basVipInfor;
	}

	public Double getGiveInteNum() {
		return this.giveInteNum;
	}

	public void setGiveInteNum(Double giveInteNum) {
		this.giveInteNum = giveInteNum;
	}

	public String getGiveInteNote() {
		return this.giveInteNote;
	}

	public void setGiveInteNote(String giveInteNote) {
		this.giveInteNote = giveInteNote;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getGiveInteDate() {
		return this.giveInteDate;
	}

	public void setGiveInteDate(Date giveInteDate) {
		this.giveInteDate = giveInteDate;
	}

	public String getShenheQingkuang() {
		return this.shenheQingkuang;
	}

	public void setShenheQingkuang(String shenheQingkuang) {
		this.shenheQingkuang = shenheQingkuang;
	}

	public Date getShenheRiqi() {
		return this.shenheRiqi;
	}

	public void setShenheRiqi(Date shenheRiqi) {
		this.shenheRiqi = shenheRiqi;
	}

	public String getShenheYuan() {
		return this.shenheYuan;
	}

	public void setShenheYuan(String shenheYuan) {
		this.shenheYuan = shenheYuan;
	}

	public Set<BasVipRelationship16> getBasVipRelationship16s() {
		return this.basVipRelationship16s;
	}

	public void setBasVipRelationship16s(Set<BasVipRelationship16> basVipRelationship16s) {
		this.basVipRelationship16s = basVipRelationship16s;
	}

    public String getEnterprise_id()
    {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterpriseId)
    {
        enterprise_id = enterpriseId;
    }
}