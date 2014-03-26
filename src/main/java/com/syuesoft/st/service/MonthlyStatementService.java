package com.syuesoft.st.service;

import com.syuesoft.model.BasUsers;
import com.syuesoft.st.vo.MonthlyStatementVo;
import com.syuesoft.util.Json;

public interface MonthlyStatementService {

    /**加载月结汇总数据*/
    public Json findAllMonthlyStatemont(MonthlyStatementVo msvo) throws Exception;
    
	/**获取月结表中本次月结开始时间*/
	public MonthlyStatementVo loadStratTime(MonthlyStatementVo monthlyStatementVo)throws Exception;
	
	/**添加月结*/
	public String add(MonthlyStatementVo monthlyStatementVo, BasUsers user)throws Exception;

	/** 反月结*/
	public void delete(MonthlyStatementVo monthlyStatementVo)throws Exception;

	/**加载月结明细数据*/
    public Json findMonthlyDetail(MonthlyStatementVo monthlyStatementVo)throws Exception;
 
}
