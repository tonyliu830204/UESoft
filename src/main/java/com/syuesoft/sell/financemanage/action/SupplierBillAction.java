package com.syuesoft.sell.financemanage.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.financemanage.service.SupplierBillService;
import com.syuesoft.sell.financemanage.vo.SupplierBillVo;
import com.syuesoft.util.Message;
@ParentPackage(value="basePackage")
@Action("supplierBillAction")
public class SupplierBillAction extends BaseAction implements ModelDriven<SupplierBillVo>{
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SupplierBillService supplierBillService;
	private SupplierBillVo supplierBillVo = new SupplierBillVo();
	
	public SupplierBillVo getModel() {
		return supplierBillVo;
	}
	
	/**
	 * 获取入库信息
	 */
	public void getInterStore(){
		try {
			supplierBillVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(supplierBillService.getInterStore(supplierBillVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获应付款查询父节点记录信息
	 */
	public void getDuePrentInfor(){
		try {
			supplierBillVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(supplierBillService.getDuePrentInfor(supplierBillVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获应付款查询子节点记录信息
	 */
	public void getDueChildInfor(){
		try {
			super.writeJson(supplierBillService.getDueChildInfor(supplierBillVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取对账单汇总信息
	 */
	public void getSupplierBillInfor(){
		try {
				supplierBillVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(supplierBillService.getSupplierBillInfor(supplierBillVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取对账单明细 父节点信息
	 */
	public void getSupplierBillDetailPrentInfor(){
		try {
			super.writeJson(supplierBillService.getSupplierBillDetailPrentInfor(supplierBillVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取对账单明细 子节点信息
	 */
	public void getSupplierBillDetailChildInfor(){
		try {
			super.writeJson(supplierBillService.getSupplierBillDetailChildInfor(supplierBillVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加对账信息
	 */
	public void addSupplierBillInfor(){
		Message msg = new Message();
		try {
				supplierBillVo.setEnterpriseId(getUserEnterpriseId());
			supplierBillService.addSupplierBillInfor(supplierBillVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 删除对款单汇总记录
	 */
	public void doDeleteInfor(){
		Message msg = new Message();
		try {
			supplierBillService.doDeleteInfor(supplierBillVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	/**
	 * 修改对款单汇总记录
	 */
	public void doEditInfor(){
		Message msg = new Message();
		try {
			supplierBillService.doEditInfor(supplierBillVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}

	/**
	 * 获取分销商对账信息
	 */
	public void getDisBill(){
		try {
				supplierBillVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(supplierBillService.getDisBill(supplierBillVo));
		} catch (Exception e) {
			logger.error("查询分销商对账汇总信息失败！", e);
		}
	}
	/**
	 * 添加分销商对账信息
	 */
	public void addDistributorBill(){
		Message msg = new Message();
		try {
				supplierBillVo.setEnterpriseId(getUserEnterpriseId());
			supplierBillService.addDistributorBill(supplierBillVo);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	
}
