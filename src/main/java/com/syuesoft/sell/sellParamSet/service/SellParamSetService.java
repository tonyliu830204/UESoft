package com.syuesoft.sell.sellParamSet.service;


import com.syuesoft.sell.sellParamSet.vo.SellParamSetVo;
import com.syuesoft.util.Json;

public interface SellParamSetService {
	/**
	 * 查询
	 * @param cis
	 * @return
	 * @throws Exception
	 */
	 public Json searchParameterOne(SellParamSetVo cis)throws Exception;
	 /**
		 * 查询
		 * @param cis
		 * @return
		 * @throws Exception
		 */
		 public Json searchParameter(SellParamSetVo cis)throws Exception;
	/**
	 * 保存新增系统参数
	 * @param cis
	 * @throws Exception
	 */
	public void saveOrUpdate(SellParamSetVo cis) throws Exception;
	public void updateParam(SellParamSetVo params)throws Exception;

}
