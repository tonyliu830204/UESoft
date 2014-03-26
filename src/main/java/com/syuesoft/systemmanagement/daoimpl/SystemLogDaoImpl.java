package com.syuesoft.systemmanagement.daoimpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.model.SystemLog;
import com.syuesoft.systemmanagement.dao.SystemLogDao;
import com.syuesoft.systemmanagement.vo.SystemLogVo;
/**
 * 系统日志Dao实现
 * */
@Repository("systemLogDao")
public class SystemLogDaoImpl extends BaseDaoImpl<SystemLog> implements SystemLogDao {
	/**
	 * 系统日志删除
	 * @param systemLogVo 系统日志视图对象
	 * @exception Exception  总异常
	 * */
	
	public void doDeleteLog(final SystemLogVo systemLogVo) throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "DELETE FROM  system_log WHERE id IN("+systemLogVo.getId()+") ";
				session.createSQLQuery(sql).executeUpdate();
				return null;
			}
			
		});
		
	}
}
