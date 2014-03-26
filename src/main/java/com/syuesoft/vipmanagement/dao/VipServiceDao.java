package com.syuesoft.vipmanagement.dao;

import java.util.List;
import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.VipcardMealR;

public interface VipServiceDao extends BaseDaoI<VipcardMealR>{
	
	//根据会员编号查询套餐名称
	public List<?> getMealNameById(String vipid)throws Exception;
}