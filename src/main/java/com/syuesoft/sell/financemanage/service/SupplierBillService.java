package com.syuesoft.sell.financemanage.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.financemanage.vo.SupplierBillVo;
import com.syuesoft.util.Json;

public interface SupplierBillService {
	
	//获取对账单汇总信息
	public Json getSupplierBillInfor(SupplierBillVo supplierBillVo)throws Exception;
	//获取对账单明细父节点信息
	public List getSupplierBillDetailPrentInfor(SupplierBillVo supplierBillVo)throws Exception;
	//获取对账单明细子节点信息
	public List getSupplierBillDetailChildInfor(SupplierBillVo supplierBillVo)throws Exception;
	//应付款查询
	public Json getDueFromInfor(SupplierBillVo supplierBillVo)throws Exception;
	//新增对款记录
	public void addSupplierBillInfor(SupplierBillVo supplierBillVo)throws Exception;
	
	//获取入库信息
	public Datagrid getInterStore(SupplierBillVo supplierBillVo) throws Exception ;
	//
	public void doDeleteInfor(SupplierBillVo supplierBillVo)throws Exception;
	//
	public void doEditInfor(SupplierBillVo supplierBillVo)throws Exception;
	//
	public List getDuePrentInfor(SupplierBillVo supplierBillVo)throws Exception;
	//
	public List getDueChildInfor(SupplierBillVo supplierBillVo)throws Exception;
	/**
	 * 分销商对账汇总查询
	 * @param SupplierBillVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getDisBill(SupplierBillVo supplierBillVo) throws Exception;
	//新增分销商对款记录
	public void addDistributorBill(SupplierBillVo supplierBillVo)throws Exception;
	

}
