package com.syuesoft.sell.instore.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.instore.vo.SellInstorehouseVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.TreeJson;

public interface InstorehouseService {
	public Datagrid getPager(SellInstorehouseVo InstoreVo)throws Exception;
	public void addInstore(SellInstorehouseVo  InstoreVo)throws Exception;
	public void saveImportInstore(SellInstorehouseVo  InstoreVo)throws Exception;
	public void deleteInstore(SellInstorehouseVo  InstoreVo)throws Exception;
	public Datagrid getPagerDel(SellInstorehouseVo InstoreVo) throws Exception;
	public void deleteCar(SellInstorehouseVo InstoreVo)throws Exception;
	public void  updateExamine(SellInstorehouseVo InstoreVo) throws Exception;
	public List<TreeJson> queryInstore(SellInstorehouseVo InstoreVo)throws Exception;
	public void updateInstore(SellInstorehouseVo instoreVo)throws Exception;
	public Boolean  findDels(SellInstorehouseVo instoreVo) throws Exception;
	public DatagridAnalyze findInstore(SellInstorehouseVo instoreVo)throws Exception;
	public List<SellInstorehouseVo> getTreegridChild(SellInstorehouseVo instoreVo)throws Exception;
	public Datagrid getQueryData(SellInstorehouseVo instoreVo)throws Exception;
	public List<ComboBoxJson> findAllInstore(SellInstorehouseVo instoreVo)throws Exception;
	public Boolean isRefundment(SellInstorehouseVo sellInstorehouseVo) throws Exception;
	public Boolean isfpType(SellInstorehouseVo sellInstorehouseVo) throws Exception;
	public Integer isfpTypeId(SellInstorehouseVo sellInstorehouseVo) throws Exception;
	
}
