package com.syuesoft.sell.sellwork.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellDbProjectDAO extends BaseDaoI<Object> {

	public List<Object> findExaminState(SellDbProjectVo sellDbProjectVo)throws Exception;

	public List<Object> findByCode(String dbProjectCode)throws Exception;
	//public void saveSellAccount(Integer xsCarSelId, String accountTypeId,Integer accountType, Double accountMoney,String remark) throws Exception; 

	//代办项目查询:父级信息
	public DatagridAnalyze queryDbInfor(SellDbProjectVo sellDbProjectVo)throws Exception;
	public List queryDbChildInfor (SellDbProjectVo sellDbProjectVo)throws Exception;
}
