package com.syuesoft.base.vo;

public class BasDeptVo extends BaseBeanVo
{
    private String deptId;				//部门序号
    
    private Integer enterpriseId;       //企业序号
    private Integer nowEnterpriseId;
    
    private String enterpriseName;	    //企业名称

    private String deptName;			//部门名称

    private String deptNum;				

    private String deptDesc;

    private String deptRemarks;			//备注

    private String memo;
    
    private String stfId;				//登陆员工Id
    
    private Boolean flag;				//是否限制为当前用户所在公司，false 为不限制，默认为true为限制
    public BasDeptVo() {
	}
    public BasDeptVo(String deptId) {
		super();
		this.deptId = deptId;
	}

	public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
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

    public String getDeptNum()
    {
        return deptNum;
    }

    public void setDeptNum(String deptNum)
    {
        this.deptNum = deptNum;
    }

    public String getDeptDesc()
    {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }

    public String getDeptRemarks()
    {
        return deptRemarks;
    }

    public void setDeptRemarks(String deptRemarks)
    {
        this.deptRemarks = deptRemarks;
    }

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getStfId() {
		return stfId;
	}

	public void setStfId(String stfId) {
		this.stfId = stfId;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Integer getNowEnterpriseId() {
		return nowEnterpriseId;
	}
	public void setNowEnterpriseId(Integer nowEnterpriseId) {
		this.nowEnterpriseId = nowEnterpriseId;
	}
	

}
