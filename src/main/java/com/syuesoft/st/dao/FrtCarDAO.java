package com.syuesoft.st.dao;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.FrtCar;
import com.syuesoft.util.Json;

/**
 * 车辆档案DAO
 * @author SuMing
 */
public interface FrtCarDAO extends BaseDaoI<FrtCar> {
	
	/**销售单模块  车辆牌照信息      预加载*/
	public Json loadCarLicense(final int page, final int rows, final String sort,final String order,final int enterprise_id)throws Exception;

	/** 销售单模块  车辆牌照信息     综合查询*/
	public Json searchCarLicenseByCondition(final String carLicense,final String carVin,final String carMotorId,final int enterprise_id)throws Exception;
	
	

}