package com.syuesoft.frt.vo;

import com.syuesoft.base.vo.BaseBeanVo;


public class CostVo extends BaseBeanVo{

	private Short costId;
	private String costItem;
	private Double costAmount;
	private String costExplain;
	private String receptionId;
	
	private String tagId;
	
	
	
	public String getTagId() {
		return tagId;
	}

    public void setTagId(String tagId)
    {
        this.tagId = tagId;
    }

	public CostVo() {
		
	}
	
	public CostVo(Short costId, String costItem, Double costAmount, String costExplain, String receptionId) {
		this.costId = costId;
		this.costItem = costItem;
		this.costAmount = costAmount;
		this.costExplain = costExplain;
		this.receptionId = receptionId;
	}
	public Short getCostId() {
		return costId;
	}
	public void setCostId(Short costId) {
		this.costId = costId;
	}
	public void setCostId(String costId) {
		if(costId!=null&&costId.length()>0)
		this.costId =Short.parseShort(costId);
	}
	public String getCostItem() {
		return costItem;
	}
	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}
	public Double getCostAmount() {
		return costAmount;
	}
	public void setCostAmount(Double costAmount) {
		this.costAmount = costAmount;
	}
	public String getCostExplain() {
		return costExplain;
	}
	public void setCostExplain(String costExplain) {
		this.costExplain = costExplain;
	}
	public String getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(String receptionId) {
		this.receptionId = receptionId;
	}
	
}
