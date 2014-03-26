package com.syuesoft.sell.sellwork.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsSellCarReserve;
import com.syuesoft.sell.sellwork.vo.CarBookVo;

public interface CarBookDao extends BaseDaoI{
	
	
	//删除汇总信息
	public int deleteCarBookInfor(CarBookVo carBookVo)throws Exception;
	
	//修改汇总信息
	public void updateCarBook(XsSellCarReserve xsSellCarReserve)throws Exception;
	
	//审核
	public void doAudit(XsSellCarReserve xsSellCarReserve)throws Exception;
	
	//取消客户预定
	public void doCancelBook(XsSellCarReserve xsSellCarReserve)throws Exception;

	
}
