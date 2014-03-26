package com.syuesoft.fbk.vo;

public class VTrackRecordVo
{
    private int page = 0;

    private int rows = 0;

    private String carLicense;

    private String carCstlName;

    private String cbrdName;// 车辆品牌
    private String cbrdId;

    private String carVan;

    private String carBuyDate;

    private String carRelationPerson;

    private String ctypeName;
    private String ctypeId;

    private String customName;

    private String customTel1;

    private String customAddr;

    private String customSex;

    private String receptionDistance;

    private String receptionId;

    private String interDate;

    private String propRepPer;

    private String propTel;

    private String preclrTime;

    private String preclrRealAmount;

    private String pareaName;

    private String colorName;
    private String reptId;//维修类别
    private String reptName;

    private String satisfaction;
    private String satisfactionName;
    private String returnVisitDate;

    private String handlePerson;

    private String returnVisitMembers;

    private String callSituation;

    private String reciptReturnvisit;

    private String handleResult;

    private String memo;

    private String complaintContent;

    private String handleProgram;

    private String complaintType;

    private String complaintDegree;

    private String score;

    private String serveyName; // 项目名称

    private String repgrpName;// 维修车间部门名称

    private String months;// 月份

    private String returnNumber;// 回访件数

    private String deptName;// 部门名称

    private String stfName;// 员工姓名
    private String prepItemName;//维修项目
    private String linkMan;//客户联系人
    private String customJm;//自编号
    private String receptionFactTime;//出厂日期
    private String receptor;//接待员
    private Boolean flag;
    private String selectedField;
    private Boolean is3D;
    
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public String getStfName()
    {
        return stfName;
    }

    public void setStfName(String stfName)
    {
        this.stfName = stfName;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getCbrdName()
    {
        return cbrdName;
    }

    public void setCbrdName(String cbrdName)
    {
        this.cbrdName = cbrdName;
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

    public String getMonths()
    {
        return months;
    }

    public void setMonths(String months)
    {
        this.months = months;
    }

    public String getReturnNumber()
    {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber)
    {
        this.returnNumber = returnNumber;
    }

    public String getRepgrpName()
    {
        return repgrpName;
    }

    public void setRepgrpName(String repgrpName)
    {
        this.repgrpName = repgrpName;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getCarCstlName()
    {
        return carCstlName;
    }

    public void setCarCstlName(String carCstlName)
    {
        this.carCstlName = carCstlName;
    }

    public String getCarVan()
    {
        return carVan;
    }

    public void setCarVan(String carVan)
    {
        this.carVan = carVan;
    }

    public String getCarBuyDate()
    {
        return carBuyDate;
    }

    public void setCarBuyDate(String carBuyDate)
    {
        this.carBuyDate = carBuyDate;
    }

    public String getCarRelationPerson()
    {
        return carRelationPerson;
    }

    public void setCarRelationPerson(String carRelationPerson)
    {
        this.carRelationPerson = carRelationPerson;
    }

    public String getCtypeName()
    {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName)
    {
        this.ctypeName = ctypeName;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getCustomTel1()
    {
        return customTel1;
    }

    public void setCustomTel1(String customTel1)
    {
        this.customTel1 = customTel1;
    }

    public String getCustomAddr()
    {
        return customAddr;
    }

    public void setCustomAddr(String customAddr)
    {
        this.customAddr = customAddr;
    }

    public String getCustomSex()
    {
        return customSex;
    }

    public void setCustomSex(String customSex)
    {
        this.customSex = customSex;
    }

    public String getReceptionDistance()
    {
        return receptionDistance;
    }

    public void setReceptionDistance(String receptionDistance)
    {
        this.receptionDistance = receptionDistance;
    }

    public String getReceptionId()
    {
        return receptionId;
    }

    public void setReceptionId(String receptionId)
    {
        this.receptionId = receptionId;
    }

    public String getInterDate()
    {
        return interDate;
    }

    public void setInterDate(String interDate)
    {
        this.interDate = interDate;
    }

    public String getPropRepPer()
    {
        return propRepPer;
    }

    public void setPropRepPer(String propRepPer)
    {
        this.propRepPer = propRepPer;
    }

    public String getPropTel()
    {
        return propTel;
    }

    public void setPropTel(String propTel)
    {
        this.propTel = propTel;
    }

    public String getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(String preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public String getPreclrRealAmount()
    {
        return preclrRealAmount;
    }

    public void setPreclrRealAmount(String preclrRealAmount)
    {
        this.preclrRealAmount = preclrRealAmount;
    }

    public String getPareaName()
    {
        return pareaName;
    }

    public void setPareaName(String pareaName)
    {
        this.pareaName = pareaName;
    }

    public String getColorName()
    {
        return colorName;
    }

    public void setColorName(String colorName)
    {
        this.colorName = colorName;
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
    }

    public String getSatisfaction()
    {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction)
    {
        this.satisfaction = satisfaction;
    }

    public String getReturnVisitDate()
    {
        return returnVisitDate;
    }

    public void setReturnVisitDate(String returnVisitDate)
    {
        this.returnVisitDate = returnVisitDate;
    }

    public String getHandlePerson()
    {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson)
    {
        this.handlePerson = handlePerson;
    }

    public String getReturnVisitMembers()
    {
        return returnVisitMembers;
    }

    public void setReturnVisitMembers(String returnVisitMembers)
    {
        this.returnVisitMembers = returnVisitMembers;
    }

    public String getCallSituation()
    {
        return callSituation;
    }

    public void setCallSituation(String callSituation)
    {
        this.callSituation = callSituation;
    }

    public String getReciptReturnvisit()
    {
        return reciptReturnvisit;
    }

    public void setReciptReturnvisit(String reciptReturnvisit)
    {
        this.reciptReturnvisit = reciptReturnvisit;
    }

    public String getHandleResult()
    {
        return handleResult;
    }

    public void setHandleResult(String handleResult)
    {
        this.handleResult = handleResult;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getComplaintContent()
    {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent)
    {
        this.complaintContent = complaintContent;
    }

    public String getHandleProgram()
    {
        return handleProgram;
    }

    public void setHandleProgram(String handleProgram)
    {
        this.handleProgram = handleProgram;
    }

    public String getComplaintType()
    {
        return complaintType;
    }

    public void setComplaintType(String complaintType)
    {
        this.complaintType = complaintType;
    }

    public String getComplaintDegree()
    {
        return complaintDegree;
    }

    public void setComplaintDegree(String complaintDegree)
    {
        this.complaintDegree = complaintDegree;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

    public String getServeyName()
    {
        return serveyName;
    }

    public void setServeyName(String serveyName)
    {
        this.serveyName = serveyName;
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

	public String getPrepItemName() {
		return prepItemName;
	}

	public void setPrepItemName(String prepItemName) {
		this.prepItemName = prepItemName;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getCustomJm() {
		return customJm;
	}

	public void setCustomJm(String customJm) {
		this.customJm = customJm;
	}

	public String getReceptionFactTime() {
		return receptionFactTime;
	}

	public void setReceptionFactTime(String receptionFactTime) {
		this.receptionFactTime = receptionFactTime;
	}

	public String getSatisfactionName() {
		return satisfactionName;
	}

	public void setSatisfactionName(String satisfactionName) {
		this.satisfactionName = satisfactionName;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getReptId() {
		return reptId;
	}

	public void setReptId(String reptId) {
		this.reptId = reptId;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getSelectedField() {
		return selectedField;
	}

	public void setSelectedField(String selectedField) {
		this.selectedField = selectedField;
	}

	public Boolean getIs3D() {
		return is3D;
	}

	public void setIs3D(Boolean is3d) {
		is3D = is3d;
	}
	
    
}
