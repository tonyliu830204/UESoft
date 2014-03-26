package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;

@SuppressWarnings("serial")
public class FrtReceptionVo extends BaseBeanVo
{
    private String q;

    private String receptionId;

    private String receLicense;

    private String receVin;

    private String resvId;

    private Integer receptionDistance;

    private String receptionMaintFlg;

    private String receptionStatus;
    
    private Short repgrpId;
    private String rcptBranch;
    
    private String startPreclrTime;
    
    private String endPreclrTime;
    private String preclrTime;
    
    private String preclrTimeBegin;
    private String preclrTimeEnd;

    private Short repwkId;

    private String receptionEndTime;
    
    private String customTel1;

    private Short receptionTechnician;

    private Short receptionInsurPer;

    private String receptionRepPer;

    private String propRepPer;

    private String propPhone;

    private String propTel;

    private String valuables;

    private String fuelSituation;

    private String oldPieces;

    private Short finComId;

    private String finStatus;

    private String expDelCarTime;

    private String problemDesc;

    private String receptionRemark;

    private String interDate;

    private Short receptor;

    private Double managementFee;

    private Short finTag;

    private Short finAllTag;

    private String receptionMaintFlgName;

    private String finStatusName;

    private String receptionStatusName;

    private String valuablesName;

    private String fuelSituationName;

    private String oldPiecesName;

    private String partsId;

    private String partsName;

    private Integer partsNum;

    private Short punitId;

    private Double partsRepairPrice;

    private Double partsAmount;

    private String partsRemark;

    private Short storeId;

    private String planStartTime;

    private String planComplTime;

    private String repitemId;

    private String repitemName;

    private Double repitemAmount;

    private Double rcptitemInnerTime;

    private Double stfId;

    private Double rcptitemTime;

    private Double charge;

    private Double claimsType;

    private String stfName;

    private Short costId;

    private String costItem;

    private Double costAmount;

    private String costExplain;

    private String interDateBegin;

    private String interDateEnd;

    private String receptionEndTimeBegin;

    private String receptionEndTimeEnd;

    private String expDelCarTimeBegin;

    private String expDelCarTimeEnd;

    private Integer adviceId;

    private String adviceContext;

    private String adviceTime;

    private String adviceTimeEnd;

    private Integer writePerson;

    private String procesContext;

    private Integer procesState;

    private Integer procesProson;

    private Short adviceClass;

    private String adviceClassName;

    private String writePersonName;

    private String procesStateName;

    private String procesProsonName;

    private Short stateId;

    private String code;

    private String name;

    private String customId;

    private String customName;

    private String carId;

    private String carLicense;

    private String carVin;

    private String carMotorId;

    private Short reptId;

    private String reptName;

    private String repgrpName;

    private String repwkName;

    private String receptionTechnicianName;

    private String receptionInsurPerName;

    private String receptorName;

    private String relcampName;

    private String rpId;

    private String rpName;
    
	private String receptionFactTime;
	
	private Long differenceTime; 
	private Boolean flag;
    
	private String ids;

    private String parts;

    private String items;

    private String others;

    private String vehicle;
    
    private Integer noComeInCompanyDays;

    private String id;
    
    private String preclrWktimeAmount;
    
    private String preMprMatAmount;
    
    private String preOtherAmount;
    
    private String preclrSumAmount;
    
    private String PreRealAmount;
    
    private String state; // treegrid打开还是关闭

    private String iconCls;

    private String _parentId;
    private Boolean param;
    
    private String preclrId;
    private Integer enterpriseId;
    private Boolean flage;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    public String getPreclrTimeBegin() {
		return preclrTimeBegin;
	}

	public void setPreclrTimeBegin(String preclrTimeBegin) {
		this.preclrTimeBegin = preclrTimeBegin;
	}

	public String getPreclrTimeEnd() {
		return preclrTimeEnd;
	}

	public void setPreclrTimeEnd(String preclrTimeEnd) {
		this.preclrTimeEnd = preclrTimeEnd;
	}

	public String getCustomTel1() {
		return customTel1;
	}

	public void setCustomTel1(String customTel1) {
		this.customTel1 = customTel1;
	}

	public Boolean getParam() {
		return param;
	}

	public void setParam(Boolean param) {
		this.param = param;
	}

	public String getRcptBranch() {
		return rcptBranch;
	}

	public void setRcptBranch(String rcptBranch) {
		this.rcptBranch = rcptBranch;
	}

	public FrtReceptionVo(){
    	
    }

    public String getPreclrWktimeAmount() {
		return preclrWktimeAmount;
	}

	public void setPreclrWktimeAmount(String preclrWktimeAmount) {
		this.preclrWktimeAmount = preclrWktimeAmount;
	}

	public String getPreMprMatAmount() {
		return preMprMatAmount;
	}

	public void setPreMprMatAmount(String preMprMatAmount) {
		this.preMprMatAmount = preMprMatAmount;
	}

	public String getPreOtherAmount() {
		return preOtherAmount;
	}

	public void setPreOtherAmount(String preOtherAmount) {
		this.preOtherAmount = preOtherAmount;
	}

	public String getPreclrSumAmount() {
		return preclrSumAmount;
	}

	public void setPreclrSumAmount(String preclrSumAmount) {
		this.preclrSumAmount = preclrSumAmount;
	}

	public String getPreRealAmount() {
		return PreRealAmount;
	}

	public void setPreRealAmount(String preRealAmount) {
		PreRealAmount = preRealAmount;
	}

	

	public String getPreclrTime() {
		return preclrTime;
	}

	public void setPreclrTime(String preclrTime) {
		this.preclrTime = preclrTime;
	}

	public String getStartPreclrTime() {
		return startPreclrTime;
	}

	public void setStartPreclrTime(String startPreclrTime) {
		this.startPreclrTime = startPreclrTime;
	}

	public String getEndPreclrTime() {
		return endPreclrTime;
	}

	public void setEndPreclrTime(String endPreclrTime) {
		this.endPreclrTime = endPreclrTime;
	}

	public FrtReceptionVo(String carId)
    {
        this.carId = carId;
    }

    public Short getStateId()
    {
        return stateId;
    }

    public void setStateId(Short stateId)
    {
        this.stateId = stateId;
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

    public Short getCostId()
    {
        return costId;
    }

    public void setCostId(Short costId)
    {
        this.costId = costId;
    }

    public String getCostItem()
    {
        return costItem;
    }

    public void setCostItem(String costItem)
    {
        this.costItem = costItem;
    }

    public Double getCostAmount()
    {
        return costAmount;
    }

    public void setCostAmount(Double costAmount)
    {
        this.costAmount = costAmount;
    }

    public String getCostExplain()
    {
        return costExplain;
    }

    public void setCostExplain(String costExplain)
    {
        this.costExplain = costExplain;
    }

    public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public Integer getPartsNum()
    {
        return partsNum;
    }

    public void setPartsNum(Integer partsNum)
    {
        this.partsNum = partsNum;
    }

    public Short getPunitId()
    {
        return punitId;
    }

    public void setPunitId(Short punitId)
    {
        this.punitId = punitId;
    }

    public Double getPartsRepairPrice()
    {
        return partsRepairPrice;
    }

    public void setPartsRepairPrice(Double partsRepairPrice)
    {
        this.partsRepairPrice = partsRepairPrice;
    }

    public Double getPartsAmount()
    {
        return partsAmount;
    }

    public void setPartsAmount(Double partsAmount)
    {
        this.partsAmount = partsAmount;
    }

    public String getPartsRemark()
    {
        return partsRemark;
    }

    public void setPartsRemark(String partsRemark)
    {
        this.partsRemark = partsRemark;
    }

    public Short getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Short storeId)
    {
        this.storeId = storeId;
    }

    public String getPartsId()
    {
        return partsId;
    }

    public void setPartsId(String partsId)
    {
        this.partsId = partsId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getQ()
    {
        return q;
    }

    public void setQ(String q)
    {
        this.q = q;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public String getReceLicense()
    {
        return receLicense;
    }

    public void setReceLicense(String receLicense)
    {
        this.receLicense = receLicense;
    }

    public String getReceVin()
    {
        return receVin;
    }

    public void setReceVin(String receVin)
    {
        this.receVin = receVin;
    }

    public String getResvId()
    {
        return resvId;
    }

    public void setResvId(String resvId)
    {
        this.resvId = resvId;
    }

    public Integer getReceptionDistance()
    {
        return receptionDistance;
    }

    public void setReceptionDistance(Integer receptionDistance)
    {
        this.receptionDistance = receptionDistance;
    }

    public String getReceptionMaintFlg()
    {
        return receptionMaintFlg;
    }

    public void setReceptionMaintFlg(String receptionMaintFlg)
    {
        this.receptionMaintFlg = receptionMaintFlg;
    }

    public String getReceptionStatus()
    {
        return receptionStatus;
    }

    public void setReceptionStatus(String receptionStatus)
    {
        this.receptionStatus = receptionStatus;
    }

    public Short getRepgrpId()
    {
        return repgrpId;
    }

    public void setRepgrpId(Short repgrpId)
    {
        this.repgrpId = repgrpId;
    }

    public void setRepgrpId(String repgrpId)
    {
        if (repgrpId != null && repgrpId.length() > 0)
            this.repgrpId = Short.parseShort(repgrpId);
    }

    public Short getRepwkId()
    {
        return repwkId;
    }

    public void setRepwkId(Short repwkId)
    {
        this.repwkId = repwkId;
    }

    public void setRepwkId(String repwkId)
    {
        if (repwkId != null && (!"".equals(repwkId)))
            this.repwkId = Short.parseShort(repwkId);
    }

    public Short getReceptionTechnician()
    {
        return receptionTechnician;
    }

    public void setReceptionTechnician(Short receptionTechnician)
    {
        this.receptionTechnician = receptionTechnician;
    }

    public void setReceptionTechnician(String receptionTechnician)
    {
        if (receptionTechnician != null && (!"".equals(receptionTechnician)))
            this.receptionTechnician = Short.parseShort(receptionTechnician);
    }

    public Short getReceptionInsurPer()
    {
        return receptionInsurPer;
    }

    public void setReceptionInsurPer(Short receptionInsurPer)
    {
        this.receptionInsurPer = receptionInsurPer;
    }

    public void setReceptionInsurPer(String receptionInsurPer)
    {
        if (receptionInsurPer != null && (!"".equals(receptionInsurPer)))
            this.receptionInsurPer = Short.parseShort(receptionInsurPer);
    }

    public String getReceptionRepPer()
    {
        return receptionRepPer;
    }

    public void setReceptionRepPer(String receptionRepPer)
    {
        this.receptionRepPer = receptionRepPer;
    }

    public String getPropRepPer()
    {
        return propRepPer;
    }

    public void setPropRepPer(String propRepPer)
    {
        this.propRepPer = propRepPer;
    }

    public String getPropPhone()
    {
        return propPhone;
    }

    public void setPropPhone(String propPhone)
    {
        this.propPhone = propPhone;
    }

    public String getPropTel()
    {
        return propTel;
    }

    public void setPropTel(String propTel)
    {
        this.propTel = propTel;
    }

    public String getValuables()
    {
        return valuables;
    }

    public void setValuables(String valuables)
    {
        this.valuables = valuables;
    }

    public String getFuelSituation()
    {
        return fuelSituation;
    }

    public void setFuelSituation(String fuelSituation)
    {
        this.fuelSituation = fuelSituation;
    }

    public String getOldPieces()
    {
        return oldPieces;
    }

    public void setOldPieces(String oldPieces)
    {
        this.oldPieces = oldPieces;
    }

    public Short getFinComId()
    {
        return finComId;
    }

    public void setFinComId(Short finComId)
    {
        this.finComId = finComId;
    }

    public void setFinComId(String finComId)
    {
        if (finComId != null && (!"".equals(finComId)))
            this.finComId = Short.parseShort(finComId);
    }

    public String getFinStatus()
    {
        return finStatus;
    }

    public void setFinStatus(String finStatus)
    {
        this.finStatus = finStatus;
    }

    public String getProblemDesc()
    {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc)
    {
        this.problemDesc = problemDesc;
    }

    public String getReceptionRemark()
    {
        return receptionRemark;
    }

    public void setReceptionRemark(String receptionRemark)
    {
        this.receptionRemark = receptionRemark;
    }

    public Short getReceptor()
    {
        return receptor;
    }

    public void setReceptor(Short receptor)
    {
        this.receptor = receptor;
    }

    public void setReceptor(String receptor)
    {
        if (receptor != null && (!"".equals(receptor)))
            this.receptor = Short.parseShort(receptor);
    }

    public Double getManagementFee()
    {
        return managementFee;
    }

    public void setManagementFee(Double managementFee)
    {
        this.managementFee = managementFee;
    }

    public String getCustomId()
    {
        return customId;
    }

    public void setCustomId(String customId)
    {
        this.customId = customId;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getCarId()
    {
        return carId;
    }

    public void setCarId(String carId)
    {
        this.carId = carId;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getCarVin()
    {
        return carVin;
    }

    public void setCarVin(String carVin)
    {
        this.carVin = carVin;
    }

    public String getCarMotorId()
    {
        return carMotorId;
    }

    public void setCarMotorId(String carMotorId)
    {
        this.carMotorId = carMotorId;
    }

    public Short getReptId()
    {
        return reptId;
    }

    public void setReptId(Short reptId)
    {
        this.reptId = reptId;
    }

    public void setReptId(String reptId)
    {
        if (reptId != null && (!"".equals(reptId)))
            this.reptId = Short.parseShort(reptId);
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
    }

    public String getRepgrpName()
    {
        return repgrpName;
    }

    public void setRepgrpName(String repgrpName)
    {
        this.repgrpName = repgrpName;
    }

    public String getRepwkName()
    {
        return repwkName;
    }

    public void setRepwkName(String repwkName)
    {
        this.repwkName = repwkName;
    }

    public String getReceptionTechnicianName()
    {
        return receptionTechnicianName;
    }

    public void setReceptionTechnicianName(String receptionTechnicianName)
    {
        this.receptionTechnicianName = receptionTechnicianName;
    }

    public String getReceptorName()
    {
        return receptorName;
    }

    public void setReceptorName(String receptorName)
    {
        this.receptorName = receptorName;
    }

    public String getRelcampName()
    {
        return relcampName;
    }

    public void setRelcampName(String relcampName)
    {
        this.relcampName = relcampName;
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

    public String getOthers()
    {
        return others;
    }

    public void setOthers(String others)
    {
        this.others = others;
    }

    public String getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(String vehicle)
    {
        this.vehicle = vehicle;
    }

    public String getReceptionInsurPerName()
    {
        return receptionInsurPerName;
    }

    public void setReceptionInsurPerName(String receptionInsurPerName)
    {
        this.receptionInsurPerName = receptionInsurPerName;
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

    public Double getRepitemAmout()
    {
        return repitemAmount;
    }

    public void setRepitemAmout(Double repitemAmout)
    {
        this.repitemAmount = repitemAmout;
    }

    public Double getRcptitemInnerTime()
    {
        return rcptitemInnerTime;
    }

    public void setRcptitemInnerTime(Double rcptitemInnerTime)
    {
        this.rcptitemInnerTime = rcptitemInnerTime;
    }

    public Double getStfId()
    {
        return stfId;
    }

    public void setStfId(Double stfId)
    {
        this.stfId = stfId;
    }

    public Double getRcptitemTime()
    {
        return rcptitemTime;
    }

    public void setRcptitemTime(Double rcptitemTime)
    {
        this.rcptitemTime = rcptitemTime;
    }

    public Double getCharge()
    {
        return charge;
    }

    public void setCharge(Double charge)
    {
        this.charge = charge;
    }

    public Double getClaimsType()
    {
        return claimsType;
    }

    public void setClaimsType(Double claimsType)
    {
        this.claimsType = claimsType;
    }

    public String getReceptionEndTime()
    {
        return receptionEndTime;
    }

    public void setReceptionEndTime(String receptionEndTime)
    {
        this.receptionEndTime = receptionEndTime;
    }

    public String getExpDelCarTime()
    {
        return expDelCarTime;
    }

    public void setExpDelCarTime(String expDelCarTime)
    {
        this.expDelCarTime = expDelCarTime;
    }

    public String getInterDate()
    {
        return interDate;
    }

    public void setInterDate(String interDate)
    {
        this.interDate = interDate;
    }

    public Short getFinTag()
    {
        return finTag;
    }

    public void setFinTag(Short finTag)
    {
        this.finTag = finTag;
    }

    public Short getFinAllTag()
    {
        return finAllTag;
    }

    public void setFinAllTag(Short finAllTag)
    {
        this.finAllTag = finAllTag;
    }

    public String getReceptionMaintFlgName()
    {
        return receptionMaintFlgName;
    }

    public void setReceptionMaintFlgName(String receptionMaintFlgName)
    {
        this.receptionMaintFlgName = receptionMaintFlgName;
    }

    public String getFinStatusName()
    {
        return finStatusName;
    }

    public void setFinStatusName(String finStatusName)
    {
        this.finStatusName = finStatusName;
    }

    public String getReceptionStatusName()
    {
        return receptionStatusName;
    }

    public void setReceptionStatusName(String receptionStatusName)
    {
        this.receptionStatusName = receptionStatusName;
    }

    public String getPlanStartTime()
    {
        return planStartTime;
    }

    public void setPlanStartTime(String planStartTime)
    {
        this.planStartTime = planStartTime;
    }

    public String getPlanComplTime()
    {
        return planComplTime;
    }

    public void setPlanComplTime(String planComplTime)
    {
        this.planComplTime = planComplTime;
    }

    public Double getRepitemAmount()
    {
        return repitemAmount;
    }

    public void setRepitemAmount(Double repitemAmount)
    {
        this.repitemAmount = repitemAmount;
    }

    public String getInterDateBegin()
    {
        return interDateBegin;
    }

    public void setInterDateBegin(String interDateBegin)
    {
        this.interDateBegin = interDateBegin;
    }

    public String getInterDateEnd()
    {
        return interDateEnd;
    }

    public void setInterDateEnd(String interDateEnd)
    {
        this.interDateEnd = interDateEnd;
    }

    public String getReceptionEndTimeBegin()
    {
        return receptionEndTimeBegin;
    }

    public void setReceptionEndTimeBegin(String receptionEndTimeBegin)
    {
        this.receptionEndTimeBegin = receptionEndTimeBegin;
    }

    public String getReceptionEndTimeEnd()
    {
        return receptionEndTimeEnd;
    }

    public void setReceptionEndTimeEnd(String receptionEndTimeEnd)
    {
        this.receptionEndTimeEnd = receptionEndTimeEnd;
    }

    public String getExpDelCarTimeBegin()
    {
        return expDelCarTimeBegin;
    }

    public void setExpDelCarTimeBegin(String expDelCarTimeBegin)
    {
        this.expDelCarTimeBegin = expDelCarTimeBegin;
    }

    public String getExpDelCarTimeEnd()
    {
        return expDelCarTimeEnd;
    }

    public void setExpDelCarTimeEnd(String expDelCarTimeEnd)
    {
        this.expDelCarTimeEnd = expDelCarTimeEnd;
    }

    public Integer getAdviceId()
    {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId)
    {
        this.adviceId = adviceId;
    }

    public String getAdviceContext()
    {
        return adviceContext;
    }

    public void setAdviceContext(String adviceContext)
    {
        this.adviceContext = adviceContext;
    }

    public String getAdviceTime()
    {
        return adviceTime;
    }

    public void setAdviceTime(String adviceTime)
    {
        this.adviceTime = adviceTime;
    }

    public String getAdviceTimeEnd()
    {
        return adviceTimeEnd;
    }

    public void setAdviceTimeEnd(String adviceTimeEnd)
    {
        this.adviceTimeEnd = adviceTimeEnd;
    }

    public Integer getWritePerson()
    {
        return writePerson;
    }

    public void setWritePerson(Integer writePerson)
    {
        this.writePerson = writePerson;
    }

    public String getProcesContext()
    {
        return procesContext;
    }

    public void setProcesContext(String procesContext)
    {
        this.procesContext = procesContext;
    }

    public Integer getProcesState()
    {
        return procesState;
    }

    public void setProcesState(Integer procesState)
    {
        this.procesState = procesState;
    }

    public Integer getProcesProson()
    {
        return procesProson;
    }

    public void setProcesProson(Integer procesProson)
    {
        this.procesProson = procesProson;
    }

    public Short getAdviceClass()
    {
        return adviceClass;
    }

    public void setAdviceClass(Short adviceClass)
    {
        this.adviceClass = adviceClass;
    }

    public String getAdviceClassName()
    {
        return adviceClassName;
    }

    public void setAdviceClassName(String adviceClassName)
    {
        this.adviceClassName = adviceClassName;
    }

    public String getWritePersonName()
    {
        return writePersonName;
    }

    public void setWritePersonName(String writePersonName)
    {
        this.writePersonName = writePersonName;
    }

    public String getProcesStateName()
    {
        return procesStateName;
    }

    public void setProcesStateName(String procesStateName)
    {
        this.procesStateName = procesStateName;
    }

    public String getProcesProsonName()
    {
        return procesProsonName;
    }

    public void setProcesProsonName(String procesProsonName)
    {
        this.procesProsonName = procesProsonName;
    }

    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public String getRpId()
    {
        return rpId;
    }

    public void setRpId(String rpId)
    {
        this.rpId = rpId;
    }

    public String getRpName()
    {
        return rpName;
    }

    public void setRpName(String rpName)
    {
        this.rpName = rpName;
    }

    public String getValuablesName()
    {
        return valuablesName;
    }

    public void setValuablesName(String valuablesName)
    {
        this.valuablesName = valuablesName;
    }

    public String getFuelSituationName()
    {
        return fuelSituationName;
    }

    public void setFuelSituationName(String fuelSituationName)
    {
        this.fuelSituationName = fuelSituationName;
    }

    public String getOldPiecesName()
    {
        return oldPiecesName;
    }

    public void setOldPiecesName(String oldPiecesName)
    {
        this.oldPiecesName = oldPiecesName;
    }

	public Integer getNoComeInCompanyDays() {
		return noComeInCompanyDays;
	}

	public void setNoComeInCompanyDays(Integer noComeInCompanyDays) {
		this.noComeInCompanyDays = noComeInCompanyDays;
	}

	public String getReceptionFactTime() {
		return receptionFactTime;
	}

	public void setReceptionFactTime(String receptionFactTime) {
		this.receptionFactTime = receptionFactTime;
	}


	public Long getDifferenceTime() {
		return differenceTime;
	}

	public void setDifferenceTime(Long differenceTime) {
		this.differenceTime = differenceTime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
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

	public String getPreclrId() {
		return preclrId;
	}

	public void setPreclrId(String preclrId) {
		this.preclrId = preclrId;
	}

	public Boolean getFlage() {
		return flage;
	}

	public void setFlage(Boolean flage) {
		this.flage = flage;
	}

}
