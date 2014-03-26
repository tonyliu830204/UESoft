package com.syuesoft.systemmanagement.vo;
public class AccesssoriesInventoryVo {
	
	private int page;
	private int rows;
	private String sort;
	private String order;//  
	private String inserted;
	private String deleted;
	private String updated;
	
	private String stinvm_Id;  //仓库盘点编号
	private String stf_Id;      //员工编号
	private String stf_Name;      //员工编号
	private String stinvm_Time; //盘点日期
	private String stinvm_Time2; //盘点日期
	private String syinvm_Sum_Count;  //盘点总数 
	private String stinvm_Sum_Amount;  //含税金额
	private String stinvm_Sum_Cost;    //未税金额
	private String stinvm_State;    //盘点状态  审核状态
	private String stinvm_StateName;    //盘点状态  审核状态
	private String stinvm_Remark;  //备注
	
	private String index_Id;       //盘点明细编号
	private String parts_Id;       //配件编码
	private String stinvd_Count;	  //个数	
	private String stinvd_Price;	   //未税价
	private String stinvd_Price1;      //含税价
	private String stinvd_Cost;     //未税成本
	private String stinvd_Cost1;     //含税成本
	private String store_Id;		//仓库编号
	
	private String parts_Name;      //配件名称
	
	private String parts_Sell_Price; //销售价格
	private String parts_Need_Point; //库存量
	private String parts_Library;	 //库位
	private String fit_Ptype;        //使用车型
	private String ptype_Name;		 //类型名称
	private String pbrd_Name;		 //品牌名称
	private String punit_Name;       //单位
	private String store_Name;       //仓别
	private String date;
	private String from;
	private String enterpriseId;

	public String getEnterpriseId() {
	   return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
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
    public String getSort()
    {
        return sort;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }
    public String getOrder()
    {
        return order;
    }
    public void setOrder(String order)
    {
        this.order = order;
    }
    public String getInserted()
    {
        return inserted;
    }
    public void setInserted(String inserted)
    {
        this.inserted = inserted;
    }
    public String getDeleted()
    {
        return deleted;
    }
    public void setDeleted(String deleted)
    {
        this.deleted = deleted;
    }
    public String getUpdated()
    {
        return updated;
    }
    public void setUpdated(String updated)
    {
        this.updated = updated;
    }
    public String getStinvm_Id()
    {
        return stinvm_Id;
    }
    public void setStinvm_Id(String stinvmId)
    {
        stinvm_Id = stinvmId;
    }
    public String getStf_Id()
    {
        return stf_Id;
    }
    public void setStf_Id(String stfId)
    {
        stf_Id = stfId;
    }
    public String getStinvm_Time()
    {
        return stinvm_Time;
    }
    public void setStinvm_Time(String stinvmTime)
    {
        stinvm_Time = stinvmTime;
    }
    public String getSyinvm_Sum_Count()
    {
        return syinvm_Sum_Count;
    }
    public void setSyinvm_Sum_Count(String syinvmSumCount)
    {
        syinvm_Sum_Count = syinvmSumCount;
    }
    public String getStinvm_Sum_Amount()
    {
        return stinvm_Sum_Amount;
    }
    public void setStinvm_Sum_Amount(String stinvmSumAmount)
    {
        stinvm_Sum_Amount = stinvmSumAmount;
    }
    public String getStinvm_Sum_Cost()
    {
        return stinvm_Sum_Cost;
    }
    public void setStinvm_Sum_Cost(String stinvmSumCost)
    {
        stinvm_Sum_Cost = stinvmSumCost;
    }
    public String getStinvm_State()
    {
        return stinvm_State;
    }
    public void setStinvm_State(String stinvmState)
    {
        stinvm_State = stinvmState;
    }
    public String getStinvm_Remark()
    {
        return stinvm_Remark;
    }
    public void setStinvm_Remark(String stinvmRemark)
    {
        stinvm_Remark = stinvmRemark;
    }
    public String getIndex_Id()
    {
        return index_Id;
    }
    public void setIndex_Id(String indexId)
    {
        index_Id = indexId;
    }
    public String getParts_Id()
    {
        return parts_Id;
    }
    public void setParts_Id(String partsId)
    {
        parts_Id = partsId;
    }
    public String getStinvd_Count()
    {
        return stinvd_Count;
    }
    public void setStinvd_Count(String stinvdCount)
    {
        stinvd_Count = stinvdCount;
    }
    public String getStinvd_Price()
    {
        return stinvd_Price;
    }
    public void setStinvd_Price(String stinvdPrice)
    {
        stinvd_Price = stinvdPrice;
    }
    public String getStinvd_Price1()
    {
        return stinvd_Price1;
    }
    public void setStinvd_Price1(String stinvdPrice1)
    {
        stinvd_Price1 = stinvdPrice1;
    }
    public String getStinvd_Cost()
    {
        return stinvd_Cost;
    }
    public void setStinvd_Cost(String stinvdCost)
    {
        stinvd_Cost = stinvdCost;
    }
    public String getStinvd_Cost1()
    {
        return stinvd_Cost1;
    }
    public void setStinvd_Cost1(String stinvdCost1)
    {
        stinvd_Cost1 = stinvdCost1;
    }
    public String getStore_Id()
    {
        return store_Id;
    }
    public void setStore_Id(String storeId)
    {
        store_Id = storeId;
    }
    public String getParts_Name()
    {
        return parts_Name;
    }
    public void setParts_Name(String partsName)
    {
        parts_Name = partsName;
    }
    public String getParts_Sell_Price()
    {
        return parts_Sell_Price;
    }
    public void setParts_Sell_Price(String partsSellPrice)
    {
        parts_Sell_Price = partsSellPrice;
    }
    public String getParts_Need_Point()
    {
        return parts_Need_Point;
    }
    public void setParts_Need_Point(String partsNeedPoint)
    {
        parts_Need_Point = partsNeedPoint;
    }
    public String getParts_Library()
    {
        return parts_Library;
    }
    public void setParts_Library(String partsLibrary)
    {
        parts_Library = partsLibrary;
    }
    public String getFit_Ptype()
    {
        return fit_Ptype;
    }
    public void setFit_Ptype(String fitPtype)
    {
        fit_Ptype = fitPtype;
    }
    public String getPtype_Name()
    {
        return ptype_Name;
    }
    public void setPtype_Name(String ptypeName)
    {
        ptype_Name = ptypeName;
    }
    public String getPbrd_Name()
    {
        return pbrd_Name;
    }
    public void setPbrd_Name(String pbrdName)
    {
        pbrd_Name = pbrdName;
    }
    public String getPunit_Name()
    {
        return punit_Name;
    }
    public void setPunit_Name(String punitName)
    {
        punit_Name = punitName;
    }
    public String getStore_Name()
    {
        return store_Name;
    }
    public void setStore_Name(String storeName)
    {
        store_Name = storeName;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public String getFrom()
    {
        return from;
    }
    public void setFrom(String from)
    {
        this.from = from;
    }
    public String getStinvm_Time2()
    {
        return stinvm_Time2;
    }
    public void setStinvm_Time2(String stinvmTime2)
    {
        stinvm_Time2 = stinvmTime2;
    }
    public String getStf_Name()
    {
        return stf_Name;
    }
    public void setStf_Name(String stfName)
    {
        stf_Name = stfName;
    }
    public String getStinvm_StateName()
    {
        return stinvm_StateName;
    }
    public void setStinvm_StateName(String stinvmStateName)
    {
        stinvm_StateName = stinvmStateName;
    }
}