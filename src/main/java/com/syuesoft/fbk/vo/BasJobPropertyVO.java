package com.syuesoft.fbk.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class BasJobPropertyVO extends BaseBeanVo
{
    private Short jobProId;				//工作属序号

    private String jobProName;			//工作名称

    private String systemId;
    
    private String jobProNote;			//备注
    
    private String jsonData;
    
    private Integer enterpriseId;       //企业序号
    
    private String enterpriseName;	    //企业名称
    
    private String stfId;				//登陆员工Id
    
    private Boolean flag;				//是否限制为当前用户所在公司，false 为不限制，默认为true为限制
    
	private String sysType;				//系统类型
	
    public BasJobPropertyVO()
    {
    }

    public BasJobPropertyVO(Short jobProId, String jobProName, String jobProNote)
    {
        this.jobProId = jobProId;
        this.jobProName = jobProName;
        this.jobProNote = jobProNote;
    }

    public Short getJobProId()
    {
        return jobProId;
    }

    public void setJobProId(Short jobProId)
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

    public String getJobProNote()
    {
        return jobProNote;
    }

    public void setJobProNote(String jobProNote)
    {
        this.jobProNote = jobProNote;
    }

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public String getJsonData()
    {
        return jsonData;
    }

    public void setJsonData(String jsonData)
    {
        this.jsonData = jsonData;
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

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
    
}