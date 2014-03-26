package com.syuesoft.util;

import java.util.List;

public class ComboTreeJson {
	
	private String id;
	
	private String text;
	
	private String state;
    
	private List<ComboTreeJson> children;
	
	public ComboTreeJson() {

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<ComboTreeJson> getChildren() {
		return children;
	}

	public void setChildren(List<ComboTreeJson> children) {
		this.children = children;
	}
}