package com.syuesoft.util;

import java.util.List;

public class ComboBoxJson {
	private String id;
	
	private String text;
	
	private String dataKey;
	
	private String dataValue;
	
	public ComboBoxJson() {

	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the dataKey
	 */
	public String getDataKey() {
		return dataKey;
	}

	/**
	 * @param dataKey the dataKey to set
	 */
	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	/**
	 * @return the dataValue
	 */
	public String getDataValue() {
		return dataValue;
	}

	/**
	 * @param dataValue the dataValue to set
	 */
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
}