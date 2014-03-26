package com.syuesoft.bas.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.frt.vo.FrtCarVo;
import com.syuesoft.frt.vo.FrtCustomVo;
import com.syuesoft.frt.vo.FrtReceptionVo;
import com.syuesoft.model.FrtCar;
import com.syuesoft.util.ComboBoxJson;
import com.syuesoft.util.Msg;

public interface BasCarArchivesService {

	public FrtCustomVo getCustomById(String customId) throws Exception; //根据id查询客户信息
	
	public Datagrid datagrid(FrtCarVo fcVo) throws Exception; //车档案datagrid
	
	public Datagrid datagridCustom(FrtCarVo fcVo) throws Exception; //客户档案combogrid
	
	public Msg save(FrtCarVo fcVo) throws Exception; //保存车档案
	
	public void remove(String carId) throws Exception; //删除车档案
	
	public void edit(FrtCarVo fcVo) throws Exception; //更新车档案
	
	public FrtCar carLicenseIsExits(String carLicense) throws Exception; //判断车牌照是否已存在
	
	public FrtCar carVinIsExits(String carVin) throws Exception; //判断VIN号是否已存在
	
	public List<ComboBoxJson> findAllCarVin(String q,Integer id) throws Exception;//查询VIN号
	
    public FrtCar findFrtCarByVin(String carVin)throws Exception;//根据vin号查找查档案信息
    
    public FrtCar findFrtCarByCustomId(String customId)throws Exception;//根据客户编号查找查档案信息
    
	public Msg isExistsVin(String carVin)throws Exception;//判断VIN号是否存在
	
	public Datagrid findAllCarByTerm(FrtCarVo fcVo) throws Exception; //前台车档案查询
	
	public Datagrid findAllReceptionByCarId(FrtCarVo fcVo) throws Exception; //前台车档案查询-维修记录
	
	public  List<FrtReceptionVo> findAllReceptionByCarIdForChild(FrtCarVo fcVo) throws Exception; //前台车档案查询-维修记录-子项
	
	public Datagrid findAllCarAnalyse(FrtCarVo fcVo) throws Exception; //前台车档案查询-数据分析
	
	public Msg updateNoIntoDays(String carId,int page,int rows)throws Exception;//更新未来厂天数(carId为null则修改所有)
	
}
