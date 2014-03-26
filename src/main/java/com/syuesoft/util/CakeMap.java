package com.syuesoft.util;

import java.awt.Color;

public class CakeMap {
	private String cakeMapLabelName;//饼图名称
	private Double cakeMapLabelValue;//饼图大小
	private Color cakeMapLabelColor;//饼图颜色
	public CakeMap() {
		// TODO Auto-generated constructor stub
	}
	public CakeMap(String cakeMapLabelName,Double cakeMapLabelValue,Color cakeMapLabelColor){
		this.cakeMapLabelName=cakeMapLabelName;
		this.cakeMapLabelValue=cakeMapLabelValue;
		this.cakeMapLabelColor=cakeMapLabelColor;
	}
	public String getCakeMapLabelName() {
		return cakeMapLabelName;
	}
	public void setCakeMapLabelName(String cakeMapLabelName) {
		this.cakeMapLabelName = cakeMapLabelName;
	}
	public Double getCakeMapLabelValue() {
		return cakeMapLabelValue;
	}
	public void setCakeMapLabelValue(Double cakeMapLabelValue) {
		this.cakeMapLabelValue = cakeMapLabelValue;
	}
	public Color getCakeMapLabelColor() {
		return cakeMapLabelColor;
	}
	public void setCakeMapLabelColor(Color cakeMapLabelColor) {
		this.cakeMapLabelColor = cakeMapLabelColor;
	}

}
