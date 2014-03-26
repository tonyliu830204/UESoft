package com.syuesoft.sell.allocateManage.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.allocateManage.vo.InstorehouseVo;
import com.syuesoft.sell.synthesis_assay.vo.DayReportAssayVo;

public interface InstorehouseService {
	//库存查询
	public Datagrid queryInstorehouse(InstorehouseVo instorehouseVo) throws Exception;
	//库存查询
	public List queryInstorehouseSum(InstorehouseVo instorehouseVo) throws Exception;
	/**车辆库存分析动态列*/
	public List doAssayColumns(InstorehouseVo instorehouseVo)throws Exception;
	//车辆颜色分类
	public String getCarColor(InstorehouseVo instorehouseVo) throws Exception;
	//分销商分类
	public String getInforByDis(InstorehouseVo instorehouseVo) throws Exception;
	//分销商分类
	public String getInforBySellState(InstorehouseVo instorehouseVo) throws Exception;
}
