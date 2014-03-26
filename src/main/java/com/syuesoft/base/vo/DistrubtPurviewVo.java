package com.syuesoft.base.vo;

/**
 * 分布点权限管理Vo
 * */
public class DistrubtPurviewVo extends BaseBeanVo{
    private Integer enterpriseId;  			//企业序号
	private Integer parentEnterpriseId;  	//企业父级序号
	private String enterpriseCode;			//企业编号
	private String enterpriseName;			//企业名称
	private String parentEnterpriseName; 	//企业名称
	private String stfId;					//员工编号
	private String stfName;					//员工名称
	private String basUsersLevel;			//员工系统级别
	private String basUsersLevelName;			//员工系统级别名称
	private String deptName;				//部门名称
	private String stfZwgz;					//职位工种
	private String stfPhone;				//员工手机号
	private String stfJsdj;					//技术等级
	private String systemType;				//系统类型
	private String systemTypeName;			//系统类型名称
	private String roleId;					//角色序号
	private String roleName;				//角色名称
	private String userId;					//登陆用户序号
	private Boolean isTrue;					//操作集团管理员标示
	
	private String removeLevel;				//去除指定员工系统级别
	
	private String enterpriseJm;	    //企业简称
	private String enterprisePerson;	//企业法人
	
    private String state; // treegrid打开还是关闭

    private String iconCls;
    
    private String q;						//下拉框查询字段
	
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getParentEnterpriseId() {
		return parentEnterpriseId;
	}
	public void setParentEnterpriseId(Integer parentEnterpriseId) {
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
	public String getParentEnterpriseName() {
		return parentEnterpriseName;
	}
	public void setParentEnterpriseName(String parentEnterpriseName) {
		this.parentEnterpriseName = parentEnterpriseName;
	}
	public String getStfId() {
		return stfId;
	}
	public void setStfId(String stfId) {
		this.stfId = stfId;
	}
	public String getStfName() {
		return stfName;
	}
	public void setStfName(String stfName) {
		this.stfName = stfName;
	}
	public String getBasUsersLevel() {
		return basUsersLevel;
	}
	public void setBasUsersLevel(String basUsersLevel) {
		this.basUsersLevel = basUsersLevel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public String getSystemTypeName() {
		return systemTypeName;
	}
	public void setSystemTypeName(String systemTypeName) {
		this.systemTypeName = systemTypeName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStfZwgz() {
		return stfZwgz;
	}
	public void setStfZwgz(String stfZwgz) {
		this.stfZwgz = stfZwgz;
	}
	public String getStfPhone() {
		return stfPhone;
	}
	public void setStfPhone(String stfPhone) {
		this.stfPhone = stfPhone;
	}
	public String getStfJsdj() {
		return stfJsdj;
	}
	public void setStfJsdj(String stfJsdj) {
		this.stfJsdj = stfJsdj;
	}
	public String getBasUsersLevelName() {
		return basUsersLevelName;
	}
	public void setBasUsersLevelName(String basUsersLevelName) {
		this.basUsersLevelName = basUsersLevelName;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getEnterpriseJm() {
		return enterpriseJm;
	}
	public void setEnterpriseJm(String enterpriseJm) {
		this.enterpriseJm = enterpriseJm;
	}
	public String getEnterprisePerson() {
		return enterprisePerson;
	}
	public void setEnterprisePerson(String enterprisePerson) {
		this.enterprisePerson = enterprisePerson;
	}
	public Boolean getIsTrue() {
		return isTrue;
	}
	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
	public String getRemoveLevel() {
		return removeLevel;
	}
	public void setRemoveLevel(String removeLevel) {
		this.removeLevel = removeLevel;
	}
	
}