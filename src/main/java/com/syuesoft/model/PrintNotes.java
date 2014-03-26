package com.syuesoft.model;


/**
 * 打印字段
 * @author HeXin
 *
 */
public class PrintNotes extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fieldId;
	private PrintGroup printgroup;
	private String fieldLable;
	private String fieldName;
	private Integer fieldSort;
	private String remark;

	public PrintNotes() {
	}

	public Integer getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public PrintGroup getPrintgroup() {
		return this.printgroup;
	}

	public void setPrintgroup(PrintGroup printgroup) {
		this.printgroup = printgroup;
	}
    
	public String getFieldLable() {
		return fieldLable;
	}

	public void setFieldLable(String fieldLable) {
		this.fieldLable = fieldLable;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getFieldSort() {
		return this.fieldSort;
	}

	public void setFieldSort(Integer fieldSort) {
		this.fieldSort = fieldSort;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}