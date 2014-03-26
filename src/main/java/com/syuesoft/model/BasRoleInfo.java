package com.syuesoft.model;

import java.util.Date;

public class BasRoleInfo extends BaseBean{

	private static final long serialVersionUID = 1L;
	private Long roleId;                                                       //角色id
	private EnterpriseInfo enterpriseInfo;									   //企业信息
	private String roleName;                                                   //角色名称
	private String systemType;                                                 //系统分类
	private String remark;                                                     //备注
	private Long person;                                                       //创建人
	private Date createDate;                                                   //创建时间
	private String roleDefaultTag;											   //默认角色标示:y为是,n为不是
	private Integer enterpriseChildId;                                              //所属企业
	
	public BasRoleInfo() {
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Long getPerson() {
		return person;
	}

	public void setPerson(Long person) {
		this.person = person;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public EnterpriseInfo getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}

	public String getRoleDefaultTag() {
		return roleDefaultTag;
	}

	public void setRoleDefaultTag(String roleDefaultTag) {
		this.roleDefaultTag = roleDefaultTag;
	}

    public Integer getEnterpriseChildId()
    {
        return enterpriseChildId;
    }

    public void setEnterpriseChildId(Integer enterpriseChildId)
    {
        this.enterpriseChildId = enterpriseChildId;
    }
}