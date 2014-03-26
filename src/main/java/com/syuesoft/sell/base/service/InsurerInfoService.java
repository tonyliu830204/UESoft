package com.syuesoft.sell.base.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.InsurerInfoVo;

public interface InsurerInfoService {
	public Datagrid getPager(InsurerInfoVo insurerInfoVo)throws Exception;
	public void addInsurerInfo(InsurerInfoVo insurerInfoVo)throws Exception;
	public void deleteInsurerInfo(InsurerInfoVo insurerInfoVo)throws Exception;
	public void updateInsurerInfo(InsurerInfoVo insurerInfoVo)throws Exception;
	public Boolean findInsurerTwo(String insurerCode,Integer enid) throws Exception;
	public Boolean findInsurer(String insurerCode,Integer id,Integer enid) throws Exception;
}
