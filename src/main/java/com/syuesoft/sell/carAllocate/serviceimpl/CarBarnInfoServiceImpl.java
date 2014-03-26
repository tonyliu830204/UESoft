package com.syuesoft.sell.carAllocate.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.carAllocate.dao.CarBarnInfoDao;
import com.syuesoft.sell.carAllocate.service.CarBarnInfoService;
import com.syuesoft.sell.carAllocate.vo.CarBarnInfoVo;

@Service("carBarnInfoService")
public class CarBarnInfoServiceImpl implements CarBarnInfoService {
	private CarBarnInfoDao carBarnInfoDao;

	public CarBarnInfoDao getCarBarnInfoDao() {
		return carBarnInfoDao;
	}

	@Autowired
	public void setCarBarnInfoDao(CarBarnInfoDao carBarnInfoDao) {
		this.carBarnInfoDao = carBarnInfoDao;
	}

	/**
	 * 
	 * 库存信息查找
	 */
	
	public Datagrid findCarBarn(CarBarnInfoVo carBarnInfoVo)throws Exception {

		return carBarnInfoDao.findCarBarn(carBarnInfoVo);
	}
	/** 
	 * 
	 * 查询车辆在库待销，退厂， 分销商调拨信息
	 */
	
	public Datagrid findCar(CarBarnInfoVo carBarnInfoVo)
			throws Exception {
		return carBarnInfoDao.findCar(carBarnInfoVo);
	}
	/** 根据调拨单汇总信息查询明细 
	 * 
	 */
	
	public List<CarBarnInfoVo> findAllocatel(CarBarnInfoVo carBarnInfoVo)
			throws Exception {
		return carBarnInfoDao.findAllocatel(carBarnInfoVo);
	}

	
	public List<CarBarnInfoVo> findBack(CarBarnInfoVo carBarnInfoVo)
			throws Exception {
		return carBarnInfoDao.findBack(carBarnInfoVo);
	}
	/**
	 * 获取该车型作为动态列
	 */
	
	public List getAnimatedColum(CarBarnInfoVo carBarnInfoVo) throws Exception {
		List list = new ArrayList();
		String sql = "";
		if(carBarnInfoVo.getCarBrand()!=null && !carBarnInfoVo.getCarBrand().equals("")){
			sql = "SELECT b.xs_model_name FROM xs_car_sell_info a, xs_car_model b, xs_car_info c WHERE a.xs_car_id = c.xs_car_id AND c.xs_car_model_id = b.xs_model_id AND a.xs_car_brand="+carBarnInfoVo.getCarBrand()+" ";
		}else if(carBarnInfoVo.getCarModel()!=null && !carBarnInfoVo.getCarModel().equals("")){
			//获取型号名称
			sql = "SELECT b.xs_model_name FROM xs_car_sell_info a, xs_car_model b, xs_car_info c WHERE a.xs_car_id = c.xs_car_id AND c.xs_car_model_id = b.xs_model_id AND b.xs_model_id="+carBarnInfoVo.getCarModel()+" GROUP BY b.xs_model_id";
		}else{
			sql = "SELECT b.xs_model_name FROM xs_car_sell_info a, xs_car_model b, xs_car_info c WHERE a.xs_car_id = c.xs_car_id AND c.xs_car_model_id = b.xs_model_id GROUP BY b.xs_model_id";
		}
		if(carBarnInfoVo.getQueryDate()!=null && !carBarnInfoVo.getQueryDate().equals("")){
			list = carBarnInfoDao.createSQLQuery(sql);
		}
		return  list;
	}

	
}
