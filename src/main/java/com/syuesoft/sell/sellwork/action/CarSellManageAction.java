package com.syuesoft.sell.sellwork.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.model.XsCustomInfo;
import com.syuesoft.sell.sellwork.service.CarSellManageService;
import com.syuesoft.sell.sellwork.vo.CarSellManageVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;


@ParentPackage(value="basePackage")
@Action("carSellManageAction")
public class CarSellManageAction extends BaseAction implements ModelDriven<CarSellManageVo> {

	private Logger logger = Logger.getLogger(this.getClass());
	private CarSellManageVo carSellManageVo = new CarSellManageVo();
	
	public CarSellManageVo getModel() {
		return carSellManageVo;
	}
	@Resource
	private CarSellManageService carSellManageService;
	
	/**
	 * 获取 预订单编号
	 */
	public void getReserveCode(){
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			List rlist = carSellManageService.getReserveCode(carSellManageVo);
			super.writeJson(rlist);
		} catch (Exception e) {
			logger.error("获取预订单编号异常", e);
		}
	}
	
	/**
	 * 获取预订单信息 车辆信息  客户信息  财务信息
	 */
	public void getInforById(){
		try {
				carSellManageVo.setEnterpriseId(getNowEnterpriseId());	
			Json json = carSellManageService.getInforById(carSellManageVo);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error("通过预定单号获取 车辆信息.客户信息 .财务信息异常", e);
		}
	}
	/**
	 * 生成销售单号
	 */
	public void addSellInfor(){
		Message msg = new Message();
		try {
				carSellManageVo.setEnterpriseId(getUserEnterpriseId());	
			Boolean bool = carSellManageService.addSellInfor(carSellManageVo);
			if(bool==null){
				msg.setMsg("数据不完整，操作失败！");
			}else if(bool) {
					msg.setSuccess(true);
					msg.setMsg("新增销售单成功！");
			}else{
				msg.setMsg("数据不完整，操作失败！");
			}
		} catch (Exception e) {
			logger.error("新增销售单失败！", e);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 查询销售单汇总
	 */
	@SuppressWarnings("unchecked")
	public void findSellInfor(){
		try {
				carSellManageVo.setEnterpriseId(getNowEnterpriseId());	
			super.writeJson(carSellManageService.findSellInfor(carSellManageVo));
		} catch (Exception e) {
			logger.error("查询销售单汇总信息异常", e);
		}
		
	}
	/**
	 * 删除销售单汇总
	 */
	public void deleteSellInfor(){
		Message msg = new Message();
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			boolean flag=carSellManageService.deleteSellInfor(carSellManageVo);
			if(flag){
				msg.setSuccess(true);
				msg.setMsg("删除销售单成功！");
			}else{
				msg.setSuccess(false);
				msg.setMsg("销售单已代办/装潢/保险，无法删除！");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("该销售单可能已经生成装潢，代办等信息或已转入结算系统，不可删除！");
			logger.error("删除销售单汇总异常", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 修改销售信息
	 */
	public void updateSellInfor(){
		Message msg = new Message();
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=carSellManageService.updateSellInfor(carSellManageVo);
			if(flag!=null&&flag==true){
				msg.setSuccess(true);				
			}else{
				msg.setMsg("数据不完整，操作失败！");
			}
		} catch (Exception e) {
			logger.error("修改销售信息失败！", e);
			msg.setMsg("修改销售信息失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 审核销售信息
	 */
	public void doAuditSellInfor(){
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carSellManageService.doAuditSellInfor(carSellManageVo));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("审核销售信息异常", e);
		}
		
	}
	/**
	 * 获取PDI检测内容
	 * 
	 */
	public void getPdiCheck(){
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());	
			List rlist = carSellManageService.getPdiCheck(carSellManageVo);
			super.writeJson(rlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 放弃购车
	 */
	public void doAabandon(){
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carSellManageService.doAabandon(carSellManageVo));
		} catch (Exception e) {
			logger.error("放弃购车操作失败!", e);
		}
		
	}
	/**
	 * 获取车辆信息
	 */
	public void getCarInfor(){
		try {
				carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			Json json = carSellManageService.getCarInfor(carSellManageVo);
			super.writeJson(json);
		} catch (Exception e) {
			logger.error("获取车辆信息失败！", e);
		}
	}
	/**
	 * 获取客户信息
	 */
	public void getcustomInfor(){
		try {
				carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carSellManageService.getcustomInfor(carSellManageVo));
		} catch (Exception e) {
			logger.error("获取客户信息失败！", e);
		}
	}
	/**获取指定客户信息*/
	public void getCustomInforByCustomId(){
		try {
			super.writeJson(carSellManageService.getCustomInforByCustomId(carSellManageVo));
		} catch (Exception e) {
			logger.error("获取指定客户信息失败！", e);
		}
	}
	/**
	 * 保存PDI检测内容
	 */
	public void savePDI(){
		Message msg = new Message();
		try {
			carSellManageService.savePDI(carSellManageVo);
			msg.setMsg("保存成功！");
			msg.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存失败！");
			msg.setSuccess(false);
			logger.error("PDI检测内容保存异常", e);
		}
		super.writeJson(msg);
	}
	/**
	 * 获取PDI检测历史记录
	 */
	public void findFactoryPdiCheck(){
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carSellManageService.findFactoryPdiCheck(carSellManageVo));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PDI检测内容保存异常", e);
		}
	}
	/**
	 * 转结算
	 */
	public void doCash(){
		Message msg = new Message();
		try {
				carSellManageVo.setEnterpriseId(getNowEnterpriseId());	
			Boolean flag=carSellManageService.doCash(carSellManageVo);
			if(flag!=null&&flag==true){
				msg.setSuccess(true);
				msg.setMsg("转结算成功！");				
			}else{
				msg.setMsg("转结算失败！");
			}
		} catch (Exception e) {
			logger.error("销售单转结算失败！", e);
			msg.setSuccess(false);
			msg.setMsg("转结算失败！");
		}
		super.writeJson(msg);
	}
	/**
	 * 添加客户信息
	 */
	public void addOutCustom(){
		XsCustomInfo xsCustomInfo = new XsCustomInfo();
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			String xsCustomCode = carSellManageService.addOutCustom(carSellManageVo);
			xsCustomInfo.setXsCustomCode(xsCustomCode);
		} catch (Exception e) {
			logger.error("信息保存异常", e);
		}
		super.writeJson(xsCustomInfo);
	}
	/**
	 * 转售后
	 */
	public void doSellAfter(){
		Message msg = new Message();
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			int yorn = carSellManageService.doSellAfter(carSellManageVo);
			if(yorn==0){
				msg.setMsg("缺少回访进度，无法转售后！");
			}else if(yorn==1){
				msg.setMsg("已转入售后！");
				msg.setSuccess(true);
			}else if(yorn==2){
				msg.setMsg("该客户已转入售后，不可重复转入！");
			}
		} catch (Exception e) {
			logger.error("转售后失败！", e);
			msg.setMsg("转入售后失败！");
		}
		super.writeJson(msg);
	}
	/**
	 *  pdi检测中formater中通过评价编号获取评价结果
	 */
	public void getCheckResault(){
		try {
			super.writeJson(carSellManageService.getCheckResault(carSellManageVo.getChild_Id()!=null && !carSellManageVo.getChild_Id().equals("") ? Integer.parseInt(carSellManageVo.getChild_Id()) : 0));
		} catch (Exception e) {
			logger.error("信息保存异常", e);
		}
		
	}
	
	// 销售单查询模块，查询父菜单信息功能
	public void getFatherInfor(){
		try {
			
				carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carSellManageService.queryFatherInfor(carSellManageVo));
		} catch (Exception e) {
			logger.error("查询销售单信息异常", e);
		}
	}
	// 销售单查询模块，查询子菜单信息功能
	public void getChildList(){
		try {
			carSellManageVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carSellManageService.findChildList(carSellManageVo));
		} catch (Exception e) {
			logger.error("查询销售单信息异常", e);
		}
	}
	/**
	 * 查找销售单相关数据
	 * */
	public void findContact(){
		try {
			super.writeJson(carSellManageService.findContact(carSellManageVo));
		} catch (Exception e) {
			logger.error("查找销售单相关数据失败！", e);
		}
	}
	/**
	 * 判断销售单是否放弃购车
	 * */
	public void isBackCar(){
		Message msg = new Message();
		try {
			Boolean flag = carSellManageService.isBackCar(carSellManageVo);
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断销售单是否放弃购车失败！", e);
			msg.setMsg("判断销售单是否放弃购车失败！");
		}
		super.writeJson(msg);
	}
}
