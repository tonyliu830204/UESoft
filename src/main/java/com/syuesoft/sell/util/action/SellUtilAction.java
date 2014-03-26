package com.syuesoft.sell.util.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.sell.util.SellUtilVo;
import com.syuesoft.sell.util.service.SellUtilService;
import com.syuesoft.util.Msg;
@ParentPackage(value="basePackage")@Action("sellUtilAction")
public class SellUtilAction extends BaseAction implements ModelDriven<SellUtilVo>{
	@Resource
	private SellUtilService sellUtilService;
	private SellUtilVo suVo=new SellUtilVo();
	
	/**
	 * 获取销售员工信息
	 */
	public void findStfName(){
		try {
			List list = sellUtilService.findStfName(suVo.getQ());
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取客户信息
	 */
	public void findCustom(){
		try {
			List list = sellUtilService.findCustom(suVo.getQ());
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取客户预定信息，车辆信息
	 */
	public void findCustominfo(){
		try {
			List list = sellUtilService.findCustominfo();
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取销售车辆型号
	 */
	public void findCarModel(){
		try {
			String car_Brand_Id = ServletActionContext.getRequest().getParameter("car_Brand_Id");
			if(car_Brand_Id!=null && !car_Brand_Id.equals("")){
				
			}
			List list = sellUtilService.findCarModel();
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取分销商
	 */
	public void findBussness(){
		try {
			List list = sellUtilService.findBussness();
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取车辆编号
	 */
	public void findCarId(){
		try {
			List list = sellUtilService.findCarId();
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取登录用户
	 */
	public void findUsers(){
		try {
			List list = sellUtilService.findUsers(suVo.getQ(),getNowEnterpriseId().toString());
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取银行名称
	 */
	public void findBank(){
		try {
			List list = sellUtilService.findBank();
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取银行名称
	 */
	public void getKeys(){
		try {
			List list = sellUtilService.getKey(suVo.getKey());
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取当前系统时间和默认资料显示时间
	 * */
	public void initDateAndSearch(){
		Msg msg=new Msg();
		try {
			msg=sellUtilService.initDateAndSearch(getNowEnterpriseId());
		} catch (Exception e) {
			msg.setMsg("获取时间失败！");
			msg.setSuccess(false);
		}
		super.writeJson(msg);
	}
	
	public SellUtilVo getModel() {
		return suVo;
	}
	
}
