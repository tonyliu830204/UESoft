package com.syuesoft.sell.util.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.sellwork.vo.CarBookVo;

public interface SellUtilDao extends BaseDaoI{
	//获取客户名称
	public List findCustom(String q)throws Exception;
	
	//获取员工名称
	public List findStfName(String q)throws Exception;
	
	//获取车辆型号
	public List findCarModel()throws Exception;
	
	//获取银行名称
	public List findBank()throws Exception;
	
	//获分销商
	public List findBussness()throws Exception;
	
	//获取车辆编号
	public List findCarId()throws Exception;
	
	//获取经办人（系统登录用户）
	public List findUsers()throws Exception;
	//获取经办人
	public List findUsers(String q,String enterpriseId)throws Exception;
}
