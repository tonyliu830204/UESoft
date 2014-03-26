package com.syuesoft.base.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.syuesoft.model.BasMenuInfo;
import com.syuesoft.model.BasStuff;

public class RoleVo extends BaseBeanVo
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Long roleId; // 角色id

    private String roleName; // 角色名称

    private String systemTypekey; // 系统分类key

    private String systemTypevalue; // 系统分类value

    private Long person; // 创建人

    private String personName; // 创建人名称

    private Date createDate; // 创建时间

    private String systemType; // 系统分类

    private String remark; // 备注

    private Set<BasMenuInfo> basMenuInfos = new HashSet<BasMenuInfo>(0); // 菜单角色

    private Set<BasStuff> basEmptRoleRs = new HashSet<BasStuff>(0); // 员工角色

    private String selecteds; // 已选择人员

    private String checkeds; // 已选择菜单

    private String startcaeateTime;

    private String endcaeateTime;
    
    private String enterpriseId;			//企业信息序号
    
    private String enterpriseChildId;            //企业信息序号
    
    private Boolean distributPurviewFlag;	//分布点权限管理角色保存表示标示

    private Boolean roleDeleteValidateFlag;	//角色删除判断有无人员标志
    
    private String roleDefaultTag;												//默认角色标示:y为是,n为不是
    
    private String systemLevel;
    
    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getSystemTypekey()
    {
        return systemTypekey;
    }

    public void setSystemTypekey(String systemTypekey)
    {
        this.systemTypekey = systemTypekey;
    }

    public String getSystemTypevalue()
    {
        return systemTypevalue;
    }

    public void setSystemTypevalue(String systemTypevalue)
    {
        this.systemTypevalue = systemTypevalue;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Set<BasMenuInfo> getBasMenuInfos()
    {
        return basMenuInfos;
    }

    public void setBasMenuInfos(Set<BasMenuInfo> basMenuInfos)
    {
        this.basMenuInfos = basMenuInfos;
    }

    public Set<BasStuff> getBasEmptRoleRs()
    {
        return basEmptRoleRs;
    }

    public void setBasEmptRoleRs(Set<BasStuff> basEmptRoleRs)
    {
        this.basEmptRoleRs = basEmptRoleRs;
    }

    public String getSelecteds()
    {
        return selecteds;
    }

    public void setSelecteds(String selecteds)
    {
        this.selecteds = selecteds;
    }

    public String getCheckeds()
    {
        return checkeds;
    }

    public void setCheckeds(String checkeds)
    {
        this.checkeds = checkeds;
    }

    public Long getPerson()
    {
        return person;
    }

    public void setPerson(Long person)
    {
        this.person = person;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public String getStartcaeateTime()
    {
        return startcaeateTime;
    }

    public void setStartcaeateTime(String startcaeateTime)
    {
        this.startcaeateTime = startcaeateTime;
    }

    public String getEndcaeateTime()
    {
        return endcaeateTime;
    }

    public void setEndcaeateTime(String endcaeateTime)
    {
        this.endcaeateTime = endcaeateTime;
    }

    public String getSystemType()
    {
        return systemType;
    }

    public void setSystemType(String systemType)
    {
        this.systemType = systemType;
    }

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Boolean getDistributPurviewFlag() {
		return distributPurviewFlag;
	}

	public void setDistributPurviewFlag(Boolean distributPurviewFlag) {
		this.distributPurviewFlag = distributPurviewFlag;
	}

	public Boolean getRoleDeleteValidateFlag() {
		return roleDeleteValidateFlag;
	}

	public void setRoleDeleteValidateFlag(Boolean roleDeleteValidateFlag) {
		this.roleDeleteValidateFlag = roleDeleteValidateFlag;
	}

	public String getRoleDefaultTag() {
		return roleDefaultTag;
	}

	public void setRoleDefaultTag(String roleDefaultTag) {
		this.roleDefaultTag = roleDefaultTag;
	}

	public String getSystemLevel() {
		return systemLevel;
	}

	public void setSystemLevel(String systemLevel) {
		this.systemLevel = systemLevel;
	}

    public String getEnterpriseChildId()
    {
        return enterpriseChildId;
    }

    public void setEnterpriseChildId(String enterpriseChildId)
    {
        this.enterpriseChildId = enterpriseChildId;
    }
}