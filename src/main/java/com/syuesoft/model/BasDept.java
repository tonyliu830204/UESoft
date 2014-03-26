package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * BasDept entity. @author MyEclipse Persistence Tools
 */

public class BasDept extends BaseBean{

	// Fields

	private Short deptId;								//部门编号
	private Integer enterpriseId;						//企业信息序号
	private String deptName;							//部门名称
	private Short deptNum;								
	private String deptDesc;							//备注
	private String memo;
	private String deptRemarks;
	private Set basStuffs = new HashSet(0);				//员工集合
	// Constructors

	/** default constructor */
	public BasDept() {
	}

	/** minimal constructor */
	public BasDept(Short deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}

	/** full constructor */
	public BasDept(Short deptId, String deptName, Short deptNum,
			String deptDesc, String deptRemarks, Set basStuffs ,String memo) {
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptNum = deptNum;
		this.deptDesc = deptDesc;
		this.deptRemarks = deptRemarks;
		this.basStuffs = basStuffs;
		this.memo = memo;
	}

	// Property accessors

	
	public Short getDeptId() {
		return this.deptId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setDeptId(Short deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Short getDeptNum() {
		return this.deptNum;
	}

	public void setDeptNum(Short deptNum) {
		this.deptNum = deptNum;
	}

	public String getDeptDesc() {
		return this.deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getDeptRemarks() {
		return this.deptRemarks;
	}

	public void setDeptRemarks(String deptRemarks) {
		this.deptRemarks = deptRemarks;
	}

	public Set getBasStuffs() {
		return this.basStuffs;
	}

	public void setBasStuffs(Set basStuffs) {
		this.basStuffs = basStuffs;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


}