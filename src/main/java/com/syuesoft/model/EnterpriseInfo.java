package com.syuesoft.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
* @ClassName: EnterpriseInfo 
* @Description: TODO(企业信息) 
* @author HeXin 
* @date 2013-9-6 下午04:52:49 
* @version 1.0
 */
public class EnterpriseInfo extends BaseBean implements Serializable{
    
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer enterpriseId;       //企业序号
	private Integer parentEnterpriseId; //父企业序号
	private String enterpriseCode;	    //企业编号
	private String enterpriseName;	    //企业名称
	private String enterpriseJm;	    //企业简称
	private String enterpriseAddress;	//企业地址
	private String enterpriseZipcode;	//邮政编码
	private String enterpriseFax;		//传真
	private String enterpriseTelephone;	//电话
	private String enterprisePerson;	//企业法人
	private Integer bank;				//开户银行
	private Integer bankName;				//开户银行名称
	private String bankNumber;			//帐号
	private String dutyNumber;			//税号
	private String complainTelephone;	//投诉电话
	private String hotlineTelephone;	//销售热线
	private String enterpriseUrl;		//网址
	private String enterpriseEmail;		//邮箱
	private String delTag;				//删除标示
	//private String enterpriseType;      //企业所属分类 --  维修、销售
	
	private Set basStuffs = new HashSet(0);	//企业对应员工集合
	
	
	public Integer getBankName() {
		return bankName;
	}
	public void setBankName(Integer bankName) {
		this.bankName = bankName;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getEnterpriseJm() {
		return enterpriseJm;
	}
	public void setEnterpriseJm(String enterpriseJm) {
		this.enterpriseJm = enterpriseJm;
	}
	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}
	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}
	public String getEnterpriseZipcode() {
		return enterpriseZipcode;
	}
	public void setEnterpriseZipcode(String enterpriseZipcode) {
		this.enterpriseZipcode = enterpriseZipcode;
	}
	public String getEnterpriseFax() {
		return enterpriseFax;
	}
	public void setEnterpriseFax(String enterpriseFax) {
		this.enterpriseFax = enterpriseFax;
	}
	public String getEnterpriseTelephone() {
		return enterpriseTelephone;
	}
	public void setEnterpriseTelephone(String enterpriseTelephone) {
		this.enterpriseTelephone = enterpriseTelephone;
	}
	public String getEnterprisePerson() {
		return enterprisePerson;
	}
	public void setEnterprisePerson(String enterprisePerson) {
		this.enterprisePerson = enterprisePerson;
	}
	public Integer getBank() {
		return bank;
	}
	public void setBank(Integer bank) {
		this.bank = bank;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getDutyNumber() {
		return dutyNumber;
	}
	public void setDutyNumber(String dutyNumber) {
		this.dutyNumber = dutyNumber;
	}
	public String getComplainTelephone() {
		return complainTelephone;
	}
	public void setComplainTelephone(String complainTelephone) {
		this.complainTelephone = complainTelephone;
	}
	public String getHotlineTelephone() {
		return hotlineTelephone;
	}
	public void setHotlineTelephone(String hotlineTelephone) {
		this.hotlineTelephone = hotlineTelephone;
	}
	public String getEnterpriseUrl() {
		return enterpriseUrl;
	}
	public void setEnterpriseUrl(String enterpriseUrl) {
		this.enterpriseUrl = enterpriseUrl;
	}
	public String getEnterpriseEmail() {
		return enterpriseEmail;
	}
	public void setEnterpriseEmail(String enterpriseEmail) {
		this.enterpriseEmail = enterpriseEmail;
	}
    public Integer getParentEnterpriseId()
    {
        return parentEnterpriseId;
    }
    public void setParentEnterpriseId(Integer parentEnterpriseId)
    {
        this.parentEnterpriseId = parentEnterpriseId;
    }
	public Set getBasStuffs() {
		return basStuffs;
	}
	public void setBasStuffs(Set basStuffs) {
		this.basStuffs = basStuffs;
	}
	public String getDelTag() {
		return delTag;
	}
	public void setDelTag(String delTag) {
		this.delTag = delTag;
	}
	
//    public String getEnterpriseType()
//    {
//        return enterpriseType;
//    }
//    public void setEnterpriseType(String enterpriseType)
//    {
//        this.enterpriseType = enterpriseType;
//    }
    
}