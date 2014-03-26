package com.syuesoft.sell.base.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.syuesoft.bas.action.BaseAction;
import com.syuesoft.sell.base.service.CarInfoService;
import com.syuesoft.sell.base.vo.CarInfoVo;
import com.syuesoft.util.Msg;
import org.apache.struts2.convention.annotation.ParentPackage;

@ParentPackage(value="basePackage")
@Action("carInfoAction")
public class CarInfoAction extends BaseAction implements ModelDriven<CarInfoVo> {
	private static final long serialVersionUID = 1L;
	Logger logger=Logger.getLogger(this.getClass());
	private CarInfoVo carInfoVo=new CarInfoVo();
	private CarInfoService  carInfoService;
	public void getPage(){
		try {
			carInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carInfoService.getPager(carInfoVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getCarInstore(){
		try {
				carInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carInfoService.getCarInstore(carInfoVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getCar(){
		try {
				carInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carInfoService.getCar(carInfoVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//入库新增选车辆
	public void getSellState(){
		try {
				carInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carInfoService.getSellState(carInfoVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveCarInfo(){     
		Msg msg = new Msg();
		try {
				carInfoVo.setEnterpriseId(getUserEnterpriseId());
			if(carInfoService.findVintwo(carInfoVo.getCarVinNumber(),carInfoVo.getEnterpriseId())){
				msg.setMsg("对不起，您输入的Vin号已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			carInfoService.addCarInfo(carInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	public void isUseVin(){
		Msg msg = new Msg();
		try {
				carInfoVo.setEnterpriseId(getNowEnterpriseId());
			Boolean flag=carInfoService.findVin(carInfoVo.getCarVinNumber(),carInfoVo.getCarId(),carInfoVo.getEnterpriseId());
			msg.setSuccess(true);
			msg.setObj(flag);
		} catch (Exception e) {
			logger.error("判断VIN是否已使用失败！", e);
			msg.setMsg("判断VIN是否已使用失败！");
		}
		super.writeJson(msg);
	}
	public void deleteCarInfo(){
		Msg msg = new Msg();
		try {
			msg=carInfoService.deleteCarInfo(carInfoVo);
		} catch (Exception e) {
			logger.error("删除车辆档案失败！", e);
			msg.setMsg("删除车辆档案失败！");
			msg.setSuccess(false);
		}
		super.writeJson(msg);
	}
	public void updateCarInfo(){
		Msg msg = new Msg();
		try {
				carInfoVo.setEnterpriseId(getUserEnterpriseId());
			if(carInfoService.findVin(carInfoVo.getCarVinNumber(),carInfoVo.getCarId(),carInfoVo.getEnterpriseId())){
				msg.setMsg("对不起，您输入的Vin号已存在，请重新输入！");
				super.writeJson(msg);
				return;
			}
			carInfoService.updateCarInfo(carInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(msg);
	}
	//查询车辆上报信息
	public void queryCarUpInfor(){
		try {
				carInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carInfoService.queryCarUpInfor(carInfoVo));
		} catch (Exception e) {
			logger.debug("查询车辆上报信息失败！");
		}	
	}
	/**
	 * 车辆上报
	 */
	public void updateCarUpInfo() {
		Msg msg = new Msg();
		try {
			carInfoVo.setEnterpriseId(getUserEnterpriseId());
			carInfoService.updateCarUpInfo(carInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("车辆上报失败！");
		}
		super.writeJson(msg);

	}
	//查询车辆附件信息
	public void getCarAttachment(){
		try {
				carInfoVo.setEnterpriseId(getNowEnterpriseId());
			super.writeJson(carInfoService.getCarAttachment(carInfoVo));
		} catch (Exception e) {
			logger.debug("查询车辆附件信息失败！");
		}	
	}	
	//查询车辆附件明细信息
	public void getAttachmentDel(){
		try {
			super.writeJson(carInfoService.getAttachmentDel(carInfoVo));
		} catch (Exception e) {
			logger.debug("查询车辆附件明细失败！");
		}	
	}
	//新增附件明细信息
	public void addAttachmentDel() {
		Msg msg = new Msg();
		try {
			carInfoVo.setEnterpriseId(getUserEnterpriseId());
			carInfoService.addAttachmentDel(carInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("新增附件明细信息失败！");
		}
		super.writeJson(msg);

	}
	//修改附件明细信息
	public void updateAttachmentDel() {
		Msg msg = new Msg();
		try {
			carInfoVo.setEnterpriseId(getNowEnterpriseId());
			carInfoService.updateAttachmentDel(carInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("修改附件明细信息失败！");
		}
		super.writeJson(msg);

	}
	//删除附件明细信息
	public void deleteAttachmentDel() {
		Msg msg = new Msg();
		try {
			carInfoService.deleteAttachmentDel(carInfoVo);
			msg.setSuccess(true);
			msg.setMsg("success");
		} catch (Exception e) {
			logger.debug("删除附件明细信息失败！");
		}
		super.writeJson(msg);

	}
	public CarInfoService getCarInfoService() {
		return carInfoService;
	}
	@Autowired
	public void setCarInfoService(CarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}
	
	public CarInfoVo getModel() {
		return carInfoVo;
	}
	


}
