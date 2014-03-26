package com.syuesoft.systemmanagement.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syuesoft.fin.vo.Datagrid;
import com.syuesoft.model.SystemLog;
import com.syuesoft.systemmanagement.dao.SystemLogDao;
import com.syuesoft.systemmanagement.service.SystemLogService;
import com.syuesoft.systemmanagement.vo.SystemLogVo;
/**
 * 系统日志Service实现
 * */
@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService {
	@Autowired
	private SystemLogDao systemLogDao;
	
	/**
	 * 保存系统日志
	 * @param systemLog 系统日志实体对象
	 * @exception Exception 总异常
	 * */
	
	public synchronized void saveLog(SystemLog systemLog) throws Exception {
		systemLogDao.save(systemLog);
	}
	/**
	 * 删除系统日志
	 * @param systemLogVo 系统日志视图对象
	 * @exception Exception 总异常
	 * */
	
	public void doDeleteLog(SystemLogVo systemLogVo) throws Exception {
		systemLogDao.doDeleteLog(systemLogVo);
	}
	/**
	 * 系统日志查询
	 * @param systemLogVo 系统日志视图对象
	 * @exception Exception 总异常
	 * @return Datagrid 返回数据表格对象
	 * */
	
	public Datagrid findSystemLogByUser(SystemLogVo systemLogVo)
			throws Exception {
		StringBuffer  sb = new StringBuffer("from SystemLog A WHERE A.enterpriseId="+systemLogVo.getEnterpriseId());
		if(systemLogVo.getBeginTime()!=null&&systemLogVo.getBeginTime().length()>0)
			sb.append(" and A.startTime>='"+systemLogVo.getBeginTime()+"'");
		if(systemLogVo.getEndTime()!=null&&systemLogVo.getEndTime().length()>0)
			sb.append(" and A.startTime<='"+systemLogVo.getEndTime()+"'");
		if(systemLogVo.getIpName()!=null&&systemLogVo.getIpName().length()>0)
			sb.append(" and A.ipName like '%"+StringEscapeUtils.escapeSql(systemLogVo.getIpName().trim())+"%'");
		if(systemLogVo.getUserName()!=null && !systemLogVo.getUserName().equals("")){
			sb.append(" and A.userName like '%"+StringEscapeUtils.escapeSql(systemLogVo.getUserName().trim())+"%'");
		}
		if(systemLogVo.getModuleName()!=null && !systemLogVo.getModuleName().equals("")){
			sb.append(" and A.moduleName like '%"+StringEscapeUtils.escapeSql(systemLogVo.getModuleName().trim())+"%'");
		}
		if(systemLogVo.getContent()!=null && !systemLogVo.getContent().equals("")){
			sb.append(" and A.content like '%"+StringEscapeUtils.escapeSql(systemLogVo.getContent().trim())+"%'");
		}
		if(systemLogVo.getSort()!=null && !systemLogVo.getSort().equals("") && systemLogVo.getOrder()!=null && !systemLogVo.getOrder().equals("")){
			sb.append(" order by A."+systemLogVo.getSort()+" "+systemLogVo.getOrder()+"");
		}
		List<SystemLogVo> rows=new ArrayList();
		List<SystemLog> list=systemLogDao.find(sb.toString(), systemLogVo.getPage(), systemLogVo.getRows());
		SystemLogVo sl=null;
		if(list!=null&&list.size()>0)
			for (SystemLog systemLog : list) {
				sl=new SystemLogVo();
				sl.setId(systemLog.getId().toString());
				sl.setUserName(systemLog.getUserName());
				sl.setIpName(systemLog.getIpName());
				sl.setStartTime(systemLog.getStartTime().toLocaleString());
				sl.setModuleName(systemLog.getModuleName());
				sl.setContent(systemLog.getContent());
				rows.add(sl);
			}
		
		Datagrid dg=new Datagrid();
		dg.setRows(rows);
		dg.setTotal(systemLogDao.getCount(sb.toString()));
		return dg;
	}
}