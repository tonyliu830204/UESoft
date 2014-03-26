package com.syuesoft.model;

import java.util.Date;

public class LoginError extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;                              //序号
    private String userName;                      //系统登录帐号
    private String date;                            //最后登录日期
    private int errorNumber;                      //今天登录失败次数
    private Long lock;                            //是否已经锁定
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}
	public Long getLock() {
		return lock;
	}
	public void setLock(Long lock) {
		this.lock = lock;
	}
}