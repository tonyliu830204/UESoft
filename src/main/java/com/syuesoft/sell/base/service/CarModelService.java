package com.syuesoft.sell.base.service;
import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.CarModelVo;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;

public interface CarModelService {
	public Datagrid getPager(CarModelVo  carModelVo) throws Exception;
	public void addCarModel(CarModelVo carModelVo) throws Exception;
	public Msg deleteCarModel(CarModelVo carModelVo) throws Exception;
	public void updateCarModel(CarModelVo carModelVo) throws Exception;
	public List<CarModelVo> findCarModel(CarModelVo carModelVo)throws Exception;
	public List<ComboBoxJson>findAllCarModel(CarModelVo model) throws Exception;
	public List<ComboBoxJson> findCarModelByBrand(CarModelVo carModelVo) throws Exception;
	public List<ComboBoxJson> findBrandByModel(CarModelVo carModelVo) throws Exception;
}
