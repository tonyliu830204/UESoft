package com.syuesoft.systemmanagement.dao;

import java.util.List;

import com.syuesoft.bas.dao.BaseDaoI;
import com.syuesoft.model.SystemLog;
import com.syuesoft.systemmanagement.vo.SystemLogVo;
/**
 * 系统日志Dao
 * */
public interface SystemLogDao extends BaseDaoI<SystemLog>{	
	/**
	 * 系统日志删除
	 * @param systemLogVo 系统日志视图对象
	 * @exception Exception  总异常
	 * */
	public void doDeleteLog(SystemLogVo systemLogVo)throws Exception;
}
