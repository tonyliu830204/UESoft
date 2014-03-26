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
import com.syuesoft.st.service.StPurOrderService;
import com.syuesoft.st.vo.StPurOrderVo;
import com.syuesoft.util.Msg;
/**
 * 采购单Action
 * @author SuMing
 */
@SuppressWarnings("serial")
@ParentPackage(value="basePackage")@Action("StPurOrderAction")
public class StPurOrderAction extends BaseAction implements
		ModelDriven<StPurOrderVo>{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StPurOrderVo stPurOrderVo = new StPurOrderVo();
	@Autowired  StPurOrderService stPurOrderService;
	
	/**
	 * 采购单汇总     综合查询/预加载
	 */
	public void searchByCondition() {
		try {
			stPurOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPurOrderService.searchByCondition(stPurOrderVo));
		} catch (Exception e) {
			logger.error("采购单模块     采购单汇总综合查询        异常", e);
		}
	}
	
	/**
	 * 加载默认票据类型
	 */
	public void loadDefaultBillType(){
		try {
			super.writeJson(stPurOrderService.findDefaultProperties(PARAMETER_SET.INOUTDEPOT,INOUTDEPOT.INDEFRECEIPTTYPE,getNowEnterpriseId()));
		} catch (Exception e) {
			logger.error("默认票据类型  加载    异常", e);
		}
	}
	
	/**
	 * 通过采购单预加载采购单明细数据
	 * 
	 */
	public void searchStOrderItemByOrderId() {
		try {
			super.writeJson(super.listConvertJson(stPurOrderService.searchStOrderItemByOrderId(stPurOrderVo.getOrderId())));
		} catch (Exception e) {
			logger.error("击库存量一条数据时 加载库存量明细单对应数据      异常", e);
		}
	}
	
	/**
	 * 选择配件    预加载
	 * 
	 */
	public void loadSelectParts(){
		try {
			stPurOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPurOrderService.searchByPartsIdAndPartsName(stPurOrderVo));
		} catch (Exception e) {
			logger.error("选择配件    预加载 异常", e);
		}
	}
	
	/**
	 * 采购单汇总        添加
	 * 
	 */
	public void addStPurOrder() {
		BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
		if(user!=null&&!user.equals(""))
			stPurOrderVo.setManager(user.getBasStuff().getStfId()+"");
		try {
			stPurOrderVo.setEnterpriseId(getNowEnterpriseId());
			stPurOrderService.add(stPurOrderVo, stPurOrderService.jsonChangeDetailList(stPurOrderVo));
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("采购单汇总        添加       异常", e);
		}
	}
	/**
	 * 采购单汇总        导入
	 * 
	 */
	public void addStPurOrderImport() {
		BasUsers user=(BasUsers) getSession().getAttribute(Contstants.CUSTOMER);
		if(user!=null&&!user.equals(""))
			stPurOrderVo.setManager(user.getBasStuff().getStfId()+"");
		try {
			stPurOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPurOrderService.addItem(stPurOrderVo, stPurOrderService.jsonChangeDetailList(stPurOrderVo)));
		} catch (Exception e) {
			logger.error("采购单汇总        导入       异常", e);
		}
	}
	/**
	 * 采购单汇总        删除
	 * 
	 */
	public void deleteStPurOrder() {
		try {
			stPurOrderService.delete(stPurOrderVo);
			Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("采购单汇总        删除      异常", e);
		}
	}

	/**
	 * 采购单汇总       修改
	 * 
	 */
	public void updateStPurOrder() {
		try {
			stPurOrderVo.setEnterpriseId(getNowEnterpriseId());
		    stPurOrderService.update(stPurOrderVo, stPurOrderService.jsonChangeDetailList(stPurOrderVo));
		    Msg msg = new Msg();
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("采购单汇总       修改       异常", e);
		}
	}
	
	/**
	 * 修改采购单审核状态
	 * 
	 */
	public void updateExamine() {
		Msg msg = new Msg();
		try {
			stPurOrderService.updateExamine(stPurOrderVo.getOrderId());
			msg.setSuccess(true);
			super.writeJson(msg);
		} catch (Exception e) {
			logger.error("修改采购单审核状态       异常", e);
		}
	}
	
	/**
	 * 领料员查询
	 */
	public void loadPurOrderMember() {
		try {
			stPurOrderVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(stPurOrderService.loadPurOrderMember(stPurOrderVo));
		} catch (Exception e) {
			logger.error("领料员查询失败！", e);
		}
	}
	
	public StPurOrderVo getModel() {
		return stPurOrderVo;
	}
}
