package com.syuesoft.sell.base.service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.ProvidebankVo;

public interface ProvidebankService {
	public Datagrid getPager(ProvidebankVo   providebankVo) throws Exception;
	public void addProvidebank(ProvidebankVo   providebankVo) throws Exception;
	public void deleteProvidebank(ProvidebankVo   providebankVo) throws Exception;
	public void updateProvidebank(ProvidebankVo   providebankVo) throws Exception;
	public  Boolean existBankTwo(String bankCode ,Integer enid) throws Exception;
	public  Boolean existBank(String bankCode,Integer id,Integer enid) throws Exception;
}
