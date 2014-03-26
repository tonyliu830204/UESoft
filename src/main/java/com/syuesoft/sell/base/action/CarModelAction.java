package com.syuesoft.sell.base.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.CarModelService;
import com.syuesoft.sell.base.vo.CarModelVo;
import com.syuesoft.util.Msg;

import org.apache.struts2.convention.annotation.ParentPackage;@ParentPackage(value="basePackage")@Action("carModelAction")
public class CarModelAction extends BaseAction implements ModelDriven<CarModelVo> {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private CarModelVo carModelVo=new CarModelVo();
	private CarModelService carModelService;
	public void getPageModel(){
		try {

				carModelVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carModelService.getPager(carModelVo));
		} catch (Exception e) {
			logger.error("查询车辆模型失败", e);
		}
	}
	public void findAllCarModel(){
		try {
				carModelVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carModelService.findAllCarModel(carModelVo));
		} catch (Exception e) {
			logger.error("查询车辆模型失败", e);
		}
	}
	public void findCarModelByBrand(){
		try {
				carModelVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carModelService.findCarModelByBrand(carModelVo));
		} catch (Exception e) {
			logger.error("查询车辆模型失败", e);
		}
	}
	
	public void findBrandByModel(){
		try {
				carModelVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carModelService.findBrandByModel(carModelVo));
		} catch (Exception e) {
			logger.error("查询车辆模型失败", e);
		}
	}
	public void saveCarModel(){     
		Msg msg = new Msg();
		try {

			carModelVo.setEnterpriseId(getUserEnterpriseId());
			carModelService.addCarModel(carModelVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("保存车辆模型失败", e);
		}
		super.writeJson(msg);
	}
	public void deleteCarModel(){
		Msg msg = new Msg();
		try {
			msg=carModelService.deleteCarModel(carModelVo);
		} catch (Exception e) {
			logger.error("删除车辆型号失败!", e);
			msg.setMsg("删除车辆型号失败!");
			msg.setSuccess(false);
		}
		super.writeJson(msg);
	}
	public void updateCarModel(){
		Msg msg = new Msg();
		try {
			carModelService.updateCarModel(carModelVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.error("更新车辆模型失败", e);
		}
		super.writeJson(msg);
	}
	public void findModel(){
		try {
				carModelVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carModelService.findCarModel(carModelVo));
		} catch (Exception e) {
			logger.error("查询车辆品牌失败", e);
		}
	}
	public CarModelService getCarModelService() {
		return carModelService;
	}
	@Autowired
	public void setCarModelService(CarModelService carModelService) {
		this.carModelService = carModelService;
	}
	
	public CarModelVo getModel() {
		return carModelVo;
	}

}
