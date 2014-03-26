package com.syuesoft.util;

import java.awt.Color;
import java.io.Serializable;

public class SnapMap implements Serializable{
	private Double snapYData;//数据轴数据（Y轴）
	private String snapXData;//分类轴数据（X轴）
	private String snapLineName;//折线名称
	private Color snapLineColor;//折线颜色
	
	public SnapMap(){
		
	}
	public SnapMap(Double snapYData,String snapXData,String snapLineName,Color snapLineColor){
		this.snapYData=snapYData;
		this.snapXData=snapXData;
		this.snapLineName=snapLineName;
		this.snapLineColor=snapLineColor;
	}
	public Double getSnapYData() {
		return snapYData;
	}
	public void setSnapYData(Double snapYData) {
		this.snapYData = snapYData;
	}
	public String getSnapXData() {
		return snapXData;
	}
	public void setSnapXData(String snapXData) {
		this.snapXData = snapXData;
	}
	public String getSnapLineName() {
		return snapLineName;
	}
	public void setSnapLineName(String snapLineName) {
		this.snapLineName = snapLineName;
	}
	public Color getSnapLineColor() {
		return snapLineColor;
	}
	public void setSnapLineColor(Color snapLineColor) {
		this.snapLineColor = snapLineColor;
	}
	
	
}
