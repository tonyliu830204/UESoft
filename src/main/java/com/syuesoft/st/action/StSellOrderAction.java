package com.syuesoft.st.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.contstants.Contstants;
import com.syuesoft.contstants.Contstants.INOUTDEPOT;
import com.syuesoft.contstants.Contstants.PARAMETER_SET;
import com.syuesoft.model.BasUsers;
import com.syuesoft.st.service.StSellOrderService;
import com.syuesoft.st.vo.StSellOrderVo;
import com.syuesoft.util.Msg;
/**
 * 销售单管理Action
 * @author SuMing
 */
@SuppressWarnings("serial")
@ParentPackage(value="basePackage")
@Action("StSellOrderAction")
public class StSellOrderAction extends BaseAction implements
		ModelDriven<StSellOrderVo> {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StSellOrderVo stSellOrderVo = new StSellOrderVo();
	@Autowired StSellOrderService stSellOrderService;
	
	/**
	 * 销售单汇总信息    综合查询
	 */
	public void searchByCondition() {
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.searchByCondition(stSellOrderVo));
		} catch (Exception e) {
			logger.error("销售单汇总信息    综合查询     异常", e);
		}
	}

	/**
	 * 销售汇总单对应相关销售明细信息加载
	 */
	public void loadStSellOrderItemBySellmmId() {
		try {
			super.writeJson(stSellOrderService.loadStSellOrderItemBySellmmId(stSellOrderVo));
		} catch (Exception e) {
			logger.error("销售汇总单对应相关销售明细信息加载    异常", e);
		}
	}
	
	/**
	 * 车辆牌照信息     预加载
	 */
	public void loadCarLicense() {
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.loadCarLicense(stSellOrderVo));
		} catch (Exception e) {
			logger.error("车辆牌照信息     预加载    异常", e);
		}
	}
	
	/**
	 * 车辆牌照信息     综合查询
	 */
	public void searchCarLicenseByCondition() {
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.searchCarLicenseByCondition(stSellOrderVo));
		} catch (Exception e) {
			logger.error("车辆牌照信息     综合查询    异常", e);
		}
	}
	
	/**
	 * 客户信息     预加载
	 */
	public void loadBasCustom() {
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.loadBasCustom(stSellOrderVo));
		} catch (Exception e) {
			logger.error("客户信息     预加载    异常", e);
		}
	}
	
	/**
	 * 客户信息      综合查询
	 */
	public void searchBasCustomByCondition() {
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.searchBasCustomByCondition(stSellOrderVo));
		} catch (Exception e) {
			logger.error("客户信息      综合查询   异常", e);
		}
	}
	
	/**
	 * 加载默认选择价格
	 */
	public void loadDefaultPriceType(){
		try {
			super.writeJson(stSellOrderService.findDefaultProperties(PARAMETER_SET.INOUTDEPOT,INOUTDEPOT.SELLBASEPRICE,getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("客户信息      综合查询   异常", e);
		}
	}
	
	/**
	 *退料员查询 
	 * */
	public void findPartsStorehousePerson(){
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.findPartsStorehousePerson(stSellOrderVo));
		} catch (Exception e) {
			logger.error("退料员查询失败！", e);
		}
	}
	/**
	 * 领料员查询
	 */
	public void loadPickingMember() {
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.loadPickingMember(stSellOrderVo));
		} catch (Exception e) {
			logger.error("领料员查询失败！", e);
		}
	}
	/**
	 * 采购员查询
	 * */
	public void findPartsStockPerson(){
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.findPartsStockPerson(stSellOrderVo));
		} catch (Exception e) {
			logger.error("采购员查询失败！", e);
		}
	}
	
	/**
	 * 领料员信息          综合查询
	 */
	public void searchPickingMemberByCondition() {
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.searchPickingMemberByCondition(stSellOrderVo));
		} catch (Exception e) {
			logger.error("领料员信息          综合查询    异常", e);
		}
	}
	
	/**
	 * 销售单模块    销售分类信息         加载
	 */
	public void loadBasPartsSell(){
		try {
			 stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			 super.writeJson(stSellOrderService.loadBasPartsSell(stSellOrderVo));
		} catch (Exception e) {
			logger.error("销售单模块    销售分类信息       加载    异常", e);
		}
	}
	
	/**
	 * 销售单管理模块    配件信息加载   查询
	 */
	public void loadFrtParts(){
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stSellOrderService.loadFrtParts(stSellOrderVo));
		} catch (Exception e) {
			logger.error("销售单管理模块    配件信息加载   查询   异常", e);
		}
	}
	
	/**
	 * 销售单管理      销售单汇总       添加
	 */
	public void addStSellOrder() {
		try {
			BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
			if(user!=null&&!user.equals(""))
				stSellOrderVo.setSellManager(user.getBasStuff().getStfId()+"");
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			stSellOrderService.add(stSellOrderVo, stSellOrderService.jsonChangeDetailList(stSellOrderVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("销售单管理      销售单汇总       添加   异常", e);
		}
	}
	
	/**
	 * 销售单管理      销售单汇总      删除
	 */
	public void deleteStSellOrder() {
		Msg msg = new Msg();
		try {
			stSellOrderService.delete(stSellOrderVo);
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("销售单管理      销售单汇总      删除    异常", e);
		}
	}
	
	/**
	 * 销售单管理      销售单汇总      修改
	 */
	public void updateStSellOrder(){
		try {
			stSellOrderVo.setEnterpriseId(getNowEnterpriseId());
			stSellOrderService.update(stSellOrderVo,stSellOrderService.jsonChangeDetailList(stSellOrderVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("销售单管理      销售单汇总      修改    异常", e);
		}
	}
	
	/**
	 * 验证该销售单是否可删除修改操作
	 */
	public void doDeleteOrUpdate(){
		Msg msg = new Msg();
		try {
			boolean isno =stSellOrderService.doDeleteOrUpdate(stSellOrderVo);
			if(isno)
				msg.setSuccess(true);
			else
				msg.setSuccess(false);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("验证该销售单是否可删除修改操作    异常", e);
		}
	}
	
	/**
	 * 销售转结算
	 */
	public void modifyPreclr(){
		Msg msg = new Msg();
		try {
			stSellOrderService.modifyPreclr(stSellOrderVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("销售转结算    异常", e);
		}finally{
			super.writeJson(msg);
		}
	}
	
	public StSellOrderVo getModel() {
		return stSellOrderVo;
	}
	
}
