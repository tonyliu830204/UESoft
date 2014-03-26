package com.syuesoft.fbk.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.syuesoft.model.FbkCollect;
import com.syuesoft.model.FrtCar;

public class TrackManagementVo
{
    private String carVan; // vin号

    private String carLicense;// 车牌照

    private String carBuyDate;// 购车日期

    private String carMotorId;// 发动机号

    private String color; // 车辆颜色

    private String customId;

    private String customName;

    private String customTel1;

    private String customAddr;// 地址

    private String rcptitemName;// 维修项目名

    private String receptionDistance;// 里程数

    private String receptionRepPer;// 保险送修人

    private String receptionId;// 工单号/前台接车id

    private String interDate;// 进厂日期

    private String preclrTime;// 计算日期

    private String preclrId;// 结算单号

    private String receptor;// 前台接待

    private String propRepPer;// 托修人
    private String propRepPerName;// 托修人姓名
    private String propPhone;// 托修人电话

    private String preclrRealAmount;// 实际收款或实际费用

    private String customSex;// 客户性别
    private String customLinkMan;//客户联系人id
    private String customLinkManName;//客户联系人名称
    private String complaintContent; // 投诉内容或客户意见及建议

    private String memo;// 回访明细备注

    private String handleResult; // 处理结果

    private String returnVisitMembers; // 回访员

    private String handlePerson;// 投诉或不满意处理人

    private String callSituation;// 通话情况

    private String reptName; // 维修类别名

    private String complaintType; // 投诉类型

    private String complaintDegree; // 投诉程度

    private String reciptReturnvisit;// //接收回访

    private String cbrdName;// 车品牌

    private String ctypeName;// 车型号
   private String  ctypeId;// 车型号名称

    private String returnVisitDate; // 回访日期

    private String satisfaction; // 满意程度
    private String  satisfactionName;
    private String notSatisfaction; // 不满意程度

    private String repgrpId;// 维修班组id

    private String complaintQK; // 投诉情况

    private String returnSituatiom;// 回访状态

    private String preclrRemark; // 结算备注

    private String problemDesc;// 故障描述

    private String handleProgram; // 处理方案

    private String serviceProposal; // 维修建议

    private String carId;

    private String detailId;

    private String collectId;

    private String dcId;

    private String projectName; // 项目名称

    private String score; // 评分
    private String scoreName;
    
    private String evaluate; // 评价
    private String evaluateOne;//很好
    private String evaluateTwo;//好
    private String evaluateThree;//一般
    private String evaluateFour;//不好
    private String evaluateFive;//很不好
    private String note;//回访项目备注
    private String resvId;//预约编号
    
    
    private String collectInformation;
    private String fbkDetail;
    private String fbkDetail1;
    private String visit3Dc;

    private String dcNameId;
    
    private Integer enterpriseId;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public String getDcNameId()
    {
        return dcNameId;
    }

    public void setDcNameId(String dcNameId)
    {
        this.dcNameId = dcNameId;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getDcId()
    {
        return dcId;
    }

    public void setDcId(String dcId)
    {
        this.dcId = dcId;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

    public String getEvaluate()
    {
        return evaluate;
    }

    public void setEvaluate(String evaluate)
    {
        this.evaluate = evaluate;
    }

    public String getCollectId()
    {
        return collectId;
    }

    public void setCollectId(String collectId)
    {
        this.collectId = collectId;
    }

    public String getDetailId()
    {
        return detailId;
    }

    public void setDetailId(String detailId)
    {
        this.detailId = detailId;
    }

    public String getCarId()
    {
        return carId;
    }

    public void setCarId(String carId)
    {
        this.carId = carId;
    }

    private int page;

    private int rows;

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

    public TrackManagementVo()
    {
    }

    public TrackManagementVo(String carVan, String carLicense,
            String carBuyDate, String carMotorId, String color,
            String customId, String customName, String customTel1,
            String customAddr, String rcptitemName, String receptionDistance,
            String receptionRepPer, String receptionId, String interDate,
            String preclrTime, String preclrId, String receptor,
            String propRepPer, String propPhone, String preclrRealAmount,
            String customSex, String complaintContent, String memo,
            String handleResult, String returnVisitMembers,
            String handlePerson, String callSituation, String reptName,
            String complaintType, String complaintDegree,
            String reciptReturnvisit, String cbrdName, String ctypeName,
            String returnVisitDate, String satisfaction, String repgrpId,
            String complaintQK, String returnSituatiom, String preclrRemark,
            String problemDesc, String handleProgram, String serviceProposal,
            String detailId, String collectId)
    {
        super();
        this.carVan = carVan;
        this.carLicense = carLicense;
        this.carBuyDate = carBuyDate;
        this.carMotorId = carMotorId;
        this.color = color;
        this.customId = customId;
        this.customName = customName;
        this.customTel1 = customTel1;
        this.customAddr = customAddr;
        this.rcptitemName = rcptitemName;
        this.receptionDistance = receptionDistance;
        this.receptionRepPer = receptionRepPer;
        this.receptionId = receptionId;
        this.interDate = interDate;
        this.preclrTime = preclrTime;
        this.preclrId = preclrId;
        this.receptor = receptor;
        this.propRepPer = propRepPer;
        this.propPhone = propPhone;
        this.preclrRealAmount = preclrRealAmount;
        this.customSex = customSex;
        this.complaintContent = complaintContent;
        this.memo = memo;
        this.handleResult = handleResult;
        this.returnVisitMembers = returnVisitMembers;
        this.handlePerson = handlePerson;
        this.callSituation = callSituation;
        this.reptName = reptName;
        this.complaintType = complaintType;
        this.complaintDegree = complaintDegree;
        this.reciptReturnvisit = reciptReturnvisit;
        this.cbrdName = cbrdName;
        this.ctypeName = ctypeName;
        this.returnVisitDate = returnVisitDate;
        this.satisfaction = satisfaction;
        this.repgrpId = repgrpId;
        this.returnSituatiom = returnSituatiom;
        this.complaintQK = complaintQK;
        this.preclrRemark = preclrRemark;
        this.problemDesc = problemDesc;
        this.handleProgram = handleProgram;
        this.serviceProposal = serviceProposal;
        this.carId = carId;
        this.detailId = detailId;
        this.collectId = collectId;
    }

    public String getCarVan()
    {
        return carVan;
    }

    public void setCarVan(String carVan)
    {
        this.carVan = carVan;
    }

    public String getCarLicense()
    {
        return carLicense;
    }

    public void setCarLicense(String carLicense)
    {
        this.carLicense = carLicense;
    }

    public String getCarBuyDate()
    {
        return carBuyDate;
    }

    public void setCarBuyDate(String carBuyDate)
    {
        this.carBuyDate = carBuyDate;
    }

    public String getCarMotorId()
    {
        return carMotorId;
    }

    public void setCarMotorId(String carMotorId)
    {
        this.carMotorId = carMotorId;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
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

    public String getRcptitemName()
    {
        return rcptitemName;
    }

    public void setRcptitemName(String rcptitemName)
    {
        this.rcptitemName = rcptitemName;
    }

    public String getReceptionDistance()
    {
        return receptionDistance;
    }

    public void setReceptionDistance(String receptionDistance)
    {
        this.receptionDistance = receptionDistance;
    }

    public String getReceptionRepPer()
    {
        return receptionRepPer;
    }

    public void setReceptionRepPer(String receptionRepPer)
    {
        this.receptionRepPer = receptionRepPer;
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

    public String getPreclrTime()
    {
        return preclrTime;
    }

    public void setPreclrTime(String preclrTime)
    {
        this.preclrTime = preclrTime;
    }

    public String getPreclrId()
    {
        return preclrId;
    }

    public void setPreclrId(String preclrId)
    {
        this.preclrId = preclrId;
    }

    public String getReceptor()
    {
        return receptor;
    }

    public void setReceptor(String receptor)
    {
        this.receptor = receptor;
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

    public String getPreclrRealAmount()
    {
        return preclrRealAmount;
    }

    public void setPreclrRealAmount(String preclrRealAmount)
    {
        this.preclrRealAmount = preclrRealAmount;
    }

    public String getCustomSex()
    {
        return customSex;
    }

    public void setCustomSex(String customSex)
    {
        this.customSex = customSex;
    }

    public String getComplaintContent()
    {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent)
    {
        this.complaintContent = complaintContent;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getHandleResult()
    {
        return handleResult;
    }

    public void setHandleResult(String handleResult)
    {
        this.handleResult = handleResult;
    }

    public String getReturnVisitMembers()
    {
        return returnVisitMembers;
    }

    public void setReturnVisitMembers(String returnVisitMembers)
    {
        this.returnVisitMembers = returnVisitMembers;
    }

    public String getHandlePerson()
    {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson)
    {
        this.handlePerson = handlePerson;
    }

    public String getCallSituation()
    {
        return callSituation;
    }

    public void setCallSituation(String callSituation)
    {
        this.callSituation = callSituation;
    }

    public String getReptName()
    {
        return reptName;
    }

    public void setReptName(String reptName)
    {
        this.reptName = reptName;
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

    public String getReciptReturnvisit()
    {
        return reciptReturnvisit;
    }

    public void setReciptReturnvisit(String reciptReturnvisit)
    {
        this.reciptReturnvisit = reciptReturnvisit;
    }

    public String getCbrdName()
    {
        return cbrdName;
    }

    public void setCbrdName(String cbrdName)
    {
        this.cbrdName = cbrdName;
    }

    public String getCtypeName()
    {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName)
    {
        this.ctypeName = ctypeName;
    }

    public String getReturnVisitDate()
    {
        return returnVisitDate;
    }

    public void setReturnVisitDate(String returnVisitDate)
    {
        this.returnVisitDate = returnVisitDate;
    }

    public String getSatisfaction()
    {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction)
    {
        this.satisfaction = satisfaction;
    }

    public String getRepgrpId()
    {
        return repgrpId;
    }

    public void setRepgrpId(String repgrpId)
    {
        this.repgrpId = repgrpId;
    }

    public String getComplaintQK()
    {
        return complaintQK;
    }

    public void setComplaintQK(String complaintQK)
    {
        this.complaintQK = complaintQK;
    }

    public String getReturnSituatiom()
    {
        return returnSituatiom;
    }

    public void setReturnSituatiom(String returnSituatiom)
    {
        this.returnSituatiom = returnSituatiom;
    }

    public String getPreclrRemark()
    {
        return preclrRemark;
    }

    public void setPreclrRemark(String preclrRemark)
    {
        this.preclrRemark = preclrRemark;
    }

    public String getProblemDesc()
    {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc)
    {
        this.problemDesc = problemDesc;
    }

    public String getHandleProgram()
    {
        return handleProgram;
    }

    public void setHandleProgram(String handleProgram)
    {
        this.handleProgram = handleProgram;
    }

    public String getServiceProposal()
    {
        return serviceProposal;
    }

    public void setServiceProposal(String serviceProposal)
    {
        this.serviceProposal = serviceProposal;
    }

	public String getCtypeId() {
		return ctypeId;
	}

	public void setCtypeId(String ctypeId) {
		this.ctypeId = ctypeId;
	}

	public String getCustomLinkMan() {
		return customLinkMan;
	}

	public void setCustomLinkMan(String customLinkMan) {
		this.customLinkMan = customLinkMan;
	}

	public String getCustomLinkManName() {
		return customLinkManName;
	}

	public void setCustomLinkManName(String customLinkManName) {
		this.customLinkManName = customLinkManName;
	}

	public String getPropRepPerName() {
		return propRepPerName;
	}

	public void setPropRepPerName(String propRepPerName) {
		this.propRepPerName = propRepPerName;
	}

	public String getEvaluateOne() {
		return evaluateOne;
	}

	public void setEvaluateOne(String evaluateOne) {
		this.evaluateOne = evaluateOne;
	}

	public String getEvaluateTwo() {
		return evaluateTwo;
	}

	public void setEvaluateTwo(String evaluateTwo) {
		this.evaluateTwo = evaluateTwo;
	}

	public String getEvaluateThree() {
		return evaluateThree;
	}

	public void setEvaluateThree(String evaluateThree) {
		this.evaluateThree = evaluateThree;
	}

	public String getEvaluateFour() {
		return evaluateFour;
	}

	public void setEvaluateFour(String evaluateFour) {
		this.evaluateFour = evaluateFour;
	}

	public String getEvaluateFive() {
		return evaluateFive;
	}

	public void setEvaluateFive(String evaluateFive) {
		this.evaluateFive = evaluateFive;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCollectInformation() {
		return collectInformation;
	}

	public void setCollectInformation(String collectInformation) {
		this.collectInformation = collectInformation;
	}

	public String getFbkDetail() {
		return fbkDetail;
	}

	public void setFbkDetail(String fbkDetail) {
		this.fbkDetail = fbkDetail;
	}

	public String getFbkDetail1() {
		return fbkDetail1;
	}

	public void setFbkDetail1(String fbkDetail1) {
		this.fbkDetail1 = fbkDetail1;
	}

	public String getVisit3Dc() {
		return visit3Dc;
	}

	public void setVisit3Dc(String visit3Dc) {
		this.visit3Dc = visit3Dc;
	}

	public String getNotSatisfaction() {
		return notSatisfaction;
	}

	public void setNotSatisfaction(String notSatisfaction) {
		this.notSatisfaction = notSatisfaction;
	}

	public String getScoreName() {
		return scoreName;
	}

	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}

	public String getSatisfactionName() {
		return satisfactionName;
	}

	public void setSatisfactionName(String satisfactionName) {
		this.satisfactionName = satisfactionName;
	}

	public String getResvId() {
		return resvId;
	}

	public void setResvId(String resvId) {
		this.resvId = resvId;
	}
	
	
}
