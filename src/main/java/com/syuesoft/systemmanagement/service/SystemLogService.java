package com.syuesoft.systemmanagement.service;

import java.util.List;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.SystemLog;
import com.syuesoft.systemmanagement.vo.SystemLogVo;
/**
 * 系统日志Service
 * */
public interface SystemLogService {	
	/**
	 * 保存系统日志
	 * @param systemLog 系统日志实体对象
	 * @exception Exception 总异常
	 * */
	public void saveLog(SystemLog systemLog) throws Exception;
	
	/**
	 * 删除系统日志
	 * @param systemLogVo 系统日志视图对象
	 * @exception Exception 总异常
	 * */
	public void doDeleteLog(SystemLogVo systemLogVo)throws Exception;
	
	/**
	 * 系统日志查询
	 * @param systemLogVo 系统日志视图对象
	 * @exception Exception 总异常
	 * @return Datagrid 返回数据表格对象
	 * */
	public Datagrid findSystemLogByUser(SystemLogVo systemLogVo)throws Exception;
}
