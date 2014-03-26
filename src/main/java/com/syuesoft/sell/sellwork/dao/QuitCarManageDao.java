package com.syuesoft.sell.sellwork.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.sell.model.XsSellExitCar;
import com.syuesoft.sell.sellwork.vo.QuitCarManageVo;
import com.syuesoft.util.Json;

public interface QuitCarManageDao extends BaseDaoI {

	//新增退车信息
	public void addQuitInfor(XsSellExitCar xsSellExitCar)throws Exception;
	
	//获取退车信息
	public Json getQuitInfor(QuitCarManageVo quitCarManageVo)throws Exception;
	
	
}
