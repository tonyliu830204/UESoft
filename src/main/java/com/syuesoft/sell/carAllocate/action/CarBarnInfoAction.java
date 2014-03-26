package com.syuesoft.sell.carAllocate.action;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.carAllocate.service.CarBarnInfoService;
import com.syuesoft.sell.carAllocate.vo.CarBarnInfoVo;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("carBarnInfoAction")
public class CarBarnInfoAction extends BaseAction implements
		ModelDriven<CarBarnInfoVo> {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private CarBarnInfoService carBarnInfoService;
	private CarBarnInfoVo car=new CarBarnInfoVo();
	
	public CarBarnInfoVo getModel() {
		return  car;
	}
	/**
	 * 查询库存车辆信息
	 */
	public void getCarList(){
		try {
				car.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(carBarnInfoService.findCarBarn( car));
		} catch (Exception e) {
			logger.error("查询车辆信息失败！", e);
		}
	}
	public CarBarnInfoService getCarBarnInfoService() {
		return carBarnInfoService;
	}

	@Autowired
	public void setCarBarnInfoService(CarBarnInfoService carBarnInfoService) {
		this.carBarnInfoService = carBarnInfoService;
	}
	/**
	 *调拨：查询车辆在库待销，退厂， 分销商调拨信息
	 * @param carBarnInfoService
	 */
	public void getCar(){
		try {
				car.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(carBarnInfoService.findCar(car));
		} catch (Exception e) {
			logger.error("查询车辆信息失败！", e);
		}
	}
	/** 根据调拨单汇总信息查询明细 
	 * 
	 */
	public void getAllocatel(){
		try {
				car.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(carBarnInfoService.findAllocatel(car));
		} catch (Exception e) {
			logger.error("查询调拨车辆信息失败！", e);
		}
	}
	
	public void getBack(){
		try {
				car.setEnterprise_id(getNowEnterpriseId());
			super.writeJson(carBarnInfoService.findBack(car));
		} catch (Exception e) {
			logger.error("查询调退车辆信息失败！", e);
		}
	}
	
}
