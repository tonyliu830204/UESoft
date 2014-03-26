package com.syuesoft.sell.base.service;
import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.SupplierInfoVo;
import com.syuesoft.sell.model.XsSupplierInfo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;

public interface SupplierInfoService   {
	public Datagrid getPager(SupplierInfoVo supplierInfoVo) throws Exception;
	public void addSupplierInfo(SupplierInfoVo supplierInfoVo) throws Exception;
	public Msg deleteSupplierInfo(SupplierInfoVo supplierInfoVo) throws Exception;
	public void updateSupplierInfo(SupplierInfoVo supplierInfoVo) throws Exception;
	public Boolean existSupplierTwo(String  supplierCode,Integer enId ) throws Exception;
	public Boolean existSupplier(String  supplierCode,Integer supplierId,Integer enid ) throws Exception;
	public List<ComboBoxJson> findAllSupplier(SupplierInfoVo supplierInfoVo) throws Exception;
}
