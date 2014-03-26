package com.syuesoft.model;

/**
 * FrtCarTree entity. @author MyEclipse Persistence Tools
 */

public class FrtCarTree extends BaseBean {

	// Fields

	private String id;
	private String text;
	private String url;
	private String pid;

	// Constructors

	/** default constructor */
	public FrtCarTree() {
	}

	/** minimal constructor */
	public FrtCarTree(String id) {
		this.id = id;
	}

	/** full constructor */
	public FrtCarTree(String id, String text, String url,
			String pid) {
		this.id = id;
		this.text = text;
		this.url = url;
		this.pid = pid;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}