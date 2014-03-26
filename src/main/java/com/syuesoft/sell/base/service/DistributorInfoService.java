package com.syuesoft.sell.base.service;
import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.vo.DistributorInfoVo;
import com.syuesoft.sell.model.XsDistributorInfo;
import com.syuesoft.util.ComboBoxJson;

public interface DistributorInfoService  {
	public Datagrid getPager(DistributorInfoVo distributorInfoVo) throws Exception;
	public void addDistributorInfo(DistributorInfoVo distributorInfoVo) throws Exception;
	public void deleteDistributorInfo(DistributorInfoVo distributorInfoVo) throws Exception;
	public void updateDistributorInfo(DistributorInfoVo distributorInfoVo) throws Exception;
	public List<ComboBoxJson> findAllInfo( Integer enterprise_id)throws Exception ;
	public Boolean  findDistributorTwo(String distributorCode,Integer enid) throws Exception;
	public Boolean  findDistributor(String distributorCode,Integer distributorId,Integer enid) throws Exception;
}
