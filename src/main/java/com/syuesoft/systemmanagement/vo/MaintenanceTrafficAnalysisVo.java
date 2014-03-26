package com.syuesoft.systemmanagement.vo;

public class MaintenanceTrafficAnalysisVo {

	private String serviceDateBegin;
	private String serviceDateEnd;
	private String cbrdId;
	private String ctypeId;
	private String receivePerson;
	
	private String state;                                                     //treegrid打开还是关闭
	private String iconCls; 
	private String _parentId;
	
	private String loadData;
	private Boolean is3D;
	private String enrolDate;
	private String selectedField;
	private Boolean flag;
	private String stfId;
	
	private int page;
	private int rows;
	private String order;
	private String sort;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
	
	public MaintenanceTrafficAnalysisVo() {
		// TODO Auto-generated constructor stub
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
	public String getServiceDateBegin() {
		return serviceDateBegin;
	}
	public void setServiceDateBegin(String serviceDateBegin) {
		this.serviceDateBegin = serviceDateBegin;
	}
	public String getServiceDateEnd() {
		return serviceDateEnd;
	}
	public void setServiceDateEnd(String serviceDateEnd) {
		this.serviceDateEnd = serviceDateEnd;
	}
	public String getCbrdId() {
		return cbrdId;
	}
	public void setCbrdId(String cbrdId) {
		this.cbrdId = cbrdId;
	}
	public String getCtypeId() {
		return ctypeId;
	}
	public void setCtypeId(String ctypeId) {
		this.ctypeId = ctypeId;
	}
	public String getReceivePerson() {
		return receivePerson;
	}
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	public String getLoadData() {
		return loadData;
	}
	public void setLoadData(String loadData) {
		this.loadData = loadData;
	}
	public Boolean getIs3D() {
		return is3D;
	}
	public void setIs3D(Boolean is3d) {
		is3D = is3d;
	}
	public String getEnrolDate() {
		return enrolDate;
	}
	public void setEnrolDate(String enrolDate) {
		this.enrolDate = enrolDate;
	}
	public String getSelectedField() {
		return selectedField;
	}
	public void setSelectedField(String selectedField) {
		this.selectedField = selectedField;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getStfId() {
		return stfId;
	}
	public void setStfId(String stfId) {
		this.stfId = stfId;
	}
	
}
