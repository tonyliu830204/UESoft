package com.syuesoft.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 打印组
 * @author HeXin
 *
 */
public class PrintGroup extends BaseBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer groupId;
	private String groupName;
	private String tableName;
	private Integer groupSort;
	private String checked;
	private String systemType;
	private String remark;
	private Set<PrintNotes> printnoteses = new HashSet<PrintNotes>();

	public PrintGroup() {
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getGroupSort() {
		return this.groupSort;
	}

	public void setGroupSort(Integer groupSort) {
		this.groupSort = groupSort;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public Set<PrintNotes> getPrintnoteses() {
		return this.printnoteses;
	}

	public void setPrintnoteses(Set<PrintNotes> printnoteses) {
		this.printnoteses = printnoteses;
	}

    public String getSystemType()
    {
        return systemType;
    }

    public void setSystemType(String systemType)
    {
        this.systemType = systemType;
    }
}