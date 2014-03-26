package com.syuesoft.st.service;

import java.util.List;

import com.syuesoft.st.vo.StPreOutVo;
import com.syuesoft.util.Json;

public interface  StPreOutService {
	
	/**明细数据由JSON转换为List集合 */
	public List<StPreOutVo> jsonChangeDetailList(StPreOutVo stPreOutVo);
	
	/** 预出库汇总信息    综合查询*/
	public Json searchByCondition(StPreOutVo stPreOutVo)throws Exception;
	
	/** 领用人信息    预加载*/
	public Json loadBasStuff(StPreOutVo stPreOutVo) throws Exception;
	
	/** 领用人信息    综合查询*/
	public Json searchBasStuffByCondition(StPreOutVo stPreOutVo) throws Exception;
	
	/**预出库汇总明细信息   新增*/
	public void add(StPreOutVo preOutJson,List<StPreOutVo> list)throws Exception;
	
	/**预出库汇总明细信息删除*/
	public void delete(StPreOutVo preOutJson)throws Exception;
	
	/**预出库汇总明细信息修改*/
	public void update(StPreOutVo spovo,List<StPreOutVo> list)throws Exception;
	
	/**根据与出库单号获取预出库明细信息*/
	public List<StPreOutVo> searchStPreOutDetailByStpremId(StPreOutVo preOutJson) throws Exception;
	
}
