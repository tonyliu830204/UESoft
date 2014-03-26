package com.syuesoft.base.vo;


public class EnterpriseInfoVo extends BaseBeanVo{
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer enterpriseId;  //企业序号
	private Integer parentEnterpriseId;  //企业序号
	private String enterpriseCode;	//企业编号
	private String enterpriseName;	//企业名称
	private String parentEnterpriseName; //企业名称
	private String enterpriseJm;	//企业简称
	private String enterpriseAddress;	//企业地址
	private String enterpriseZipcode;	//邮政编码
	private String enterpriseFax;		//传真
	private String enterpriseTelephone;	//电话
	private String enterprisePerson;	//企业法人
	private Integer bank;				//开户银行
	private String bankName;				//开户银行名称
	private String bankNumber;			//帐号
	private String dutyNumber;			//税号
	private String complainTelephone;	//投诉电话
	private String hotlineTelephone;	//销售热线
	private String enterpriseUrl;		//网址
	private String enterpriseEmail;		//邮箱
	
	private Integer workId;                     //企业业务序号
    private String enterpriseGjFootnote;        //估价单注脚
    private String enterpriseJSISONO;           //派工单ISO编号
    private String enterprisePgHead;            //派工单抬头
    private String enterpriseSpFootnot;         //索赔单注脚
    private String enterpriseJcFootnote;        //接车单注脚
    private String enterpriseJsHead;            //结算单抬头
    private Integer enterpriseLoginLimit;       //最大登录上限
    private Integer enterpriseSMSLimit;         //可用短信条数
    private String outboundnumber;              //出库单ISO编号
    private String enterprisePorint;            //网点编号
    private String enterpriseRemark;            //车档案备注格式
    private String enterprisePath;              //最新文件目录
    private String enterpriseJsFootnote;        //结算单注脚
	
    private String enterpriseType;
    
    private Boolean enterpriseInfoFlag;			//企业目录查询标示
    private String basUsersLevel;				//员工系统级别
    
    public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	private Boolean enterpriseDeleteValidateFlag;	//企业删除判断有无人员标志
    
    private String type;
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
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
	public String getEnterpriseRemark() {
		return enterpriseRemark;
	}
	public void setEnterpriseRemark(String enterpriseRemark) {
		this.enterpriseRemark = enterpriseRemark;
	}
	public String getEnterprisePath() {
		return enterprisePath;
	}
	public void setEnterprisePath(String enterprisePath) {
		this.enterprisePath = enterprisePath;
	}
	public String getEnterpriseJsFootnote() {
		return enterpriseJsFootnote;
	}
	public void setEnterpriseJsFootnote(String enterpriseJsFootnote) {
		this.enterpriseJsFootnote = enterpriseJsFootnote;
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
    public String getParentEnterpriseName()
    {
        return parentEnterpriseName;
    }
    public void setParentEnterpriseName(String parentEnterpriseName)
    {
        this.parentEnterpriseName = parentEnterpriseName;
    }
    public Integer getWorkId()
    {
        return workId;
    }
    public void setWorkId(Integer workId)
    {
        this.workId = workId;
    }
    public String getEnterpriseGjFootnote()
    {
        return enterpriseGjFootnote;
    }
    public void setEnterpriseGjFootnote(String enterpriseGjFootnote)
    {
        this.enterpriseGjFootnote = enterpriseGjFootnote;
    }
    public String getEnterpriseJSISONO()
    {
        return enterpriseJSISONO;
    }
    public void setEnterpriseJSISONO(String enterpriseJSISONO)
    {
        this.enterpriseJSISONO = enterpriseJSISONO;
    }
    public String getEnterprisePgHead()
    {
        return enterprisePgHead;
    }
    public void setEnterprisePgHead(String enterprisePgHead)
    {
        this.enterprisePgHead = enterprisePgHead;
    }
    public String getEnterpriseSpFootnot()
    {
        return enterpriseSpFootnot;
    }
    public void setEnterpriseSpFootnot(String enterpriseSpFootnot)
    {
        this.enterpriseSpFootnot = enterpriseSpFootnot;
    }
    public String getEnterpriseJcFootnote()
    {
        return enterpriseJcFootnote;
    }
    public void setEnterpriseJcFootnote(String enterpriseJcFootnote)
    {
        this.enterpriseJcFootnote = enterpriseJcFootnote;
    }
    public String getEnterpriseJsHead()
    {
        return enterpriseJsHead;
    }
    public void setEnterpriseJsHead(String enterpriseJsHead)
    {
        this.enterpriseJsHead = enterpriseJsHead;
    }
    public Integer getEnterpriseLoginLimit()
    {
        return enterpriseLoginLimit;
    }
    public void setEnterpriseLoginLimit(Integer enterpriseLoginLimit)
    {
        this.enterpriseLoginLimit = enterpriseLoginLimit;
    }
    public Integer getEnterpriseSMSLimit()
    {
        return enterpriseSMSLimit;
    }
    public void setEnterpriseSMSLimit(Integer enterpriseSMSLimit)
    {
        this.enterpriseSMSLimit = enterpriseSMSLimit;
    }
    public String getOutboundnumber()
    {
        return outboundnumber;
    }
    public void setOutboundnumber(String outboundnumber)
    {
        this.outboundnumber = outboundnumber;
    }
    public String getEnterprisePorint()
    {
        return enterprisePorint;
    }
    public void setEnterprisePorint(String enterprisePorint)
    {
        this.enterprisePorint = enterprisePorint;
    }
    public String getEnterpriseType()
    {
        return enterpriseType;
    }
    public void setEnterpriseType(String enterpriseType)
    {
        this.enterpriseType = enterpriseType;
    }
	public Boolean getEnterpriseInfoFlag() {
		return enterpriseInfoFlag;
	}
	public void setEnterpriseInfoFlag(Boolean enterpriseInfoFlag) {
		this.enterpriseInfoFlag = enterpriseInfoFlag;
	}
	public String getBasUsersLevel() {
		return basUsersLevel;
	}
	public void setBasUsersLevel(String basUsersLevel) {
		this.basUsersLevel = basUsersLevel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getEnterpriseDeleteValidateFlag() {
		return enterpriseDeleteValidateFlag;
	}
	public void setEnterpriseDeleteValidateFlag(Boolean enterpriseDeleteValidateFlag) {
		this.enterpriseDeleteValidateFlag = enterpriseDeleteValidateFlag;
	}
    
}