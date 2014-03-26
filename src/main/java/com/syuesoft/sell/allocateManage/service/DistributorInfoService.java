package com.syuesoft.sell.allocateManage.service;


import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.DistributorInfoVo;

public interface DistributorInfoService {
	/**
	 * 查询分销商信息
	 * @param disVo
	 * @return
	 * @throws Exception
	 */
	public Datagrid getDistributorInfo(DistributorInfoVo disVo) throws Exception;
	
}