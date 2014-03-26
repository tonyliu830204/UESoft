package com.syuesoft.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.syuesoft.contstants.Contstants;
import com.syuesoft.model.BasUsers;

public class SystemUser {
	
	public static BasUsers getUser(){
		 // 当前用户
	    HttpServletRequest request = ServletActionContext.getRequest();
	    HttpSession session = request.getSession();
	    BasUsers user = (BasUsers) session.getAttribute(Contstants.CUSTOMER);
		return user;
	}
}
