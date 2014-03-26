package com.syuesoft.sell.sellwork.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.base.dao.BaseTagDAO;
import com.syuesoft.sell.sellwork.vo.PossibleCustomFollowVo;

public interface PossibleCustomFollowDao extends BaseDaoI {
		
	//获取客户档案信息
	public List findCustomInfor(PossibleCustomFollowVo possibleCustomFollowVo,
			BaseTagDAO baseTagDAO)throws Exception;
	
	//获取客户档案信息的 几倍和统计数量
	public List findCustomInforCount(PossibleCustomFollowVo possibleCustomFollowVo, BaseTagDAO baseTagDAO)throws Exception;
	
	//通过客户编号获取潜在客户记录
	public List findCustomById(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	//删除一条潜在客户记录
	public void deleteCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	
	/**
	 * 查询潜在客户信息
	 * @param possibleCustomFollowVo
	 * @return
	 * @throws Exception
	 * @author zhangbin
	 */
	public Datagrid getTzCustom(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	/**
	 *查询 潜在客户跟踪记录
	 * @param possibleCustomFollowVo
	 * @return
	 * @throws Exception
	 * @author zhangbin
	 */
	public Datagrid getTzCustomTrack(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;

	
}
