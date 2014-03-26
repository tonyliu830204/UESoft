package com.syuesoft.sell.sellwork.service;

import java.util.List;

import com.syuesoft.sell.base.service.CustomInfoService;
import com.syuesoft.sell.sellwork.vo.BackRegisterVo;
import com.syuesoft.util.Json;

public interface BackRegisterService{

	//保存登记表明细
	public void saveDetailAll(BackRegisterVo backRegisterVo)throws Exception;
	
	//保存登记表汇总
	public void saveRecord(BackRegisterVo backRegisterVo)throws Exception;
	
	//查询登记表汇总
	public Json findRecord(BackRegisterVo backRegisterVo)throws Exception;
	
	//获取预定详细信息
	public Json findRecordById(BackRegisterVo backRegisterVo)throws Exception;
	
	//來店客流登记信息浏览
	public Json findRecordLook(BackRegisterVo backRegisterVo)throws Exception;
	
	//保存修改后的汇总信息
	public void saveEditRecord(BackRegisterVo backRegisterVo)throws Exception;
	
	//保存修改后的明细信息
	public void saveEditDetailAll(BackRegisterVo backRegisterVo)throws Exception;
	
	//删除明细信息
	public void deleteDetailInfo(BackRegisterVo backRegisterVo)throws Exception;
	
	//删除汇总信息
	public void deleteRecord(BackRegisterVo backRegisterVo)throws Exception;
	
	//获取员工名称 用于combox
	public List getBasStuff()throws Exception;
	
	//转入跟踪系统
	public void doTurnin(BackRegisterVo backRegisterVo,CustomInfoService customInfoService)throws Exception;
	
	//放弃跟踪
	public void doAbandonAttention(BackRegisterVo backRegisterVo)throws Exception;
	
	//客流时间段分析  返回时间段
	public List doTimeAssay(BackRegisterVo backRegisterVo)throws Exception;

	//获取个时间段内的统计数量 
	public String getTimeAssay(BackRegisterVo backRegisterVo) throws Exception;
	
	//客流业务员分析  返回业务员名称 作为列
	public List doRegisterWorker(BackRegisterVo backRegisterVo)throws Exception;
	
	//获取业务员分析统计结果的json
	public String getRegisterWorker(BackRegisterVo backRegisterVo) throws Exception;

	//车辆型号分析
	public List doRegisterCarmodel(BackRegisterVo backRegisterVo) throws Exception;
	public String getRegisterCarmodel(BackRegisterVo backRegisterVo) throws Exception;

	//接待员车型分析
	public List doRegisterUserCarmodel(BackRegisterVo backRegisterVo) throws Exception;
	public String getRegisterUserCarmodel(BackRegisterVo backRegisterVo)
			throws Exception;
	//客流车级别分析
	public List doRegisterCarLevel(BackRegisterVo backRegisterVo) throws Exception;
	public String getRegisterCarLevel(BackRegisterVo backRegisterVo) throws Exception;
	//客流车来源分析
	public List doRegisterCustomSource(BackRegisterVo backRegisterVo) throws Exception;
	//客流车来源分析
	public String getRegisterCustomSource(BackRegisterVo backRegisterVo) throws Exception;
	//接待员车品牌分析
	public List doRegisterCarBrand(BackRegisterVo backRegisterVo) throws Exception;
	public String getRegisterCarBrand(BackRegisterVo backRegisterVo) throws Exception;
	
}
