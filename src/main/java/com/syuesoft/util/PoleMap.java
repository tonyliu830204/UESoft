package com.syuesoft.util;

import java.awt.Color;

public class PoleMap {
	private Double poleYData;//数据轴数据（Y轴）
	private String poleBarName;//柱形分类名称
	private String poleXData;//分类轴数据（X轴）
	private Color poleBarColor;//柱形分类颜色
	public String getPoleBarName() {
		return poleBarName;
	}
	public void setPoleBarName(String poleBarName) {
		this.poleBarName = poleBarName;
	}
	public String getPoleXData() {
		return poleXData;
	}
	public void setPoleXData(String poleXData) {
		this.poleXData = poleXData;
	}
	public Double getPoleYData() {
		return poleYData;
	}
	public void setPoleYData(Double poleYData) {
		this.poleYData = poleYData;
	}
	public Color getPoleBarColor() {
		return poleBarColor;
	}
	public void setPoleBarColor(Color poleBarColor) {
		this.poleBarColor = poleBarColor;
	}
	 
}
