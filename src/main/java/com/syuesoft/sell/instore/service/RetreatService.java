package com.syuesoft.sell.instore.service;
import java.util.List;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.instore.vo.SellRetreatVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface RetreatService {
	public Datagrid getPager(SellRetreatVo  retreatVo)throws Exception;
	public void addRetreat(SellRetreatVo  retreatVo)throws Exception;
	public void deleteRetreat(SellRetreatVo  retreatVo)throws Exception;
	public void updateRetreat(SellRetreatVo  retreatVo)throws Exception;	
	public void updateExamine(SellRetreatVo  retreatVo)throws Exception;
	public Datagrid getPagerDel(SellRetreatVo retreatVo) throws Exception;
	public DatagridAnalyze findQueryBack(SellRetreatVo retreatVo) throws Exception;
	public List<SellRetreatVo> getTreegridChild(SellRetreatVo retreatVo)throws Exception;
	public Boolean isRefundment(SellRetreatVo retreatVo) throws Exception;//判断是否已审核
}
