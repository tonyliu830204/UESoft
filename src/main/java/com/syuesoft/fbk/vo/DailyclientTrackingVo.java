package com.syuesoft.fbk.vo;

import com.syuesoft.model.FrtCar;

public class DailyclientTrackingVo
{
	private String carId;// 车辆id
	private String adviceId;// 维修建议id
	private String carLicense; // 车辆牌照
	private String customName; // 客户名称
	private String customTel; //客户手机
	private String  adviceContext;//建议内容
	private String  adviceTime;//建议日期
	private String  writePerson;//建议填写人
	private String  writePersonName;//建议填写人
	private String procesState;//处理进度
	private String procesStateName;//处理进度
	private String adviceTimeEnd;//处理时间
	private String procesProson;//处理人
	private String procesProsonName;//处理人
    private String drId; // 日常跟踪提醒id
    private String txInfomation; // 提醒信息 (如此客户喜欢做一些比较高档的保养或其他需求的)

    private String memoInfomation; // 备注信息

    private String txDj; // 提醒等级 (重要提醒 还是一般提醒)

    private String clStatus; // 处理情况 (待定)

    private int page;

    private int rows;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getRows()
    {
        return rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public String getDrId()
    {
        return drId;
    }

    public void setDrId(String drId)
    {
        this.drId = drId;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getTxInfomation()
    {
        return txInfomation;
    }

    public void setTxInfomation(String txInfomation)
    {
        this.txInfomation = txInfomation;
    }

    public String getMemoInfomation()
    {
        return memoInfomation;
    }

    public void setMemoInfomation(String memoInfomation)
    {
        this.memoInfomation = memoInfomation;
    }

    public String getTxDj()
    {
        return txDj;
    }

    public void setTxDj(String txDj)
    {
        this.txDj = txDj;
    }

    public String getClStatus()
    {
        return clStatus;
    }

    public void setClStatus(String clStatus)
    {
        this.clStatus = clStatus;
    }

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getAdviceId() {
		return adviceId;
	}

	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
	}

	public String getAdviceContext() {
		return adviceContext;
	}

	public void setAdviceContext(String adviceContext) {
		this.adviceContext = adviceContext;
	}

	public String getAdviceTime() {
		return adviceTime;
	}

	public void setAdviceTime(String adviceTime) {
		this.adviceTime = adviceTime;
	}

	public String getWritePerson() {
		return writePerson;
	}

	public void setWritePerson(String writePerson) {
		this.writePerson = writePerson;
	}

	public String getWritePersonName() {
		return writePersonName;
	}

	public void setWritePersonName(String writePersonName) {
		this.writePersonName = writePersonName;
	}

	public String getProcesState() {
		return procesState;
	}

	public void setProcesState(String procesState) {
		this.procesState = procesState;
	}

	public String getAdviceTimeEnd() {
		return adviceTimeEnd;
	}

	public void setAdviceTimeEnd(String adviceTimeEnd) {
		this.adviceTimeEnd = adviceTimeEnd;
	}

	public String getProcesProson() {
		return procesProson;
	}

	public void setProcesProson(String procesProson) {
		this.procesProson = procesProson;
	}

	public String getProcesProsonName() {
		return procesProsonName;
	}

	public void setProcesProsonName(String procesProsonName) {
		this.procesProsonName = procesProsonName;
	}

	public String getProcesStateName() {
		return procesStateName;
	}

	public void setProcesStateName(String procesStateName) {
		this.procesStateName = procesStateName;
	}

	public String getCustomTel() {
		return customTel;
	}

	public void setCustomTel(String customTel) {
		this.customTel = customTel;
	}
    

}
