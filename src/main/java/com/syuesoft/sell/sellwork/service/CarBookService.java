package com.syuesoft.sell.sellwork.service;

import java.util.List;

import com.syuesoft.sell.sellwork.vo.CarBookVo;
import com.syuesoft.util.Json;
import com.syuesoft.util.Message;
import com.syuesoft.util.Msg;

public interface CarBookService {

	//新增车辆预定信息
	public Message addCarBookInfor(CarBookVo carBookVo)throws Exception;
	
	//查询预定单汇总信息
	public Json findCarBookInfor(CarBookVo carBookVo)throws Exception;
	
	//删除汇总信息
	public int deleteCarBookInfor(CarBookVo carBookVo)throws Exception;
	
	//修改汇总信息
	public void updateCarBook(CarBookVo carBookVo)throws Exception;
	
	//审核
	public Msg doAudit(CarBookVo carBookVo)throws Exception;
	
	//取消客户预定
	public Message doAduitCancelBook(CarBookVo carBookVo)throws Exception;
	
	//取消客户预定
	public void doCancelBook(CarBookVo carBookVo)throws Exception;
	
	/**根据车辆型号查找标准销售价*/
	public Double findCarTypeSellPriceOrControlPrice(CarBookVo carBookVo,Boolean flag)throws Exception;
	//判读预订单是否已审核
	public Boolean isRefundment(CarBookVo carBookVo) throws Exception ;
	
}
