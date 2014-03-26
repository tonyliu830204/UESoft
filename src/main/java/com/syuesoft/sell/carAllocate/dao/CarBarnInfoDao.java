package com.syuesoft.sell.carAllocate.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.carAllocate.vo.CarBarnInfoVo;
import com.syuesoft.sell.model.XsCarInfo;

public interface CarBarnInfoDao extends BaseDaoI<XsCarInfo> {
	/** 查询车辆在库待销信息*/
	public Datagrid findCarBarn(CarBarnInfoVo carBarnInfoVo)throws Exception;
	/** 查询车辆在库待销，没退厂， 分销商调拨信息*/
	public Datagrid findCar(CarBarnInfoVo carBarnInfoVo)throws Exception;
	/** 根据调拨单汇总信息查询明细 */
	public List<CarBarnInfoVo> findAllocatel(CarBarnInfoVo carBarnInfoVo)throws Exception;	
	/** 根据调退单汇总信息编号查询明细 */
	public List<CarBarnInfoVo> findBack(CarBarnInfoVo carBarnInfoVo)throws Exception;
	
	
}
