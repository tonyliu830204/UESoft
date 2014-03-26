package com.syuesoft.sell.base.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.syuesoft.bas.daoimpl.BaseDaoImpl;
import com.syuesoft.sell.base.dao.DbProjectDAO;
import com.syuesoft.sell.model.XsDbProject;

@Repository("dbProjectDAO")
public class DbProjectDAOImpl extends BaseDaoImpl<XsDbProject> implements DbProjectDAO{
	private final Logger logger = Logger.getLogger(this.getClass());
	
	public List<Object[]> findById(Integer deptId)  {
		try {
			return this.createSQLQuery("select DEPT_ID,DEPT_NAME from bas_dept where DEPT_ID='"+deptId+"'");
		} catch (Exception e) {
			logger.error("查询部门信息失败！");
			return null;
		}
	}

}
