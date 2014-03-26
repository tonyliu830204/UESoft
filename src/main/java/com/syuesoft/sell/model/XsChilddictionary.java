package com.syuesoft.sell.model;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * BasChilddictionary entity. @author MyEclipse Persistence Tools
 */

public class XsChilddictionary  implements java.io.Serializable {


    // Fields    

     private Integer childId;
     private Integer parentId;
     private Long stfId;
     private Timestamp createTime;
     private String dataKey;
     private String dataValue;
     private String remark;
     private Integer enterpriseId;
     private Set xsSupplierInfos = new HashSet(0);
     private Set xsSupplierInfos2 = new HashSet(0);
     private Set xsSellAttachments = new HashSet(0);
     //车辆型号
     private Set xsCarModels = new HashSet(0);
     private Set xsSellBacksExamine = new HashSet(0);
     private Set xsSellBacksState = new HashSet(0);
     private Set xsSellBacksType = new HashSet(0);
     //车辆预订表关联字段
     private Set xsSellCarReserve = new HashSet(0);
     private Set xsSellCarReserve1 = new HashSet(0);
     private Set xsSellCarReserve2 = new HashSet(0);
     private Set xsSellCarReserve3 = new HashSet(0);
     private Set xsSellCarReserve4 = new HashSet(0);
     private Set xsSellCarReserve5 = new HashSet(0);
     private Set xsSellCarReserve6 = new HashSet(0);
     private Set xsSellCarReserve7 = new HashSet(0);
     //装潢项目(销售及赠送)审核 
     private Set xsSellZhProjectAudit = new HashSet(0);
     //装潢项目(销售及赠送) 是否转结算
     private Set xsSellZhProjectReckoning = new HashSet(0);
     //车辆档案
     private Set xsCarInfoBrands = new HashSet(0);
     private Set xsCarInfoColor = new HashSet(0);
     private Set xsCarInfoInteriorColor = new HashSet(0);
     private Set xsCarInfoTypes = new HashSet(0);
     private Set xsCarInfoNorms = new HashSet(0);
     private Set xsCarInfoToolCase = new HashSet(0);
     private Set xsCarInfoFootd = new HashSet(0);
     private Set xsCarSellState = new HashSet(0);
     private Set xsCarCertificate = new HashSet(0);
     private Set xsCarDistribut = new HashSet(0);
     private Set xsFixStatus = new HashSet(0);
     //车辆加装xs_sell_carInstall
     private Set xsSellCarinstallExamine = new HashSet(0); 
     private Set xsSellCarinstallFinishState = new HashSet(0);
     //xs_sell_certificate合格证管理
     private Set xsSellCertificateState = new HashSet(0);
     //xs_sell_collections
     private Set xsSellCollectionClassify = new HashSet(0);
     private Set xsSellCollectionType = new HashSet(0);
     private Set xsSellCollectionUnfinished = new HashSet(0);
     //预收款使用记录
     private Set xsSellCollectionsDetailWay = new HashSet(0);
     private Set xsSellCollectionsDetailInvoice = new HashSet(0);
     private Set xsSellCollectionsDetailExamine = new HashSet(0);
     private Set xsSellCollectionsDetailDetailType = new HashSet(0);
     //xs_sell_cover 
     private Set xsSellCoverCallState = new HashSet(0);
     private Set xsSellCoverDegree = new HashSet(0);
     //xs_sell_cover_content
     private Set xsSellCoverContentEvaluate = new HashSet(0);
     

    public Set getXsSellCoverContentEvaluate() {
		return xsSellCoverContentEvaluate;
	}
	public void setXsSellCoverContentEvaluate(Set xsSellCoverContentEvaluate) {
		this.xsSellCoverContentEvaluate = xsSellCoverContentEvaluate;
	}
	public Set getXsSellCoverCallState() {
		return xsSellCoverCallState;
	}
	public void setXsSellCoverCallState(Set xsSellCoverCallState) {
		this.xsSellCoverCallState = xsSellCoverCallState;
	}
	public Set getXsSellCoverDegree() {
		return xsSellCoverDegree;
	}
	public void setXsSellCoverDegree(Set xsSellCoverDegree) {
		this.xsSellCoverDegree = xsSellCoverDegree;
	}
	public Set getXsCarCertificate() {
		return xsCarCertificate;
	}
	public void setXsCarCertificate(Set xsCarCertificate) {
		this.xsCarCertificate = xsCarCertificate;
	}
	public Set getXsCarDistribut() {
		return xsCarDistribut;
	}
	public void setXsCarDistribut(Set xsCarDistribut) {
		this.xsCarDistribut = xsCarDistribut;
	}
	public Set getXsFixStatus() {
		return xsFixStatus;
	}
	public void setXsFixStatus(Set xsFixStatus) {
		this.xsFixStatus = xsFixStatus;
	}
	public Set getXsCarSellState() {
		return xsCarSellState;
	}
	public void setXsCarSellState(Set xsCarSellState) {
		this.xsCarSellState = xsCarSellState;
	}
	public Set getXsSellCollectionsDetailWay() {
		return xsSellCollectionsDetailWay;
	}

	public void setXsSellCollectionsDetailWay(Set xsSellCollectionsDetailWay) {
		this.xsSellCollectionsDetailWay = xsSellCollectionsDetailWay;
	}

	public Set getXsSellCollectionsDetailInvoice() {
		return xsSellCollectionsDetailInvoice;
	}

	public void setXsSellCollectionsDetailInvoice(Set xsSellCollectionsDetailInvoice) {
		this.xsSellCollectionsDetailInvoice = xsSellCollectionsDetailInvoice;
	}

	public Set getXsSellCollectionsDetailExamine() {
		return xsSellCollectionsDetailExamine;
	}

	public void setXsSellCollectionsDetailExamine(Set xsSellCollectionsDetailExamine) {
		this.xsSellCollectionsDetailExamine = xsSellCollectionsDetailExamine;
	}

	public Set getXsSellCollectionsDetailDetailType() {
		return xsSellCollectionsDetailDetailType;
	}

	public void setXsSellCollectionsDetailDetailType(
			Set xsSellCollectionsDetailDetailType) {
		this.xsSellCollectionsDetailDetailType = xsSellCollectionsDetailDetailType;
	}

	public Set getXsSellCollectionClassify() {
		return xsSellCollectionClassify;
	}

	public void setXsSellCollectionClassify(Set xsSellCollectionClassify) {
		this.xsSellCollectionClassify = xsSellCollectionClassify;
	}

	public Set getXsSellCollectionType() {
		return xsSellCollectionType;
	}

	public void setXsSellCollectionType(Set xsSellCollectionType) {
		this.xsSellCollectionType = xsSellCollectionType;
	}

	public Set getXsSellCollectionUnfinished() {
		return xsSellCollectionUnfinished;
	}

	public void setXsSellCollectionUnfinished(Set xsSellCollectionUnfinished) {
		this.xsSellCollectionUnfinished = xsSellCollectionUnfinished;
	}

	public Set getXsSellCertificateState() {
		return xsSellCertificateState;
	}

	public void setXsSellCertificateState(Set xsSellCertificateState) {
		this.xsSellCertificateState = xsSellCertificateState;
	}

	public Set getXsSellCarinstallExamine() {
		return xsSellCarinstallExamine;
	}

	public void setXsSellCarinstallExamine(Set xsSellCarinstallExamine) {
		this.xsSellCarinstallExamine = xsSellCarinstallExamine;
	}

	public Set getXsSellCarinstallFinishState() {
		return xsSellCarinstallFinishState;
	}

	public void setXsSellCarinstallFinishState(Set xsSellCarinstallFinishState) {
		this.xsSellCarinstallFinishState = xsSellCarinstallFinishState;
	}

	public Set getXsCarInfoBrands() {
		return xsCarInfoBrands;
	}

	public void setXsCarInfoBrands(Set xsCarInfoBrands) {
		this.xsCarInfoBrands = xsCarInfoBrands;
	}

	public Set getXsCarInfoColor() {
		return xsCarInfoColor;
	}


	public Set getXsSellZhProjectAudit() {
		return xsSellZhProjectAudit;
	}

	public void setXsSellZhProjectAudit(Set xsSellZhProjectAudit) {
		this.xsSellZhProjectAudit = xsSellZhProjectAudit;
	}

	public Set getXsSellZhProjectReckoning() {
		return xsSellZhProjectReckoning;
	}

	public void setXsSellZhProjectReckoning(Set xsSellZhProjectReckoning) {
		this.xsSellZhProjectReckoning = xsSellZhProjectReckoning;
	}


	public void setXsCarInfoColor(Set xsCarInfoColor) {
		this.xsCarInfoColor = xsCarInfoColor;
	}

	public Set getXsCarInfoInteriorColor() {
		return xsCarInfoInteriorColor;
	}

	public void setXsCarInfoInteriorColor(Set xsCarInfoInteriorColor) {
		this.xsCarInfoInteriorColor = xsCarInfoInteriorColor;
	}


	public Set getXsSellCarReserve() {
		return xsSellCarReserve;
	}

	public void setXsSellCarReserve(Set xsSellCarReserve) {
		this.xsSellCarReserve = xsSellCarReserve;
	}

	public Set getXsSellCarReserve1() {
		return xsSellCarReserve1;
	}

	public void setXsSellCarReserve1(Set xsSellCarReserve1) {
		this.xsSellCarReserve1 = xsSellCarReserve1;
	}

	public Set getXsSellCarReserve2() {
		return xsSellCarReserve2;
	}

	public void setXsSellCarReserve2(Set xsSellCarReserve2) {
		this.xsSellCarReserve2 = xsSellCarReserve2;
	}

	public Set getXsSellCarReserve3() {
		return xsSellCarReserve3;
	}

	public void setXsSellCarReserve3(Set xsSellCarReserve3) {
		this.xsSellCarReserve3 = xsSellCarReserve3;
	}

	public Set getXsSellCarReserve4() {
		return xsSellCarReserve4;
	}

	public void setXsSellCarReserve4(Set xsSellCarReserve4) {
		this.xsSellCarReserve4 = xsSellCarReserve4;
	}

	public Set getXsSellCarReserve5() {
		return xsSellCarReserve5;
	}

	public void setXsSellCarReserve5(Set xsSellCarReserve5) {
		this.xsSellCarReserve5 = xsSellCarReserve5;
	}

	public Set getXsSellCarReserve6() {
		return xsSellCarReserve6;
	}

	public void setXsSellCarReserve6(Set xsSellCarReserve6) {
		this.xsSellCarReserve6 = xsSellCarReserve6;
	}

	public Set getXsSellCarReserve7() {
		return xsSellCarReserve7;
	}

	public void setXsSellCarReserve7(Set xsSellCarReserve7) {
		this.xsSellCarReserve7 = xsSellCarReserve7;
	}

	public Set getXsSellBacksExamine() {
		return xsSellBacksExamine;
	}

	public void setXsSellBacksExamine(Set xsSellBacksExamine) {
		this.xsSellBacksExamine = xsSellBacksExamine;
	}

	public Set getXsSellBacksState() {
		return xsSellBacksState;
	}

	public void setXsSellBacksState(Set xsSellBacksState) {
		this.xsSellBacksState = xsSellBacksState;
	}

	public Set getXsSellBacksType() {
		return xsSellBacksType;
	}

	public void setXsSellBacksType(Set xsSellBacksType) {
		this.xsSellBacksType = xsSellBacksType;
	}

	public Set getXsSellAttachments() {
		return xsSellAttachments;
	}

	public void setXsSellAttachments(Set xsSellAttachments) {
		this.xsSellAttachments = xsSellAttachments;
	}
	
	
	public Set getXsCarInfoTypes() {
		return xsCarInfoTypes;
	}

	public void setXsCarInfoTypes(Set xsCarInfoTypes) {
		this.xsCarInfoTypes = xsCarInfoTypes;
	}

	public Set getXsCarInfoNorms() {
		return xsCarInfoNorms;
	}

	public void setXsCarInfoNorms(Set xsCarInfoNorms) {
		this.xsCarInfoNorms = xsCarInfoNorms;
	}

	public Set getXsCarInfoToolCase() {
		return xsCarInfoToolCase;
	}

	public void setXsCarInfoToolCase(Set xsCarInfoToolCase) {
		this.xsCarInfoToolCase = xsCarInfoToolCase;
	}

	public Set getXsCarInfoFootd() {
		return xsCarInfoFootd;
	}

	public void setXsCarInfoFootd(Set xsCarInfoFootd) {
		this.xsCarInfoFootd = xsCarInfoFootd;
	}

	public Set getXsCarModels() {
		return xsCarModels;
	}

	public void setXsCarModels(Set xsCarModels) {
		this.xsCarModels = xsCarModels;
	}

	public XsChilddictionary() {
     }
     
    public Set getXsSupplierInfos() {
		return xsSupplierInfos;
	}


	public Set getXsSupplierInfos2() {
		return xsSupplierInfos2;
	}

	public void setXsSupplierInfos2(Set xsSupplierInfos2) {
		this.xsSupplierInfos2 = xsSupplierInfos2;
	}

	public void setXsSupplierInfos(Set xsSupplierInfos) {
		this.xsSupplierInfos = xsSupplierInfos;
	}

	public Integer getChildId() {
        return this.childId;
    }
    
    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Long getStfId() {
        return this.stfId;
    }
    
    public void setStfId(Long stfId) {
        this.stfId = stfId;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDataKey() {
        return this.dataKey;
    }
    
    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return this.dataValue;
    }
    
    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
    
}