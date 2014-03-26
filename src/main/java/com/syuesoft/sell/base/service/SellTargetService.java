package com.syuesoft.sell.base.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.SellTargetVo;
import com.syuesoft.util.TreeJson;

public interface SellTargetService {
	public List<TreeJson> retriveTree(Integer enterprise_id) throws Exception;
	public Datagrid getPager(SellTargetVo targetVo) throws Exception;
	public void saveSellTarget(SellTargetVo targetVo) throws Exception;
	public void deleteSellTarget(SellTargetVo targetVo) throws Exception;
	public void updateSellTarget(SellTargetVo targetVo)throws Exception;
}
