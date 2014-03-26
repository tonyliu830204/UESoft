package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

public class FrtResevationVo extends BaseBeanVo
{

    private Integer partsIndex;

    private String partsId;

    private Integer resvpartsCnt;

    private Double resvpartsPrice;

    private Double resvpartsCost;

    private String partsName;

    private Integer resvIndex;

    private String repitemId;

    private String repitemName;

    private Double repitemPrice;

    private Short stfId;

    private String stfName;

    private String rtrId;

    private String rtrServices;

    private String rtrSatisfaction;

    private String rtrReportTime;

    private String rtrIdea;

    private String rtrReplyTime;

    private String rtrIsCome;

    private String rtrReason;

    private String rtrInTime;

    private String rtrOutTime;

    private String rtrCsr;

    private String rtrReturnVisitTime;

    private String rtrRemarke;

    private String cbrdId;

    private String resvId;

    private String carLicense;

    private String carId;

    private String carMotorId;

    private String customId;

    private String customName;

    private String resvEnterTime;

    private Short reptId;

    private String reptName;

    private Short repwkId;

    private String repwkName;

    private String resvStatus;

    private Integer resvDistance;

    private String resvRealTime;

    private String resvFixpel;

    private String resvFixtel;

    private String resvFixphone;

    private Short repgrpId;

    private String repgrpName;

    private String resvType;

    private String resvRemark;

    private String resevationRepPer;

    private String carRealationSex;

    private String resvTypeName;

    private String resvStatusName;

    private String resvEnterTimeBegin;

    private String resvEnterTimeEnd;

    private String resvRealTimeBegin;

    private String resvRealTimeEnd;

    private Short stateId;

    private String code;

    private String name;

    private String state;

    private String rpId;

    private String carVin;

    private String q;

    private String vehicle;

    private String parts;

    private String items;

    private Boolean vehicleFlag;
    
    private Boolean flag;

    private String id;
    
    private int printTempletId;

    private int page;

    private int rows;

    private String order;

    private String sort;

    private String key;
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public FrtResevationVo()
    {

    }

    public FrtResevationVo(Short stfId, String stfName, String carLicense,
            String rtrId, String rtrServices, String rtrSatisfaction,
            String rtrReportTime, String rtrIdea, String rtrReplyTime,
            String rtrIsCome, String rtrReason, String rtrInTime,
            String rtrOutTime, String rtrCsr, String rtrReturnVisitTime,
            String rtrRemarke, String resvId, String carId, String carMotorId,
            String customId, String customName, String resvEnterTime,
            Short reptId, String reptName, Short repwkId, String repwkName,
            String resvStatus, Integer resvDistance, String resvRealTime,
            String resvFixpel, String resvFixtel, String resvFixphone,
            Short repgrpId, String repgrpName, String resvType,
            String resvRemark)
    {
        this.stfId = stfId;
        this.stfName = stfName;
        this.carLicense = carLicense;
        this.rtrId = rtrId;
        this.rtrServices = rtrServices;
        this.rtrSatisfaction = rtrSatisfaction;
        this.rtrReportTime = rtrReportTime;
        this.rtrIdea = rtrIdea;
        this.rtrReplyTime = rtrReplyTime;
        this.rtrIsCome = rtrIsCome;
        this.rtrReason = rtrReason;
        this.rtrInTime = rtrInTime;
        this.rtrOutTime = rtrOutTime;
        this.rtrCsr = rtrCsr;
        this.rtrReturnVisitTime = rtrReturnVisitTime;
        this.rtrRemarke = rtrRemarke;
        this.resvId = resvId;
        this.carId = carId;
        this.carMotorId = carMotorId;
        this.customId = customId;
        this.customName = customName;
        this.resvEnterTime = resvEnterTime;
        this.reptId = reptId;
        this.reptName = reptName;
        this.repwkId = repwkId;
        this.repwkName = repwkName;
        this.resvStatus = resvStatus;
        this.resvDistance = resvDistance;
        this.resvRealTime = resvRealTime;
        this.resvFixpel = resvFixpel;
        this.resvFixtel = resvFixtel;
        this.resvFixphone = resvFixphone;
        this.repgrpId = repgrpId;
        this.repgrpName = repgrpName;
        this.resvType = resvType;
        this.resvRemark = resvRemark;
    }

    public Integer getPartsIndex()
    {
        return partsIndex;
    }

    public void setPartsIndex(Integer partsIndex)
    {
        this.partsIndex = partsIndex;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }

    public Integer getResvpartsCnt()
    {
        return resvpartsCnt;
    }

    public void setResvpartsCnt(Integer resvpartsCnt)
    {
        this.resvpartsCnt = resvpartsCnt;
    }

    public Double getResvpartsPrice()
    {
        return resvpartsPrice;
    }

    public void setResvpartsPrice(Double resvpartsPrice)
    {
        this.resvpartsPrice = resvpartsPrice;
    }

    public Double getResvpartsCost()
    {
        return resvpartsCost;
    }

    public void setResvpartsCost(Double resvpartsCost)
    {
        this.resvpartsCost = resvpartsCost;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public Integer getResvIndex()
    {
        return resvIndex;
    }

    public void setResvIndex(Integer resvIndex)
    {
        this.resvIndex = resvIndex;
    }

    public String getRepitemId()
    {
        return repitemId;
    }

    public void setRepitemId(String repitemId)
    {
        this.repitemId = repitemId;
    }

    public String getRepitemName()
    {
        return repitemName;
    }

    public void setRepitemName(String repitemName)
    {
        this.repitemName = repitemName;
    }

    public Double getRepitemPrice()
    {
        return repitemPrice;
    }

    public void setRepitemPrice(Double repitemPrice)
    {
        this.repitemPrice = repitemPrice;
    }

    public Short getStfId()
    {
        return stfId;
    }

    public void setStfId(Short stfId)
    {
        this.stfId = stfId;
    }

    public String getRtrId()
    {
        return rtrId;
    }

    public void setRtrId(String rtrId)
    {
        this.rtrId = rtrId;
    }

    public String getRtrServices()
    {
        return rtrServices;
    }

    public void setRtrServices(String rtrServices)
    {
        this.rtrServices = rtrServices;
    }

    public String getRtrSatisfaction()
    {
        return rtrSatisfaction;
    }

    public void setRtrSatisfaction(String rtrSatisfaction)
    {
        this.rtrSatisfaction = rtrSatisfaction;
    }

    public String getRtrIdea()
    {
        return rtrIdea;
    }

    public void setRtrIdea(String rtrIdea)
    {
        this.rtrIdea = rtrIdea;
    }

    public String getRtrIsCome()
    {
        return rtrIsCome;
    }

    public void setRtrIsCome(String rtrIsCome)
    {
        this.rtrIsCome = rtrIsCome;
    }

    public String getRtrReason()
    {
        return rtrReason;
    }

    public void setRtrReason(String rtrReason)
    {
        this.rtrReason = rtrReason;
    }

    public String getRtrReportTime()
    {
        return rtrReportTime;
    }

    public void setRtrReportTime(String rtrReportTime)
    {
        this.rtrReportTime = rtrReportTime;
    }

    public String getRtrReplyTime()
    {
        return rtrReplyTime;
    }

    public void setRtrReplyTime(String rtrReplyTime)
    {
        this.rtrReplyTime = rtrReplyTime;
    }

    public String getRtrInTime()
    {
        return rtrInTime;
    }

    public void setRtrInTime(String rtrInTime)
    {
        this.rtrInTime = rtrInTime;
    }

    public String getRtrOutTime()
    {
        return rtrOutTime;
    }

    public void setRtrOutTime(String rtrOutTime)
    {
        this.rtrOutTime = rtrOutTime;
    }

    public String getRtrReturnVisitTime()
    {
        return rtrReturnVisitTime;
    }

    public void setRtrReturnVisitTime(String rtrReturnVisitTime)
    {
        this.rtrReturnVisitTime = rtrReturnVisitTime;
    }

    public String getRtrCsr()
    {
        return rtrCsr;
    }

    public void setRtrCsr(String rtrCsr)
    {
        this.rtrCsr = rtrCsr;
    }

    public String getResvId()
    {
        return resvId;
    }

    public void setResvId(String resvId)
    {
        this.resvId = resvId;
    }

    public String getCarId()
    {
        return carId;
    }

    public void setCarId(String carId)
    {
        this.carId = carId;
    }

    public String getCustomId()
    {
        return customId;
    }

    public void setCustomId(String customId)
    {
        this.customId = customId;
    }

    public Short getReptId()
    {
        return reptId;
    }

    public void setReptId(Short reptId)
    {
        this.reptId = reptId;
    }

    public Short getRepwkId()
    {
        return repwkId;
    }

    public void setRepwkId(Short repwkId)
    {
        this.repwkId = repwkId;
    }

    public String getResvStatus()
    {
        return resvStatus;
    }

    public void setResvStatus(String resvStatus)
    {
        this.resvStatus = resvStatus;
    }

    public Integer getResvDistance()
    {
        return resvDistance;
    }

    public void setResvDistance(Integer resvDistance)
    {
        this.resvDistance = resvDistance;
    }

    public String getResvEnterTime()
    {
        return resvEnterTime;
    }

    public void setResvEnterTime(String resvEnterTime)
    {
        this.resvEnterTime = resvEnterTime;
    }

    public String getResvRealTime()
    {
        return resvRealTime;
    }

    public void setResvRealTime(String resvRealTime)
    {
        this.resvRealTime = resvRealTime;
    }

    public String getResvFixpel()
    {
        return resvFixpel;
    }

    public void setResvFixpel(String resvFixpel)
    {
        this.resvFixpel = resvFixpel;
    }

    public String getResvFixtel()
    {
        return resvFixtel;
    }

    public void setResvFixtel(String resvFixtel)
    {
        this.resvFixtel = resvFixtel;
    }

    public String getResvFixphone()
    {
        return resvFixphone;
    }

    public void setResvFixphone(String resvFixphone)
    {
        this.resvFixphone = resvFixphone;
    }

    public Short getRepgrpId()
    {
        return repgrpId;
    }

    public void setRepgrpId(Short repgrpId)
    {
        this.repgrpId = repgrpId;
    }

    public String getResvType()
    {
        return resvType;
    }

    public void setResvType(String resvType)
    {
        this.resvType = resvType;
    }

    public String getResvRemark()
    {
        return resvRemark;
    }

    public void setResvRemark(String resvRemark)
    {
        this.resvRemark = resvRemark;
    }

    public String getRtrRemarke()
    {
        return rtrRemarke;
    }

    public void setRtrRemarke(String rtrRemarke)
    {
        this.rtrRemarke = rtrRemarke;
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

    public String getOrder()
    {
        return order;
    }

    public void setOrder(String order)
    {
        this.order = order;
    }

    public String getSort()
    {
        return sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
    }

    public String getRepwkName()
    {
        return repwkName;
    }

    public void setRepwkName(String repwkName)
    {
        this.repwkName = repwkName;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public String getRepgrpName()
    {
        return repgrpName;
    }

    public void setRepgrpName(String repgrpName)
    {
        this.repgrpName = repgrpName;
    }

    public String getResvEnterTimeBegin()
    {
        return resvEnterTimeBegin;
    }

    public void setResvEnterTimeBegin(String resvEnterTimeBegin)
    {
        this.resvEnterTimeBegin = resvEnterTimeBegin;
    }

    public String getResvEnterTimeEnd()
    {
        return resvEnterTimeEnd;
    }

    public void setResvEnterTimeEnd(String resvEnterTimeEnd)
    {
        this.resvEnterTimeEnd = resvEnterTimeEnd;
    }

    public String getCarMotorId()
    {
        return carMotorId;
    }

    public void setCarMotorId(String carMotorId)
    {
        this.carMotorId = carMotorId;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public Short getStateId()
    {
        return stateId;
    }

    public void setStateId(Short stateId)
    {
        this.stateId = stateId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(String vehicle)
    {
        this.vehicle = vehicle;
    }

    public String getParts()
    {
        return parts;
    }

    public void setParts(String parts)
    {
        this.parts = parts;
    }

    public String getItems()
    {
        return items;
    }

    public void setItems(String items)
    {
        this.items = items;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    /**
     * @return the key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    public String getCbrdId()
    {
        return cbrdId;
    }

    public void setCbrdId(String cbrdId)
    {
        this.cbrdId = cbrdId;
    }

    public String getResvStatusName()
    {
        return resvStatusName;
    }

    public void setResvStatusName(String resvStatusName)
    {
        this.resvStatusName = resvStatusName;
    }

    public String getResvTypeName()
    {
        return resvTypeName;
    }

    public void setResvTypeName(String resvTypeName)
    {
        this.resvTypeName = resvTypeName;
    }

    public String getResvRealTimeBegin()
    {
        return resvRealTimeBegin;
    }

    public void setResvRealTimeBegin(String resvRealTimeBegin)
    {
        this.resvRealTimeBegin = resvRealTimeBegin;
    }

    public String getResvRealTimeEnd()
    {
        return resvRealTimeEnd;
    }

    public void setResvRealTimeEnd(String resvRealTimeEnd)
    {
        this.resvRealTimeEnd = resvRealTimeEnd;
    }

    public String getRpId()
    {
        return rpId;
    }

    public void setRpId(String rpId)
    {
        this.rpId = rpId;
    }

    public String getResevationRepPer()
    {
        return resevationRepPer;
    }

    public void setResevationRepPer(String resevationRepPer)
    {
        this.resevationRepPer = resevationRepPer;
    }

    public String getCarRealationSex()
    {
        return carRealationSex;
    }

    public void setCarRealationSex(String carRealationSex)
    {
        this.carRealationSex = carRealationSex;
    }

    public Boolean getVehicleFlag()
    {
        return vehicleFlag;
    }

    public void setVehicleFlag(Boolean vehicleFlag)
    {
        this.vehicleFlag = vehicleFlag;
    }

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public int getPrintTempletId() {
		return printTempletId;
	}

	public void setPrintTempletId(int printTempletId) {
		this.printTempletId = printTempletId;
	}

}
