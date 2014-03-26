package com.syuesoft.sell.sellwork.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsSellCustomSalesmanDetail;
import com.syuesoft.sell.sellwork.vo.BackRegisterVo;
import com.syuesoft.util.Json;

public interface BackRegisterDao extends BaseDaoI{
	
	//查询登记表汇总
	public List findRecord(BackRegisterVo backRegisterVo ,String sql)throws Exception;
	
	//通过登记表汇总编号查询明细信息
	public Json findRecordById(BackRegisterVo backRegisterVo)throws Exception;
	
	//來店客流登记信息浏览
	public List findRecordLook(String sql, int page, int rows)throws Exception;
	
	//删除明细信息
	public void deleteDetailInfo(BackRegisterVo backRegisterVo)throws Exception;
	
	//删除汇总信息
	public void deleteRecord(BackRegisterVo backRegisterVo)throws Exception;
	
	//获取员工名称 用于combobox
	public List getBasStuff()throws Exception;
	


	
}
