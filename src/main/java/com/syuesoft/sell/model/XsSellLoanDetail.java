package com.syuesoft.sell.model;

/**
 * XsSellLoanDetail entity. @author MyEclipse Persistence Tools
 */

public class XsSellLoanDetail implements java.io.Serializable {

	// Fields

	private Integer loanDetailId;
	private XsSellLoan xsSellLoan;
	private Integer num;
	private String repaymentDate;
	private Double remainder;
	private Double shouldAmount;
	private Double shouldInterest;
	private String repaymentRate;
	private Double shouldReturnAmount;
	private String returnamountFlag;
	private String returnDate;

	// Constructors

	/** default constructor */
	public XsSellLoanDetail() {
	}

	/** full constructor */
	public XsSellLoanDetail(XsSellLoan xsSellLoan, Integer num,
			String repaymentDate, Double remainder, Double shouldAmount,
			Double shouldInterest, String repaymentRate,
			Double shouldReturnAmount, String returnamountFlag,
			String returnDate) {
		this.xsSellLoan = xsSellLoan;
		this.num = num;
		this.repaymentDate = repaymentDate;
		this.remainder = remainder;
		this.shouldAmount = shouldAmount;
		this.shouldInterest = shouldInterest;
		this.repaymentRate = repaymentRate;
		this.shouldReturnAmount = shouldReturnAmount;
		this.returnamountFlag = returnamountFlag;
		this.returnDate = returnDate;
	}

	// Property accessors

	public Integer getLoanDetailId() {
		return this.loanDetailId;
	}

	public void setLoanDetailId(Integer loanDetailId) {
		this.loanDetailId = loanDetailId;
	}

	public XsSellLoan getXsSellLoan() {
		return this.xsSellLoan;
	}

	public void setXsSellLoan(XsSellLoan xsSellLoan) {
		this.xsSellLoan = xsSellLoan;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getRepaymentDate() {
		return this.repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Double getRemainder() {
		return this.remainder;
	}

	public void setRemainder(Double remainder) {
		this.remainder = remainder;
	}

	public Double getShouldAmount() {
		return this.shouldAmount;
	}

	public void setShouldAmount(Double shouldAmount) {
		this.shouldAmount = shouldAmount;
	}

	public Double getShouldInterest() {
		return this.shouldInterest;
	}

	public void setShouldInterest(Double shouldInterest) {
		this.shouldInterest = shouldInterest;
	}

	public String getRepaymentRate() {
		return this.repaymentRate;
	}

	public void setRepaymentRate(String repaymentRate) {
		this.repaymentRate = repaymentRate;
	}

	public Double getShouldReturnAmount() {
		return this.shouldReturnAmount;
	}

	public void setShouldReturnAmount(Double shouldReturnAmount) {
		this.shouldReturnAmount = shouldReturnAmount;
	}

	public String getReturnamountFlag() {
		return this.returnamountFlag;
	}

	public void setReturnamountFlag(String returnamountFlag) {
		this.returnamountFlag = returnamountFlag;
	}

	public String getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

}