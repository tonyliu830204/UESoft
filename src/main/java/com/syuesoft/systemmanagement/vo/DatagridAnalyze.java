package com.syuesoft.systemmanagement.vo;

import java.util.List;

public class DatagridAnalyze {

	private long total;
	
	private List footer;
	
	private List rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}
	
}
