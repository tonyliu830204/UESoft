package com.syuesoft.sell.sellwork.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.fbk.vo.ComboxVo;
import com.syuesoft.model.BasStuff;
import com.syuesoft.model.BasUsers;
import com.syuesoft.sell.sellwork.service.BackRegisterService;
import com.syuesoft.sell.sellwork.vo.BackRegisterVo;
import com.syuesoft.sell.sellwork.vo.XsSellLoanVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
/**
 * 购车贷款Action
 * */
@ParentPackage(value="basePackage")
@Action("xsSellLoanAction")
public class XsSellLoanAction extends BaseAction implements ModelDriven<XsSellLoanVo> {

	private Logger log = Logger.getLogger(this.getClass());
	private XsSellLoanVo xsSellLoanVo = new XsSellLoanVo();
	
	public XsSellLoanVo getModel() {
		return xsSellLoanVo;
	}
	
}
