package com.syuesoft.base.vo;

/*
 * 员工信息    Vo  
 */
public class BasStuffVo
{

    private String stfId;

    private String pcstfId;

    private String stfYid;

    private String pcStfYid;

    private String stfName;

    private String pcStfName;

    private String stfMark;

    private Long userId; // 登录编号

    private String loginName; // 登录帐号

    private String loginPassword; // 登录密码

    private String oldPassword; // 登录密码
    // private String userState; //用户状态

    private String accountState; // 访问状态

    private String systemId; // 企业网点编号
    
    private String systemKey;
    
    private String systemValue;
    
    private String leavl; // 员工级别 超级管理员 企业管理员 员工

    private String stfSex;

    private String stfSexvalue;

    private String stfYes;

    private String stfYesvalue;

    private String deptId;

    private String deptName;

    private String repgrpId;

    private String repgrpName;

    private String stfZwgz; // 职位工种

    private String stfPhone;

    private String stfTel;

    private String stfZxqk;

    private String stfZxqkvalue;

    private String stfBirthday;

    private String stfGj; // 籍贯

    private String stfWorkDate;

    private String jobProId;				//工作属性

    private String jobProName;

    private String stfByyx; // 毕业院校

    private String stfBysj; // 毕业时间

    private String stfSxzy; // 所学专业

    private String stfWhcd; // 文化程度

    private String stfGznx; // 工作年限

    private String stfJsdj;// 技术等级

    private String stfYhbyzs;// 已获职业证书

    private String stfSfzhm;// 身份证号码

    private String stfSg;// 身高

    private String stfTz;// 体重

    private String stfSl;// 视力

    private String stfXx;// 血型

    private String stfHyzk;// 婚姻状况

    private String stfHkszd;// 户口所在地

    private String stfXjzdz;// 现居住地址

    private String stfMz;// 民族

    private String stfZzmm;// 政治面貌

    private String stfWysp;// 外语水平

    private String stfJsjsp;// 计算机水平

    private String stfDzyx;// 电子邮箱

    private String stfTc;// 特长

    private String stfAh;// 爱好

    private String stfDbrxm;// 担保人姓名

    private String stfDbrsfzhm;// 担保人身份证号码

    private String stfPoxm;// 配偶姓名

    private String stfPosfzhm;// 配偶身份证号码

    private String stfYjrq;// 押金日期

    private String stfYjje;// 押金金额

    private String stfYjbz;// 押金备注

    private String baseInfo;

    private String loginInfo;

    private String otherInfo;

    private int printTempletId;
    
    private String enterpriseId;			//公司序号
    
    private String enterpriseName;			//公司名称
    
    private String parentEnterpriseId;      //父公司
    
    private String repgrpId2;				//销售班组序号
    
    private String repgrpId2Name;				//销售班组名称
    
    private String roleName;					//角色名称
    
    private String tempEnterpriseId;
    // 排序
    private String sort;

    private String order;

    // 分页
    private int page;
    private int rows;

    public BasStuffVo()
    {

    }
    
    public String getStfZwgz()
    {
        return stfZwgz;
    }

    public void setStfZwgz(String stfZwgz)
    {
        this.stfZwgz = stfZwgz;
    }

    public String getStfGj()
    {
        return stfGj;
    }

    public void setStfGj(String stfGj)
    {
        this.stfGj = stfGj;
    }

    public String getStfZxqk()
    {
        return stfZxqk;
    }

    public void setStfZxqk(String stfZxqk)
    {
        this.stfZxqk = stfZxqk;
    }

    public String getStfByyx()
    {
        return stfByyx;
    }

    public void setStfByyx(String stfByyx)
    {
        this.stfByyx = stfByyx;
    }

    public String getStfBysj()
    {
        return stfBysj;
    }

    public void setStfBysj(String stfBysj)
    {
        this.stfBysj = stfBysj;
    }

    public String getStfSxzy()
    {
        return stfSxzy;
    }

    public void setStfSxzy(String stfSxzy)
    {
        this.stfSxzy = stfSxzy;
    }

    public String getStfWhcd()
    {
        return stfWhcd;
    }

    public void setStfWhcd(String stfWhcd)
    {
        this.stfWhcd = stfWhcd;
    }

    public String getStfGznx()
    {
        return stfGznx;
    }

    public void setStfGznx(String stfGznx)
    {
        this.stfGznx = stfGznx;
    }

    public String getStfJsdj()
    {
        return stfJsdj;
    }

    public void setStfJsdj(String stfJsdj)
    {
        this.stfJsdj = stfJsdj;
    }

    public String getStfYhbyzs()
    {
        return stfYhbyzs;
    }

    public void setStfYhbyzs(String stfYhbyzs)
    {
        this.stfYhbyzs = stfYhbyzs;
    }

    public String getStfSfzhm()
    {
        return stfSfzhm;
    }

    public void setStfSfzhm(String stfSfzhm)
    {
        this.stfSfzhm = stfSfzhm;
    }

    public String getStfSg()
    {
        return stfSg;
    }

    public void setStfSg(String stfSg)
    {
        this.stfSg = stfSg;
    }

    public String getStfTz()
    {
        return stfTz;
    }

    public void setStfTz(String stfTz)
    {
        this.stfTz = stfTz;
    }

    public String getStfSl()
    {
        return stfSl;
    }

    public void setStfSl(String stfSl)
    {
        this.stfSl = stfSl;
    }

    public String getStfXx()
    {
        return stfXx;
    }

    public void setStfXx(String stfXx)
    {
        this.stfXx = stfXx;
    }

    public String getStfHyzk()
    {
        return stfHyzk;
    }

    public void setStfHyzk(String stfHyzk)
    {
        this.stfHyzk = stfHyzk;
    }

    public String getStfHkszd()
    {
        return stfHkszd;
    }

    public void setStfHkszd(String stfHkszd)
    {
        this.stfHkszd = stfHkszd;
    }

    public String getStfXjzdz()
    {
        return stfXjzdz;
    }

    public void setStfXjzdz(String stfXjzdz)
    {
        this.stfXjzdz = stfXjzdz;
    }

    public String getStfMz()
    {
        return stfMz;
    }

    public void setStfMz(String stfMz)
    {
        this.stfMz = stfMz;
    }

    public String getStfZzmm()
    {
        return stfZzmm;
    }

    public void setStfZzmm(String stfZzmm)
    {
        this.stfZzmm = stfZzmm;
    }

    public String getStfWysp()
    {
        return stfWysp;
    }

    public void setStfWysp(String stfWysp)
    {
        this.stfWysp = stfWysp;
    }

    public String getStfJsjsp()
    {
        return stfJsjsp;
    }

    public void setStfJsjsp(String stfJsjsp)
    {
        this.stfJsjsp = stfJsjsp;
    }

    public String getStfDzyx()
    {
        return stfDzyx;
    }

    public void setStfDzyx(String stfDzyx)
    {
        this.stfDzyx = stfDzyx;
    }

    public String getStfTc()
    {
        return stfTc;
    }

    public void setStfTc(String stfTc)
    {
        this.stfTc = stfTc;
    }

    public String getStfAh()
    {
        return stfAh;
    }

    public void setStfAh(String stfAh)
    {
        this.stfAh = stfAh;
    }

    public String getStfDbrxm()
    {
        return stfDbrxm;
    }

    public void setStfDbrxm(String stfDbrxm)
    {
        this.stfDbrxm = stfDbrxm;
    }

    public String getStfDbrsfzhm()
    {
        return stfDbrsfzhm;
    }

    public void setStfDbrsfzhm(String stfDbrsfzhm)
    {
        this.stfDbrsfzhm = stfDbrsfzhm;
    }

    public String getStfPoxm()
    {
        return stfPoxm;
    }

    public void setStfPoxm(String stfPoxm)
    {
        this.stfPoxm = stfPoxm;
    }

    public String getStfPosfzhm()
    {
        return stfPosfzhm;
    }

    public void setStfPosfzhm(String stfPosfzhm)
    {
        this.stfPosfzhm = stfPosfzhm;
    }

    public String getStfYjrq()
    {
        return stfYjrq;
    }

    public void setStfYjrq(String stfYjrq)
    {
        this.stfYjrq = stfYjrq;
    }

    public String getStfYjje()
    {
        return stfYjje;
    }

    public void setStfYjje(String stfYjje)
    {
        this.stfYjje = stfYjje;
    }

    public String getStfYjbz()
    {
        return stfYjbz;
    }

    public void setStfYjbz(String stfYjbz)
    {
        this.stfYjbz = stfYjbz;
    }

    public String getRepgrpId()
    {
        return repgrpId;
    }

    public void setRepgrpId(String repgrpId)
    {
        this.repgrpId = repgrpId;
    }

    public String getRepgrpName()
    {
        return repgrpName;
    }

    public void setRepgrpName(String repgrpName)
    {
        this.repgrpName = repgrpName;
    }

    public String getStfId()
    {
        return stfId;
    }

    public void setStfId(String stfId)
    {
        this.stfId = stfId;
    }

    public String getStfYid()
    {
        return stfYid;
    }

    public void setStfYid(String stfYid)
    {
        this.stfYid = stfYid;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public String getStfMark()
    {
        return stfMark;
    }

    public void setStfMark(String stfMark)
    {
        this.stfMark = stfMark;
    }

    public String getStfSex()
    {
        return stfSex;
    }

    public void setStfSex(String stfSex)
    {
        this.stfSex = stfSex;
    }

    public String getStfYes()
    {
        return stfYes;
    }

    public void setStfYes(String stfYes)
    {
        this.stfYes = stfYes;
    }

    public String getJobProId()
    {
        return jobProId;
    }

    public void setJobProId(String jobProId)
    {
        this.jobProId = jobProId;
    }

    public String getJobProName()
    {
        return jobProName;
    }

    public void setJobProName(String jobProName)
    {
        this.jobProName = jobProName;
    }

    public String getDeptId()
    {
        return deptId;
    }

    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getStfBirthday()
    {
        return stfBirthday;
    }

    public void setStfBirthday(String stfBirthday)
    {
        this.stfBirthday = stfBirthday;
    }

    public String getStfWorkDate()
    {
        return stfWorkDate;
    }

    public void setStfWorkDate(String stfWorkDate)
    {
        this.stfWorkDate = stfWorkDate;
    }

    public String getStfPhone()
    {
        return stfPhone;
    }

    public void setStfPhone(String stfPhone)
    {
        this.stfPhone = stfPhone;
    }

    public String getStfTel()
    {
        return stfTel;
    }

    public void setStfTel(String stfTel)
    {
        this.stfTel = stfTel;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginPassword()
    {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword)
    {
        this.loginPassword = loginPassword;
    }

    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    public String getAccountState()
    {
        return accountState;
    }

    public void setAccountState(String accountState)
    {
        this.accountState = accountState;
    }

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getPcStfYid()
    {
        return pcStfYid;
    }

    public void setPcStfYid(String pcStfYid)
    {
        this.pcStfYid = pcStfYid;
    }

    public String getPcStfName()
    {
        return pcStfName;
    }

    public void setPcStfName(String pcStfName)
    {
        this.pcStfName = pcStfName;
    }

    public String getStfZxqkvalue()
    {
        return stfZxqkvalue;
    }

    public void setStfZxqkvalue(String stfZxqkvalue)
    {
        this.stfZxqkvalue = stfZxqkvalue;
    }

    public String getStfSexvalue()
    {
        return stfSexvalue;
    }

    public void setStfSexvalue(String stfSexvalue)
    {
        this.stfSexvalue = stfSexvalue;
    }

    public String getStfYesvalue()
    {
        return stfYesvalue;
    }

    public void setStfYesvalue(String stfYesvalue)
    {
        this.stfYesvalue = stfYesvalue;
    }

    public String getBaseInfo()
    {
        return baseInfo;
    }

    public void setBaseInfo(String baseInfo)
    {
        this.baseInfo = baseInfo;
    }

    public String getLoginInfo()
    {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo)
    {
        this.loginInfo = loginInfo;
    }

    public String getOtherInfo()
    {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo)
    {
        this.otherInfo = otherInfo;
    }

    public int getPrintTempletId()
    {
        return printTempletId;
    }

    public void setPrintTempletId(int printTempletId)
    {
        this.printTempletId = printTempletId;
    }

    public String getLeavl()
    {
        return leavl;
    }

    public void setLeavl(String leavl)
    {
        this.leavl = leavl;
    }

    public String getPcstfId()
    {
        return pcstfId;
    }

    public void setPcstfId(String pcstfId)
    {
        this.pcstfId = pcstfId;
    }

    public String getSystemKey()
    {
        return systemKey;
    }

    public void setSystemKey(String systemKey)
    {
        this.systemKey = systemKey;
    }

    public String getSystemValue()
    {
        return systemValue;
    }

    public void setSystemValue(String systemValue)
    {
        this.systemValue = systemValue;
    }

    public String getRepgrpId2()
    {
        return repgrpId2;
    }

    public void setRepgrpId2(String repgrpId2)
    {
        this.repgrpId2 = repgrpId2;
    }

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getRepgrpId2Name() {
		return repgrpId2Name;
	}

	public void setRepgrpId2Name(String repgrpId2Name) {
		this.repgrpId2Name = repgrpId2Name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    public String getParentEnterpriseId()
    {
        return parentEnterpriseId;
    }

    public void setParentEnterpriseId(String parentEnterpriseId)
    {
        this.parentEnterpriseId = parentEnterpriseId;
    }

	public String getTempEnterpriseId() {
		return tempEnterpriseId;
	}

	public void setTempEnterpriseId(String tempEnterpriseId) {
		this.tempEnterpriseId = tempEnterpriseId;
	}
    
}