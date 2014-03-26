package com.syuesoft.sell.sellwork.vo;

public class CarFixVo {
	
	private int page;    		
	private int rows;    		
	private String xs_car_id;    		//车辆档案信息编号
	private String xs_car_licensePlate; //车牌照
	private String xs_car_brand;		//车辆品牌
	private String xs_car_brandName;    //车辆品牌名称
	private String xs_car_color;		//车辆外观色
	private String xs_car_colorName;    //车辆外观色
	private String carInteriorColor;    //车辆内饰色
	private String carInteriorColorName;//车辆内饰色名称
	private String xs_car_model_id; 	//车型号	
	private String xs_car_modelName;    //车型号名称 
	private String xs_car_code;			//车辆编号	
	private String xs_car_vin_number;	//车架号VIN	
	private String xs_car_motor_number;	//发动机号	
	private String xs_car_ocn;			//OCN码
	private String xs_car_sell_state;	//销售状态	
	private String fix_status;  		//加装状态
	private String fix_statusN;  		//加装状态
	
	
	private String install_id;			//加装序号	
	private String install_code;		//加装单号	
	private String sun_money;			//计划总金额	
	private String factSunMoney;        //实际总金额
	private String finish_state;		//完工状态
	private String finish_stateName;    //完工状态
	private String finish_stateKey;     //完工状态
	private String examine;				//审核状态
	private String examineName;         //审核状态
	private String examineKey;          //审核状态
	private String applySTF_ID;         //申请人
	private String applySTF_NAME;       //申请人名称
	private String applyDate;           //申请日期
	private String STF_ID;              //装配人
    private String STF_NAME;            //装配人名称
    private String configureDate;       //装配日期
    private String outSTF_ID;           //出库人
    private String outSTF_NAME;         //出库人名称
    private String outDate;             //出库日期
	private String remark;				//备注
	
	private String detailId;			//配件明细编号	
	private String partsId;			    //配件编号
	private String partsName;           //配件名称
	private String parts_case_money;	//配件成本金额	
	private String partsRepairPrice;	//配件销售金额
	private String punitId;             //配件单位编号
	private String punitName;           //配件单位名称
    private String partsNum;            //配件数量
	private String partsAmount;         //配件金额
	private String partsNowCount;       //配件库存量
	private String storeId;             //
	
	private String installItemId;       //项目明细编号
    private String itemId;              //项目编号
    private String itemName;            //项目名称
    private String itemCost;            //项目成本
    private String itemMoney;           //项目金额
    private String itemRemark;
    
    private String baseInfo;            //加装信息
	private String configParts;         //加装配件            
	private String configItem;          //加装项目
    private Integer enterpriseId;
	private String sort;
	private String order;
    public Integer getEnterpriseId() {
       return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
       this.enterpriseId = enterpriseId;
    }
    
	private String outId;               //出库编号
    private String outCode;             //出库单号
	private String outProjectId;        //出库单项目明细
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
    public String getXs_car_id()
    {
        return xs_car_id;
    }
    public void setXs_car_id(String xsCarId)
    {
        xs_car_id = xsCarId;
    }
    public String getXs_car_licensePlate()
    {
        return xs_car_licensePlate;
    }
    public void setXs_car_licensePlate(String xsCarLicensePlate)
    {
        xs_car_licensePlate = xsCarLicensePlate;
    }
    public String getXs_car_brand()
    {
        return xs_car_brand;
    }
    public void setXs_car_brand(String xsCarBrand)
    {
        xs_car_brand = xsCarBrand;
    }
    public String getXs_car_brandName()
    {
        return xs_car_brandName;
    }
    public void setXs_car_brandName(String xsCarBrandName)
    {
        xs_car_brandName = xsCarBrandName;
    }
    public String getXs_car_color()
    {
        return xs_car_color;
    }
    public void setXs_car_color(String xsCarColor)
    {
        xs_car_color = xsCarColor;
    }
    public String getXs_car_colorName()
    {
        return xs_car_colorName;
    }
    public void setXs_car_colorName(String xsCarColorName)
    {
        xs_car_colorName = xsCarColorName;
    }
    public String getCarInteriorColor()
    {
        return carInteriorColor;
    }
    public void setCarInteriorColor(String carInteriorColor)
    {
        this.carInteriorColor = carInteriorColor;
    }
    public String getCarInteriorColorName()
    {
        return carInteriorColorName;
    }
    public void setCarInteriorColorName(String carInteriorColorName)
    {
        this.carInteriorColorName = carInteriorColorName;
    }
    public String getXs_car_model_id()
    {
        return xs_car_model_id;
    }
    public void setXs_car_model_id(String xsCarModelId)
    {
        xs_car_model_id = xsCarModelId;
    }
    public String getXs_car_modelName()
    {
        return xs_car_modelName;
    }
    public void setXs_car_modelName(String xsCarModelName)
    {
        xs_car_modelName = xsCarModelName;
    }
    public String getXs_car_code()
    {
        return xs_car_code;
    }
    public void setXs_car_code(String xsCarCode)
    {
        xs_car_code = xsCarCode;
    }
    public String getXs_car_vin_number()
    {
        return xs_car_vin_number;
    }
    public void setXs_car_vin_number(String xsCarVinNumber)
    {
        xs_car_vin_number = xsCarVinNumber;
    }
    public String getXs_car_motor_number()
    {
        return xs_car_motor_number;
    }
    public void setXs_car_motor_number(String xsCarMotorNumber)
    {
        xs_car_motor_number = xsCarMotorNumber;
    }
    public String getXs_car_ocn()
    {
        return xs_car_ocn;
    }
    public void setXs_car_ocn(String xsCarOcn)
    {
        xs_car_ocn = xsCarOcn;
    }
    public String getXs_car_sell_state()
    {
        return xs_car_sell_state;
    }
    public void setXs_car_sell_state(String xsCarSellState)
    {
        xs_car_sell_state = xsCarSellState;
    }
    public String getFix_status()
    {
        return fix_status;
    }
    public void setFix_status(String fixStatus)
    {
        fix_status = fixStatus;
    }
    public String getInstall_id()
    {
        return install_id;
    }
    public void setInstall_id(String installId)
    {
        install_id = installId;
    }
    public String getInstall_code()
    {
        return install_code;
    }
    public void setInstall_code(String installCode)
    {
        install_code = installCode;
    }
    public String getSun_money()
    {
        return sun_money;
    }
    public void setSun_money(String sunMoney)
    {
        sun_money = sunMoney;
    }
    public String getFactSunMoney()
    {
        return factSunMoney;
    }
    public void setFactSunMoney(String factSunMoney)
    {
        this.factSunMoney = factSunMoney;
    }
    public String getFinish_state()
    {
        return finish_state;
    }
    public void setFinish_state(String finishState)
    {
        finish_state = finishState;
    }
    public String getFinish_stateName()
    {
        return finish_stateName;
    }
    public void setFinish_stateName(String finishStateName)
    {
        finish_stateName = finishStateName;
    }
    public String getFinish_stateKey()
    {
        return finish_stateKey;
    }
    public void setFinish_stateKey(String finishStateKey)
    {
        finish_stateKey = finishStateKey;
    }
    public String getExamine()
    {
        return examine;
    }
    public void setExamine(String examine)
    {
        this.examine = examine;
    }
    public String getExamineName()
    {
        return examineName;
    }
    public void setExamineName(String examineName)
    {
        this.examineName = examineName;
    }
    public String getExamineKey()
    {
        return examineKey;
    }
    public void setExamineKey(String examineKey)
    {
        this.examineKey = examineKey;
    }
    public String getApplySTF_ID()
    {
        return applySTF_ID;
    }
    public void setApplySTF_ID(String applySTFID)
    {
        applySTF_ID = applySTFID;
    }
    public String getApplySTF_NAME()
    {
        return applySTF_NAME;
    }
    public void setApplySTF_NAME(String applySTFNAME)
    {
        applySTF_NAME = applySTFNAME;
    }
    public String getApplyDate()
    {
        return applyDate;
    }
    public void setApplyDate(String applyDate)
    {
        this.applyDate = applyDate;
    }
    public String getSTF_ID()
    {
        return STF_ID;
    }
    public void setSTF_ID(String sTFID)
    {
        STF_ID = sTFID;
    }
    public String getSTF_NAME()
    {
        return STF_NAME;
    }
    public void setSTF_NAME(String sTFNAME)
    {
        STF_NAME = sTFNAME;
    }
    public String getConfigureDate()
    {
        return configureDate;
    }
    public void setConfigureDate(String configureDate)
    {
        this.configureDate = configureDate;
    }
    public String getOutSTF_ID()
    {
        return outSTF_ID;
    }
    public void setOutSTF_ID(String outSTFID)
    {
        outSTF_ID = outSTFID;
    }
    public String getOutSTF_NAME()
    {
        return outSTF_NAME;
    }
    public void setOutSTF_NAME(String outSTFNAME)
    {
        outSTF_NAME = outSTFNAME;
    }
    public String getOutDate()
    {
        return outDate;
    }
    public void setOutDate(String outDate)
    {
        this.outDate = outDate;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getDetailId()
    {
        return detailId;
    }
    public void setDetailId(String detailId)
    {
        this.detailId = detailId;
    }
    public String getPartsId()
    {
        return partsId;
    }
    public void setPartsId(String partsId)
    {
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
    public String getParts_case_money()
    {
        return parts_case_money;
    }
    public void setParts_case_money(String partsCaseMoney)
    {
        parts_case_money = partsCaseMoney;
    }
    public String getPartsRepairPrice()
    {
        return partsRepairPrice;
    }
    public void setPartsRepairPrice(String partsRepairPrice)
    {
        this.partsRepairPrice = partsRepairPrice;
    }
    public String getPunitId()
    {
        return punitId;
    }
    public void setPunitId(String punitId)
    {
        this.punitId = punitId;
    }
    public String getPunitName()
    {
        return punitName;
    }
    public void setPunitName(String punitName)
    {
        this.punitName = punitName;
    }
    public String getPartsNum()
    {
        return partsNum;
    }
    public void setPartsNum(String partsNum)
    {
        this.partsNum = partsNum;
    }
    public String getPartsAmount()
    {
        return partsAmount;
    }
    public void setPartsAmount(String partsAmount)
    {
        this.partsAmount = partsAmount;
    }
    public String getPartsNowCount()
    {
        return partsNowCount;
    }
    public void setPartsNowCount(String partsNowCount)
    {
        this.partsNowCount = partsNowCount;
    }
    public String getInstallItemId()
    {
        return installItemId;
    }
    public void setInstallItemId(String installItemId)
    {
        this.installItemId = installItemId;
    }
    public String getItemId()
    {
        return itemId;
    }
    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }
    public String getItemName()
    {
        return itemName;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    public String getItemCost()
    {
        return itemCost;
    }
    public void setItemCost(String itemCost)
    {
        this.itemCost = itemCost;
    }
    public String getItemMoney()
    {
        return itemMoney;
    }
    public void setItemMoney(String itemMoney)
    {
        this.itemMoney = itemMoney;
    }
    public String getItemRemark()
    {
        return itemRemark;
    }
    public void setItemRemark(String itemRemark)
    {
        this.itemRemark = itemRemark;
    }
    public String getBaseInfo()
    {
        return baseInfo;
    }
    public void setBaseInfo(String baseInfo)
    {
        this.baseInfo = baseInfo;
    }
    public String getConfigParts()
    {
        return configParts;
    }
    public void setConfigParts(String configParts)
    {
        this.configParts = configParts;
    }
    public String getConfigItem()
    {
        return configItem;
    }
    public void setConfigItem(String configItem)
    {
        this.configItem = configItem;
    }
    public String getOutId()
    {
        return outId;
    }
    public void setOutId(String outId)
    {
        this.outId = outId;
    }
    public String getOutCode()
    {
        return outCode;
    }
    public void setOutCode(String outCode)
    {
        this.outCode = outCode;
    }
    public String getOutProjectId()
    {
        return outProjectId;
    }
    public void setOutProjectId(String outProjectId)
    {
        this.outProjectId = outProjectId;
    }
    public String getStoreId()
    {
        return storeId;
    }
    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
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

	public String getFix_statusN() {
		return fix_statusN;
	}

	public void setFix_statusN(String fixStatusN) {
		fix_statusN = fixStatusN;
	}
    
}