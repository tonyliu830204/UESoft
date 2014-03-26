package com.syuesoft.sell.sellwork.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.sell.model.XsCustomLeva;
import com.syuesoft.sell.sellwork.vo.PossibleCustomFollowVo;
import com.syuesoft.util.Msg;

public interface PossibleCustomFollowService {

	//获取客户档案信息
	public List findCustomInfor(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	//获取客户档案信息的 几倍和统计数量
	public List findCustomInforCount(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	//通过客户编号获取潜在客户记录
	public List findCustomById(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	//保存一条潜在客户记录
	public void addCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	//删除一条潜在客户记录
	public void deleteCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	//修改一条潜在客户记录
	public void updateCustomRecord(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	//审批
	public Msg doAudit(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
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

	//
	public XsCustomLeva getLevelDays(PossibleCustomFollowVo possibleCustomFollowVo)throws Exception;
	
	
}
