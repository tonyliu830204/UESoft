package com.syuesoft.sell.base.service;

import java.io.Serializable;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.CustomInfoVo;
import com.syuesoft.util.Msg;

public interface CustomInfoService {
	public Datagrid getPager(CustomInfoVo customInfoVo)  throws Exception;
	public Serializable addCustomInfo(CustomInfoVo customInfoVo)  throws Exception;
	public Msg deleteCustomInfo(CustomInfoVo customInfoVo)  throws Exception;
	public void updateCustomInfo(CustomInfoVo customInfoVo)  throws Exception;
	public boolean findByNumber (CustomInfoVo customInfoVo)throws Exception;
	public boolean findByNumberEdit (CustomInfoVo customInfoVo)throws Exception;
	public Datagrid getCustomInfo(CustomInfoVo customInfoVo)throws Exception;
	public boolean isExtisCustomCard (CustomInfoVo customInfoVo) throws Exception;
	public boolean isExtisCustomCardTwo (CustomInfoVo customInfoVo) throws Exception;
}
