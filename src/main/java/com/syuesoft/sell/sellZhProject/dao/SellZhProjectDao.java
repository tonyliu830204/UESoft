package com.syuesoft.sell.sellZhProject.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.BaseBean;
import com.syuesoft.sell.sellZhProject.vo.SellZhProjectVo;
import com.syuesoft.sell.sellwork.vo.SellDbProjectVo;
import com.syuesoft.systemmanagement.vo.DatagridAnalyze;

public interface SellZhProjectDao  extends  BaseDaoI<Object>{
	/**
	 * 根据销售单号查装潢项目清单
	 * @param sellId
	 * @return
	 * @throws Exception
	 */
	public  Datagrid getZhList(SellZhProjectVo sellZhProjectVo) throws Exception;
	public List<Object> findExaminState(SellZhProjectVo SellZhProjectVo)throws Exception ;
	
	
	public Integer getChildId(String pkey,String ckey)throws Exception;
	//综合分析：装潢项目查询
	public DatagridAnalyze getZhInfor(SellZhProjectVo sellZhProjectVo) throws Exception;
	public List getZhDelInfor(SellZhProjectVo SellZhProjectVo)throws Exception ;

}
