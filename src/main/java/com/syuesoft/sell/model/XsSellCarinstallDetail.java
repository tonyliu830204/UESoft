package com.syuesoft.sell.model;

/**
 * XsSellCarinstallDetail entity. @author MyEclipse Persistence Tools
 */

public class XsSellCarinstallDetail implements java.io.Serializable {
	// Fields
	/** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
    */ 
    private static final long serialVersionUID = 1L;
    private Integer detailId;                            //明细编号
	private XsSellCarinstall xsSellCarinstall;           //加装序号
	private String partsId;                              //配件编号
	private String partsName;                            //配件名称
	private Double paetsCaseMoney;                       //配件成本金额
	private Double paetsSellMoney;                       //配件销售金额
	private Float partsNum;                              //配件个数
	private Double partsAmount;                          //配件金额
	private Integer punitId;                             //
	private Integer storeId;                             //
	private String remark;                               //备注

	// Constructors

	/** default constructor */
	public XsSellCarinstallDetail() {
	}

	/** full constructor */
	public XsSellCarinstallDetail(XsSellCarinstall xsSellCarinstall,
	        String partsId, Double paetsCaseMoney, Double paetsSellMoney,
			String remark) {
		this.xsSellCarinstall = xsSellCarinstall;
		this.partsId = partsId;
		this.paetsCaseMoney = paetsCaseMoney;
		this.paetsSellMoney = paetsSellMoney;
		this.remark = remark;
	}

	// Property accessors

	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public XsSellCarinstall getXsSellCarinstall() {
		return this.xsSellCarinstall;
	}

	public void setXsSellCarinstall(XsSellCarinstall xsSellCarinstall) {
		this.xsSellCarinstall = xsSellCarinstall;
	}

	public String getPartsId() {
		return this.partsId;
	}

	public void setPartsId(String partsId) {
		this.partsId = partsId;
	}
    
	public String getPartsName()
    {
        return partsName;
    }

    public void setPartsName(String partsName)
    {
        this.partsName = partsName;
    }

    public Double getPaetsCaseMoney() {
		return this.paetsCaseMoney;
	}

	public void setPaetsCaseMoney(Double paetsCaseMoney) {
		this.paetsCaseMoney = paetsCaseMoney;
	}

	public Double getPaetsSellMoney() {
		return this.paetsSellMoney;
	}

	public void setPaetsSellMoney(Double paetsSellMoney) {
		this.paetsSellMoney = paetsSellMoney;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    public Float getPartsNum()
    {
        return partsNum;
    }

    public void setPartsNum(Float partsNum)
    {
        this.partsNum = partsNum;
    }

    public Double getPartsAmount()
    {
        return partsAmount;
    }

    public void setPartsAmount(Double partsAmount)
    {
        this.partsAmount = partsAmount;
    }

    public Integer getPunitId()
    {
        return punitId;
    }

    public void setPunitId(Integer punitId)
    {
        this.punitId = punitId;
    }

    public Integer getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }
}