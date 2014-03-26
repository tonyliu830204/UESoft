package com.syuesoft.sell.base.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.CustomLevaVo;
import com.syuesoft.util.ComboBoxJson;

public interface CustomLevaService {
	public Datagrid getPager(CustomLevaVo  leva)  throws Exception;
	public void addCustom(CustomLevaVo leva)  throws Exception;
	public void deleteCustom(CustomLevaVo leva)  throws Exception;
	public void updateCustom(CustomLevaVo leva)  throws Exception;
	//查询所有潜在客户等级
	public List<ComboBoxJson> findAllLeva(CustomLevaVo leva1)throws Exception;
	public Boolean  findLeva(String levaCode,String name,Integer id,Integer enid) throws Exception;
	public Boolean  findLevaTwo(String levaCode,String name,Integer enid) throws Exception;
}
