package com.syuesoft.model;

/**
 * BasJudgmentWord entity. @author MyEclipse Persistence Tools
 */

public class BasJudgmentWord extends BaseBean{

	// Fields

	private Integer wordId;
	private String wordName;
	private String memo;

	// Constructors

	/** default constructor */
	public BasJudgmentWord() {
	}

	/** full constructor */
	public BasJudgmentWord(String wordName, String memo) {
		this.wordName = wordName;
		this.memo = memo;
	}

	// Property accessors

	public Integer getWordId() {
		return this.wordId;
	}

	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}

	public String getWordName() {
		return this.wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}