package com.syuesoft.sell.base.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.CarInfoVo;
import com.syuesoft.util.Msg;

public interface CarInfoService {
	public Datagrid getPager(CarInfoVo carInfoVo) throws Exception;
	public void addCarInfo(CarInfoVo carInfoVo) throws Exception;
	public Msg deleteCarInfo(CarInfoVo carInfoVo) throws Exception;
	public void updateCarInfo(CarInfoVo carInfoVo) throws Exception;
	public Datagrid getSellState(CarInfoVo carInfoVo) throws Exception;
	Datagrid getCarInstore(CarInfoVo carInfoVo) throws Exception;
	public Datagrid getCar(CarInfoVo carInfoVo) throws Exception;
	public boolean findVintwo (String vinNo,Integer id) throws Exception;
	public boolean findVin (String vinNo,Integer carId,Integer id) throws Exception;
	public Datagrid queryCarUpInfor(CarInfoVo carInfoVo) throws Exception;
	public void updateCarUpInfo(CarInfoVo carInfoVo) throws Exception;
	//车辆附件管理
	public Datagrid getCarAttachment(CarInfoVo carInfoVo) throws Exception;
	//车辆附件管理 明细
	public Datagrid getAttachmentDel(CarInfoVo carInfoVo) throws Exception;
	public void addAttachmentDel(CarInfoVo carInfoVo) throws Exception;
	public void deleteAttachmentDel(CarInfoVo carInfoVo) throws Exception;
	public void updateAttachmentDel(CarInfoVo carInfoVo) throws Exception;
	
}
