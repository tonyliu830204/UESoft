package com.syuesoft.vipmanagement.vo;

public class SmsRecordManagementVo {
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private String info_detailId;
	private String now_Send_Date;
	private String now_Send_Date1;
	private String car_License;
	private String car_Vin;
	private String car_Id;
	private String custom_Tel1; //短信接收号码
	private String custom_Name; //联系人
	private String cst_Name;    //客户分类
	private String send_Content;// 发送内容
	private String smsState;    //是否发送成功
	private String info_Id;     //是否发送编号
	
	public String getNow_Send_Date1()
    {
        return now_Send_Date1;
    }
    public void setNow_Send_Date1(String nowSendDate1)
    {
        now_Send_Date1 = nowSendDate1;
    }
    public String getInfo_Id() {
		return info_Id;
	}
	public void setInfo_Id(String infoId) {
		info_Id = infoId;
	}
	public String getNow_Send_Date() {
		return now_Send_Date;
	}
	public void setNow_Send_Date(String nowSendDate) {
		now_Send_Date = nowSendDate;
	}
	public String getCar_License() {
		return car_License;
	}
	public void setCar_License(String carLicense) {
		car_License = carLicense;
	}
	public String getCar_Vin() {
		return car_Vin;
	}
	public void setCar_Vin(String carVin) {
		car_Vin = carVin;
	}
	public String getCustom_Tel1() {
		return custom_Tel1;
	}
	public void setCustom_Tel1(String customTel1) {
		custom_Tel1 = customTel1;
	}
	public String getCustom_Name() {
		return custom_Name;
	}
	public void setCustom_Name(String customName) {
		custom_Name = customName;
	}
	public String getCst_Name() {
		return cst_Name;
	}
	public void setCst_Name(String cstName) {
		cst_Name = cstName;
	}
	public String getSend_Content() {
		return send_Content;
	}
	public void setSend_Content(String sendContent) {
		send_Content = sendContent;
	}
	public String getSmsState()
    {
        return smsState;
    }
    public void setSmsState(String smsState)
    {
        this.smsState = smsState;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
    public String getCar_Id()
    {
        return car_Id;
    }
    public void setCar_Id(String carId)
    {
        car_Id = carId;
    }
    public String getInfo_detailId()
    {
        return info_detailId;
    }
    public void setInfo_detailId(String infoDetailId)
    {
        info_detailId = infoDetailId;
    }
}