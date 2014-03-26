package com.syuesoft.sell.carAllocate.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.carAllocate.vo.CarBarnInfoVo;

public interface CarBarnInfoService {
	/** 查询车辆在库待销信息*/
	public Datagrid findCarBarn(CarBarnInfoVo carBarnInfoVo)throws Exception;
	/** 查询车辆在库待销，退厂， 分销商退回信息*/
	public Datagrid findCar(CarBarnInfoVo carBarnInfoVo)throws Exception;
	/** 根据调拨单汇总信息查询明细 */
	public List<CarBarnInfoVo> findAllocatel(CarBarnInfoVo carBarnInfoVo)throws Exception;	
	/** 根据调退单汇总信息编号查询明细 */
	public List<CarBarnInfoVo> findBack(CarBarnInfoVo carBarnInfoVo)throws Exception;
	public List getAnimatedColum(CarBarnInfoVo carBarnInfoVo) throws Exception ;

}
